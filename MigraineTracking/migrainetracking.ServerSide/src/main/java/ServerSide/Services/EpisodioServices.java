/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Services;

import ServerSide.Init.PersistenceManager;
import ServerSide.Models.DTOs.EpisodioDolorDTO;
import ServerSide.Models.Entities.EpisodioDolor;
import com.google.gson.Gson;
import java.util.List;
import org.codehaus.jettison.json.JSONObject;
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
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //--------------------------------------------------------------------------
    // Service call methods
    //--------------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarEpisodio(EpisodioDolorDTO episodio){
        
       JSONObject respuesta = new JSONObject();
       EpisodioDolor episodioEntity = new EpisodioDolor();
       episodioEntity.setFecha(episodio.getFecha());
       episodioEntity.setHoursSlept(episodio.getHoursSlept());
       episodioEntity.setId(episodio.getId());
       episodioEntity.setIntensidad(episodio.getIntensidad());
       episodioEntity.setLocalizacion(episodio.getLocalizacion());
       episodioEntity.setCatalizadores(toJson(episodio.getCatalizadores()));
       episodioEntity.setMedicamentos(toJson(episodio.getMedicamentos()));
       episodioEntity.setSintomas(toJson(episodio.getSintomas()));
       //Hace falta asignar el paciente 
       
       
       try{
           entityManager.getTransaction().begin();
           entityManager.persist(episodioEntity);
           entityManager.getTransaction().commit();
           entityManager.refresh(episodioEntity);
           respuesta.put("episodio_dolor_id", episodioEntity.getId());
           
       }
       catch(Throwable t){
          t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
          episodioEntity=null;  
       }
       finally{
           entityManager.clear();
           entityManager.close();
       }
       
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(respuesta).build();
        
    }
 
    /**
     * Retorna los detalles de un episodio particular
     * @param id el id del episodio
     * @return un episodio de dolor dado el id
     */
    @GET
    @Path("/{id}")
    public Response getDetalles(@PathParam("id")Long id){
        Query q = entityManager.createQuery("SELECT u FROM EpisodioDolor u WHERE u.id = :id");
        q.setParameter("id", id);
        EpisodioDolor episodio = (EpisodioDolor)q.getSingleResult();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(episodio).build();
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
    
    public String toJson(List lista){
        return new Gson().toJson(lista);
    }
}
