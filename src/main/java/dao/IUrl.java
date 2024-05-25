package dao;

import java.util.List;

import entity.Url;

public interface IUrl {
	
	Url  saveUrl(Url url);
	boolean  existById(String id);
	Url getById(String id);

}
