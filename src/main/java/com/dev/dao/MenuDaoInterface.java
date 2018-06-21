package com.dev.dao;

import java.util.List;

import com.dev.model.Menu;
import com.dev.model.User;

public interface MenuDaoInterface {
	
	List<Menu> getMenuList();

	Menu createMenuItem(Menu menuItem);
	

}
