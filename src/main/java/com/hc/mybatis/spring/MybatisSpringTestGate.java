package com.hc.mybatis.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring整合mybatis测试
 * @author 李晓冰
 * @date 2020年12月18日
 */
public class MybatisSpringTestGate {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        Class<MybatisSpringTestGate> clazz = MybatisSpringTestGate.class;
        Package aPackage = clazz.getPackage();
        String basePackageName = aPackage.getName();
        applicationContext.scan(basePackageName);
        applicationContext.refresh();
    }
}
