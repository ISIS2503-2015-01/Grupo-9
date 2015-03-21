/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;

/**
 *
 * @author Personal
 */
@Remote
public interface IServicioRegistroEpisodioMockRemote {
    
     /**
     * Registra un nuevo episodioDeDolor
     * @param nuevo El episodio de dolor que se quiere registrar
     * @param noIdPaciente El numero de identificacion del Paciente al que se quiere asociar el episodio de dolor
     * @return el id del episodio que se registro
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     */
    public Long registrarEpisodio( EpisodioDolorDTO nuevo, int noIdPaciente ) throws OperacionInvalidaException;
    
    /**
     * Elimina un episodio de dolor
     * @param idEpisodio El id del episodio de dolor 
     * @param noIdPaciente el numero de identificacion del paciente
     * @return el id del episodio que se elimino 
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     * @throws NoExisteException en caso de que no exista el episodio con ese id 
     *         o un paciente con ese numero de identificacion
     */
    public Long eliminarEpisodio(int idEpisodio, int noIdPaciente) throws OperacionInvalidaException, NoExisteException;
    
    /**
     * Edita un episodio de dolor
     * <b>pre:</b> El episodio y el paciente que se van a actualizar ya existe
     * @param editado el episodio de dolor que se quiere editar
     * @param noIdPaciente el numero de identificacion del paciente
     * @return el id del episodio editado 
     */
    public Long editarEpisodio(EpisodioDolorDTO editado, int noIdPaciente);
    
    /**
     * Devuelve todos los episodios del sistema
     * @return la lista de episodios
     */
    public List<EpisodioDolorDTO> getEpisodios( );
    
    
    /**
     * Devuelve todos los episodios de un paciente
     * <b>pre:</b> El paciente ya existe en el sistema.
     * @param  noIdPaciente el numero de identificacion de un paciente
     * @return la lista de episodios
     */
    public List<EpisodioDolorDTO> getEpisodiosPorPaciente(int noIdPaciente);

    public EpisodioDolorDTO getEpisodioById(Long idEp);
}
