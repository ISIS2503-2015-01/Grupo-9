/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;


import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.NoExisteException;

import migrainetracking.logica.interfaces.IServicioAnalisisMockRemote;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.persistencia.mock.ServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.mock.ServicioPersistenciaPaciente;


/**
 *
 * Este bean se va a encargar de la logica relacionada con el analisis de las 
 * reglas y catalizadores propuestos por los doctores, con el fin de sugerirle 
 * al paciente que comidas/habitos/ debe evitar.
 * 
 */
@Stateless
public class ServicioAnalisisMock implements IServicioAnalisisMockRemote {

    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * Atributo para manejar la instanciacion de la clase
     */
    public static ServicioAnalisisMock instancia;
    
    /**
     * Persistencia para los catalizadores
     */
    IServicioPersistenciaEpisodioDolor persistencia;
    
    //-----------------------------------------------------
    // Constructor
    //-----------------------------------------------------
    
    /**
     * Metodo constructor
     */
    public ServicioAnalisisMock()
    {
        persistencia = ServicioPersistenciaEpisodioDolor.getInstance();
    }
    
    /**
     * Metodo para obtener la instancia de la clase
     * @return la instancia de la clase
     */
    public static ServicioAnalisisMock getInstance()
    {
        if(instancia==null || true)
        {
            instancia = new ServicioAnalisisMock();
        }
        return instancia;
    }
    
    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    
    /**
     * Metodo para obtener los catalizadores dado un episodio
     * @param episodio el episodio del cual se quiere obtener los catalizadores
     * @return los catalizadores del episodio
     * @throws NoExisteException si el episodio no existe
     */
    @Override
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio) throws NoExisteException {
        
        Object episo = persistencia.findById(EpisodioDolor.class, episodio);
        EpisodioDolor ep = (EpisodioDolor) episo;
        if(ep==null)
        {
           throw new NoExisteException ("El episodio del cual se quieren los catalizadores no existe");
        }
        return episodio.getCatalizadores();
    }

    /**
     * Metodo para obtener los episodios de un paciente dado su id
     * @param noIdPaciente el id del paciente el cual se quiere obtener los episodios
     * @return los episodios del paciente, cuyo id se dio como parametro
     * @throws NoExisteException si el paciente con el id dado como parametro no existe
     */
    @Override
    public List<EpisodioDolor> getEpisodiosPaciente(Long noIdPaciente) throws NoExisteException {
        IServicioPersistenciaPaciente pac = ServicioPersistenciaPaciente.getInstance();
        int noId = noIdPaciente.intValue();
        Object p = pac.findById(Paciente.class, noId);
        if(p==null)
        {
            throw new NoExisteException("El paciente del cual quiere obtener episodios no existe");
        }
        return pac.getEpisodiosByPaciente(noId);
    }
}
