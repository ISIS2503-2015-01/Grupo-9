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
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Medicamento;
import migrainetracking.dto.Paciente;
import migrainetracking.dto.Sintoma;
import migrainetracking.logica.interfaces.IServicioRevisionEpisodiosMockRemote;
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
     * Metodo para retornar la instancia de la clase
     * @return la instancia de la clase
     */
    public static ServicioRevisionEpisodiosMock getInstance()
    {
        if(instancia==null)
        {
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
    public List<EpisodioDolor> getEpisodiosById(Long id) {
        IServicioPersistenciaPaciente pac = ServicioRegistroUsuariosMock.getInstance().persistenciaPaciente;
        return pac.getEpisodiosByPaciente(id.intValue());
    }

    /**
     * Metodo que retorna los episodios de un paciente, dado una fecha de inicio, de fin y el id del paciente
     * @param fecha_in fecha de inicio de los episodios
     * @param fecha_fin fecha de fin de los episodios
     * @param noId el numero de id del paciente
     * @return los episodios del paciente entre las fechas establecidas
     */
    public List<EpisodioDolor> getEpisodioByFechas(Date fecha_in, Date fecha_fin, int noId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que retorna los sintomas de un episodio dado su id
     * @param id el id del episodio
     * @return los sintomas del episodio cuyo numero de id se dio como parametro
     */
    @Override
    public List<Sintoma> getSintomasDelEpisodio(Long id) {
        EpisodioDolor e = (EpisodioDolor) persistencia.findById(EpisodioDolor.class, id);
        return e.getSintomas();
    }

    /**
     * Metodo que retorna los catalizadores de un episodio dado su id
     * @param id el id del episodio
     * @return la lista de los catalizadores del episodio
     */
    @Override
    public List<Catalizador> getCatalizadoresDelEpisodio(Long id) {
        EpisodioDolor e = (EpisodioDolor) persistencia.findById(EpisodioDolor.class, id);
        return e.getCatalizadores();
    }

    /**
     * Metodo que retorna los medicamentos de un episodio dado su id
     * @param id el id del episodio
     * @return los medicamentos del episodio
     */
    @Override
    public List<Medicamento> getMedicamentosDelEpisodio(Long id) {
        EpisodioDolor e = (EpisodioDolor) persistencia.findById(EpisodioDolor.class, id);
        return e.getMedicamentosActuales();
    }

    /**
     * Retorna los episodios que se generaron entre las fechas dadas como parametro del paciente cuyo id se dio como parametro
     * @param fecha_in la fecha de inicio 
     * @param fecha_fin la fecha de fin
     * @param id el id del paciente
     * @return los episodios que se generarion entre las fechas establecidas del paciente cuyo id se dio por parametro
     */
    @Override
    public List<EpisodioDolor> getEpisodioByFechas(Date fecha_in, Date fecha_fin, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
