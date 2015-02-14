/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;

/**
 *
 * @author Personal
 */
@Remote
public interface IServicioRegistroEpisodioMockRemote extends IServiciosCRUDMockRemote{
    
     /**
     * Registra un nuevo episodioDeDolor
     * @param nuevo El episodio de dolor que se quiere registrar
     * @param noIdPaciente El numero de identificacion del Paciente al que se quiere asociar el episodio de dolor
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     */
    public void registrarEpisodio( EpisodioDolor nuevo, int noIdPaciente ) throws OperacionInvalidaException;
    
    /**
     * Elimina un episodio de dolor
     * @param idEpisodio El id del episodio de dolor 
     * @param noIdPaciente el numero de identificacion del paciente
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     * @throws NoExisteException en caso de que no exista el episodio con ese id 
     *         o un paciente con ese numero de identificacion
     */
    public void eliminarEpisodio(int idEpisodio, int noIdPaciente) throws OperacionInvalidaException, NoExisteException;
    
    /**
     * Edita un episodio de dolor
     * @param editado el episodio de dolor que se quiere editar
     * @param noIdPaciente el numero de identificacion del paciente
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     * @throws NoExisteException en caso de que no exista el episodio con ese id 
     *         o un paciente con ese numero de identificacion
     */
    public void editarEpisodio(EpisodioDolor editado, int noIdPaciente) throws NoExisteException,OperacionInvalidaException;
    
    /**
     * Devuelve todos los episodios del sistema
     * @return la lista de episodios
     */
    public List<EpisodioDolor> getEpisodios( );
    
    
    /**
     * Devuelve todos los episodios de un paciente
     * @param  noIdPaciente el numero de identificacion de un paciente
     * @return la lista de episodios
     */
    public List<EpisodioDolor> getEpisodiosPorPaciente(int noIdPaciente);
    
}
