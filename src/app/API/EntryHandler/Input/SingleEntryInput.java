package app.API.EntryHandler.Input;

import java.util.ArrayList;
import java.util.List;

import app.Entry.AbstractEntry;

public class SingleEntryInput implements IEntryInput {
	private List entryList;
	
	public SingleEntryInput(AbstractEntry entry) {
		this.entryList = new ArrayList();
		this.entryList.add(entry);
	}

	public List getInputList() throws Exception {
		return entryList;
	}
}
