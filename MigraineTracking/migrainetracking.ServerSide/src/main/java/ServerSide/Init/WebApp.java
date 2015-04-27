/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide.Init;



import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

/**
 *
 * @author estudiante
 */
@ApplicationPath("/webresources")
public class WebApp extends ResourceConfig{
    
    public WebApp( ){
        packages("ServerSide.User");
        packages("ServerSide.Services");
        packages("ServerSide.security.auth");
        
    }  
}
