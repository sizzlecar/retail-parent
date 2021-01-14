package com.voyageone.retail.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.boot.bind.PropertiesConfigurationFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Component
@ConfigurationProperties(ModuleDataSourceConfig.PREFIX)
@Slf4j
public class ModuleDataSourceConfig implements ApplicationContextAware, EnvironmentAware {

    static final String PREFIX = "retail.datasource-list";

    private ApplicationContext applicationContext;
    private Map<String, DataSourceProperties> dataSources = new HashMap<>();
    private Map<String, DataSource> dataSourceMap = new HashMap<>();
    private MutablePropertySources propertySources;
    private String defaultDatasource;
    private BeanDefinitionRegistry registry;
    private ConfigurationPropertiesBinder binder;
    public ModuleDataSourceConfig() {
        log.info("creating");
    }

    public Map<String, DataSourceProperties> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSourceProperties> dataSources) {
        this.dataSources = dataSources;
    }

    public String getDefaultDatasource() {
        return defaultDatasource;
    }

    public void setDefaultDatasource(String defaultDatasource) {
        this.defaultDatasource = defaultDatasource;
    }

    public void clearDataSourceCache() {
        this.dataSourceMap.clear();
    }

    /**
     * 根据 datasourceName 在容器上下文内查找，或根据配置文件动态创建。如果都不行，就使用容器中默认的 DataSource
     */
    public DataSource getDataSourceOrDefault(String datasourceName) {

        DataSource dataSource = getDataSourceFromProperties(datasourceName);

        if (dataSource != null) {
            return dataSource;
        }

        if (!datasourceName.isEmpty()
                && applicationContext.containsBean(datasourceName)
                && applicationContext.isTypeMatch(datasourceName, DataSource.class)) {

            log.info("find datasource from context; for datasourceName [{}]", datasourceName);
            // properties 配置里找不到，就从容器中尝试按名字找
            return applicationContext.getBean(datasourceName, DataSource.class);
        }

        String defaultDatasource = getDefaultDatasource();

        if (!isEmpty(defaultDatasource)) {
            // 优先从模块数据源配置中使用默认数据源
            dataSource = getDataSourceFromProperties(defaultDatasource);

            if (dataSource != null) {
                log.info("using default datasource [{}] => [{}]", defaultDatasource, datasourceName);
                return dataSource;
            }
        }

        // 默认的 MyBatis SqlSessionFactory 的配置中，会使用 schema 插件
        // 其会通过 use 或 set session (for oracle) 来切换目标库
        // 而上下文中的默认数据源可能会同时被非默认配置的 SqlSessionFactory 或 JdbcTemplate 等其他组件使用
        // 造成无法找到表等无法预料的错误
        // => 所以这里使用警告级别
        log.warn("using default datasource in context; for datasourceName [{}]", datasourceName);
        // 都没有，那就使用默认的
        return applicationContext.getBean(DataSource.class);
    }

    private DataSource getDataSourceFromProperties(String datasourceName) {

        if (datasourceName.isEmpty()) {
            return null;
        }

        DataSource dataSource = dataSourceMap.get(datasourceName);

        if (dataSource != null) {
            return dataSource;
        }

        if (!this.dataSources.containsKey(datasourceName)) {
            return null;
        }

        log.info("create datasource; datasource name [{}]", datasourceName);
        // 定义的 properties 配置里有，就从 properties 配置中动态创建
        DataSourceProperties dataSourceProperties = this.dataSources.get(datasourceName);
        dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
        dataSourceMap.put(datasourceName, dataSource);

        // 绑定数据源的其他属性
        postProcessBeforeInitialization(dataSource, datasourceName);

        return dataSource;
    }

    /**
     * 将 properties (或 yml) 配置，根据属性名称自动绑定到对象上
     * 参考 {@link org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor#postProcessBeforeInitialization}
     * 实现
     */
    private void postProcessBeforeInitialization(DataSource target, String beanName) {

        if (propertySources == null) {
            return;
        }

        bind(ConfigurationPropertiesBean.get(this.applicationContext, target, beanName));

        String prefix = PREFIX + "." + beanName;

        PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<>(
                target);
        factory.setPropertySources(propertySources);
        factory.setTargetName(prefix);
        try {
            factory.bindPropertiesToTarget();
        } catch (Exception ex) {
            String targetClass = ClassUtils.getShortName(target.getClass());
            throw new BeanCreationException(beanName, "Could not bind properties to "
                    + targetClass + " (" + prefix + ")", ex);
        }
    }

    private void bind(ConfigurationPropertiesBean bean) {
        if (bean == null || hasBoundValueObject(bean.getName())) {
            return;
        }
        Assert.state(bean.getBindMethod() == ConfigurationPropertiesBean.BindMethod.JAVA_BEAN, "Cannot bind @ConfigurationProperties for bean '"
                + bean.getName() + "'. Ensure that @ConstructorBinding has not been applied to regular bean");
        try {
            this.binder.bind(bean);
        }
        catch (Exception ex) {
            throw new ConfigurationPropertiesBindException(bean, ex);
        }
    }

    private boolean hasBoundValueObject(String beanName) {
        return this.registry.containsBeanDefinition(beanName) && this.registry
                .getBeanDefinition(beanName) instanceof ConfigurationPropertiesValueObjectBeanDefinition;
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            propertySources = ((ConfigurableEnvironment) environment).getPropertySources();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
