package com.hc.mybatis.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码自动生成器
 *
 * @author Li Xiaobing
 * @Classname MyCodeGenerator
 * @date 2020/12/24 9:09
 */
public class MyCodeGenerator {

    private static final String INCLUDE_TABLE = "s_org";

    private static final String TEMPLATE_BASE_PATH = "mybatis/templates";

    private static final String TARGET_DIR_BASE_PATH = System.getProperty("user.dir");

    private static final String PATH1 = TARGET_DIR_BASE_PATH + "/src/main/java/com/hc/plus/system/";
    private static final String PATH2 = TARGET_DIR_BASE_PATH + "/src/main/resources/mappers/";
    private static final String PATH3 = TARGET_DIR_BASE_PATH + "/src/main/java/com/hc/plus/system/";


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 设置模板生成引擎
        mpg.setTemplateEngine(new MyFreemarkerTemplateEngine());
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
        // 设置自定义特殊文件输出配置
        mpg.setCfg(initInjectionConfig());

        mpg.execute();
    }

    private static GlobalConfig initGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(TARGET_DIR_BASE_PATH);
        globalConfig.setAuthor("Li Xiaobing");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true); //实体属性 Swagger2 注解
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        globalConfig.setEnableCache(false);// XML 二级缓存
        globalConfig.setBaseResultMap(true);// XML ResultMap
        globalConfig.setBaseColumnList(true);// XML columList

        // 设置不同类的类名生成规则
//        globalConfig.setEntityName("%sEntity");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setMapperName("%sDao");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        return globalConfig;
    }

    public static PackageConfig initPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        // 这个地方可以理解为包名，但是也可以理解为目录文件名
        String basePackage = "com.huice.";
        packageConfig.setParent(null);
        packageConfig.setController(basePackage + "core.system.controller");
        packageConfig.setService(basePackage + "core.system.service");
        packageConfig.setServiceImpl(basePackage + "core.system.service.impl");
        packageConfig.setMapper(basePackage + "core.system.dao");

        packageConfig.setXml("mappers");
        packageConfig.setEntity(basePackage + "database.entity");
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
        strategyConfig.setInclude(INCLUDE_TABLE);

//        strategyConfig.setEntityTableFieldAnnotationEnable(true); // 实体类字段上的@TableField(value="密码");
//        strategyConfig.setSuperEntityClass("com.hc.entity.MySupser");
//        strategyConfig.setSuperEntityColumns("id","name");写于父类中的公共字段
//        strategyConfig.setSuperMapperClass("com.hc.mapper.MyMapper");
//        strategyConfig.setSuperServiceClass("com.hc.service.MyService");
//        strategyConfig.setSuperServiceImplClass("com.hc.service.imp.MyServiceImp");
//        strategyConfig.setSuperControllerClass("com.hc.controller.MyController");
        return strategyConfig;
    }

    public static TemplateConfig initTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setController(TEMPLATE_BASE_PATH + "/controller.java");
//        templateConfig.setService(TEMPLATE_BASE_PATH + "/service.java");
//        templateConfig.setServiceImpl(TEMPLATE_BASE_PATH + "/serviceImpl.java");
//        templateConfig.setMapper(TEMPLATE_BASE_PATH + "/mapper.java");
//        templateConfig.setXml(TEMPLATE_BASE_PATH + "/mapper.xml");
//        templateConfig.setEntity(TEMPLATE_BASE_PATH + "/entity.java");
        return templateConfig;
    }


    // 此配置会优先PackageConfig 的相同输出地址配置
    public static InjectionConfig initInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String,Object> map =new HashMap<>();
                LocalDateTime localDateTime = LocalDateTime.now();
                map.put("datetime", localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                map.put("needCheckRepeated", true);
                map.put("checkRepeatedField", "name");
                map.put("checkRepeatedColumn", "name");
                setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        // 设置controller
        focList.add(new FileOutConfig("/" + TEMPLATE_BASE_PATH + "/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH1 + "controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        });

        // 设置service
        focList.add(new FileOutConfig("/" + TEMPLATE_BASE_PATH + "/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH1 + "service/" + tableInfo.getEntityName() + "Service.java";
            }
        });

        // 设置serviceImpl
        focList.add(new FileOutConfig("/" + TEMPLATE_BASE_PATH + "/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH1 + "service/impl/" + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });

        // 设置dao
        focList.add(new FileOutConfig("/" + TEMPLATE_BASE_PATH + "/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH1 + "dao/" + tableInfo.getEntityName() + "Dao.java";
            }
        });

        // 设置xml文件位置
        focList.add(new FileOutConfig("/" + TEMPLATE_BASE_PATH + "/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH2 + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        // 设置entity文件位置
        focList.add(new FileOutConfig("/" + TEMPLATE_BASE_PATH + "/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH3 + tableInfo.getEntityName() + ".java";
            }
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }
}
