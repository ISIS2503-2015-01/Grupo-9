/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.poblarBDS;

import javax.ejb.Remote;

/**
 *
 * @author estudiante
 */
@Remote
public interface IPoblarBDService {

    /**
     * Pobla la base de datos
     * @throws java.lang.Exception
     */
    public void poblarTodo() throws Exception;
}
