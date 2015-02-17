/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;


import java.util.List;
import java.util.*;
import javax.ejb.Stateless;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.excepciones.NoExisteException;

import migrainetracking.logica.interfaces.IServicioAnalisisMockRemote;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaCatalizador;


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
    IServicioPersistenciaCatalizador persistencia;
    
    //-----------------------------------------------------
    // Constructor
    //-----------------------------------------------------
    
    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    
    @Override
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio) throws NoExisteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EpisodioDolor> getEpisodiosPaciente(Long noIdPaciente) throws NoExisteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
