/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.migrainetracking.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.logica.ejb.ServicioRevisionEpisodiosMock;
import migrainetracking.logica.interfaces.IServicioRevisionEpisodiosMockRemote;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Personal
 */
@Path("/revisionepisodios")
public class RevisionEpisodiosService {

    @Context
    private UriInfo context;

    @EJB
    IServicioRevisionEpisodiosMockRemote revEpService;

    /**
     * Creates a new instance of RevisionEpisodiosService
     */
    public RevisionEpisodiosService() {
        revEpService = ServicioRevisionEpisodiosMock.getInstance();
    }
    
    // <DETALLES DEL EPISODIO> 
    @GET
    @Path("/getMedicamentos/episodioid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicamentosEpisodio(@PathParam("id") Long id){
        List<MedicamentoDTO> resp = revEpService.getMedicamentosDelEpisodio(id);
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(resp).build();
    }
    
    @GET
    @Path("/getCatalizadores/episodio_id/{id_ep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalizadoresDeEpisodio(@PathParam("id_ep") Long id){
        List<CatalizadorDTO> resp = revEpService.getCatalizadoresDelEpisodio(id);
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(resp).build();
    }
    
    @GET
    @Path("/getSintomas/episodio_id/{id_ep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSintomasDeEpisodio(@PathParam("id_ep") Long id){
        List<SintomaDTO> resp = revEpService.getSintomasDelEpisodio(id);
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(resp).build();
    }
    // </DETALLES DEL EPISODIO>
    
    /** <episodios de un paciente>.¨*/
    @GET
    @Path("/getEpisodios/pacid={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpsByPacId(@PathParam("id") Long id) {
        List<EpisodioDolorDTO> eps = revEpService.getEpisodiosById(id);
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(eps).build();
    }
    /** </episodios de un paciente>.¨*/
    
    /** <episodios de un paciente en un rango de fechas>.¨*/
    @GET
    @Path("/getEpisodiosByFechas/{id}_{fechain}_{fechafin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpsByFechas(@PathParam("id") int noIdentificacion, @PathParam("fechain") String fechain, @PathParam("fechafin") String fechafin) throws ParseException, JSONException {
        Date fecha_in = new SimpleDateFormat("yyyy-MM-dd").parse(fechain);
        Date fecha_fin = new SimpleDateFormat("yyyy-MM-dd").parse(fechafin);
        List<EpisodioDolorDTO> eps;
        try {
            eps = revEpService.getEpisodioByFechas(fecha_in, fecha_fin, noIdentificacion);
        } catch (NoExisteException ex) {
            JSONObject msj = new JSONObject();
            msj.put("Error de sistema", ex.getMessage());
            return Response.status(500).header("Access-Allow-Control-Origin", "*").entity(msj).build();
        }
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(eps).build();
    }

    /** </episodios de un paciente en un rango de fechas>.¨*/
    @GET
    @Path("/getEpisodios2Dias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEps2Dias()
    {
        List<EpisodioDolorDTO> eps = revEpService.getEpisodios2Dias();
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(eps).build();
    }
    
    @GET
    @Path("/getCatalizadores/episodioid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalizadoresEpisodio(@PathParam("id") Long id){
        List<CatalizadorDTO> cat = revEpService.getCatalizadoresDelEpisodio(id);
        return Response.status(200).header("Access-Allow-Control-Origin","*").entity(cat).build();
    }
   
    /**
     * Retrieves representation of an instance of
     * mycompany.migrainetracking.services.RevisionEpisodiosService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of
     * RevisionEpisodiosService
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
