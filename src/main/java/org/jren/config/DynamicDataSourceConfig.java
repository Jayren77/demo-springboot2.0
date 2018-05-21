package org.jren.config;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;


/**
 * 数据源
 * @author jren
 *
 */
@Configuration
public class DynamicDataSourceConfig {

    
    @Bean(name = "datasource")
    @Qualifier("datasource")
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource dataSource(){
    	return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    
    
}
