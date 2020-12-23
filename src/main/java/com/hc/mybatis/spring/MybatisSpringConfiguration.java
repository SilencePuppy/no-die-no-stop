package com.hc.mybatis.spring;

import com.hc.mybatis.spring.dao.OrgMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mybaits-spring 常规型配置
 * @author 李晓冰
 * @date 2020年12月18日
 */
@Configuration
@PropertySource("classpath:mybatis/jdbc.properties")
@MapperScan("com.hc.mybatis.spring.dao" )
public class MybatisSpringConfiguration {

    @Bean(name = "dataSource")
    DataSource dataSource(@Value("${driver}") String driver, @Value("${url}") String url,
                          @Value("${name}") String username, @Value("${password}") String password) {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 这各类实现了FactoryBean接口哦
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 配置项，configuration里面environments、DataSource、transactionManager这些项不用设置，
        // 主要用来设置settings、typeAliases一些配置。
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        // 配置映射文件xml的位置，如果对应的映射器类和xml文件在一个位置，那么可以忽略
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = pathMatchingResourcePatternResolver.getResource("mybatis/mappers/OrgMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器，这样spring才可以开启其事务管理功能,注意：这个bean和mybaits其实没关系。
     * 如何想有关系的话，就是使用同一个datasource来创建这个类。
     * 被spring管理了事务后，我们就算通过sqlSession获取connection后也不能自己调用commit(),rollback()方法。
     * 调用了也没有，要想进行编程性事务的话自己看mybatis-spring事务的内容
     * @param dataSource 必须和sqlSessionFactory是同一个dataSource,否则mybatis不能被spring进行事务管理。
     * @return 事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * sqlSessionTemplate是mybatis-spring 提供的一个类。使用时这个类可以保证sqlSession的线程安全性，管理sqlSession
     * 的生命周期和必要的关闭、提交和回滚事务操作。在实现自己的dao时如何想使用sqlSession的话，应该总是使用SqlSessionTemplate
     * public class UserDaoImpl implements UserDao {
     * @param sqlSessionFactory
     * @return
     * @autowired private SqlSession sqlSession;
     * public User getUser(String userId) {
     * return sqlSession.selectOne("org.mybatis.spring.sample.mapper.UserMapper.getUser", userId);
     * }
     * }
     */
    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * Mybatis 映射器配置方案
     * 1. 通过MapperFactoryBean进行设置，设置是需要指定该类的sqlSessionFactory属性，和这个类需要代表的接口类名。
     *    这里通过泛型的方式进行配置。如果映射器对应的xml文件和接口类在一起的话，xml文件也会被解析，否则就需要单独配置mapperLocations属性。
     * 2. 第一种方式是一个一个配置的，有一个映射器就需要一个这样的mapper。可以通过配置、注解、代码进行自动化扫描配置。
     *    1）<mybatis:scan/> xml配置使用
     *    2）@MapperScan 在@Configure类上通过注解配置
     *    3）MapperScannerConfigurer 类代码实现。
     *      对于第1)和2)方式，过滤接口时可以通过指定标记接口或者指定的注解来表示要过滤出哪些接口(前者表示接口必选继承某一个指定接口，后者表示接口必须有哪些注解)。
     *    两个是与的关系，默认是都没设置（所有接口都加载）。spring会按照组件默认命名规则对映射器进行命名。我们可以通过添加@Component或者JSR-330的@Name注解进行
     *    指定。提醒一下，你可以设置 annotation 属性为你自定义的注解，然后在你的注解上设置@Component或@Name。这样你自定义的注解就可以同时拥有过滤和命名的能力了。
     *
     * @param sqlSessionFactory
     * @return
     */
//    @Bean
    public MapperFactoryBean<OrgMapper> userMapper(SqlSessionFactory sqlSessionFactory){
        MapperFactoryBean<OrgMapper> factoryBean = new MapperFactoryBean<>();
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }
//    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hc.mybatis.spring.dao");
        // 可以指定sqlSessionFactory实例在spring中的名字
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
