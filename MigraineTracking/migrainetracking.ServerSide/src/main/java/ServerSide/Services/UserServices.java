/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Init.Stormpath;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.resource.ResourceException;
import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Personal
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class UserServices {

    @Path("/login")
    @POST
    public Response login() {
        
//##############################################################################        
//############ ESTE FUE UN LOGIN DE PRUEBA PARA VERIFICAR STORMPATH ############
//##############################################################################        
//        String usernameOrEmail = "teog29@yopmail.com";
//        String rawPassword = "Caracoli1";
//
//        //Create an authentication request using the credentials
//        AuthenticationRequest request = new UsernamePasswordRequest(usernameOrEmail, rawPassword);
//
//################## ServerSide.Init.Stormpath tiene los metodos estaticos de la auth ##################   
//        Stormpath.createStormPathClient(); 
//        Application application = Stormpath.getStormPathApp();
//        try {
//            AuthenticationResult result = application.authenticateAccount(request);
//            return result.toString();
//        } catch (ResourceException ex) {
//            System.out.println(ex.getStatus() + " " + ex.getMessage());
//        }
        return null;
    }

}
