package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Db_connection;

public class HashtagFeed {
	
	private int parentFeedID;
	private String hashtag;
	private String content;
	
	
	public int getParentFeedID() {
		return parentFeedID;
	}

	public void setParentFeedID(int parentFeedID) {
		this.parentFeedID = parentFeedID;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/*function to store value into the database table named hashtagfeed*/

	public void storeValue(){
		Db_connection db=new Db_connection();
		Connection conn=db.getConnection();
		Date date1=new Date();
		DateFormat dformat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String formatted_date=dformat.format(date1);	
		
		try {
			PreparedStatement pstatement= conn.prepareStatement("Insert into hashtagfeed values(?,?,?,?,?)");
			pstatement.setString(1,null);
			pstatement.setString(2,formatted_date);
			pstatement.setInt(3, getParentFeedID());
			pstatement.setString(4,getHashtag());
			pstatement.setString(5,getContent());
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/* function to fetch the posts and comments of a hashtag */ 
	
	/* Hashmap is used for its advantages, over arraylist */
	
	public Map retrieveValue(){
		
		List id = new ArrayList();
		List post = new ArrayList();
		List reply = new ArrayList();
		Map data = new HashMap();
		
		Db_connection db=new Db_connection();
		Connection conn=db.getConnection();
		String query = "SELECT ID,content FROM hashtagfeed WHERE hashtag='"+getHashtag()+"' and parentFeedID= "+0+" ORDER BY time DESC";// fetching posts and main id (main post has parent id =0)
		Statement statement, smt1;
		
		try {
			statement = conn.createStatement();
			smt1 = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              int id1 =	resultSet.getInt("ID");
              id.add(id1);
              post.add(resultSet.getString("content"));
            	ResultSet rs = smt1.executeQuery("SELECT content FROM hashtagfeed WHERE hashtag='"+getHashtag()+"' and parentFeedID= "+id1+" ORDER BY time DESC");// fetching replies of a particular post to feed the arraylist reply (replies of a post have the same id of the post)
            	List rep = new ArrayList();
            	while(rs.next()){	
            		rep.add(rs.getString("content"));
            	}
            	reply.add(rep);
            }          
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		data.put("id", id);
		data.put("post", post);
		data.put("reply", reply);
		return data;		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
