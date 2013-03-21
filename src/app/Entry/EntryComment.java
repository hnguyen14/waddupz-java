package app.Entry;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.Utils.HibernateUtil;

public class EntryComment implements Serializable{

	private long id;
	private String comment;
	private String author;
	private String email;
	private Date commentDate;
	
	
	public EntryComment(){
	}
	
	
	public EntryComment(long id, String comment, String author, String email, Date commentDate) {
		super();
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.email = email;
		this.commentDate = commentDate;
	}
	
	public EntryComment(String comment, String author, String email, Date date) {
		super();
		this.comment = comment;
		this.author = author;
		this.email = email;
		this.commentDate = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	public String toString() {
		return 	getId() + "##" + getAuthor() + "##" + getEmail() + "##" + 
				getComment() + "##" + getCommentDate().getTime();
	}
	
	public String getHTML() {
		String ret_val = "";
		ret_val += "<tr><td colspan=\"2\">\n" +
						"<div id=\"entry_source\">\n" +
							"<a href=\"mailto:" + getEmail() +"\">" + getAuthor() + "</a> - " + getCommentDate().toString() + "\n" + 
						"</div>\n" +
						"<div style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px;\">\n" +
							getComment() + "\n" +
						"</div>\n" + 
					"</td></tr>\n";
	
		return ret_val;
	}
	
	public static void main(String args[]) {

	}
	
}
