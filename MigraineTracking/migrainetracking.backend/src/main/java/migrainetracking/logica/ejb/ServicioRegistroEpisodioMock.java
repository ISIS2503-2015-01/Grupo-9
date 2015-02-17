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
     * 
     */
    public ServicioRegistroEpisodioMock() {
        persistencia = ServicioPersistenciaEpisodioDolor.getInstance();
    }

    public static ServicioRegistroEpisodioMock getInstance(){
        if( instancia==null || true ){
            instancia = new ServicioRegistroEpisodioMock();
        }
        return instancia;
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
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

    @Override
    public Long editarEpisodio(EpisodioDolor editado, int noIdPaciente) {
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;
        persistencia.update(editado);
        Long respId = pacPersServ.actualizarEpsiodio(editado, noIdPaciente);
        assert respId==null : "No se cumplo la pre condicion.";
        return respId;
    }

    @Override
    public List<EpisodioDolor> getEpisodios() {
        IServicioPersistenciaEpisodioDolor epPUServ = ServicioPersistenciaEpisodioDolor.getInstance();
        return epPUServ.findAll(EpisodioDolor.class);
    }

    @Override
    public List<EpisodioDolor> getEpisodiosPorPaciente(int noIdPaciente) {
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;;
        return pacPersServ.getEpisodiosByPaciente(noIdPaciente);
    }
}
