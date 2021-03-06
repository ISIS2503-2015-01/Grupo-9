/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mycompany.migrainetracking.services;

import java.util.ArrayList;
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
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.ejb.ServicioAnalisisMock;
import migrainetracking.logica.ejb.ServicioRegistroEpisodioMock;
import migrainetracking.logica.ejb.ServicioRevisionEpisodiosMock;
import migrainetracking.logica.interfaces.IServicioAnalisisMockRemote;
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
    
    @EJB
    private IServicioAnalisisMockRemote beanAnalisis;
    

    /**
     * Creates a new instance of RegistroEpisodioService
     */
    public RegistroEpisodioService() {
        beanRegEps  = ServicioRegistroEpisodioMock.getInstance();
        beanAnalisis = ServicioAnalisisMock.getInstance();
    }
    //Meter regla y probar funcion no prioridad
    @POST
    @Path("/create/EpisodioDolor/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEpisdioDolor(EpisodioDolorDTO ep ) throws JSONException{
        JSONObject rta = new JSONObject();
        List<String> acciones ;
        try {
            Long id = beanRegEps.registrarEpisodio(ep,ep.getPacienteId());
            acciones = beanAnalisis.getAcciones( id );
            rta.put("acciones", acciones);
        } catch (OperacionInvalidaException ex) {
            rta.put("Error de sistema : ",ex.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(rta).build();
        } catch (NoExisteException ex) {
            System.err.println("NO SE CUMPLIO LA PRE-CONDICION. EL EPISODIO YA DEBERIA DE EXISTIR");
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    /** <Todos los episodios>. OJO SOLO BOTA 10 pq no hay paginacion todavia¨*/
    @GET
    @Path("/getAll/EpisodiosDolor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodios(){
        List<EpisodioDolorDTO> eps = beanRegEps.getEpisodios();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(eps).build();
    }
    /** <TODOS los episodios>.¨*/
    
    
    @GET
    @Path("/getEpisodios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Deprecated
    public Response getEpisiodiosByPaciente(@PathParam("id") int noIdPaciente){
        List<EpisodioDolorDTO> eps = beanRegEps.getEpisodiosPorPaciente(noIdPaciente);
        return Response.status(Response.Status.OK).entity(eps).build();
    }
    
    /** <DEVUELVE UN EPISODIO DADO SU ID>*/
    @GET
    @Path("/getEpisodio/episodio_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodio(@PathParam("id") Long id){
        EpisodioDolorDTO resp = beanRegEps.getEpisodioById(id);
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(resp).build();
    }
    /** </DEVUELVE UN EPISODIO DADO SU ID>*/
    
    //<--------------------------------------------->
    
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
