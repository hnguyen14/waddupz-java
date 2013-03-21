package app.API.EntryHandler.Input;

import app.Utils.HttpUtils;

public abstract class CrawlerEntryInput implements IEntryInput{

	private String url;
	
	public CrawlerEntryInput(String url) {
		this.url = url;
	}
	
	protected String fetch() {
		return HttpUtils.get(this.url);
	}
}
