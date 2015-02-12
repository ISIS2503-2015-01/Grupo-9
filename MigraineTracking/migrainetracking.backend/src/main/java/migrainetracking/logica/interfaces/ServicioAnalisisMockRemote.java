/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;

/**
 *
 * @author Personal
 */
@Remote
public interface ServicioAnalisisMockRemote {
    /**
     * Devuelve una lista de catalizadores posibles, asociados con un episodio de dolor.
     * @return 
     */
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio);
    
}
