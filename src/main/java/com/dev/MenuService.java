package com.dev;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.dao.MenuDaoInterface;
import com.dev.model.Menu;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;




@Path("menu") //http://localhost:8083/hotel-services/webapi/menu
public class MenuService {
	
private static MenuDaoInterface menuDao;
	
	@Autowired
    public void setMenuDao(MenuDaoInterface menuDao) {
		if(menuDao == null) {
			System.out.println("menuDao is not getting initialized....");
		}
        this.menuDao = menuDao;
    }
	
	@GET
//	@PermitAll
	//@SecurityChecked
	@Secured
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Response getMenuList(){
		//String username = formParams.getFirst("username").toString();
		 CacheManager cm = CacheManager.getInstance();
			Cache cache = cm.getCache("cache");
		List<Menu> menu = menuDao.getMenuList();
		return Response.ok().entity(menu).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@POST
	//@RolesAllowed("ADMIN")
	@Path("create")//this is concatination to what is there above in the class path(//http://localhost:8083/hotel-services/webapi/menu/create)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Response createMenuItem(MultivaluedMap<String,String> formParams){
		
		String itemName = formParams.getFirst("name").toString();
		//String cost = formParams.getFirst("cost");
		Double cost = (double) Integer.parseInt(formParams.getFirst("cost"));
		Double quantity = (double) Integer.parseInt(formParams.getFirst("quantity"));
		Menu menuItem = new Menu();
		menuItem.setName(itemName);
		menuItem.setCost(cost);
		menuItem.setQuantity(quantity);
		menuDao.createMenuItem(menuItem);
		return Response.ok().entity(menuItem).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

}
