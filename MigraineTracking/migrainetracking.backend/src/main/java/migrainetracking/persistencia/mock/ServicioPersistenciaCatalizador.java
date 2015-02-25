/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaCatalizador;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaCatalizador implements IServicioPersistenciaCatalizador{
    
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * La lista que contiene a los catalizadores
     */
    private List<CatalizadorDTO> catalizadores;
    
    /**
     * 
     */
    public static ServicioPersistenciaCatalizador instancia;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    public ServicioPersistenciaCatalizador()
    {
        if(catalizadores==null)
        {
            catalizadores = new ArrayList();
        }
    }
    
    public static ServicioPersistenciaCatalizador getInstance()
    {
        if(instancia==null || true)
            return new ServicioPersistenciaCatalizador();
        else
            return instancia;
    }
    
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        CatalizadorDTO nuevo = (CatalizadorDTO) obj;
        if(findById(CatalizadorDTO.class, nuevo.getId())==null)
        {
            catalizadores.add(nuevo);
            Utils.printf("New catalizador(" + nuevo.getEspecificacion()+ ") was added");
        }
        else
        {
            throw new OperacionInvalidaException("El catalizador que se quiere agregar ya existe.");
        }
    }

    @Override
    public void update(Object obj) {
        CatalizadorDTO update = (CatalizadorDTO) obj;
        for(int i=0;i<catalizadores.size();i++)
        {
            CatalizadorDTO actual = catalizadores.get(i);
            if(actual.getId()==update.getId())
            {
                catalizadores.set(i, update);
                Utils.printf("El catalizador " + update.getEspecificacion() + " se ha actualizado" );
                break;
            }
        }
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        CatalizadorDTO borrar = (CatalizadorDTO) obj;
        if(findById(CatalizadorDTO.class,borrar.getId())!=null)
        {
            catalizadores.remove(borrar);
            Utils.printf("Se ha eliminado el catalizador " + borrar.getEspecificacion());
        }
        else
        {
            throw new OperacionInvalidaException("El catalizador " + borrar.getEspecificacion() + " no se puede borrar, porque no existe en el sistema");
        }
    }

    @Override
    public List findAll(Class c) {
        return this.catalizadores;
    }

    @Override
    public Object findById(Class c, Object id) {
        long idNo = Long.parseLong(id.toString());
        for(CatalizadorDTO cat: catalizadores)
        {
            if(cat.getId()==idNo)
            {
                return cat;
            }
        }
        return null;
    }
    
    
    
}
