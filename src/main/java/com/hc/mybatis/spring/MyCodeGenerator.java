package com.hc.mybatis.spring;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 代码自动生成器
 *
 * @author Li Xiaobing
 * @Classname MyCodeGenerator
 * @date 2020/12/24 9:09
 */
public class MyCodeGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 设置模板生成引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        mpg.setGlobalConfig(initGlobalConfig());
        // 包名配置
        mpg.setPackageInfo(initPackageConfig());
        // 数据库(源)配置
        mpg.setDataSource(initDataSourceConfig());
        // 数据库表配置
        mpg.setStrategy(initStrategyConfig());
        // 配置不同文件生成的模板
        mpg.setTemplate(initTemplateConfig());

        mpg.execute();
    }

    private static GlobalConfig initGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("Li Xiaobing");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true); //实体属性 Swagger2 注解
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        globalConfig.setEnableCache(false);// XML 二级缓存
        globalConfig.setBaseResultMap(true);// XML ResultMap
        globalConfig.setBaseColumnList(true);// XML columList

        // 设置不同类的类名生成规则
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        return globalConfig;
    }

    public static PackageConfig initPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.hc.core");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper.xml");
        return packageConfig;
    }

    public static DataSourceConfig initDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:postgresql://localhost:5432/testdb");
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUsername("postgres");
        dsc.setPassword("postgres");
        dsc.setDbType(DbType.POSTGRE_SQL);
        return dsc;
    }

    public static StrategyConfig initStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 大写命名
        strategyConfig.setCapitalMode(true);
        // 表名下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 表字段下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("s");
        strategyConfig.setFieldPrefix("s");
        strategyConfig.setEnableSqlFilter(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setChainModel(false);
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(false);
        strategyConfig.setRestControllerStyle(true);
        // controller层url地址用羊肉串格式显示还是驼峰
        strategyConfig.setControllerMappingHyphenStyle(true);

        // 正则表达式
        strategyConfig.setInclude("s_org");

//        strategyConfig.setEntityTableFieldAnnotationEnable(true); // 实体类字段上的@TableField(value="密码");
//        strategyConfig.setSuperEntityClass("com.hc.entity.MySupser");
//        strategyConfig.setSuperEntityColumns("id","name");写于父类中的公共字段
//        strategyConfig.setSuperMapperClass("com.hc.mapper.MyMapper");
//        strategyConfig.setSuperServiceClass("com.hc.service.MyService");
//        strategyConfig.setSuperServiceImplClass("com.hc.service.imp.MyServiceImp");
//        strategyConfig.setSuperControllerClass("com.hc.controller.MyController");
        return strategyConfig;
    }

    public static TemplateConfig initTemplateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();
        final String BASE_PATH="returnJson";

        templateConfig.setController(BASE_PATH + "/controller.java");
        templateConfig.setService(BASE_PATH + "/service.java");
        templateConfig.setServiceImpl(BASE_PATH + "/serviceImpl.java");
        templateConfig.setMapper(BASE_PATH + "/mapper.java");
        templateConfig.setXml(BASE_PATH + "/mapper.xml");
        templateConfig.setEntity(BASE_PATH + "/entity.java");
        return templateConfig;
    }
}
