/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Models.DTOs.EpisodioDolorDTO;
import java.util.Date;
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
@Path("/episodios")
@Produces(MediaType.APPLICATION_JSON)
public class EpisodioServices {
    
    @PersistenceContext(unitName ="myPU")
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
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarEpisodio(EpisodioDolorDTO episodio){
        return null;
        
    }
 
    @GET
    @Path("/{id}")
    public Response getDetalles(@PathParam("id")Long id){
        return null;
       // Mandar el List<EpisodioDolorDTO> con todo vacio excpeto las listas de catalizadores,sintomas, y medicamentos ...
    }
    
    @GET
    @Path("/{id}/{fecha1}/{fecha2}")
    public Response getBetweenFechas( @PathParam("id") Long id , @PathParam("fecha1") String fecha1 , @PathParam("fecha2") String fecha2 ){
        return null;
        
    }
    
    //--------------------------------------------------------------------------
    // Logic support methods
    //--------------------------------------------------------------------------
    
    
    //--------------------------------------------------------------------------
    // Persistence support methods
    //--------------------------------------------------------------------------
}
