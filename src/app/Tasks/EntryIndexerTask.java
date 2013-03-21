package app.Tasks;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.API.Indexer.EntriesIndexer;
import app.Tasks.QuartzUtil.AbstractTask;
import app.Utils.LogUtils;

public class EntryIndexerTask extends AbstractTask{

	protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
		try { 
			String entryType = dataMap.getString("entryType");
			
			if (logger != null)
				logger.info("Start Indexing Entries ..... " + new Date(System.currentTimeMillis()));
			
			EntriesIndexer.indexAllEntry(entryType,logger);
			
			if (logger != null)
				logger.info("DONE Task ..... " + new Date(System.currentTimeMillis()));
		} catch (Exception e) {
			if (logger != null)
				logger.error(e);
		}
	}

	public static void main(String[] args) {
		
	}
}
