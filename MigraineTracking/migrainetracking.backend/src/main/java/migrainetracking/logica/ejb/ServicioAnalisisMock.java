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
