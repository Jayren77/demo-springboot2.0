package org.jren.scheduled;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


@Component
public class MailQuartzJob extends QuartzJobBean {

	
	@Autowired
	MailService mailService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
			System.out.println("Task start");
			mailService.mailSend();

	}

}
