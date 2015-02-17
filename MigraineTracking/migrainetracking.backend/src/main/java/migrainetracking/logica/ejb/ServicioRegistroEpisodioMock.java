/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.IServicioRegistroEpisodioMockRemote;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.persistencia.mock.ServicioPersistenciaEpisodioDolor;

/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioRegistroEpisodioMock implements IServicioRegistroEpisodioMockRemote {
    
    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Atributo para manejar la persistencia
     */
    IServicioPersistenciaEpisodioDolor persistencia;

    /**
     * Atributo para manejar la instaciacion
     */
    private static ServicioRegistroEpisodioMock instancia;
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     *Metodo constructor 
     */
    public ServicioRegistroEpisodioMock() {
        persistencia = ServicioPersistenciaEpisodioDolor.getInstance();
    }

    /**
     * Metodo para manejar la instaciacion de la clase
     * @return la instancia de la clase
     */
    public static ServicioRegistroEpisodioMock getInstance(){
        if( instancia==null || true ){
            instancia = new ServicioRegistroEpisodioMock();
        }
        return instancia;
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
    
    /**
     * Registra un nuevo episodio de dolor al paciente cuyo id se da por parametro
     * @param nuevo el episodio de dolor que se va a agregar
     * @param noIdPaciente el id del paciente al cual se le va a agregar el episodio de dolor
     * @return el id del paciente al cual se le agrego el episodio
     * @throws OperacionInvalidaException si no se puede registrar el episodio
     */
    @Override
    public Long registrarEpisodio(EpisodioDolor nuevo, int noIdPaciente) throws OperacionInvalidaException {
        
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;
        Long respId = pacPersServ.agregarEpsiodio(nuevo, noIdPaciente);
        
        if (respId == null) {
            throw new OperacionInvalidaException("No existe el paciente a quien se le quiere registrar el episodio.");
        }
        persistencia.create(nuevo);
        return respId;
    }

    /**
     * Se elimina un episodio de dolor dado el id del paciente
     * @param idEpisodio el id del episodio que se va a eliminar
     * @param noIdPaciente el id del paciente al cual se le va a eliminar el episodio
     * @return el id del paciente al cual se le elimino el episodio de dolor
     * @throws OperacionInvalidaException si no se puede eliminar el episodio
     * @throws NoExisteException si el paciente o el episodio no existen
     */
    @Override
    public Long eliminarEpisodio(int idEpisodio, int noIdPaciente) throws OperacionInvalidaException, NoExisteException {
        EpisodioDolor ep = (EpisodioDolor) persistencia.findById(EpisodioDolor.class, (long) idEpisodio);
        
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;
        Long respId = pacPersServ.eliminarEpisodio(ep, noIdPaciente);
        if (respId == null) {
            throw new OperacionInvalidaException("No existe el paciente a quien se le quiere registrar el episodio.");
        }
        try {
            persistencia.delete(ep);
        } catch (OperacionInvalidaException e) {
            throw new NoExisteException(e.getMessage());
        }
        return respId;
    }

    /**
     * Se edita un episodio de dolor dado como parametro al usuario cuyo id se da como parametro
     * @param editado el episodio a editar
     * @param noIdPaciente el id del paciente al cual se le va a editar el episodio
     * @return el id del paciente al cual se le edito el episodio
     */
    @Override
    public Long editarEpisodio(EpisodioDolor editado, int noIdPaciente) {
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;
        persistencia.update(editado);
        Long respId = pacPersServ.actualizarEpsiodio(editado, noIdPaciente);
        assert respId==null : "No se cumplo la pre condicion.";
        return respId;
    }

    /**
     * Retorna los epidosios del sistema
     * @return una lista con todos los episodios de dolor
     */
    @Override
    public List<EpisodioDolor> getEpisodios() {
        IServicioPersistenciaEpisodioDolor epPUServ = ServicioPersistenciaEpisodioDolor.getInstance();
        return epPUServ.findAll(EpisodioDolor.class);
    }

    /**
     * Retorna los episodios de dolor de un paciente cuyo id es dado como parametro
     * @param noIdPaciente el id del paciente del cual se quiere obtener los episodios
     * @return los episodios del paciente cuyo id se dio como parametro
     */
    @Override
    public List<EpisodioDolor> getEpisodiosPorPaciente(int noIdPaciente) {
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;;
        return pacPersServ.getEpisodiosByPaciente(noIdPaciente);
    }
}
