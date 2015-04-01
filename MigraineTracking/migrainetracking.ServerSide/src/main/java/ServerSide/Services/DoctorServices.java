/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Models.Entities.Doctor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @Path("/{id}")
    public Response findById( @PathParam("id") Long id ){
        Query q = entityManager.createQuery("SELECT u FROM Doctor u WHERE u.id = :id");
        q.setParameter("id", id);
        Doctor doctor = (Doctor)q.getSingleResult();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(doctor).build();
        
    }
    
    @GET
    public Response findAll(){
        Query q = entityManager.createQuery("select u from Doctor u order by u.id ASC");
        List<Doctor> doctors = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(doctors).build();
        
    }
    
    @POST
    public Response registrarDoctor(){
        return null;
    }
   
    
    //--------------------------------------------------------------------------
    // Logic support methods
    //--------------------------------------------------------------------------

    
    //--------------------------------------------------------------------------
    // Persistence support methods
    //--------------------------------------------------------------------------
    

    
}
