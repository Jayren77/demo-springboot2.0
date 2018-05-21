package org.jren.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;


/**
 * mq配置类
 */
@Configuration
@EnableJms
public class MqConfig {
	
	private static final Logger log = LoggerFactory.getLogger(MqConfig.class);
	

	

}
