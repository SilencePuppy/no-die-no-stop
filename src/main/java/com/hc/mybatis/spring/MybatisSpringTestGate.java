package com.hc.mybatis.spring;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * spring整合mybatis测试
 * @author 李晓冰
 * @date 2020年12月18日
 */
public class MybatisSpringTestGate {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        Class<MybatisSpringTestGate> clazz = MybatisSpringTestGate.class;
        Package aPackage = clazz.getPackage();
        String basePackageName = aPackage.getName();
        applicationContext.scan(basePackageName);
        applicationContext.refresh();

        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
        Connection connection = sqlSession.getConnection();
        System.out.println(connection);
        DatabaseMetaData metaData = connection.getMetaData();
        String databaseProductName = metaData.getDatabaseProductName();

        System.out.println(databaseProductName);
    }
}
