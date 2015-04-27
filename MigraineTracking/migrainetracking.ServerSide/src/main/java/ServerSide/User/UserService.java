/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide.User;

import ServerSide.Models.DTOs.UserDTO;
import ServerSide.security.jwt.api.JsonWebToken;
import ServerSide.security.jwt.api.JwtHashAlgorithm;
import com.google.gson.Gson;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.tenant.Tenant;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author estudiante
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    
    public final static String API_KEY_PATH = "C:\\Users\\template\\Documents\\Grupo-9\\MigraineTracking\\migrainetracking.ServerSide\\apiKey.properties";
    
    @Path("/login/paciente")
    @POST
    public Response loginPaciente(UserDTO user) {
        int status = 500; 
        String token = "El usuario y contrasenha no existen en el sistema";
        UserDTO usuario = new UserDTO();
       
        ApiKey apiKey = ApiKeys.builder().setFileLocation(API_KEY_PATH).build();
        Client client = Clients.builder().setApiKey(apiKey).build();

        try {
            AuthenticationRequest request = new UsernamePasswordRequest(user.getUsername(), user.getPassword());
            Tenant tenant = client.getCurrentTenant();
            ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("MigraineTracking")));
            Application application = applications.iterator().next();

            AuthenticationResult result = application.authenticateAccount(request);
            Account account = result.getAccount();
            usuario.setEmail(account.getEmail());
            usuario.setName(account.getFullName());
            usuario.setUsername(account.getUsername());
            usuario.setPassword(usuario.getPassword());
            
            usuario.setLevelAccess("pacientes");
            token = new Gson().toJson(JsonWebToken.encode(usuario, "Un14nd3s2014@", JwtHashAlgorithm.HS256));
            status = 200;

        } catch (ResourceException ex) {
            System.out.println(ex.getStatus() + " " + ex.getMessage());
        }

        return Response.status(status).entity(token).build();
    }
    
    @Path("/login/doctor")
    @POST
    public Response loginDoctor(UserDTO user) {
        int status = 500; 
        String token = "El usuario y contrasenha no existen en el sistema";
        UserDTO usuario = new UserDTO();
       
        ApiKey apiKey = ApiKeys.builder().setFileLocation(API_KEY_PATH).build();
        Client client = Clients.builder().setApiKey(apiKey).build();

        try {
            AuthenticationRequest request = new UsernamePasswordRequest(user.getUsername(), user.getPassword());
            Tenant tenant = client.getCurrentTenant();
            ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("MigraineTracking")));
            Application application = applications.iterator().next();

            AuthenticationResult result = application.authenticateAccount(request);
            Account account = result.getAccount();
            usuario.setEmail(account.getEmail());
            usuario.setName(account.getFullName());
            usuario.setUsername(account.getUsername());
            usuario.setPassword(usuario.getPassword());
            
            usuario.setLevelAccess("doctores");
            token = new Gson().toJson(JsonWebToken.encode(usuario, "Un14nd3s2014@", JwtHashAlgorithm.HS256));
            status = 200;

        } catch (ResourceException ex) {
            System.out.println(ex.getStatus() + " " + ex.getMessage());
        }

        return Response.status(status).entity(token).build();
    }
    
    @Path("/login/administrador")
    @POST
    public Response loginAdministrador(UserDTO user) {
        int status = 500; 
        String token = "El usuario y contrasenha no existen en el sistema";
        UserDTO usuario = new UserDTO();
       
        ApiKey apiKey = ApiKeys.builder().setFileLocation(API_KEY_PATH).build();
        Client client = Clients.builder().setApiKey(apiKey).build();

        try {
            AuthenticationRequest request = new UsernamePasswordRequest(user.getUsername(), user.getPassword());
            Tenant tenant = client.getCurrentTenant();
            ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("MigraineTracking")));
            Application application = applications.iterator().next();

            AuthenticationResult result = application.authenticateAccount(request);
            Account account = result.getAccount();
            usuario.setEmail(account.getEmail());
            usuario.setName(account.getFullName());
            usuario.setUsername(account.getUsername());
            usuario.setPassword(usuario.getPassword());
            
            usuario.setLevelAccess("administradores");
            token = new Gson().toJson(JsonWebToken.encode(usuario, "Un14nd3s2014@", JwtHashAlgorithm.HS256));
            status = 200;

        } catch (ResourceException ex) {
            System.out.println(ex.getStatus() + " " + ex.getMessage());
        }

        return Response.status(status).entity(token).build();
    }
}