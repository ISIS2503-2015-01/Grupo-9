/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Local;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;

/**
 *
 * @author estudiante
 */
@Local
public interface ServicioAnalisisMockLocal {
    /**
     * Devuelve una lista de catalizadores posibles, asociados con un episodio de dolor.
     * @return 
     */
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio);

}
