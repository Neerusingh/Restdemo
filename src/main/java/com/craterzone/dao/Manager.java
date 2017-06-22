package com.craterzone.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Manager {
	private Properties p;
	private static Manager mngr;
    private Manager(){}

	public Properties loadProperties() {
		if (p == null) {
			p = new Properties();
			try {
				InputStream	input = new FileInputStream(new File(
						"/home/neeru/ServletDemos/Restdemo/WebContent/WEB-INF/databaseManager.properties"));
                 p.load(input);
				 input.close();
                } catch (IOException e) {
                   e.printStackTrace();
			}
			finally{
				
			}
		}
		return p;
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Properties prop = getInstance().loadProperties();
			Class.forName(prop.getProperty("driver_name"));
             con = (Connection) DriverManager.getConnection(
					prop.getProperty("db_url") + "/" + prop.getProperty("db_name"), prop.getProperty("db_username"),
					prop.getProperty("db_password"));
           } catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Manager getInstance() {
         if (mngr != null) {
			return mngr;
		}
		mngr = new Manager();
         return mngr;
	}
}
