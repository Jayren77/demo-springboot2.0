package org.jren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 主服务启动类</br>
 *
 * 除去 @see DataSourceAutoConfiguration 避免springboot自动加载单数据源
 * @EnableCaching 开启缓存服务
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching 
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
