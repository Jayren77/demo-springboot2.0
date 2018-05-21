package org.jren.jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Service;

/**
 * 队列监听器
 */
@Service
public class MqMessageListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(MqMessageListener.class);


	@JmsListener(destination = "MQ_NAME",containerFactory = "containerFactory")
	public void receive(String msg)  {

	}
}
