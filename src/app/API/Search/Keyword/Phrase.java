package app.API.Search.Keyword;

import java.io.Serializable;

public class Phrase implements Serializable{
	private long id;
	private String phrase;
	private String category;
	private String language;
	private boolean validity;
	
	public Phrase(){}
	
	public Phrase(long id, String phrase, String category, String language, boolean validity) {
		super();
		this.id = id;
		this.phrase = phrase;
		this.category = category;
		this.language = language;
		this.validity = validity;
	}

	public Phrase(String phrase, String category, String language, boolean validity) {
		super();
		this.phrase = phrase;
		this.category = category;
		this.language = language;
		this.validity = validity;
	}
	
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (id ^ (id >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Phrase other = (Phrase) obj;
		return this.phrase.equalsIgnoreCase(other.phrase);
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	public boolean isValidity() {
		return validity;
	}
	public void setValidity(boolean validity) {
		this.validity = validity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String toString() {
		return 	getId() + "##" + getCategory() + "##" + getLanguage() + "##" +
				getPhrase() + "##" + (isValidity() ? "1" : "0");
	}
	
}
