/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Models.DTOs.DoctorDTO;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Personal
 */
@Path("/doctores")
@Produces(MediaType.APPLICATION_JSON)
public class DoctorServices {
    @PersistenceContext(unitName = "myPU")
    EntityManager em; 

    @PostConstruct
    public void init(){
        try{
            em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }
        catch(Exception e){}
    }
    
    //--------------------------------------------------------------------------
    // Service call methods
    //--------------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarEpisodio(){
        
    }
    
    //--------------------------------------------------------------------------
    // Logic support methods
    //--------------------------------------------------------------------------
    @GET
    @Path("/{id}")
    public Response findById( @PathParam("id") Long id ){
        
    }
    
    @GET
    public Response findAll(){
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarEpisodio(DoctorDTO doctor){
        
    }
    
   
    
    //--------------------------------------------------------------------------
    // Persistence support methods
    //--------------------------------------------------------------------------
    
    
}
