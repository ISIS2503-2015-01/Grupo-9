/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mycompany.migrainetracking.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.ejb.ServicioRegistroEpisodioMock;
import migrainetracking.logica.ejb.ServicioRevisionEpisodiosMock;
import migrainetracking.logica.interfaces.IServicioRegistroEpisodioMockRemote;
import migrainetracking.logica.interfaces.IServicioRevisionEpisodiosMockRemote;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Personal
 */
@Path("/registroepisodios")
public class RegistroEpisodioService {

    @Context
    private UriInfo context;
    
    @EJB
    private IServicioRegistroEpisodioMockRemote beanRegEps;
    

    /**
     * Creates a new instance of RegistroEpisodioService
     */
    public RegistroEpisodioService() {
        beanRegEps  = ServicioRegistroEpisodioMock.getInstance();
    }

    @POST
    @Path("/create/EpisodioDolor/pacid={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEpisdioDolor(EpisodioDolor ep,@PathParam("id")int idPac ) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = beanRegEps.registrarEpisodio(ep, idPac);
            rta.put("episodio id",id);
        } catch (OperacionInvalidaException ex) {
            rta.put("Error de sistema : ",ex.getMessage());
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @GET
    @Path("/getAll/EpisodiosDolor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodios(){
        List<EpisodioDolor> eps = beanRegEps.getEpisodios();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(eps).build();
    }
    
    @GET
    @Path("/getEpisodios/pacid={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisiodiosByPaciente(@PathParam("id") int noIdPaciente){
        List<EpisodioDolor> eps = beanRegEps.getEpisodiosPorPaciente(noIdPaciente);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(eps).build();
    }
    
    /**
     * Retrieves representation of an instance of mycompany.migrainetracking.services.RegistroEpisodioService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegistroEpisodioService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
