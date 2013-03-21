package app.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;

public class LogUtils {
	
	public static Logger getTaskLogger(String TaskName) throws IOException{
		
		String fileName = ConfigUtils.getAppProperty("server.task.taskFolder") + TaskName + "_" + 
					(new Date(System.currentTimeMillis())).toString().replaceAll(" ", "_").replaceAll(":", "_") + ".log";
		
		Logger log = Logger.getLogger(TaskName);
		PatternLayout layout = new PatternLayout("%m%n");
		FileAppender appender =new FileAppender(layout, fileName);
		appender.setThreshold(Priority.INFO);
		log.addAppender(appender);
		
		return log;
		
	}

	public static void main(String args[]) {
	}
	
}
