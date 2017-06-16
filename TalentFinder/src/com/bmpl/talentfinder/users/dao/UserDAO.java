package com.bmpl.talentfinder.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bmpl.talentfinder.users.dto.RightDTO;
import com.bmpl.talentfinder.users.dto.UserRoleRightMappingDTO;
import com.bmpl.talentfinder.users.dto.userDTO;

//POJO - plain old java object
//DAO contains DB code
public class UserDAO {
   
	private Connection createConnection() throws ClassNotFoundException, SQLException, NamingException{
		//Step-1 Load the driver
		
//			oracle.jdbc.driver.OracleDriver  for oracle
//			String driverName = "com.mysql.jdbc.Driver" ; //driver name is basically the name of the class
			
//			String driverName = DBproprtyReader.getValue("drivername");
			//String driverName = getValue("drivername");
			//Class.forName(driverName); //loads driver
			//step 2 create connection
//			String dbURL = "jdbc:mysql://localhost:3306/talentfinderdb";
		//	String dbURL = DBproprtyReader.getValue("dburl");
			//url for oracle is (jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
//			String userid = "root";
//			String password = "megaboy44";
			
		//	String userid = DBproprtyReader.getValue("userid");
		//	String password = DBproprtyReader.getValue("password");
		//	Connection connection = DriverManager.getConnection(dbURL,userid,password);
//			if(connection!=null){
//				System.out.println("Connection Created!!");
//				
//			}
		
		Context initContext  = new InitialContext(); //we tell context where our web application is working.it is handle of tomcat
		Context envContext  = (Context)initContext.lookup("java:/comp/env"); //tomcat make connection here in this environment
		DataSource dataSource = (DataSource)envContext.lookup("jdbc/myjndi");//get the data source from the pool where we have these connection
		Connection connection = dataSource.getConnection();
			return connection;
		
	}
	public boolean addUser(String userid,String password) throws ClassNotFoundException, SQLException, NamingException{
		
		Connection connection = null;
		Statement stmt = null;
		try{
		String sql = "insert into old_master (userid,password) values('"+userid+"','"+password+"')";
		connection = createConnection(); // store connection built
		//step -3 create do query
		stmt = connection.createStatement(); //create statement
		int inserted = stmt.executeUpdate(sql); //if inserted or updated it return 1
		return inserted>0?true:false;
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
	
	public UserRoleRightMappingDTO isAuthenticate(userDTO userDTO) throws ClassNotFoundException, SQLException, NamingException{
		UserRoleRightMappingDTO userRoleRight = null;
//		boolean isFound = false;
		Connection connection = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			connection = createConnection();
//			pstmt = connection.prepareStatement("select userid,password from "
//					+ "old_master where userid =? and password =? and pincode=?");  // ? is called placeholder
			pstmt = connection.prepareStatement(SQLQueries.LOGIN_QUERY);
			pstmt.setString(1, userDTO.getUserid());
			pstmt.setString(2, userDTO.getPassword());
//			pstmt.setInt(3, Integer.parseInt(userDTO.getPinCode()));
			//resultSet object will be made no matter it has value or not
//			rs = stmt.executeQuery("select userid,password from "
//					+ "old_master where userid ='"+userDTO.getUserid()+"' and password ='"
//							+ userDTO.getPassword()+"' and pincode="+userDTO.getPinCode());
			rs = pstmt.executeQuery();
			ArrayList<RightDTO> rightList = new ArrayList<>();
			while(rs.next()){
				//checking if rs has row or not
//				isFound=true;
				if(userRoleRight== null){
					/*this will run only for once when a user will come 
					*there after when a new right is being given to same user no new object of userroleright
					*will be made though an arraylist of rights of that user will be formed 
					*/
				userRoleRight = new UserRoleRightMappingDTO();
				userRoleRight.setUserid(rs.getString("userid"));
				userRoleRight.setRole(rs.getString("rolename"));
				}
				RightDTO rightDTO = new RightDTO();
				rightDTO.setName(rs.getString("rightname"));
				rightDTO.setScreen(rs.getString("screenname"));
				rightList.add(rightDTO);
				
				
			}
			if(userRoleRight!=null){
				userRoleRight.setRightlist(rightList);
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
		return userRoleRight;
	}
	/*
	 * testing for the query to run
	 */
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		UserDAO userdao = new UserDAO();
//		if(userdao.addUser("mukul", "123")){
//			System.out.println("record added");
//		}
//		else{
//			System.out.println("record not added");
//		}
//	}
}
