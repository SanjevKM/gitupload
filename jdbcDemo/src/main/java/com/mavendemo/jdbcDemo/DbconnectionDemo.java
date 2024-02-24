package com.mavendemo.jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*
;
public class DbconnectionDemo {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the id:");
		int a=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Name:");
		String b=sc.nextLine();
		System.out.println("Enter the score:");
		float c=sc.nextFloat();

		
		String URL ="jdbc:oracle:thin:@localhost:1521:xe";
		String userName="DATABASE";
		String passWord="DATABASE";
		
		//Quries
		//final String QUERY_CREATE="CREATE TABLE DATABASE.TEST_CHECK(TESTID NUMBER,TESTNAME VARCHAR2(25),TESTSCORE NUMBER(4,2))";
		final String QUERY_INSERT="INSERT INTO DATABASE.TEST_CHECK VALUES("+a+",'"+b+"',"+c+")";
		final String Q="SELECT * FROM DATABASE.TEST_CHECK";
		//final String K="UPDATE DATABASE.TEST_CHECK SET TESTSCORE=98.3 WHERE TESTID=2";
		//final String K="DELETE FROM DATABASE.TEST_CHECK WHERE TESTSCORE=98.3";
		//step1 - Registering the driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 Create connection
			Connection con=DriverManager.getConnection(URL,userName,passWord);
			
			if(con==null)
				System.out.println("Connection not Established!");
			else
				System.out.println("Connection is EStablished!");
			
			//step3 - Creating the statement
			Statement stat=con.createStatement();
//			PreparedStatement pstmt = con.prepareStatement(QUERY_INSERT);
//            pstmt.setInt(1, a);
//            pstmt.setString(2, b);
//            pstmt.setFloat(3, c);
			
			//step4 - executing the statement
//			boolean iscreated=stat.execute(QUERY_INSERT);
//			if(iscreated)
//				System.out.println("Table Created");
//			else
//				System.out.println("Table not Created");
			
			//STEP5
			int rowupdate=stat.executeUpdate(QUERY_INSERT);
			if(rowupdate>0)
				System.out.println("Row updated");
			else
				System.out.println("Row not updated");
			
			ResultSet test=stat.executeQuery(Q);
			while(test.next()) {
				System.out.println(test.getInt(1)+" "+test.getString(2)+" "+test.getFloat(3));
			}
		
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
