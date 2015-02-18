/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.utils.Utils;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaPaciente implements IServicioPersistenciaPaciente {
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------

    /**
     * Atruto para manejar la instanciacion de la clase
     */
    private static ServicioPersistenciaPaciente instancia;
    
    /**
     * Lista de pacientes en el sistema
     */
    private List<Paciente> pacientes;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
    /**
     * Constructor sin argumentos
     */
    public ServicioPersistenciaPaciente() {
        if (this.pacientes == null) {
            this.pacientes = new ArrayList<Paciente>();
            /* Datos pruebas funcionalidades*/
//            int numData = 20;
//            for (int i = 0; i < numData; i++) {
//                DataFactory df = new DataFactory();
//                String nomb = df.getFirstName() +" "+ df.getLastName() ;
//                int ced = df.getNumberBetween(80000000, 110000000);
//                Date fechNac = df.getBirthDate();
//                Paciente temp = new Paciente(nomb, ced, fechNac);
//                this.pacientes.add(temp);
//            }
            /*Datos deterministicos prueba carga (A mano)*/
        }
    }

    /**
     * Metodo que se encarga de la instanciacion de la clase
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaPaciente getInstance() {
        if (instancia == null || true) {
            return new ServicioPersistenciaPaciente();
        } else {
            return instancia;
        }
    }
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * Metodo que se encarga de crear a un paciente
     * @param obj el paciente que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el paciente
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        Paciente newPac = (Paciente) obj;
        if (findById(Paciente.class, newPac.getNoIdentificacion()) == null) {
            pacientes.add(newPac);
            Utils.printf("New paciente(" + newPac.getNombre() + ") was ADDED");
        } else {
            throw new OperacionInvalidaException("El paciente que quiere agregar ya existe en el sistema");
        }
    }

    /**
     * Metodo que se encarga de editar el paciente que se da por parametro
     * @param obj el paciente que se va a editar
     */
    @Override
    public void update(Object obj) {
        Paciente toEdit = (Paciente) obj;
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente tempPac = pacientes.get(i);
            if (toEdit.equals(tempPac)) {
                pacientes.set(i, toEdit);
                Utils.printf("New paciente(" + tempPac.getNombre() + ") was UPDATED");
                break;
            }
        }
    }

    /**
     * Metodo que se encarga de eliminar el paciente que se da por parametro
     * @param obj el paciente que se quiere eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el paciente
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        Paciente oldPac = (Paciente) obj;
        if (findById(Paciente.class, oldPac.getNoIdentificacion()) != null) {
            pacientes.remove(oldPac);
            Utils.printf("Paciente(" + oldPac.getNombre() + ")was DELETED");
        } else {
            throw new OperacionInvalidaException("El paciente que quiere eliminar no existe en el sistema");
        }
    }

    /**
     * Metodo que se encarga de retornar a todos los paciente
     * @param c la clase a la que pertenecen los elementos que se quieren retornar
     * @return todos los pacientes
     */
    @Override
    public List findAll(Class c) {
        return this.pacientes;
    }

    /**
     * Metodo que retorna al paciente que tiene el id dado por parametro
     * @param c la clase a la que pertence el elemento que se va a retornar
     * @param id el id del paciente
     * @return el paciente con el id dado por parameto
     */
    @Override
    public Object findById(Class c, Object id) {
        int noId = Integer.parseInt(id.toString());
        for (Paciente paciente : pacientes) {
            if (paciente.getNoIdentificacion() == noId) {
                return paciente;
            }
        }
        return null;
    }

    //----- find all hace la misma monda.
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * metodo que se encarga de agregarle un episodio al paciente
     * @param nuevo el nuevo episodio
     * @param noIdPaciente el id del paciente al cual se le va a agregar el episodio
     * @return el id del paciente al cual se le agrego el episodio. null en caso que no se halla agregado
     */
    @Override
    public Long agregarEpsiodio(EpisodioDolor nuevo, int noIdPaciente) {
        Paciente p = (Paciente) findById(Paciente.class, noIdPaciente);
        if (p != null) {
            p.getEpisodios().add(nuevo);
            return (long) noIdPaciente;
        } else {
            return null;
        }
    }

    /**
     * Metodo que se encarga de eliminar un episodio de un paciente
     * @param viejo el episodio que se va a eliminar
     * @param noIdPaciente el id del paciene dueno del episodio
     * @return el id del paciente al cual se le elimino el episodio
     */
    @Override
    public Long eliminarEpisodio(EpisodioDolor viejo, int noIdPaciente) {
        Paciente p = (Paciente) findById(Paciente.class, noIdPaciente);
        if (p != null) {
            p.getEpisodios().remove(viejo);
            return (long) noIdPaciente;
        } else {
            return null;
        }
    }

    /**
     * Metodo que se encarga de actualizar un episodio 
     * @param toEdit el episodio que se va a editar
     * @param noIdPaciente el id del paciente dueno del episodio
     * @return el id del paciente al cual se le edito el episodio
     */
    @Override
    public Long actualizarEpsiodio(EpisodioDolor toEdit, int noIdPaciente) {
        Paciente p = (Paciente) findById(Paciente.class, noIdPaciente);
        if (p != null) {
            List<EpisodioDolor> eps = p.getEpisodios();
            for (int i = 0; i < eps.size(); i++) {
                EpisodioDolor tempEp = eps.get(i);
                if (toEdit.equals(tempEp)) {
                    eps.set(i, tempEp);
                    return tempEp.getId();
                }
            }
        }
        
        return null;
    }

    /**
     *Metodo que se encarga de retornar los episodios de un paciente dado su id 
     * @param noId el id del paciente
     * @return los episodios del paciente 
     */
    @Override
    public List<EpisodioDolor> getEpisodiosByPaciente(int noId) {
        Paciente p = (Paciente) findById(Paciente.class, noId);
        assert p==null:"No se cumplio la precondicion del metodo. Revise implementacion web";
        return p.getEpisodios();
    }
}
