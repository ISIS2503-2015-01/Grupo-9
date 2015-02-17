/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.IServicioRegistroEpisodioMockRemote;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.persistencia.mock.ServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.mock.ServicioPersistenciaPaciente;

/**
 *
 * @author estudiante
 */
@Stateless
public class SevicioRegistroEpisodioMock implements IServicioRegistroEpisodioMockRemote {

    public static IServicioRegistroEpisodioMockRemote getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    IServicioPersistenciaEpisodioDolor persistencia;

    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    public SevicioRegistroEpisodioMock() {
        persistencia = ServicioPersistenciaEpisodioDolor.getInstance();
    }

    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
    @Override
    public Long registrarEpisodio(EpisodioDolor nuevo, int noIdPaciente) throws OperacionInvalidaException {
        persistencia.create(nuevo);
        IServicioPersistenciaPaciente pacPersServ = ServicioPersistenciaPaciente.getInstance();
        Long respId = pacPersServ.agregarEpsiodio(nuevo, noIdPaciente);
        if (respId == null) {
            throw new OperacionInvalidaException("No existe el paciente a quien se le quiere registrar el episodio.");
        }
        return respId;
    }

    @Override
    public Long eliminarEpisodio(int idEpisodio, int noIdPaciente) throws OperacionInvalidaException, NoExisteException {
        EpisodioDolor ep = (EpisodioDolor) persistencia.findById(EpisodioDolor.class, (long) idEpisodio);
        try {
            persistencia.delete(ep);
        } catch (OperacionInvalidaException e) {
            throw new NoExisteException(e.getMessage());
        }
        
        IServicioPersistenciaPaciente pacPersServ = ServicioPersistenciaPaciente.getInstance();
        Long respId = pacPersServ.eliminarEpisodio(ep, noIdPaciente);
        if (respId == null) {
            throw new OperacionInvalidaException("No existe el paciente a quien se le quiere registrar el episodio.");
        }
        return respId;
    }

    @Override
    public Long editarEpisodio(EpisodioDolor editado, int noIdPaciente) {
        IServicioPersistenciaPaciente pacPersServ = ServicioPersistenciaPaciente.getInstance();
        Long respId = pacPersServ.actualizarEpsiodio(editado, noIdPaciente);
        assert respId==null : "No se cumplo la pre condicion.";
        return respId;
    }

    @Override
    public List<EpisodioDolor> getEpisodios() {
        IServicioPersistenciaPaciente pacPersServ = ServicioPersistenciaPaciente.getInstance();
        return pacPersServ.findAll(EpisodioDolor.class);
    }

    @Override
    public List<EpisodioDolor> getEpisodiosPorPaciente(int noIdPaciente) {
        IServicioPersistenciaPaciente pacPersServ = ServicioPersistenciaPaciente.getInstance();
        return pacPersServ.getEpisodiosByPaciente(noIdPaciente);
    }

}
