package org.jren.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * zookeeper配置实例
 */
@Component
@Profile(value = {"pro","tst"})
public class ZookeeperConfig {
	
	@Value("${spring.redis.zkAddr:}")
	private String zkAddr;
	@Value("${spring.redis.zkSessionTimeoutMs:0}")
	private int zkSessionTimeoutMs;
	@Value("${spring.redis.zkProxyDir:}")
	private String zkProxyDir;
	@Value("${spring.redis.zkGroupDir:}")
	private String zkGroupDir;

	public String getZkAddr() {
		return zkAddr;
	}

	public int getZkSessionTimeoutMs() {
		return zkSessionTimeoutMs;
	}

	public String getZkProxyDir() {
		return zkProxyDir;
	}

	public String getZkGroupDir() {
		return zkGroupDir;
	}

	

}