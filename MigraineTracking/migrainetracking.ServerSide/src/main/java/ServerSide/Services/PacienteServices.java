/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Models.DTOs.PacienteDTO;
import ServerSide.Models.Entities.EpisodioDolor;
import ServerSide.Models.Entities.Paciente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    @Path("/{id}")
    public Response findById( @PathParam("cedula") Long cedula ){
        Query q = entityManager.createQuery("SELECT u FROM Paciente u WHERE u.cedula = :cedula");
        q.setParameter("cedula", cedula);
        Paciente paciente = (Paciente)q.getSingleResult();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(paciente).build();
    }
    
    @GET
    public Response getAll(){
       Query q = entityManager.createQuery("SELECT u FROM Paciente u order by u.cedula ASC");
        List<Paciente> pacientes = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacientes).build();
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarPaciente(PacienteDTO paciente){
        return null;
        
    }
    
    @Path("/episodios/{cedula}")
    @GET
    public Response getEpisodiosByPaciente(@PathParam("cedula") Long cedula){
        
        Query q = entityManager.createQuery("SELECT u FROM EpisodioDolor u WHERE u.paciente.cedula = :cedula");
        q.setParameter("cedula", cedula);
        List<EpisodioDolor> episodios = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(episodios).build();
        
    }
}
