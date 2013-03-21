package app.Tasks.QuartzUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;

public class SchedulerHelper {
	
	public static QuartzJobDetailsWrapper getJobDetailWrapper(Scheduler sched, String job, String groupName) {
		try{
			JobDetail jd = sched.getJobDetail(job, groupName);
			Trigger t = sched.getTriggersOfJob(job, groupName)[0];
			JobDataMap jdm = jd.getJobDataMap();
			
			

			Map params = new HashMap();
			String keys[] = jdm.getKeys();
			for (int i = 0 ; i < keys.length; i++) {
				params.put(keys[i], jdm.getString(keys[i]));
			}

			QuartzJobDetailsWrapper qjd = 
				new QuartzJobDetailsWrapper(job,jd.getJobClass().getCanonicalName(),
						t.getPreviousFireTime(),t.getNextFireTime(),
						groupName,sched.getTriggerState(t.getName(), t.getGroup()),params);
			return qjd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map getAllJobs(Scheduler sched) {
		
		Map ret_val = new HashMap();
		try {
			String[] groups = sched.getJobGroupNames();
			for (int i = 0; i < groups.length; i++) {
				String groupName = groups[i];
				if (!ret_val.containsKey(groupName))
					ret_val.put(groupName, new ArrayList());
				List l = (List) ret_val.get(groupName);
				String[] jobs = sched.getJobNames(groupName);
				for (int j = 0 ; j < jobs.length; j++) {
					String job = jobs[j];
					l.add(getJobDetailWrapper(sched, job, groupName));
				}
				
			}
		} catch (Exception e) {

		}
		return ret_val;
	}
	
	public static class QuartzJobDetailsWrapper {
		private String jobName;
		private String jobClass;
		private Date previousStartTime;
		private Date nextStartTime;
		private String groupName;
		private int state;
		private Map params;
		
		public QuartzJobDetailsWrapper(String jobName, String jobClass, Date previousStartTime, 
									Date nextStartTime, String groupName, int state, Map params) {
			this.jobName = jobName;
			this.jobClass = jobClass;
			this.previousStartTime = previousStartTime;
			this.nextStartTime = nextStartTime;
			this.groupName = groupName;
			this.state = state;
			this.params = params;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getJobClass() {
			return jobClass;
		}
		public void setJobClass(String jobClass) {
			this.jobClass = jobClass;
		}
		public String getJobName() {
			return jobName;
		}
		public void setJobName(String jobName) {
			this.jobName = jobName;
		}
		public Date getNextStartTime() {
			return nextStartTime;
		}
		public void setNextStartTime(Date nextStartTime) {
			this.nextStartTime = nextStartTime;
		}
		public Date getPreviousStartTime() {
			return previousStartTime;
		}
		public void setPreviousStartTime(Date previousStartTime) {
			this.previousStartTime = previousStartTime;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public Map getParams() {
			return params;
		}
		public void setParams(Map params) {
			this.params = params;
		}
		public String toString() {
			String ret_val = "";
			ret_val += "Name : " + jobName + "\n";
			ret_val += "Class : " + jobClass + "\n";
			ret_val += "Group : " + groupName + "\n";;
			ret_val += "Prev Start : " + previousStartTime + "\n";;
			ret_val += "Next Start : " + nextStartTime + "\n";;
			ret_val += "State :" + state + "\n";
			ret_val += "PARAMS : \n";
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				ret_val = "\t" + key + " : " + params.get(key) + "\n";
			}
			return ret_val;
		}
	
	}
	public static void main(String args[]) {
		try {
			Scheduler scheduler = ServerScheduler.getScheduler();
			Map map = SchedulerHelper.getAllJobs(scheduler);
			
			for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
				String groupName = (String) iter.next();
				List l = (List) map.get(groupName);
				System.out.println("------------ " + groupName + " -------------");
				for (int i = 0 ; i < l.size(); i++) {
					System.out.println(((QuartzJobDetailsWrapper) l.get(i)).toString());
				}
				
			}
			ServerScheduler.shutdown();
		} catch (Exception e) {

		}
	}

}
