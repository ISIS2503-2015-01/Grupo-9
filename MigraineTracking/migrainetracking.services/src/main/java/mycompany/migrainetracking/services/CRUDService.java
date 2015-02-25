/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mycompany.migrainetracking.services;

import java.util.List;
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
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.ejb.ServiciosCRUDMock;
import migrainetracking.logica.interfaces.IServiciosCRUDMockRemote;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Personal
 */

// Servicio que ofrece las funcionalidades crud sobre un conjunto de comoponenetes de la app.
@Path("/crud")
public class CRUDService {

    @Context
    private UriInfo context;

    @EJB
    private IServiciosCRUDMockRemote beanCRUD;
    
    /**
     * Creates a new instance of CUDServices
     */
    public CRUDService() {
        beanCRUD = ServiciosCRUDMock.getInstance();
    }
    //Pruebas no prioridad (desempeño)
    @POST
    @Path("/create/Sintoma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSintoma(SintomaDTO s) throws JSONException
    {
        JSONObject resp = new JSONObject();
        try
        {   
            Long id = beanCRUD.create(s);
            resp.put("Sintoma ID", id);
        }
        catch(OperacionInvalidaException ex)
        {
            resp.put("Operacion invalida exception",ex.getMessage());
        }
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    //Pruebas no prioridad (desempeño)
    @POST
    @Path("/create/Catalizador")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCatalizador(CatalizadorDTO c) throws JSONException
    {
        JSONObject resp = new JSONObject();
        try
        {
            Long id = beanCRUD.create(c);
            resp.put("Catalizador ID", id);
        }
        catch(OperacionInvalidaException ex)
        {
            resp.put("Operacion invalida exception",ex.getMessage());
        }
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    //Pruebas no prioridad (desempeño)
    @POST
    @Path("/create/Medicamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicamento(MedicamentoDTO m) throws JSONException
    {
        JSONObject resp = new JSONObject();
        try
        {
            Long id = beanCRUD.create(m);
            resp.put("Medicamento ID", id);
        }
        catch(OperacionInvalidaException ex)
        {
            resp.put("Operacion invalida exception",ex.getMessage());
        }
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    //Pruebas no prioridad (desempeño)
    @POST
    @Path("/create/Regla")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRegla(ReglaDTO r) throws JSONException
    {
        JSONObject resp = new JSONObject();
        try
        {   
            Long id = beanCRUD.create(r);
            resp.put("Regla ID", id);
        }
        catch(OperacionInvalidaException ex)
        {
            resp.put("Operacion invalida exception",ex.getMessage());
        }
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    
    @POST
    @Path("/delete/Sintoma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSintoma(SintomaDTO s) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.delete(s);
            rta.put("Sintoma", id);
        } catch (OperacionInvalidaException ex) {
            rta.put("Excepcion:",ex.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/delete/Catalizador")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCatalizador(CatalizadorDTO c) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.delete(c);
            rta.put("Catalizador", id);
        } catch (OperacionInvalidaException ex) {
            rta.put("Excepcion:",ex.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/delete/Medicamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMedicamento(MedicamentoDTO m) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.delete(m);
            rta.put("Medicamento", id);
        } catch (OperacionInvalidaException ex) {
            rta.put("Excepcion:",ex.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/delete/Regla")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRegla(ReglaDTO r) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.delete(r);
            rta.put("Regla", id);
        } catch (OperacionInvalidaException ex) {
            rta.put("Excepcion:",ex.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/update/Sintoma")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSintoma(SintomaDTO s) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.update(s);
            rta.append("Sintoma", id);
        } catch (Exception e) {
            rta.append("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    //Pruebas no prioridad (desempeño)
    @POST
    @Path("/update/Catalizador")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCatalizador(CatalizadorDTO c) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.update(c);
            rta.append("Catalizador", id);
        } catch (Exception e) {
            rta.append("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/update/Medicamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicamento(MedicamentoDTO m) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.update(m);
            rta.append("Medicamento", id);
        } catch (Exception e) {
            rta.append("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    //Pruebas no prioridad (desempeño)
    @POST
    @Path("/update/Regla")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRegla(ReglaDTO r) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = beanCRUD.update(r);
            rta.append("Regla", id);
        } catch (Exception e) {
            rta.append("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    //Pruebas prioridad (desempeño y escalabilidad)
    @GET
    @Path("/getAll/Sintomas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSintomas(){
        List sintomas;
        sintomas = beanCRUD.getAll(SintomaDTO.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(sintomas).build();
    }
    //Pruebas prioridad (desempeño y escalabilidad)
    @GET
    @Path("/getAll/Catalizadores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCatalizadores(){
        List catalizadores;
        catalizadores = beanCRUD.getAll(CatalizadorDTO.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(catalizadores).build();
    }
    //Pruebas prioridad (desempeño y escalabilidad)
    @GET
    @Path("/getAll/Medicamentos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicamentos(){
        List medicamentos;
        medicamentos = beanCRUD.getAll(MedicamentoDTO.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(medicamentos).build();
    }
    //Pruebas no prioridad (desempeño)
    @GET
    @Path("/getAll/Reglas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReglas(){
        List reglas;
        reglas = beanCRUD.getAll(ReglaDTO.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reglas).build();
    }
    
    /**
     * Retrieves representation of an instance of mycompany.migrainetracking.services.CUDServices
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CUDServices
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
