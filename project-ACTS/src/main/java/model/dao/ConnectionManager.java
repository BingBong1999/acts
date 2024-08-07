package model.dao;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {

	/*
	 * private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	 * private static final String DB_URL = "jdbc:oracle:thin:@202.20.119.117:1521:orcl"; 
	 * private static final String DB_USERNAME = "dbp";
	 * private static final String DB_PASSWORD = "dbp";
	 */

	private static DataSource ds = null;

	public ConnectionManager() {
		
		InputStream input = null;
		Properties prop = new Properties();

		try {
			
			input = getClass().getResourceAsStream("/context.properties");
			prop.load(input);
			
		} catch (IOException ex) {
			
			ex.printStackTrace();
		
		} finally {
			
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}

		try {
			
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(prop.getProperty("db.driver"));
			bds.setUrl(prop.getProperty("db.url"));
			bds.setUsername(prop.getProperty("db.username"));
			bds.setPassword(prop.getProperty("db.password"));
			
			ds = bds;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public void close() {
		
		BasicDataSource bds = (BasicDataSource) ds;
		
		try {
			bds.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void printDataSourceStats() {
		
		try {
			
			BasicDataSource bds = (BasicDataSource) ds;
			System.out.println("NumActive: " + bds.getNumActive());
			System.out.println("NumIdle: " + bds.getNumIdle());
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
