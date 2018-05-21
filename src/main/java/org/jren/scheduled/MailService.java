package org.jren.scheduled;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Service;


@Service
@DisallowConcurrentExecution //不允许重复执行,不过现在看到的情况是一天一次的mailsend 应该不会出现并发的情况
public class MailService{

	/**
	 * 
	 * <p>Title: mailSend </p>
	 * <p>Description: mailSend </p>
	 * @author jren
	 * Date: 2018年5月14日 上午11:37:51
	 */
	public void mailSend() {
		System.out.println("mailSend>>>>>>>>>>>>>");
	}


}
