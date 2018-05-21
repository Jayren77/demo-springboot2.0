package org.jren.config;

import javax.sql.DataSource;

import org.jren.scheduled.MailQuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;



/**
 * 定时任务配置
 */
@Configuration
public class QuartzConfig implements SchedulerFactoryBeanCustomizer{

	@Autowired
    @Qualifier("datasource")
	DataSource dataSource;


	@Value("${spring.quartz.cron.mail}")
	private String mailCronSchedule;

	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		//在这里完成数据源的注入,应该也有其他的方式
		schedulerFactoryBean.setDataSource(dataSource);
	}

	/************************注册邮件定时任务************************/
	@Bean
	public JobDetail mailJobDetail(){
		return JobBuilder.newJob(MailQuartzJob.class).withIdentity("mailJob").
				// 即使没有Trigger关联时,也不需要删除该jobDetail
				storeDurably(). 
				build();
	}

	@Bean
	public Trigger mailTrigger(){
		System.out.println(mailCronSchedule);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(mailCronSchedule);
		return TriggerBuilder.newTrigger().forJob(mailJobDetail())
				.withIdentity("mailJob").withSchedule(scheduleBuilder).build();
	}
}
