package com.craterzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import com.craterzone.exception.InternalServerErrorException;
import com.craterzone.exception.UserNotFoundException;
import com.craterzone.model.User;

public class Dao {
	private Connection con;
	private static Dao dao = new Dao();

	private Dao() {
	}

	public User get(int uid) throws UserNotFoundException, InternalServerErrorException {

		try {
			con = Manager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from login where id=?");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getInt("password"), rs.getString("email"));
			}
			throw new UserNotFoundException("user not found");
		}
		catch (SQLException  e ) {
			//System.out.println("error found);
			throw new InternalServerErrorException("INternal server error");
		} finally {
			
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }
		
	}

	public Boolean create(User user) throws SQLException {
		int uid = user.getId();
		String uname = user.getName();
		int upassword = user.getPassword();
		String uemail = user.getEmail();
		try {
			con = Manager.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into login values(?,?,?,?)");
			ps.setInt(1, uid);
			ps.setString(2, uname);
			ps.setInt(3, upassword);
			ps.setString(4, uemail);
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			con.close();
		}
		return false;
	}

	public Boolean update(User user) throws SQLException, InternalServerErrorException {
		int uid = user.getId();
		String uname = user.getName();
		int upassword = user.getPassword();
		String uemail = user.getEmail();
		try {
			con = Manager.getConnection();
			PreparedStatement ps = con.prepareStatement("update  login set name=?,password=? and email=? where id=?");
			ps.setString(1, uname);
			ps.setInt(2, upassword);
			ps.setString(3, uemail);
			ps.setInt(4, uid);
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			}
			
		} catch (Exception e) {
			throw new InternalServerErrorException("internal server error");
		}
		finally{
			con.close();
		}
		return false;
	}

	public static Dao getInstance() {
		if (dao != null) {
			return dao;
		}
		dao = new Dao();
		return dao;
	}

}
