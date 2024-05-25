package dao;

import db.DbConfig;
import service.UrlServiceImpl;

public class DAOFactory {

	
	public static IUrl createIurl() {
		return new UrlServiceImpl(DbConfig.getConnection());
	}
}
