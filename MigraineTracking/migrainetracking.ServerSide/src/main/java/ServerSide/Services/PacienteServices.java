/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Models.DTOs.PacienteDTO;
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
@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
public class PacienteServices {
    @PersistenceContext(unitName = "myPU")
    EntityManager entityManager; 

    @PostConstruct
    public void init(){
        try{
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }
        catch(Exception e){}
    }
    
    //--------------------------------------------------------------------------
    // Service call methods
    //--------------------------------------------------------------------------
    @GET
    public Response getAll(){
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarEpisodio(PacienteDTO paciente){
        
    }
    
    @Path("/{cedula}/episodios")
    @GET
    public Response getEpisodiosByPaciente(@PathParam("cedula") Long cedula){
        
    }
    
    //--------------------------------------------------------------------------
    // Logic support methods
    //--------------------------------------------------------------------------
    
    
    //--------------------------------------------------------------------------
    // Persistence support methods
    //--------------------------------------------------------------------------
}
