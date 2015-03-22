/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.interfaces;

import java.util.List;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.excepciones.NoExisteException;

/**
 *
 * @author Personal
 */
public interface IServicioPersistenciaDoctor extends IServicioPersistenciaMockRemote{
    
    /**
     * Lista de pacientes del doctor
     * @return 
     */
    public List<PacienteDTO> getDocsPacients(int noId) throws NoExisteException;
}
