package com.voyageone.retail.oauth;

import com.voyageone.retail.common.generator.Generator;

/**
 * 根据 dao-config.xml 配置，自动生成dao,model,mapper.xml等基础代码
 * 启动类
 * @author carl.che
 */
public class CodeGenerator {

    public static void main(String[] args) throws Exception{
        Generator.generate();
    }

}
