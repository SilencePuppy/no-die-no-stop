package com.hc.mybatis.spring;

import com.hc.mybatis.spring.dao.OrgMapper;
import com.hc.mybatis.spring.entity.OrgEntity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * spring整合mybatis测试
 *
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

        OrgEntity orgEntity = new OrgEntity(1L, 0L, 0, "node1");
        List<OrgEntity> list = new ArrayList<>();
        list.add(orgEntity);

        OrgMapper mapper = sqlSession.getMapper(OrgMapper.class);
        mapper.batchInsert(list);
    }

    List<OrgEntity> getTreeList() {
        List<OrgEntity> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            long iId = i + 1L;
            OrgEntity iOrgEntity = new OrgEntity(iId, 0L, i, "node-" + (i + 1));
            list.add(iOrgEntity);
            for (int j = 0; j < 10; j++) {
                long jId = iId * 10 + j + 1;
                OrgEntity jOrgEntity = new OrgEntity(jId, iId, j, iOrgEntity.getName() + "-" + (j + 1));
                list.add(jOrgEntity);
                for (int k = 0; k < 50; k++) {
                    long kid = jId * 10 + k + 1;
                    OrgEntity kOrgEntity = new OrgEntity(kid, jId, k, jOrgEntity.getName() + "-" + (k + 1));
                    list.add(kOrgEntity);
                }
            }
        }

        return list;
    }
}
