package app.Tasks.QuartzUtil;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.utils.TriggerStatus;

public class ServerScheduler {
	
	public static Scheduler scheduler = null;
	
	public static void init() throws SchedulerException	{
		scheduler = StdSchedulerFactory.getDefaultScheduler();
		if (scheduler.isInStandbyMode() || scheduler.isShutdown())
			scheduler.start();
	}
	
	public static void shutdown() throws SchedulerException{
		scheduler.shutdown();
	}

	public static Scheduler getScheduler() throws SchedulerException {
		if (scheduler == null)
			init();
		return scheduler;
	}
	
	public static void pauseJob(String jobName, String jobGroup) throws SchedulerException {
		if (scheduler == null)
			init();
		scheduler.pauseJob(jobName, jobGroup);
		
		Trigger t[] = scheduler.getTriggersOfJob(jobName, jobGroup);
		
		for (int i= 0 ; i < t.length; i++ ) {
			scheduler.pauseTrigger(t[i].getName(), t[i].getGroup());
		}
	}
	
	public static void resumeJob(String jobName, String jobGroup) throws SchedulerException {
		if (scheduler == null)
			init();
		scheduler.resumeJob(jobName, jobGroup);
		
		Trigger t[] = scheduler.getTriggersOfJob(jobName, jobGroup);
		
		for (int i= 0 ; i < t.length; i++ ) {
			scheduler.resumeTrigger(t[i].getName(), t[i].getGroup());
		}
	}
	
	public static void removeJob(String jobName, String jobGroup) throws SchedulerException {
		if (scheduler == null)
			init();
		
		Trigger t[] = scheduler.getTriggersOfJob(jobName, jobGroup);
		
		for (int i= 0 ; i < t.length; i++ ) {
			scheduler.unscheduleJob(t[i].getName(), t[i].getGroup());
		}
		scheduler.deleteJob(jobName, jobGroup);
	}
	
	public static void addJob(String jobName, String jobClass, String jobGroup, String param,String startTime, String interval) 
		throws ClassNotFoundException,SchedulerException{
		if (scheduler == null)
			init();
		Class classObject = Class.forName(jobClass);
		
		// Setting up Job Detail
		JobDetail jd = new JobDetail(jobName, jobGroup, classObject);
		if (param != null && param.length() > 0) {
			Map map = new HashMap();
			String toks[] = param.split("\\|");
			for (int i = 0 ; i < toks.length; i++) {
				int idx = toks[i].indexOf("=", 0);
				String name = toks[i].substring(0,idx);
				String value = toks[i].substring(idx+1, toks[i].length());
				map.put(name.trim(), value.trim());
			}
			JobDataMap jdm = new JobDataMap(map);
			jd.setJobDataMap(jdm);
		}
		
		// Setting up Trigger
		int intrval = Integer.parseInt(interval);
		Trigger t = TriggerUtils.makeSecondlyTrigger(intrval);
		t.setName(jobName + " Trigger");

		if (startTime!= null && startTime.length() > 0) {
			String[] toks = startTime.split(" ");
			String[] dates = toks[0].split("/");
			String[] time = toks[1].split(":");

			GregorianCalendar calender = new GregorianCalendar(
					Integer.parseInt(dates[0]),
					Integer.parseInt(dates[1]) - 1,
					Integer.parseInt(dates[2]),
					Integer.parseInt(time[0]),
					Integer.parseInt(time[1]),
					Integer.parseInt(time[2]));

			Date startDate = calender.getTime();
			t.setStartTime(startDate);
		}
		
		// schedule Job with scheduler
		scheduler.scheduleJob(jd, t);
		
	}
	
	public static void main(String args[]){
	}
}
