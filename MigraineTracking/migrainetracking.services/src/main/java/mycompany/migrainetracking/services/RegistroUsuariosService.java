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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import migrainetracking.dto.Doctor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.ejb.ServicioRegistroUsuariosMock;
import migrainetracking.logica.interfaces.IServicioRegistroUsuariosMockRemote;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;




/**
 * REST Web Service
 *
 * @author Personal
 */
@Path("/registrousuarios")
public class RegistroUsuariosService {

    @Context
    private UriInfo context;
    @EJB
    private IServicioRegistroUsuariosMockRemote userRegService;

    /**
     * Creates a new instance of RegistroUsuariosService
     */
    public RegistroUsuariosService() {
        userRegService = ServicioRegistroUsuariosMock.getInstance();//new ServicioRegistroUsuariosMock();
    }

    @POST
    @Path("/create/Doctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctorDTO) throws JSONException {
        JSONObject resp = new JSONObject();
        try {
            Long id = userRegService.crearUsuario(doctorDTO);
            resp.put("DoctorID", id);
        } catch (OperacionInvalidaException ex) {
            resp.put("Operacion invalida exception",ex.getMessage());
        } 
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    
    @POST
    @Path("/create/Paciente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPaciente(Paciente pacienteDTO) throws JSONException{
        JSONObject resp = new JSONObject();
        try {
            Long id = userRegService.crearUsuario(pacienteDTO);
            resp.put("PacienteID", id);
        } catch (OperacionInvalidaException ex) {
            resp.put("Operacion invalida exception",ex.getMessage());
        } 
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    
    @POST
    @Path("/delete/Doctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDoctor(Doctor docDto) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.eliminarUsuario(docDto);
            rta.put("DoctorID", id);
        } catch (Exception e) {
            rta.put("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
     @POST
    @Path("/delete/Paciente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePaciente(Paciente pacDto) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.eliminarUsuario(pacDto);
            rta.put("pacienteID", id);
        } catch (Exception e) {
            rta.put("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/update/Paciente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePaciente(Paciente pacDto) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.actualizarUsuario(pacDto);
            rta.append("pacienteID", id);
        } catch (Exception e) {
            rta.append("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/update/Doctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(Doctor docDto) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.actualizarUsuario(docDto);
            rta.put("DoctorID", id);
        } catch (Exception e) {
            rta.put("Excepcion:",e.getMessage());
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @GET
    @Path("/getAll/Doctores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors(){
        List<Doctor> docs;
        docs = userRegService.getUsuarios(Doctor.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(docs).build();
    }
    
    @GET
    @Path("/getAll/Pacientes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPacients(){
        List<Paciente> pacs;
        pacs = userRegService.getUsuarios(Paciente.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacs).build();
    }
    
    /**
     * Retrieves representation of an instance of mycompany.migrainetracking.services.RegistroUsuariosService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
       return "Estamos arriba";
    }

    /**
     * PUT method for updating or creating an instance of RegistroUsuariosService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
