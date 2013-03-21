package app.Tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.API.EntryHandler.Input.CJProductInput.CJQuery;
import app.DB.Pools;
import app.Tasks.QuartzUtil.AbstractTask;

public class CJProductCrawler extends AbstractTask{

	public static List<String> getKeywords() {
		List<String> ret_val = new ArrayList<String>();
		try {
			Connection conn = Pools.getConnection();
			String sql = "select keyword from external_keyword where language=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "en");
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String kw = rs.getString("keyword");
				if (!ret_val.contains(kw))
					ret_val.add(kw);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret_val;
	}
	
	protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			List<String> keywords = getKeywords();
			List<String> processList = new ArrayList<String>();
			int count = 1;
			System.out.println(keywords.size());
			ThreadPoolExecutor tpe = new ThreadPoolExecutor(20,40,300L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
			int nameCount = 1;
			for (String keyword : keywords) {
				processList.add(keyword);
				if (count++ % 10 == 0) {
					String threadName = "CJQuery-" + nameCount++;
					System.out.println("Enqueueing .... " + threadName + " - " + processList.get(0));					
					tpe.execute(new CJQuery(threadName,processList));
					processList = new ArrayList<String>();
				}
			}
			String threadName = "CJQuery-" + nameCount++;
			tpe.execute(new CJQuery(threadName,processList));
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	} 
}
