/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.utils.Utils;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaEpisodioDolor implements IServicioPersistenciaEpisodioDolor{
    
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * Atributo que contiene los episodios del sistema
     */
    private List<EpisodioDolor> episodios;
    
    /**
     * Atributo para manejar la instanciacion de la clase
     */
    private static ServicioPersistenciaEpisodioDolor instancia;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public ServicioPersistenciaEpisodioDolor(){
        if(this.episodios==null){
            episodios = new ArrayList<EpisodioDolor>();         
            /*Dataos pruebas funcionalidades*/
//            int numData = 30;
//            String[] loc = {"Alrededor del ojo","Mitad derecha de la cabeza","Mitad izquierda de la cabeza","Frente del ojo","Mejilla","Nariz","Encias"};
//            Integer[] intens = {1,2,3,4,5};
//            Random rand = new Random();
//            for(int i = 0 ; i < numData ; i++){
//                DataFactory df = new DataFactory();
//                EpisodioDolor ep = new EpisodioDolor();
//                ep.setId( (long)i );
//                Date endDate = new java.util.Date();
//                ep.setFecha( df.getDateBetween( new Date( endDate.getTime() - 60*24*3600*1000 )  , endDate ) ) ;
//                ep.setIntensidadDolor( df.getItem(intens) );
//                ep.setLocalizacion( df.getItem(loc) );
//                
//                List<Paciente> pacientes = ServicioPersistenciaPaciente.getInstance().getPacientes();
//                Paciente pac = pacientes.get( df.getNumberBetween( 0, pacientes.size()-1 ) );
//                pac.getEpisodios().add( ep );
//                
//                this.episodios.add(ep);
//            }
              /*Datos pruebas de carga (A mano y poquitos) [rampup = 1 seg && loopcount = 1] */ 
        }
    }
    
    /**
     * Metodo que se encarga de retornar la instancia de la clase
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaEpisodioDolor getInstance(){
        if(instancia==null || true){
            instancia = new ServicioPersistenciaEpisodioDolor();
        }
        return instancia;
    }
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * Metodo que se encarga de crear un episodio de dolor
     * @param obj el episodio que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el episodio
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
       EpisodioDolor ep = (EpisodioDolor) obj;
           this.episodios.add(ep);
           Utils.printf("EpisodioDolor("+ep.getId()+") was created");
       
    }

    /**
     * Metodo que se encarga de editar a un episodio
     * @param obj el episodio a editar 
     */
    @Override
    public void update(Object obj) {
        EpisodioDolor toEdit = (EpisodioDolor) obj;
        for(int i = 0 ; i < episodios.size() ; i++){
            EpisodioDolor tempEp = episodios.get(i);
            if( toEdit.equals( tempEp ) ){
                episodios.set(i,toEdit);
                break;
            }
        }
    }

    /**
     * Metodo que se encarga de eliminar el episodio que se da por parametro
     * @param obj el episodio a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el episodio
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
       EpisodioDolor ep = (EpisodioDolor) obj;
       if ( findById( EpisodioDolor.class ,  ep ) != null ) {
           this.episodios.remove(ep);
           Utils.printf("EpisodioDolor("+ep.getId()+") was deleted");
       }else{
           throw new OperacionInvalidaException("El EpisodioDolor("+ep.getId()+") no existe en el sistema");
       }
    }

    /**
     * Metodo que se encarga de retornar todos los episodios en el sistema
     * @param c la clase a la que pertencen los elementos que se quieren buscar
     * @return todos los episodios
     */
    @Override
    public List findAll(Class c) {
        return this.episodios;
    }

    /**
     * Metodo que se encarga de retornar un episodio dado su id
     * @param c la clase a la cual pertence el elemento que se quiere retornar
     * @param id el id del objeto que se quiere retornar
     * @return el episodio cuyo id es igual al id dado por parametro
     */
    @Override
    public Object findById(Class c, Object id) {
        Long nId = Long.parseLong(id.toString());
        for(EpisodioDolor ep : this.episodios){
            if( ep.getId().equals(nId) ){
                return ep;
            }
        }
        return null;
    }
}