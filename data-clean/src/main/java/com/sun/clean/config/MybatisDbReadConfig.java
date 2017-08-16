package com.sun.clean.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @authur sunjian.
 */
@Configuration
@MapperScan(basePackages = {"com.sun.clean.dao.mapper.read"}, sqlSessionFactoryRef = "readSqlSessionFactory")
public class MybatisDbReadConfig
{
    @Autowired
    @Qualifier("read")
    private DataSource readDataSource;

    //驼峰映射
    protected boolean mapUnderscoreToCamelCase = true;

    @Bean
    public SqlSessionFactory readSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(readDataSource); // 使用read数据源,连接读取的数据库
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));//配置mybatis配置文件(驼峰映射)
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate readSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(readSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }
}
