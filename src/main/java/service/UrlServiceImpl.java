package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.Timestamp;

import dao.IUrl;
import db.DbConfig;
import entity.Url;
import exception.DbException;
import model.Product;

public class UrlServiceImpl implements IUrl{

	private Connection conn = null;
	
	public UrlServiceImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Url saveUrl(Url url) {
		String save = "insert into urls (id , fullUrl , expiresTime) values (? ,? ,?)";
		this.conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			conn = DbConfig.getConnection();
			ps = conn.prepareStatement(save);
			ps.setString(1, url.getId());
			ps.setString(2, url.getFullUrl());
			ps.setDate(3,java.sql.Date.valueOf(url.getExpiresTime().toLocalDate()));
			int affectedRow = ps.executeUpdate();
			
			if(affectedRow > 0) {
					return url;
			}
			
			
		}catch (Exception e) {
			throw new DbException(e.getMessage(),e.getCause());
		}finally{
			DbConfig.closeConnection(conn);
			DbConfig.closePreparedStatement(ps);
			DbConfig.closeResultSet(rs);
		}
		
		
		return null;
	}

	@Override
	public boolean existById(String id) {
        String query = "SELECT COUNT(*) FROM urls WHERE id = ?";
		this.conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		try {
			conn = DbConfig.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				int count = rs.getInt(1);
				return count > 0;
			}
			return false ;
		}catch(Exception e) {
			throw new DbException(e.getMessage(),e.getCause());
		}finally {
			DbConfig.closeConnection(conn);
			DbConfig.closePreparedStatement(ps);
			DbConfig.closeResultSet(rs);
		}
	}

	@Override
	public Url getById(String id) {
		String getById = "Select * from urls where id = ?";
		this.conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DbConfig.getConnection();
			ps = conn.prepareStatement(getById);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			
			 if (rs != null && rs.next()) {
		            Url url = new Url(rs.getString("id"), rs.getString("fullUrl"), rs.getDate("expiresTime"));
		            System.out.print("url full"+url.getFullUrl());
		            return url;
		        }
			
			
		}catch (Exception e) {
			throw new DbException(e.getMessage(),e.getCause());
		}finally {
			DbConfig.closeConnection(conn);
			DbConfig.closePreparedStatement(ps);
			DbConfig.closeResultSet(rs);
		}
		return null;
	}
	
	

}
