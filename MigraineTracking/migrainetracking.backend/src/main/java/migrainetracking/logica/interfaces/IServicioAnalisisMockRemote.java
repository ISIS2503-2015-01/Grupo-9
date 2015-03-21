/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.excepciones.NoExisteException;

/**
 *
 * @author Personal
 */
@Remote
public interface IServicioAnalisisMockRemote {
    /**
     * Devuelve una lista de catalizadores posibles, asociados con un episodio de dolor.
     * @param id - id number del episodio.
     * @return La lista de los catalizadores del episodio
     * @throws NoExisteException si el episodio de dolor no existe
     */
    public List<String> getAcciones(Long id) throws NoExisteException;
}
