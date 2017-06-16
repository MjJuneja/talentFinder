package com.bmpl.talentfinder.company.dao;
import static com.bmpl.talentfinder.users.dao.DBproprtyReader.getValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

import com.bmpl.talentfinder.company.dto.CompanyDTO;
import com.bmpl.talentfinder.company.dto.PostDTO;
import com.bmpl.talentfinder.users.dao.DBproprtyReader;

import javafx.util.converter.LocalTimeStringConverter;
public class CompanyDAO {
	private Connection createConnection() throws ClassNotFoundException, SQLException{
		
			String driverName = getValue("drivername");
			Class.forName(driverName); 
			String dbURL = DBproprtyReader.getValue("dburl");
			String userid = DBproprtyReader.getValue("userid");
			String password = DBproprtyReader.getValue("password");
			Connection connection = DriverManager.getConnection(dbURL,userid,password);
			return connection;
	}
	
	public boolean addCompany(String userid,String password,String companyName,String phone, String size,String name) throws ClassNotFoundException, SQLException{
		boolean flag = false;
		Connection connection = null;
		Statement stmt = null;
		Validations valid = new Validations();
		if(valid.isValidPhone(phone) && valid.isValidUserid(userid)){
		try{
			
		String sql = "insert into company_info (companyname,email,password,size,name,phone)"
				+ " values('"+companyName+"','"+userid+"','"+password+"','"+size+"','"+name+"','"+phone+"')";
		connection = createConnection();
		stmt = connection.createStatement(); 
		int inserted = stmt.executeUpdate(sql); 
		if(inserted>0){
			flag = true;
		}
		}
		finally{
			if(stmt!=null){
			stmt.close();
			}
			if(connection!=null){
				connection.close();
			}
		}
		}
		return flag;
	}
	
	
	public boolean isAuthenticate(CompanyDTO companyDTO) throws ClassNotFoundException, SQLException{
		boolean isFound = false;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			connection = createConnection();
			pstmt = connection.prepareStatement("select email,password from "
					+ "company_info where email =? and password =? and companyname=?");  
			pstmt.setString(1,companyDTO.getUserid() );
			pstmt.setString(2, companyDTO.getPassword());
			pstmt.setString(3, companyDTO.getCompName());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				isFound=true;
			}
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(connection!=null){
				connection.close();
			}
		}
		return isFound;
	}
	public boolean addPost(PostDTO postdto) throws ClassNotFoundException, SQLException{
		boolean flag = false;
		Connection connection = null;
		Statement stmt = null;
		
		try{
			
			String sql = "insert into jobpost (companyname,post,time,stars,email)"
					+ " values('"+postdto.getCompanyName()+"','"+postdto.getPost()+"','"+postdto.getTime()+""
							+ "','"+postdto.getStar()+"','"+postdto.getEmail()+"')";
			connection = createConnection();
			stmt = connection.createStatement(); 
			int inserted = stmt.executeUpdate(sql); 
			if(inserted>0){
				flag = true;
			}
			}
			finally{
				if(stmt!=null){
				stmt.close();
				}
				if(connection!=null){
					connection.close();
				}
			}
			
			return flag;
		
		
	}
	
	public ArrayList<PostDTO> collectPost() throws ClassNotFoundException, SQLException{
//		boolean isFound = false;
		Connection connection = null;
//		Statement myStmt = null;
		Statement pstmt = null;
		ResultSet rs = null;
		ArrayList<PostDTO> list = null;
//		ResultSet myRs = null;
		try{
			
			connection = createConnection();
//		    myStmt = connection.createStatement();
//			myRs=myStmt.executeQuery("select * from jobpost");
//			ResultSetMetaData rm=myRs.getMetaData();
//			int count=rm.getColumnCount();
			String sql = "select companyname,post from jobpost";
			pstmt= connection.createStatement();
			rs = pstmt.executeQuery(sql);
			 list = new ArrayList<>();
			while(rs.next()){
				PostDTO post= new PostDTO(rs.getString("companyname"),rs.getString("post"));
				list.add(post);
//				isFound=true;
				System.out.println(rs.getString("companyname"));
			}

			
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(connection!=null){
				connection.close();
			}
		}
		
			return list;
		 
		
	}
}
