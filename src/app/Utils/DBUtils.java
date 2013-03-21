package app.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import app.DB.Pools;
import app.Entry.AbstractEntry;
import app.Entry.NewsEntry;

public class DBUtils {
	public static boolean isEntryDuplicate(AbstractEntry entry) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from AbstractEntry as entry where entry.url=? or entry.title=?");
		query.setString(0, entry.getUrl());
		query.setString(1, entry.getTitle());

		List l = query.list();

		session.close();
		if (l != null && l.size() > 0) 
			return true;
		else 
			return false;
	}
	
	public static AbstractEntry isEntryAlreadyExists(AbstractEntry entry) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from AbstractEntry as entry where entry.url=? or entry.title=?");
		query.setString(0, entry.getUrl());
		query.setString(1, entry.getTitle());

		List l = query.list();

		session.close();
		if (l != null && l.size() > 0) 
			return (AbstractEntry) l.get(0);
		else 
			return null;
	}
	
	public synchronized static long getNextLongFromSequence(String seqName) {
		long ret_val = 1;
		try {
			String insert_sql = "insert into sequence(sequence_id,value) values (?,?)";
			String update_sql = "update sequence set value=? where sequence_id=?";
			String select_sql = "select value from sequence where sequence_id=?";
			
			Connection con = Pools.getConnection();
			PreparedStatement stmt = con.prepareStatement(select_sql);
			stmt.setString(1, seqName);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				ret_val = rs.getLong("value");
				rs.close();
				stmt.close();
				stmt = con.prepareStatement(update_sql);
				stmt.setLong(1, ret_val + 1);
				stmt.setString(2, seqName);
				stmt.executeUpdate();
			} else {
				rs.close();
				stmt.close();
				stmt = con.prepareStatement(insert_sql);
				stmt.setString(1, seqName);
				stmt.setLong(2, ret_val + 1);				
				stmt.executeUpdate();
			}
			con.close();			
		} catch  (Exception e) {
			e.printStackTrace();
		}
		return ret_val;
	}
}
