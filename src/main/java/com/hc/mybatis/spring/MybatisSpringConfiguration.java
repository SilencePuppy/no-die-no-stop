package com.hc.mybatis.spring;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 李晓冰
 * @date 2020年12月18日
 */
@Configuration
@PropertySource("classpath:mybatis/jdbc.properties")
public class MybatisSpringConfiguration {

    @Bean(name = "dataSource")
    DataSource dataSource(@Value("${driver}") String driver,@Value("${url}") String url,
                          @Value("${name}") String username,@Value("${password}") String password){
        PooledDataSource dataSource =new PooledDataSource();
        dataSource.setDriver(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 这各类实现了FactoryBean接口哦
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 配置项，configuration里面environments、DataSource、transactionManager这些项不用设置，
        // 主要用来设置settings、typeAliases一些配置。
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return  sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器，这样spring才可以开启其事务管理功能,注意：这个bean和mybaits其实没关系
     * 如何想有关系的话，就是使用同一个datasource来创建这个类。
     * @param dataSource 必须和sqlSessionFactory是同一个dataSource,否则mybatis不能被spring进行事务管理。
     * @return 事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
