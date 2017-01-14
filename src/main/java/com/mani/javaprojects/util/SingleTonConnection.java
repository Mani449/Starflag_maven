package com.mani.javaprojects.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleTonConnection {
	private static Connection connection=null;
public static Connection connection() throws SQLException,Exception
{
	if(connection==null || connection.isClosed())
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connection=DriverManager.getConnection("jdbc:oracle:thin:root/manikanta@oid.cbgzh5cglbfm.us-east-2.rds.amazonaws.com:1521:ORCL");
			System.out.println(connection);
		}
	connection.setAutoCommit(false);
	return connection;
}
}