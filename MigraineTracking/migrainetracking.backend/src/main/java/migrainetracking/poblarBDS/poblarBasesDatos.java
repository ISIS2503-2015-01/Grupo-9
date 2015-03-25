/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.poblarBDS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import migrainetracking.persistencia.Entities.Catalizador;
import migrainetracking.persistencia.Entities.Doctor;
import migrainetracking.persistencia.Entities.EpisodioDolor;
import migrainetracking.persistencia.Entities.Medicamento;
import migrainetracking.persistencia.Entities.Paciente;
import migrainetracking.persistencia.Entities.Regla;
import migrainetracking.persistencia.Entities.Sintoma;
import migrainetracking.persistencia.mock.PersistenceServiceMaster;
import migrainetracking.utils.Utils;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author estudiante
 */
@Stateful
public class poblarBasesDatos extends PersistenceServiceMaster implements IPoblarBDService {

    private final DataFactory df;
    private final EntityManager em;
    
    private final static int NUMPACS = 100;
    private final static int NUMCAT = 50;
    private final static int NUMSINT = 25;
    private final static int NUMMED = 25;
    private final static int NUMEPS = 300;
    private final static int NUMDOCS = 10;
    private final static int NUMREG = 25;

    public poblarBasesDatos() {
        df = new DataFactory();
        em = this.entityMgr;
    }

    //Metodo que pobla toda la base de datos.
    @Override
    public void poblarTodo() throws Exception {
        try {

            EntityTransaction tran;
            tran = entityMgr.getTransaction();
            tran.begin();
 

            poblarSintomas();
            tran.commit();

            tran.begin();
            poblarCatalizadores();
            tran.commit();

            tran.begin();
            poblarReglas();
            tran.commit();

            tran.begin();
            poblarMedicamentos();
            tran.commit();

            poblarPacientes();
            actualizarRelacionesPacientes();

            poblarEpisodios();
            actualizarRelacionesEpisodios();

            poblarDoctores();
            actualizarRelacionesDoctores();
            
            Utils.printf("Se poblo exitosamente");

        } catch (Exception e) {
            entityMgr.getTransaction().rollback();
            System.out.println("Hubo un error en la carga de datos");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     *
     */
    private void actualizarRelacionesPacientes() {

        Integer[] maxRows = {2, 3, 4};
        Query q;
        q = entityMgr.createQuery("SELECT c FROM Catalizador c WHERE c.tipo = 'habito'");
        List<Catalizador> habitos = q.getResultList();
        Collections.shuffle(habitos);

        q = entityMgr.createQuery("Select m FROM Medicamento m ");
        List<Medicamento> meds = q.getResultList();
        Collections.shuffle(meds);

        q = entityMgr.createQuery("SELECT p from Paciente p");
        List<Paciente> pacs = q.getResultList();

        for (Paciente p : pacs) {

            int tamHab = habitos.size();
            int r1 = df.getNumberBetween(0, tamHab - 5);
            int r2 = r1 + df.getItem(maxRows, 100);

            entityMgr.getTransaction().begin();

            List<Catalizador> catas = habitos.subList(r1, r2);
            for (int i = 0; i < catas.size(); i++) {
                Catalizador hab = catas.get(i);
                p.getHabitos().add(hab);
            }
            
            r1 = df.getNumberBetween(0, meds.size() - 5);
            r2 = r1 + df.getItem(maxRows, 100);
            
            for (Medicamento m : meds.subList(r1, r2)) {
                p.getMedicamentosDiarios().add(m);
            }

            entityMgr.merge(p);
            entityMgr.getTransaction().commit();
        }
    }

    /**
     *
     */
    private void poblarPacientes() {

        Integer[] maxRows = {2, 3, 4};
        String localizaciones[] = {"frente", "cabeza", "cuello", "ojos", "oidos"};

        EntityTransaction tran = entityMgr.getTransaction();

        for (int i = 1; i <= NUMPACS; i++) {
            
            tran.begin();

            String name = df.getFirstName() + " " + df.getLastName();
            int id = 10000000 +(int)( Math.random()*( (999999999-10000000)+1) );
            Date fecha = df.getBirthDate();
            int peso = df.getNumberBetween(40, 100);
            int estatura = df.getNumberBetween(140, 200);
            String contrasenia=df.getRandomText(10); // pa prox esto se cifra..
            Paciente p = new Paciente();
            p.setNoIdentificacion(id);
            p.setNombre(name);
            p.setEstatura(estatura);
            p.setPeso(peso);
            p.setFechaNacimiento(fecha);
            p.setContrasenia(contrasenia);
            entityMgr.persist(p);
            tran.commit();
            Utils.printf("Pacientes : "+i);
            
        }

    }

    /**
     *
     */
    private void poblarMedicamentos() {
        String nombres5[] = {"ibuprofeno", "corticoide", "acetaminofen", "eutirox", "hidroclorotiazida"};
        for (int r = 0; r < NUMMED; r++) {
            String nombre5 = df.getItem(nombres5, 100);
            int cantidad5 = df.getNumberBetween(1, 10);
            int intervalo5 = df.getNumberBetween(1, 48);
            int miligramos5 = df.getNumberBetween(1, 1000);
            Date fechaR5 = df.getDateBetween(df.getDate(2014, 5, 1), df.getDate(2015, 5, 1));
            Medicamento m = new Medicamento();
            m.setNombre(nombre5);
            m.setCantidadVecesAlDia(cantidad5);
            m.setIntervaloHoras(intervalo5);
            m.setMiligramos(miligramos5);
            m.setFechaRecetado(fechaR5);
            entityMgr.persist(m);
        }
    }

    /**
     *
     */
    private void actualizarRelacionesEpisodios() {
        Query q;
        Integer[] maxRows = {2, 3, 4};
        int r1, r2;

        q = entityMgr.createQuery("SELECT c FROM Catalizador c WHERE c.tipo!='habito' ");
        List<Catalizador> cats = q.getResultList();
        Collections.shuffle(cats);

        q = entityMgr.createQuery("SELECT s FROM Sintoma s ");
        List<Sintoma> sints = q.getResultList();
        Collections.shuffle(sints);

        q = entityMgr.createQuery("Select m FROM Medicamento m ");
        List<Medicamento> meds = q.getResultList();
        Collections.shuffle(meds);

        q = entityMgr.createQuery("SELECT e FROM EpisodioDolor e");

        List<EpisodioDolor> eps = q.getResultList();
        for (EpisodioDolor e : eps) {
            em.getTransaction().begin();

            r1 = df.getNumberBetween(0, cats.size() - 5);
            r2 = r1 + df.getItem(maxRows, 100);
            e.getCatalizadores().addAll(cats.subList(r1, r2));

            r1 = df.getNumberBetween(0, sints.size() - 5);
            r2 = r1 + df.getItem(maxRows, 100);
            e.getSintomas().addAll(sints.subList(r1, r2));

            r1 = df.getNumberBetween(0, meds.size() - 5);
            r2 = r1 + df.getItem(maxRows, 100);
            e.getMedicamentosActuales().addAll(meds.subList(r1, r2));

            em.merge(e);
            em.getTransaction().commit();
        }
    }

    /**
     *
     */
    private void poblarEpisodios() {

        Integer[] maxRows = {2, 3, 4};
        Query q;
        int r1 ;
        int r2 ;

        q = entityMgr.createQuery("SELECT p from Paciente p");
        List<Paciente> pacs = q.getResultList();

        String localizaciones[] = {"frente", "cabeza", "cuello", "ojos", "oidos"};

        for (int j = 0; j < NUMEPS; j++) {
            em.getTransaction().begin();
            String localizacion = df.getItem(localizaciones, 100);
            int intensidad = df.getNumberBetween(1, 10);
            int horas = df.getNumberBetween(0, 24);

            EpisodioDolor e = new EpisodioDolor();
            e.setFecha(df.getDateBetween(df.getDate(2014, 11, 1), df.getDate(2015, 9, 1)));
            e.setIntensidadDolor(intensidad);
            e.setHorasDeSueño(horas);
            e.setLocalizacion(localizacion);

            em.persist(e);

            Paciente p = pacs.get(df.getNumberBetween(0, pacs.size() - 1));
            e.setPaciente(p);
            p.getEpisodios().add(e);

            em.merge(p);
            em.getTransaction().commit();
            Utils.printf(" Episodios : " + j);
        }
    }

    
    /**
     *
     * Poblada de doctores
     */
    private void poblarDoctores() {

        String values[] = {"Neurologia", "Pediatria", "Medicina General", "Medicina Interna", "Acupuntura"};
        for (int i = 1; i <= NUMDOCS; i++) {
            em.getTransaction().begin();
            String name = df.getFirstName() + " " + df.getLastName();
            int id = Integer.parseInt(df.getNumberText(8));
            Date fecha = df.getDateBetween(df.getDate(1930, 1, 1), df.getDate(2015, 1, 1));
            String especialidad = df.getItem(values, 100);
            String contrasenia=df.getRandomText(10); // pa prox esto se cifra..
            Doctor d = new Doctor();
            d.setNombre(name);
            d.setEspecialidad(especialidad);
            d.setFechaNacimiento(fecha);
            d.setNoIdentificacion(id);
            d.setContrasenia(contrasenia);
            em.persist(d);
            em.getTransaction().commit();
            Utils.printf("Doctores : "+i);
        }

    }

    
    /**
     * 
     */
    private void actualizarRelacionesDoctores() {
        
        Query q;
        q = this.entityMgr.createQuery("SELECT p FROM Paciente p");
        List<Paciente> pacs = q.getResultList();
        
        q = this.em.createQuery("SELECT d FROM Doctor d");
        List<Doctor> docs = q.getResultList();
        
        for( Paciente p : pacs ){
            em.getTransaction().begin();
            Doctor d = docs.get( df.getNumberBetween(0, docs.size()-1));
            d.getPacientes().add( p );
            em.merge(d);
            em.getTransaction().commit();
        }
    }
    
    
    /**
     *
     *
     */
    private void poblarCatalizadores() throws Exception {
        String tipos[] = {"alimento", "actividad fisica", "bebida", "habito"};

        for (int i = 0; i < NUMCAT; i++) {
            int frec = df.getNumberBetween(0, 4);
            String especificacion = df.getRandomText(60);

            Catalizador c = new Catalizador();
            c.setTipo(df.getItem(tipos, 100));
            c.setEspecificacion(especificacion);
            c.setFrecuencia(frec);

            entityMgr.persist(c);

        }

    }
    

    /**
     *
     */
    private void poblarSintomas() throws Exception {

        String localizaciones[] = {"frente", "cabeza", "cuello", "ojos", "oidos"};
        String nombres6[] = {"mareo", "nauseas", "escalofrios", "temblor", "zumbido"};

        for (int i = 0; i < NUMSINT; i++) {
            Sintoma s = new Sintoma();
            s.setNombre(df.getItem(nombres6, 100));
            s.setLocalizacion(df.getItem(localizaciones, 100));
            s.setIntensidad(df.getNumberBetween(1, 3));

            entityMgr.persist(s);

        }

    }
    

    /**
     *
     */
    private void poblarReglas() throws Exception {
        String localizaciones[] = {"frente", "cabeza", "cuello", "ojos", "oidos"};
        String[] acciones = {"No se rompa una pata", "Haga algo inteligente", "No haga nada", "Dirigase a la division de urgencias", "Ponga sus pies en agua", "Tomese una pastilla", "No haga activaidades fisicas", "No coma azucar"};
        for (int i = 0; i < NUMREG ; i++) {
            int intensidadMin = df.getNumberBetween(1, 10);
            int intensidadMax = df.getNumberBetween(intensidadMin, 10);
            Regla r = new Regla();
            r.setIntensidadDolorMin(intensidadMin);
            r.setIntensidadDolorMax(intensidadMax);
            r.setLocalizacionDolor(df.getItem(localizaciones, 100));
            r.setAcciones(df.getItem(acciones, 100));
            this.entityMgr.persist(r);
        }

    }

    @Deprecated
    public void poblarMAriaPaula() {
//        String values[] = {"Neurologia", "Pediatria", "Medicina General", "Medicina Interna", "Acupuntura"};
//        String nombres5[] = {"ibuprofeno", "corticoide", "acetaminofen", "eutirox", "hidroclorotiazida"};
//        String nombres6[] = {"mareo", "nauseas", "escalofrios", "temblor", "zumbido"};
//        String localizaciones[] = {"frente", "cabeza", "cuello", "ojos", "oidos"};
//
//        int idH = 1;
//        int idM = 1;
//        int idE = 1;
//        int idH6 = 1;
//        int idRg = 1;
//        //Lista Doctores
//        for (int i = 0; i < 300; i++) {
//            String name = df.getFirstName() + " " + df.getLastName();
//            int id = Integer.parseInt(df.getNumberText(8));
//            String fecha = df.getNumberBetween(1920, 2015) + "-" + df.getNumberBetween(1, 12) + "-" + df.getNumberBetween(1, 28);
//            String especialidad = df.getItem(values, 100);
//            
//
//            //Lista Pacientes
//            for (int j = 0; j < 5; j++) {
//                String name1 = df.getFirstName() + " " + df.getLastName();
//                int id1 = Integer.parseInt(df.getNumberText(8));
//                String fecha1 = df.getNumberBetween(1920, 2015) + "-" + df.getNumberBetween(1, 12) + "-" + df.getNumberBetween(1, 28);
//                int peso = df.getNumberBetween(40, 100);
//                int estatura = df.getNumberBetween(140, 200);
//
//                SystentityMgr.out.println("INSERT INTO APP.PACIENTE (NOIDENTIFICACION, ESTATURA, FECHANACIMIENTO, NOMBRE, PESO) VALUES (" + id1 + ", " + estatura + ", '" + fecha1 + "', '" + name1 + "', " + peso + ");");
//                SystentityMgr.out.println("INSERT INTO APP.DOCTOR_PACIENTE (DOCTOR_NOIDENTIFICACION, PACIENTES_NOIDENTIFICACION) VALUES (" + id + ", " + id1 + ");");
//
//                //Lista Medicamentos
//                for (int k = 0; k < 3; k++) {
//                    String nombres[] = {"ibuprofeno", "corticoide", "acetaminofen", "eutirox", "hidroclorotiazida"};
//
//                    String nombre = df.getItem(nombres, 100);
//                    int cantidad = df.getNumberBetween(1, 10);
//                    int intervalo = df.getNumberBetween(1, 48);
//                    int miligramos = df.getNumberBetween(1, 1000);
//                    String fechaR = df.getNumberBetween(2014, 2015) + "-" + df.getNumberBetween(1, 12) + "-" + df.getNumberBetween(1, 28);
//
//                    SystentityMgr.out.println("INSERT INTO APP.MEDICAMENTO (ID, CANTIDADVECESALDIA, FECHARECETADO, INTERVALOHORAS, MILIGRAMOS, NOMBRE) VALUES (" + idM + ", " + cantidad + ", '" + fechaR + "', " + intervalo + ", " + miligramos + ", '" + nombre + "');");
//                    idM++;
//                    SystentityMgr.out.println("INSERT INTO APP.PACIENTE_MEDICAMENTO (PACIENTE_NOIDENTIFICACION, MEDICAMENTOSDIARIOS_ID) VALUES (" + id1 + ", " + idM + ");");
//                }
//                //Lista Habitos
//                for (int m = 0; m < 3; m++) {
//                    int frecuencia = df.getNumberBetween(1, 10);
//                    String especificacion = df.getRandomText(60);
//                    SystentityMgr.out.println("INSERT INTO APP.CATALIZADOR (ID, ESPECIFICACION, FRECUENCIA, TIPO) VALUES (" + idH + ", '" + especificacion + "', " + frecuencia + ", 'habito');");
//                    SystentityMgr.out.println("INSERT INTO APP.PACIENTE_CATALIZADOR (PACIENTE_NOIDENTIFICACION, HABITOS_ID) VALUES (" + id1 + ", " + idH + ");");
//                    idH++;
//                }
//                //Lista Episodios
//                for (int n = 0; n < 5; n++) {
//
//                    String fecha4 = df.getNumberBetween(2014, 2015) + "-" + df.getNumberBetween(1, 12) + "-" + df.getNumberBetween(1, 28);
//                    String localizacion = df.getItem(localizaciones, 100);
//                    int intensidad = df.getNumberBetween(1, 10);
//                    int horas = df.getNumberBetween(0, 24);
//
//                    SystentityMgr.out.println("INSERT INTO APP.EPISODIODOLOR (ID, FECHA, \"HORASDESUEÑO\", INTENSIDADDOLOR, LOCALIZACION, PACIENTE_NOIDENTIFICACION) VALUES (" + idE + ", '" + fecha4 + "', " + horas + ", " + intensidad + ", '" + localizacion + "', " + id1 + ");");
//
//                    //Lista Catalizadores
//                    for (int p = 0; p < 3; p++) {
//                        String tipo1 = df.getItem(tipos, 100);
//                        int frecuencia1 = df.getNumberBetween(1, 10);
//                        String especificacion1 = df.getRandomText(60);
//
//                        SystentityMgr.out.println("INSERT INTO APP.CATALIZADOR (ID, ESPECIFICACION, FRECUENCIA, TIPO) VALUES (" + idH + ", '" + especificacion1 + "', " + frecuencia1 + ", '" + tipo1 + "');");
//                        SystentityMgr.out.println("INSERT INTO APP.EPISODIODOLOR_CATALIZADOR (EPISODIODOLOR_ID, CATALIZADORES_ID) VALUES (" + idE + ", " + idH + ");");
//
//                        int intensidadMax = df.getNumberBetween(1, 10);
//                        int intensidadMin = df.getNumberBetween(1, 10);
//                        String localizacionRg = df.getItem(localizaciones, 70);
//
//                        SystentityMgr.out.println("INSERT INTO APP.REGLA (ID, INTENSIDADDOLORMAX, INTENSIDADDOLORMIN, LOCALIZACIONDOLOR) VALUES (" + idRg + ", " + intensidadMax + ", " + intensidadMin + ", '" + localizacionRg + "');");
//                        SystentityMgr.out.println("INSERT INTO APP.REGLA_CATALIZADOR (REGLA_ID, EVITABLES_ID) VALUES (" + idRg + ", " + idH + ");");
//                        idRg++;
//                        idH++;
//
//                    }
//                    //Lista Medicamentos Actuales
//                    for (int r = 0; r < 2; r++) {
//                        String nombre5 = df.getItem(nombres5, 100);
//                        int cantidad5 = df.getNumberBetween(1, 10);
//                        int intervalo5 = df.getNumberBetween(1, 48);
//                        int miligramos5 = df.getNumberBetween(1, 1000);
//                        String fechaR5 = df.getNumberBetween(2014, 2015) + "-" + df.getNumberBetween(1, 12) + "-" + df.getNumberBetween(1, 28);
//
//                        SystentityMgr.out.println("INSERT INTO APP.MEDICAMENTO (ID, CANTIDADVECESALDIA, FECHARECETADO, INTERVALOHORAS, MILIGRAMOS, NOMBRE) VALUES (" + idM + ", " + cantidad5 + ", '" + fechaR5 + "', " + intervalo5 + ", " + miligramos5 + ", '" + nombre5 + "');");
//                        SystentityMgr.out.println("INSERT INTO APP.EPISODIODOLOR_MEDICAMENTO (EPISODIODOLOR_ID, MEDICAMENTOSACTUALES_ID) VALUES (" + idE + ", " + idM + ");");
//                        idM++;
//                    }
//                    //Lista Sintomas
//                    for (int t = 0; t < 3; t++) {
//                        String nombre6 = df.getItem(nombres6, 100);
//                        int intensidad6 = df.getNumberBetween(1, 10);
//                        String localizacion6 = df.getItem(localizaciones, 100);
//                        SystentityMgr.out.println("INSERT INTO APP.SINTOMA (ID, INTENSIDAD, LOCALIZACION, NOMBRE) VALUES (" + idH6 + ", " + intensidad6 + ", '" + localizacion6 + "', '" + nombre6 + "');");
//                        SystentityMgr.out.println("INSERT INTO APP.EPISODIODOLOR_SINTOMA (EPISODIODOLOR_ID, SINTOMAS_ID) VALUES (" + idE + ", " + idH6 + ");");
//                        idH6++;
//                    }
//                    idE++;
//                }
//
//            }
//
//        }
    }
    
}
