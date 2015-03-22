/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.logica.interfaces.IServicioRevisionEpisodiosMockRemote;
import migrainetracking.persistencia.Entities.EpisodioDolor;
import migrainetracking.persistencia.Entities.Sintoma;
import migrainetracking.persistencia.converters.CatalizadorConverter;
import migrainetracking.persistencia.converters.MedicamentoConverter;
import migrainetracking.persistencia.converters.SintomaConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.persistencia.mock.ServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.mock.ServicioPersistenciaPaciente;

/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioRevisionEpisodiosMock implements IServicioRevisionEpisodiosMockRemote {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Atributo para manejar la instaciacion de la clase
     */
    public static ServicioRevisionEpisodiosMock instancia;
    
    /**
     * persistencia para los episodios de dolor
     */
    IServicioPersistenciaEpisodioDolor persistencia;
    
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public ServicioRevisionEpisodiosMock()
    {
       this.persistencia = ServicioPersistenciaEpisodioDolor.getInstance();
    }
    

    /**
     * Metodo para retornar la instanciacion de la clase
     * @return la instancia de la clase
     */
    public static ServicioRevisionEpisodiosMock getInstance(){
        boolean sinSingleton = true;
        if(instancia == null || sinSingleton){
            instancia = new ServicioRevisionEpisodiosMock();
        }
        return instancia;
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------

    /**
     * Metodo que retorna los episodios de un paciente cuyo id se recibe por parametro
     * @param id el id del paciente del cual se quieren los episodios
     * @return la lista de episodios del paciente cuyo id se da por parametro
     */
    @Override
    public List<EpisodioDolorDTO> getEpisodiosById(Long id) {
        IServicioPersistenciaPaciente pacPersServ = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente ;
        return pacPersServ.getEpisodiosByPaciente( id.intValue() );
    }

    /**
     * Metodo que retorna los episodios de un paciente, dado una fecha de inicio, de fin y el id del paciente
     * @param fecha_in fecha de inicio de los episodios
     * @param fecha_fin fecha de fin de los episodios
     * @param noId el numero de id del paciente
     * @return los episodios del paciente entre las fechas establecidas
     * @throws migrainetracking.excepciones.NoExisteException
     */
    @Override
    public List<EpisodioDolorDTO> getEpisodioByFechas(Date fecha_in, Date fecha_fin, int noId) throws NoExisteException {
        IServicioPersistenciaPaciente pacServ = ServicioPersistenciaPaciente.getInstance();
        PacienteDTO pac = (PacienteDTO) pacServ.findById(PacienteDTO.class, noId);
        if( pac == null ){
            throw new NoExisteException("El paciente con ese numero de identificacion no existe");
        }
        return persistencia.getEpisodioByFechas( fecha_in, fecha_fin, noId );
    }

    // <----------------------------------------------------->
    /**
     * Metodo que retorna los sintomas de un episodio dado su id
     * @param id el id del episodio
     * @return los sintomas del episodio cuyo numero de id se dio como parametro
     */
    @Override
    public List<SintomaDTO> getSintomasDelEpisodio(Long id) {
        return persistencia.getSintomas(id);
    }

    /**
     * Metodo que retorna los catalizadores de un episodio dado su id
     * @param id el id del episodio
     * @return la lista de los catalizadores del episodio
     */
    @Override
    public List<CatalizadorDTO> getCatalizadoresDelEpisodio(Long id) {
        return persistencia.getCatalizadores(id);
    }

    /**
     * Metodo que retorna los medicamentos de un episodio dado su id
     * @param id el id del episodio
     * @return los medicamentos del episodio
     */
    @Override
    public List<MedicamentoDTO> getMedicamentosDelEpisodio(Long id) {
        return persistencia.getMedicamentos(id);
    }     
    
    /**
     * Metodo que retorna los episodios de dolor registrados en los ultimos dos dias
     * @return una lista con los episodios de dolor  
     */
    @Override
    public List<EpisodioDolorDTO> getEpisodios2Dias()
    {
        return persistencia.getEpisodios2Dias();
    }
}
