package app.Tasks;

import java.util.Date;

import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.API.EntryHandler.EntryInputEngine;
import app.API.EntryHandler.Input.CSVEntryInput;
import app.API.EntryHandler.Input.IEntryInput;
import app.Tasks.QuartzUtil.AbstractTask;
import app.Utils.ConfigUtils;
import app.Utils.HibernateUtil;
import app.Utils.LogUtils;
import app.WebReaders.NewEggFeedRetriever;

public class NewEggCsvParsingTask extends AbstractTask{

	protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
		try {
//			Logger log  = LogUtils.getTaskLogger("New_Egg_Retriever");
//			log.info("Start retrieving New Egg Feed @ " + new Date(System.currentTimeMillis()));

			String csvURI = dataMap.getString("location");
			String onlyGetDealStr = dataMap.getString("dealOnly");
			boolean onlyGetDeal = Boolean.parseBoolean(onlyGetDealStr);
			String target = ConfigUtils.getAppProperty("server.uploader.CSVLocation") + "NewEgg_shop.csv";

//			log.info("Location : " + csvURI + " - Get deal Only : " + onlyGetDealStr + " - Target file : " + target);

			NewEggFeedRetriever retriever = new NewEggFeedRetriever(csvURI,target,onlyGetDeal);
			retriever.retrieve();
//			log.info("Done getting New Egg Feed .... " + new Date(System.currentTimeMillis()));

			IEntryInput method = new CSVEntryInput(target);
			EntryInputEngine engine = new EntryInputEngine(method);
			engine.upsertToDB();
//			log.info("Done upload feed to DB .... " + new Date(System.currentTimeMillis()));
//			log.info("Done With Task");
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}
	

	public static void main(String[] args) {

	}
	
}
