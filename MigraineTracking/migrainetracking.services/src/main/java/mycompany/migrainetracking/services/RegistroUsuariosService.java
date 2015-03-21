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
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.DoctorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.ejb.ServicioRegistroUsuariosMock;
import migrainetracking.logica.interfaces.IServicioRegistroUsuariosMockRemote;
import migrainetracking.persistencia.Entities.Doctor;
import migrainetracking.persistencia.Entities.Paciente;
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
    public Response createDoctor(DoctorDTO doctorDTO) throws JSONException {
        JSONObject resp = new JSONObject();
        try {
            Long id = userRegService.crearUsuario(doctorDTO);
            resp.put("DoctorID", id);
        } catch (OperacionInvalidaException ex) {
            resp.put("Operacion invalida exception",ex.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(resp).build();
        } 
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    
    @POST
    @Path("/create/Paciente/docid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPaciente(PacienteDTO pacienteDTO,@PathParam("id") Long docid) throws JSONException{
        JSONObject resp = new JSONObject();
        try {
            Long id = userRegService.crearUsuario(pacienteDTO);
            //acoplar el paciente al doctorr...
            
            resp.put("PacienteID", id);
        } catch (OperacionInvalidaException ex) {
            resp.put("Operacion invalida exception",ex.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(resp).build();
        } 
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    
    @POST
    @Path("/delete/Doctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDoctor(DoctorDTO docDto) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.eliminarUsuario(docDto);
            rta.put("DoctorID", id);
        } catch (Exception e) {
            rta.put("Excepcion:",e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(rta).build();
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
     @POST
    @Path("/delete/Paciente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePaciente(PacienteDTO pacDto) throws JSONException{
        
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.eliminarUsuario(pacDto);
            rta.put("pacienteID", id);
        } catch (Exception e) {
            rta.put("Excepcion:",e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(rta).build();
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/update/Paciente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePaciente(PacienteDTO pacDto) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.actualizarUsuario(pacDto);
            rta.append("pacienteID", id);
        } catch (Exception e) {
            rta.append("Excepcion:",e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(rta).build();
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("/update/Doctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(DoctorDTO docDto) throws JSONException{
        JSONObject rta = new JSONObject();
        try {
            Long id = userRegService.actualizarUsuario(docDto);
            rta.put("DoctorID", id);
        } catch (Exception e) {
            rta.put("Excepcion:",e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity(rta).build();
        }
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @GET
    @Path("/getAll/Doctores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors(){
        List<DoctorDTO> docs;
        docs = userRegService.getUsuarios(DoctorDTO.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(docs).build();
    }
    
    @GET
    @Path("/getAll/Pacientes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPacients(){
        List<PacienteDTO> pacs;
        pacs = userRegService.getUsuarios(PacienteDTO.class);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacs).build();
    }
    
    
    @GET
    @Path("/getById/Doctor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") Long id){
        DoctorDTO doc;
        doc = (DoctorDTO) userRegService.getUsuarioById(DoctorDTO.class,id);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(doc).build();
    }
    
    @GET
    @Path("/getById/Paciente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacienteById(@PathParam("id") Long id){
        PacienteDTO pacs;
        pacs = (PacienteDTO) userRegService.getUsuarioById(Paciente.class, id);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacs).build();
    }
    
    @GET
    @Path("/getPacientes/doctorid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacientesDelDoctor(@PathParam("id") Long docid){
        List<PacienteDTO> pacs = userRegService.getPacientesDeDoctor(docid);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(pacs).build();
    }
    @GET
    @Path("/getMedicamentosDiarios/pacienteid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicamentosDiarios(@PathParam("id") Long pacid){
        List<MedicamentoDTO> resp = userRegService.getMedicamentosDiariosPaciente(pacid);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }
    @GET
    @Path("/getHabitos/pacienteid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHabitos(@PathParam("id") Long pacid){
        List<CatalizadorDTO> resp = userRegService.getHabitosPaciente(pacid);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
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
