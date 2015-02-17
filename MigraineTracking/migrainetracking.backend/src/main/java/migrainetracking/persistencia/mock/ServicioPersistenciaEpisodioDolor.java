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
    
    private static ServicioPersistenciaEpisodioDolor instancia;
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    private List<EpisodioDolor> episodios;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
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
    
    public static ServicioPersistenciaEpisodioDolor getInstance(){
        if(instancia==null || true){
            instancia = new ServicioPersistenciaEpisodioDolor();
        }
        return instancia;
    }
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
       EpisodioDolor ep = (EpisodioDolor) obj;
           this.episodios.add(ep);
           Utils.printf("EpisodioDolor("+ep.getId()+") was created");
       
    }

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

    @Override
    public List findAll(Class c) {
        return this.episodios;
    }

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
