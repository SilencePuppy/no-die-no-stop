package com.hc.mybatis.spring;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
                          @Value("${username}") String username,@Value("${password}") String password){
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
}
