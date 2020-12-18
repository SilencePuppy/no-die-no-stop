package com.hc.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis 创建和使用测试
 * @author 李晓冰
 * @date 2020年12月18日
 */
public class MybatisInitGate {
    public static void test1() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"development");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
    }

    public static void test2(){
        DataSource dataSource=new PooledDataSource("org.postgresql.Driver","jdbc:postgresql://localhost:5432/testdb","postgres","postgres");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment =new Environment("default",transactionFactory,dataSource);
        Configuration configuration  = new Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
    }

    public static void main(String[] args) throws IOException {
        test1();
        test2();
    }
}
