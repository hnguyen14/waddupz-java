package app.Tasks.QuartzUtil;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.mail.MessagingException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.Utils.LogUtils;

public abstract class AbstractTask implements Job{

	protected abstract void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException;
//	protected abstract String getLogFileName();
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// Do precondition checkt
		Exception exception = null;
		
		String taskName = context.getJobDetail().getName();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String isLogging = dataMap.getString("logging");
		
		Logger logger = null;
		
		if ("true".equalsIgnoreCase(isLogging)) {
			try {
				logger = LogUtils.getTaskLogger(taskName.replaceAll(" ", "_"));
			} catch (Exception e ) {
				throw new JobExecutionException(e.getMessage());
			}
		}
		
		try {
			doTask(dataMap, logger);
		} catch (Exception e) {
			exception = e;
		}

		// DO post condition check
		
		String attachments[] = null;
		if (logger != null) {
			Enumeration appenders = logger.getAllAppenders();
			Object obj = null;
			ArrayList<String> files = new ArrayList<String>();
			while (appenders.hasMoreElements()) {
				obj = appenders.nextElement();
				if (obj instanceof FileAppender) {
					FileAppender fa = (FileAppender) obj;
					files.add(fa.getFile());
				}
				
			}
			attachments = new String[files.size()];
			for (int i = 0; i < files.size(); i++) {
				attachments[i] = files.get(i);
			}
		}
		
		
//		String subject = taskName;
//		String bodyMsg = "Task Excuted Successfully";
//		if (exception != null) {
//			subject += " - Failed";
//			bodyMsg = exception.getMessage();
//		}
//		try {
//			NotificationEmail.sendTaskNotificationMail(subject, bodyMsg, attachments);
//		} catch (MessagingException e) {
//			throw new JobExecutionException(e);
//		}
	}
	
}
