/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.Entities.Medicamento;
import migrainetracking.persistencia.converters.MedicamentoConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaMedicamento;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaMedicamento extends PersistenceServiceMaster implements IServicioPersistenciaMedicamento {
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------

    /**
     * Atributo para modelar la instanciacion de la clase
     */
    public static ServicioPersistenciaMedicamento instancia;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    /**
     * Metodo constructor de la clase sin atributos
     */
    public ServicioPersistenciaMedicamento() {
        super();
    }

    /**
     * metodo que se encarga de la instanciacion de la clase
     *
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaMedicamento getInstance() {

        instancia = new ServicioPersistenciaMedicamento();
        return instancia;

    }

    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------
    /**
     * Metodo que se encarga de crear un nuevo medicamento
     *
     * @param obj el medicamento a crear
     * @throws OperacionInvalidaException si no se puede crear el medicamento
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        MedicamentoDTO nuevo = (MedicamentoDTO) obj;
        if(findById(MedicamentoDTO.class, nuevo)!=null)
        {
            throw new OperacionInvalidaException("No se puede crear el medicamento porque ya existe en el sistema");
        }
        EntityTransaction tran = this.entityMgr.getTransaction();
        Medicamento med = MedicamentoConverter.dtoToEntity(nuevo);
        try
        {
            tran.begin();
            this.entityMgr.persist(med);
            tran.commit();
            this.entityMgr.refresh(med);
            Utils.printf("Se ha creado un medicamento nuevo");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de editar un medicamento
     *
     * @param obj el medicamento a editar
     */
    @Override
    public void update(Object obj) {
        MedicamentoDTO existe = (MedicamentoDTO) obj;
        Medicamento med=MedicamentoConverter.dtoToEntity(existe);
        EntityTransaction tran=this.entityMgr.getTransaction();
        try
        {
            tran.begin();
            this.entityMgr.merge(med);
            tran.commit();
            Utils.printf("El medicamento ha sido actualizado");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error: " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de eliminar un medicamento
     *
     * @param obj el medicamento a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el medicamento
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        MedicamentoDTO borrar = (MedicamentoDTO) obj;
        if(findById(MedicamentoDTO.class, obj)==null)
        {
            throw new OperacionInvalidaException("El medicamento no se puede eliminar porque no existe en el sistema");
        }
        EntityTransaction tran = this.entityMgr.getTransaction();
        Medicamento med = MedicamentoConverter.dtoToEntity(borrar);
        try
        {
            tran.begin();
            this.entityMgr.remove(med);
            tran.commit();
            this.entityMgr.refresh(med);
            Utils.printf("El medicamento ha sido eliminado");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error al eliminar el medicamento: " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de retornar a todos los medicamentos
     *
     * @param c la clase a la que pertenecen los elementos que se quieren
     * retornar
     * @return la lista con los medicamentos
     */
    @Override
    public List findAll(Class c) {
        Query q = this.entityMgr.createQuery("SELECT m FROM MEDICAMENTO m");
        List<Medicamento> medicamentos = q.getResultList();
        List<MedicamentoDTO> medicamentosDTO = new ArrayList<MedicamentoDTO>();
        for(int i=0;i<medicamentos.size();i++)
        {
            Medicamento acutal = medicamentos.get(i);
            MedicamentoDTO dto = MedicamentoConverter.entityToDto(acutal);
            medicamentosDTO.add(dto);
        }
        return medicamentosDTO;
    }

    /**
     * Metodo que retorna el medicamento con el id dado por parametro
     *
     * @param c la clase a la que pertence el objeto que se quiere buscar
     * @param id el id del medicamento
     * @return el medicamento con el id igual al que se dio por parametro
     */
    @Override
    public Object findById(Class c, Object id) {
        int idMed = Integer.parseInt(id.toString());
        Query q = this.entityMgr.createQuery("SELECT m FROM MEDICAMENTO m WHERE m.id:=param");
        q.setParameter("param", idMed);
        Medicamento sol;
        try
        {
            sol =(Medicamento)q.getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
        return MedicamentoConverter.entityToDto(sol);
    }   
}
