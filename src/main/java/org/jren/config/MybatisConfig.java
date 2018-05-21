package org.jren.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jren.datasource.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * mybatis相关配置
 */
@Configuration
@MapperScan(basePackages= {"org.jren.edb.dao"})
public class MybatisConfig {

	/**
	 * 注入动态数据源
	 */
    @Autowired
    DynamicDataSource dynamicDataSource;

    /**
     * 
     * <p>Title: sqlSessionFactory </p>
     * <p>Description:配置mybatis会话工厂类 </p>
     * @author jren
     * Date: 2018年4月12日 下午5:10:13
     * @return mybatis会话工厂类
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        return factoryBean.getObject();
    }
    
    /**
     * 
     * <p>Title: sqlSessionTemplate</p>
     * <p>Description: 配置会话模板 </p>
     * @author jren
     * Date: 2018年4月12日 下午5:11:32
     * @return SqlSessionTemplate
     * @throws Exception
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory());
        return template;
    }
    /**
     * 
     * <p>Title: dataSourceTransactionManager </p>
     * <p>Description: 配置事务管理器  TODO</p>
     * @author jren
     * Date: 2018年4月12日 下午5:19:53
     * @return DataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
    	DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dynamicDataSource);
    	return dataSourceTransactionManager;
    }
}
