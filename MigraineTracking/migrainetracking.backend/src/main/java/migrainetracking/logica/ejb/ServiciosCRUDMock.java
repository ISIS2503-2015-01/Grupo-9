/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.ejb;

import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.DoctorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.IServiciosCRUDMockRemote;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaCatalizador;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaMedicamento;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaRegla;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaSintoma;
import migrainetracking.persistencia.mock.ServicioPersistenciaCatalizador;
import migrainetracking.persistencia.mock.ServicioPersistenciaMedicamento;
import migrainetracking.persistencia.mock.ServicioPersistenciaRegla;
import migrainetracking.persistencia.mock.ServicioPersistenciaSintoma;

/**
 *
 * Este bean se encarga de implementar los servicios CRUD de: Catalizador,Regla,Sintoma,Medicamento.
 */
@Stateless
public class ServiciosCRUDMock implements IServiciosCRUDMockRemote {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Atributo para manejar la instanciaciona
     */
    public static ServiciosCRUDMock instancia;
    
    /**
     * Atributo para manejar la persistencia de los catalizadores
     */
    IServicioPersistenciaCatalizador persistenciaCatalizadores;
    
    /**
     * Atributo para manejar la persistencia de los sintomas
     */
    IServicioPersistenciaSintoma persistenciaSintomas;
    
    /**
     * Atributo para manejar la persistncia de las reglas
     */
    IServicioPersistenciaRegla persistenciaReglas;
    
    /**
     * Atributo para manejar la persistencia de los medicamentos
     */
    IServicioPersistenciaMedicamento persistenciaMedicamentos;
    
    //---------------------------------------------------------------------------
    // COnstructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo constructor de la clase
     */
    public ServiciosCRUDMock()
    {
        this.persistenciaCatalizadores = ServicioPersistenciaCatalizador.getInstance();
        this.persistenciaSintomas = ServicioPersistenciaSintoma.getInstance();
        this.persistenciaReglas = ServicioPersistenciaRegla.getInstance();
        this.persistenciaMedicamentos = ServicioPersistenciaMedicamento.getInstance();
    }
    
    /**
     * Metodo que se encarga de retornar la instanciacion de la clase
     * @return la instanciacion de la clase
     */
    public static ServiciosCRUDMock getInstance()
    {
        boolean sinSingleton = true;
        if( instancia==null || sinSingleton ){
            instancia = new ServiciosCRUDMock();
        }
        return instancia;
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
    
    
    /**
     * Se encarga de crear los objetos que recibe como parametro
     * @param o el objeto a crear
     * @return el id del objeto creado
     * @throws OperacionInvalidaException si no se puede crear el objeto
     */
    @Override
    public Long create(Object o) throws OperacionInvalidaException {
        if(o instanceof CatalizadorDTO)
        {
            this.persistenciaCatalizadores.create(o);
            return ((CatalizadorDTO)o).getId();
        }
        else if(o instanceof MedicamentoDTO)
        {
            this.persistenciaMedicamentos.create(o);
            return ((MedicamentoDTO)o).getId();
        }
        else if(o instanceof ReglaDTO)
        {
            this.persistenciaReglas.create(o);
            return (long) ((ReglaDTO)o).getId();
        }
        else if(o instanceof SintomaDTO)
        {
            this.persistenciaSintomas.create(o);
            return ((SintomaDTO)o).getId();
        }
        else
            return (long) -1;
    }

    /**
     * Se encarga de eliminar los objetos que le llegan como parametro
     * @param o el objeto a eliminar
     * @return el id del objeto que se elimino
     */
    @Override
    public Long delete(Object o) throws OperacionInvalidaException {
        if(o instanceof CatalizadorDTO)
        {
            this.persistenciaCatalizadores.delete(o);
            return ((CatalizadorDTO)o).getId();
        }
        else if(o instanceof MedicamentoDTO)
        {
            this.persistenciaMedicamentos.delete(o);
            return ((MedicamentoDTO)o).getId();
        }
        else if(o instanceof ReglaDTO)
        {
            this.persistenciaReglas.delete(o);
            return (long) ((ReglaDTO)o).getId();
        }
        else if(o instanceof SintomaDTO)
        {
            this.persistenciaSintomas.delete(o);
            return ((SintomaDTO)o).getId();
        }
        else
            return (long) -1;
    }

    /**
     * Metodo que edita el objeto que recibe por parametro
     * @param o el objeto que se va a editar
     * @return el id del objeto que se edito
     */
    @Override
    public Long update(Object o) {
        if(o instanceof CatalizadorDTO)
        {
            this.persistenciaCatalizadores.update(o);
            return ((CatalizadorDTO)o).getId();
        }
        else if(o instanceof MedicamentoDTO)
        {
            this.persistenciaMedicamentos.update(o);
            return ((MedicamentoDTO)o).getId();
        }
        else if(o instanceof ReglaDTO)
        {
            this.persistenciaReglas.update(o);
            return (long) ((ReglaDTO)o).getId();
        }
        else if(o instanceof SintomaDTO)
        {
            this.persistenciaSintomas.update(o);
            return ((SintomaDTO)o).getId();
        }
        else
            return (long) -1;
    }

    /**
     * Retorna todos los elementos de la clase que se dio como parametro
     * @param clase la clase de la cual se quieren tener todos los objetos
     * @return la lista con todos los objetos de la clase que se paso por parametro
     */
    @Override
    public List<Object> getAll(Class clase) {
        if(CatalizadorDTO.class.equals(clase))
        {
            return persistenciaCatalizadores.findAll(CatalizadorDTO.class);
        }
        else if(MedicamentoDTO.class.equals(clase))
        {
            return persistenciaMedicamentos.findAll(MedicamentoDTO.class);
        }
        else if(ReglaDTO.class.equals(clase))
        {
            return persistenciaReglas.findAll(ReglaDTO.class);
        }
        else if(SintomaDTO.class.equals(clase))
        {
            return persistenciaSintomas.findAll(SintomaDTO.class);
        }
        else
            return null;
    }
}
