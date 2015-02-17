/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.Medicamento;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaMedicamento;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaMedicamento  implements IServicioPersistenciaMedicamento{
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * La lista de los medicamentos en el sistema
     */
    private List<Medicamento> medicamentos;
    
    /**
     * Atributo para modelar la instanciacion de la clase
     */
    public static ServicioPersistenciaMedicamento instancia;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
    /**
     *Metodo constructor de la clase 
     */
    public ServicioPersistenciaMedicamento()
    {
        if(medicamentos==null)
        {
            medicamentos = new ArrayList<Medicamento>();
        }
    }
    
    /**
     *  
     */
    public static ServicioPersistenciaMedicamento getInstance()
    {
        if(instancia!=null)
        {
            return instancia;
        }
        else
        {
            instancia = new ServicioPersistenciaMedicamento();
            return instancia;
        }
    }
    
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        Medicamento nuevo = (Medicamento) obj;
        
        for(Medicamento med: medicamentos)
        {
            if(med.getNombre().equals(nuevo.getNombre()))
            {
                throw new OperacionInvalidaException("El medicamento " + nuevo.getNombre() + " ya existe en el sistema");
            }
        }
        medicamentos.add(nuevo);
    }

    @Override
    public void update(Object obj) {
        Medicamento actualizar = (Medicamento) obj;
        
        for(int i=0;i<medicamentos.size();i++)
        {
            Medicamento actual = medicamentos.get(i);
            if(actual.getNombre().equals(actualizar.getNombre()))
            {
                medicamentos.set(i,actualizar);
                Utils.printf("El medicameno " + actual.getNombre() + " ha sido actualizado");
                break;
            }
        }
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        Medicamento borrar = (Medicamento) obj;
        
        if(findById(Medicamento.class, borrar.getNombre())!=null)
        {
            this.medicamentos.remove(borrar);
            Utils.printf("El medicamento " + borrar.getNombre() + " ha sido eliiminado");
        }
        else
        {
            throw new OperacionInvalidaException("No se pudo eliminar el medicamento " + borrar.getNombre());
        }
    }

    @Override
    public List findAll(Class c) {
        return this.medicamentos;
    }

    @Override
    public Object findById(Class c, Object id) {
        String nombre = id.toString();
        for(int i=0;i<medicamentos.size();i++)
        {
            Medicamento actual = medicamentos.get(i);
            if(actual.getNombre().equals(nombre))
            {
                return actual;
            }
        }
        return null;
    }
}
