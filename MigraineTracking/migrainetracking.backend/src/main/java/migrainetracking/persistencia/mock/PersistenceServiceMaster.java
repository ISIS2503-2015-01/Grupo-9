/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import migrainetracking.persistencia.conexion.PersistenceManager;

/**
 *
 * @author estudiante
 */
public class PersistenceServiceMaster {
    
    @PersistenceContext(unitName="migraineTrackingPU")
    protected EntityManager em;
    
    public PersistenceServiceMaster(){
        try{
            this.em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }
        catch(Exception e){}
    }
    
}
