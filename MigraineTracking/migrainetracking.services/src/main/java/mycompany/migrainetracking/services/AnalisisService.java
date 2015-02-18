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
import migrainetracking.logica.ejb.ServicioAnalisisMock;
import migrainetracking.logica.interfaces.IServicioAnalisisMockRemote;

/**
 * REST Web Service
 *
 * @author Personal
 */
@Path("analisis")
public class AnalisisService {

    @Context
    private UriInfo context;
    
    @EJB
    private IServicioAnalisisMockRemote beanAnalisis;
    
    /**
     * Creates a new instance of analisis
     */
    public AnalisisService() {
        beanAnalisis = ServicioAnalisisMock.getInstance();
    }
    
    /**
     * 
     */

    /**
     * Retrieves representation of an instance of mycompany.migrainetracking.services.analisis
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
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
