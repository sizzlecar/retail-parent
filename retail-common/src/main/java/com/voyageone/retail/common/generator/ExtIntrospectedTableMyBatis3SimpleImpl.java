package com.voyageone.retail.common.generator;

import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;

import java.util.List;

public class ExtIntrospectedTableMyBatis3SimpleImpl extends IntrospectedTableMyBatis3SimpleImpl {



    /*
     * @chuanyu.liang add
     */
    public List<AbstractJavaGenerator> getJavaModelGenerators() {
        return javaModelGenerators;
    }

    /*
     * @chuanyu.liang add
     */
    public AbstractXmlGenerator getXmlMapperGenerator() {
        return xmlMapperGenerator;
    }

}
