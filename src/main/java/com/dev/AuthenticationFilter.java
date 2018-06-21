package com.dev;

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.Priorities;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.dev.dao.UserDaoInterface;
import com.dev.model.User;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {
	
	private static UserDaoInterface userDao;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
    	//workaround
        String authorizationHeader =
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String uri = requestContext.getUriInfo().getRequestUri().toString();
        CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("cache");
        if(authorizationHeader!=null){
        	if(uri.contains("login")){
        		//get the username & password, validate it & generate token cache it.
        		return;
        	} else if(uri.contains("menu")){
        		if(null != cache && null!=cache.get("ashok")){
        			@SuppressWarnings("deprecation")
        			String tokenFromClient =
                    requestContext.getHeaderString("my-auth-token");
					String tokenCached = (String) cache.get("ashok").getValue();
            		if(tokenFromClient.equals(tokenCached)){
            			return;
            		} else {
            			throw new NotAuthorizedException("Authorization header must be provided");
            		}
        		}
				 else {
        			throw new NotAuthorizedException("Authorization header must be provided");
        		}
        		
        		//get the token from cache , compare both & validate and return.
        	} else if(uri.contains("orderDetails") && uri.contains("create") ){
				 @SuppressWarnings("deprecation")
     			String tokenFromClient =
                 requestContext.getHeaderString("my-auth-token");
					String tokenCached = (String) cache.get("ashok").getValue();
         		if(tokenFromClient.equals(tokenCached)){
         			return;
         		} else {
         			throw new NotAuthorizedException("Authorization header must be provided");
         		}
 		}
        }
       
        else if(uri.contains("login")){
        	if(requestContext.getHeaderString("access-control-request-headers") !=null && requestContext.getHeaderString("access-control-request-headers").contains("authorization")){
        		
        		return;
        	} else {
        		throw new NotAuthorizedException("Authorization header must be provided");
        	}
        } else if(uri.contains("menu")){
        	if(requestContext.getHeaderString("access-control-request-headers") !=null && requestContext.getHeaderString("access-control-request-headers").contains("authorization")){
        		
        		return;
        	} else {
        		throw new NotAuthorizedException("Authorization header must be provided");
        	}	
        }

        // Check if the HTTP Authorization header is present and formatted correctly
     //   if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
     //       throw new NotAuthorizedException("Authorization header must be provided");
     //   }

        // Extract the token from the HTTP Authorization header
     //   String token = authorizationHeader.substring("Bearer".length()).trim();

       // try {

            // Validate the token
        //    validateToken(token);

      //  } catch (Exception e) {
       //     requestContext.abortWith(
         //       Response.status(Response.Status.UNAUTHORIZED).build());
       // }
    }

    private void validateToken(String token) throws Exception {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
    }
    
   
}