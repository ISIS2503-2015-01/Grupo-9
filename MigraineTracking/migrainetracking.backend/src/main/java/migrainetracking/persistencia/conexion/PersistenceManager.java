/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.conexion;

import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import migrainetracking.utils.Utils;

/**
 *
 * @author estudiante
 */
public class PersistenceManager {
    
    /**
     * 
     */
    public static final boolean DEBUG = true;
    
    /**
     * COnstante para manejar el componente singleton
     */
    private static final PersistenceManager singleton = new PersistenceManager();
    
    /**
     * Atributo que modela al entityManagerFactory para manejar la persistencia de las entidades
     */
    private EntityManagerFactory emf;
 
    /**
     * Metodo que retorna la instancia de la clase
     * @return la instancia de la clase
     */
    public static PersistenceManager getInstance() {
 
        return singleton;
    }
 
 
    /**
     * Metodo constructor de la clase sin atributos
     */
    private PersistenceManager() {
    }
 
    /**
     * Metodo que retorna el entityManagerFactore
     * @return el entityManagerFactory
     */
    public EntityManagerFactory getEntityManagerFactory() {
 
        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }
 
    /**
     * Meotod que se encarga de cerrar el entity manager
     */
    public void closeEntityManagerFactory() {
 
        if (emf != null) {
            emf.close();
            emf = null;
            if (DEBUG) {
                String msj = "Persistence finished at " + new java.util.Date();
                Utils.printf(msj);
            }
        }
    }
 
    /**
     * Metodo que crea un nuevo entity manager factory
     */
    protected void createEntityManagerFactory() {
        this.emf = Persistence.createEntityManagerFactory("migraineTrackingPU", System.getProperties());
        if (DEBUG) {
            String msj = "Persistence started at " + new java.util.Date();
            Utils.printf(msj);
        }
    }
}
