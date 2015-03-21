/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;


import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.logica.interfaces.IServicioAnalisisMockRemote;
import migrainetracking.persistencia.Entities.EpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaCatalizador;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaRegla;
import migrainetracking.persistencia.mock.ServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.mock.ServicioPersistenciaRegla;


/**
 *
 * Este bean se va a encargar de la logica relacionada con el analisis de las 
 * reglas y catalizadores propuestos por los doctores, con el fin de sugerirle 
 * al paciente que comidas/habitos/ debe evitar.
 * 
 */
@Stateless
public class ServicioAnalisisMock implements IServicioAnalisisMockRemote {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Atributo para manejar la instaciacion de la clase
     */
    public static ServicioAnalisisMock instancia;
    
    /**
     * persistencia para los episodios de las reglas
     */
    IServicioPersistenciaRegla persistenciaReglas;

    /**
     * Persistnecia de los episodios de dolor
     */
    IServicioPersistenciaEpisodioDolor persistenciaEpisodios;
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public ServicioAnalisisMock()
    {
       this.persistenciaEpisodios = ServicioPersistenciaEpisodioDolor.getInstance();
       this.persistenciaReglas = ServicioPersistenciaRegla.getInstance();
    }
    
    /**
     *Metodo para retornar la instancia de la clase
     * @return la instancia de la clase
     */
    public static ServicioAnalisisMock getInstance(){
        boolean sinSingleton = true;
        if(instancia == null || sinSingleton){
            instancia = new ServicioAnalisisMock();
        }
        return instancia;
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
    
    /**
     * Metodo que retorna los catalizadores
     * @param id el id del episodio de dolor del cual se quieren los catalizadores
     * @return Los catalizadores del episodio
     * @throws NoExisteException si el episodio no existe
     */
    @Override
    public List<String> getAcciones(Long id) throws NoExisteException {
       EpisodioDolorDTO ep = (EpisodioDolorDTO) persistenciaEpisodios.findById(EpisodioDolorDTO.class, id);
       if( ep ==null){
           throw new NoExisteException("El episodio ese no existe en el sistema");
       }
       return persistenciaReglas.getAcciones(ep);
    }
}
