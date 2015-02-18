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
import migrainetracking.dto.EpisodioDolor;
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

    @GET
    @Path("/getEpisodios/pacid={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpsByPacId(@PathParam("id") Long id) {
        List<EpisodioDolor> eps = revEpService.getEpisodiosById(id);
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(eps).build();
    }
    //Pruebas prioridad (desempeño y escalabilidad) a nivel de Doctor, probar que funcione
    @GET
    @Path("/getEpisodiosByFechas/pacid={id}&fechas=[{fechain},{fechafin}]")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpsByFechas(@PathParam("id") int noIdentificacion, @PathParam("fechain") String fechain, @PathParam("fechafin") String fechafin) throws ParseException, JSONException {
        Date fecha_in = new SimpleDateFormat("yyyy-MM-dd").parse(fechain);
        Date fecha_fin = new SimpleDateFormat("yyyy-MM-dd").parse(fechafin);
        List<EpisodioDolor> eps;
        try {
            eps = revEpService.getEpisodioByFechas(fecha_in, fecha_fin, noIdentificacion);
        } catch (NoExisteException ex) {
            JSONObject msj = new JSONObject();
            msj.put("Error de sistema", ex.getMessage());
            return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(msj).build();
        }
        return Response.status(200).header("Access-Allow-Control-Origin", "*").entity(eps).build();
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
