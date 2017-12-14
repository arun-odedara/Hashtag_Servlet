package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import dao.Db_connection;

public class Hashtag {

	private String new_hashtag;

	public String getNew_hashtag() {
		return new_hashtag;
	}


	public void setNew_hashtag(String new_hashtag) {
		this.new_hashtag = new_hashtag;
	}
	
	/*function to store value into the database table named hashtag*/

	public boolean storeValue(){
		
		Db_connection db=new Db_connection();
		Connection conn=db.getConnection();
		Date date1=new Date();
		DateFormat dformat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String formatted_date=dformat.format(date1);
		int val=0;
		try {
			PreparedStatement pstatement=conn.prepareStatement("Insert into hashtag values(?,?)");
			pstatement.setString(1, getNew_hashtag());
			pstatement.setString(2, formatted_date);
			val=pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(val!=0)
			return true;
		else
			return false;
	}
	
	/* funtion to search for hashtag names from the database table hashtag */
	
	public boolean searchValue(){
		
		int val=0;
		Db_connection db=new Db_connection();
		Connection conn=db.getConnection();
		
		String query = "SELECT name FROM hashtag WHERE name='"+getNew_hashtag()+"'";
		Statement statement; 
		try {
			statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
	
			if(resultSet.next()){
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
}
