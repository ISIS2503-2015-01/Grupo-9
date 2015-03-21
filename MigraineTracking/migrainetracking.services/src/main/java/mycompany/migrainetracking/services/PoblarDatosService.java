/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mycompany.migrainetracking.services;

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
import migrainetracking.logica.ejb.ServicioAnalisisMock;
import migrainetracking.poblarBDS.IPoblarBDService;
import migrainetracking.logica.interfaces.IServicioAnalisisMockRemote;
import migrainetracking.poblarBDS.poblarBasesDatos;

/**
 * REST Web Service
 *
 * @author Personal
 */
@Path("poblarBD")
public class PoblarDatosService {

    @Context
    private UriInfo context;
    
    @EJB
    private IServicioAnalisisMockRemote beanAnalisis;
    
    @EJB
    private IPoblarBDService beanPobBD;
    
    /**
     * Creates a new instance of analisis
     */
    public PoblarDatosService() {
        beanAnalisis = ServicioAnalisisMock.getInstance();
        beanPobBD = new poblarBasesDatos();
    }
    
    /**
     * 
     */
    @Path("/todos")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String poblarDB(){
        try {
            beanPobBD.poblarTodo();
        } catch (Exception e) {
            return "No se poblo!";
        }
        return "La BD fue poblada con exito";
    }

    /**
     * Retrieves representation of an instance of mycompany.migrainetracking.services.analisis
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson()  {
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of analisis
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
