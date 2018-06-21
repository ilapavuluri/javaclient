package com.dev;

import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.dao.UserDaoInterface;
import com.dev.model.User;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Path("user") //http://localhost:8083/hotel-services/webapi/user
public class UserService {
	
	private static UserDaoInterface userDao;
	
	@Autowired
    public void setUserDao(UserDaoInterface userDao) {
		if(userDao == null) {
			System.out.println("userDao is not getting initialized....");
		}
        this.userDao = userDao;
    }
	
	@POST
	//@OPTIONS
	//@SecurityChecked
	//@PermitAll
	@Secured
	@Path("login")//this is concatination to what is there above in the class path(//http://localhost:8083/hotel-services/webapi/user/login)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Response verifyUser(MultivaluedMap<String,String> formParams){
		String username = formParams.getFirst("username").toString();
		String password = formParams.getFirst("password").toString();
		Boolean isAuthorized =  authenticate(username,password);
		if(isAuthorized){
			String token = issueToken(username);
			JsonObject resObj =
			        Json.createObjectBuilder()
			                .add("username", username)
			                .add("password", password)
			                .add("token", token)
			        .build();
			
			return Response.ok().entity(resObj).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity("Authorization Required").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
	}
	
	private String issueToken(String username) {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("cache");
		if(cache==null){
			cm.addCache("cache");
			cache = cm.getCache("cache");
		}
		cache.put(new Element(username,randomUUIDString));
		//cm.addCache("cache");
		return randomUUIDString;
	}

	private Boolean authenticate(String username, String password) {
		User user = userDao.getUserByName(username);
		if(null!=user){
			return password.matches(user.getPassword());
		}
		else {
			return false;
		}
	}

	@POST
	@Path("adduser")//this is concatination to what is there above in the class path(//http://localhost:8083/hotel-services/webapi/user/login)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Response addUser(MultivaluedMap<String,String> formParams){
		String username = formParams.getFirst("username").toString();
		String password = formParams.getFirst("password").toString();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userDao.createUser(user);
		return Response.ok().entity(user).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

}
