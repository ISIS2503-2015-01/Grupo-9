/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.interfaces;

import java.util.List;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;

/**
 *
 * @author Personal
 */
public interface IServicioPersistenciaRegla extends IServicioPersistenciaMockRemote{
    
    /**
     * 
     * @param episodio - Es el objeto del episodio de dolor.
     * @return Los evitables del episodio segun las reglas definidas por los doctores.
     */
    public List<Catalizador> getEvitables(EpisodioDolor episodio);
    
}
