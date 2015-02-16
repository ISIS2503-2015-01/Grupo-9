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

    private static ServicioPersistenciaPaciente instancia;
    private List<Paciente> pacientes;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    public ServicioPersistenciaPaciente() {
        if (this.pacientes == null) {
            this.pacientes = new ArrayList<Paciente>();
            int numData = 20;
            for (int i = 0; i < numData; i++) {
                DataFactory df = new DataFactory();
                String nomb = df.getFirstName() +" "+ df.getLastName() ;
                int ced = df.getNumberBetween(80000000, 110000000);
                Date fechNac = df.getBirthDate();
                Paciente temp = new Paciente();
                temp.setNombre(nomb);
                temp.setNoIdentificacion(ced);
                temp.setFechaNacimiento(fechNac);
                this.pacientes.add(temp);
            }
        }
    }

    public static ServicioPersistenciaPaciente getInstance() {
        if (instancia == null) {
            return new ServicioPersistenciaPaciente();
        } else {
            return instancia;
        }
    }
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

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

    @Override
    public List findAll(Class c) {
        return this.pacientes;
    }

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

    @Override
    public List<EpisodioDolor> getEpisodiosByPaciente(int noId) {
        Paciente p = (Paciente) findById(Paciente.class, noId);
        assert p==null:"No se cumplio la precondicion del metodo. Revise implementacion web";
        return p.getEpisodios();
    }

    
}
