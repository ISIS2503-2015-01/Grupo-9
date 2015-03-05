/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.poblarBDS;


import java.util.Date;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author estudiante
 */
public class poblarBasesDatos {
private final DataFactory df;

    public static void main(String[] args) {
       poblarBasesDatos p = new poblarBasesDatos();
       p.poblarDoctores();
    }

    public poblarBasesDatos() {
        df = new DataFactory();
        
    }
		
    public void poblarPacientes(){
	for(int i =0;i<1500;i++){
            String name = df.getFirstName() +" "+df.getLastName();
            int id = Integer.parseInt(df.getNumberText(8));
            Date fecha = df.getBirthDate();
            int peso=df.getNumberBetween(40, 100);
            int estatura = df.getNumberBetween(140, 200);
            System.out.println(name + " "+ id + " "+fecha+" "+peso+" "+estatura);
	}
        
    }
    

    
    public void poblarDoctores(){
        String values[] = {"Neurologia","Pediatria","Medicina General", "Medicina Interna", "Acupuntura"};
        String tipos[] = {"alimento", "actividad fisica", "bebida", "habito"};
        String nombres5[] = {"ibuprofeno","corticoide","acetaminofen", "eutirox", "hidroclorotiazida"};
        String nombres6[] = {"mareo","nauseas","escalofrios", "temblor", "zumbido"};
        String localizaciones[] = {"frente","cabeza","cuello", "ojos", "oidos"};
        
        int idH = 1;
        int idM = 1;
        int idE = 1;
        int idH6 = 1;
        int idRg=1;
        //Lista Doctores
        for(int i =0;i<300;i++){
            String name = df.getFirstName() +" "+df.getLastName();
            int id = Integer.parseInt(df.getNumberText(8));
            String fecha = df.getNumberBetween(1920, 2015) + "-" + df.getNumberBetween(1, 12)+ "-" + df.getNumberBetween(1, 28);
            String especialidad = df.getItem(values, 100);
            System.out.println("INSERT INTO APP.DOCTOR (NOIDENTIFICACION, ESPECIALIDAD, FECHANACIMIENTO, NOMBRE) VALUES ("+id+", '"+especialidad+"', '"+fecha+"', '"+name+"');");
            
            //Lista Pacientes
            for( int j= 0; j<5;j++){
                String name1 = df.getFirstName() +" "+df.getLastName();
                int id1 = Integer.parseInt(df.getNumberText(8));
                String fecha1 = df.getNumberBetween(1920, 2015) + "-" + df.getNumberBetween(1, 12)+ "-" + df.getNumberBetween(1, 28);
                int peso=df.getNumberBetween(40, 100);
                int estatura = df.getNumberBetween(140, 200);
                
                System.out.println("INSERT INTO APP.PACIENTE (NOIDENTIFICACION, ESTATURA, FECHANACIMIENTO, NOMBRE, PESO) VALUES ("+id1+", "+estatura+", '"+fecha1+"', '"+name1+"', "+peso+");");
                System.out.println("INSERT INTO APP.DOCTOR_PACIENTE (DOCTOR_NOIDENTIFICACION, PACIENTES_NOIDENTIFICACION) VALUES ("+id+", "+id1+");");
                
                //Lista Medicamentos
                for( int k = 0; k<3;k++){
                    String nombres[] = {"ibuprofeno","corticoide","acetaminofen", "eutirox", "hidroclorotiazida"};
                    
                    String nombre = df.getItem(nombres, 100);
                    int cantidad = df.getNumberBetween(1, 10);
                    int intervalo = df.getNumberBetween(1, 48);
                    int miligramos = df.getNumberBetween(1, 1000);
                    String fechaR =df.getNumberBetween(2014, 2015) + "-" + df.getNumberBetween(1, 12)+ "-" + df.getNumberBetween(1, 28);
                    
                    System.out.println("INSERT INTO APP.MEDICAMENTO (ID, CANTIDADVECESALDIA, FECHARECETADO, INTERVALOHORAS, MILIGRAMOS, NOMBRE) VALUES ("+idM+", "+cantidad+", '"+fechaR+"', "+intervalo+", "+miligramos+", '"+nombre+"');");
                    idM++;
                    System.out.println("INSERT INTO APP.PACIENTE_MEDICAMENTO (PACIENTE_NOIDENTIFICACION, MEDICAMENTOSDIARIOS_ID) VALUES ("+id1+", "+idM+");");
                }
                //Lista Habitos
                for( int m = 0; m<3;m++){
                    int frecuencia = df.getNumberBetween(1,10);
                    String especificacion = df.getRandomText(60);
                    System.out.println("INSERT INTO APP.CATALIZADOR (ID, ESPECIFICACION, FRECUENCIA, TIPO) VALUES ("+idH+", '"+especificacion+"', "+frecuencia+", 'habito');");
                    System.out.println("INSERT INTO APP.PACIENTE_CATALIZADOR (PACIENTE_NOIDENTIFICACION, HABITOS_ID) VALUES ("+id1+", "+idH+");");
                    idH++;
                }
                //Lista Episodios
                for( int n = 0; n<5;n++){
                    
                    String fecha4 = df.getNumberBetween(2014, 2015) + "-" + df.getNumberBetween(1, 12)+ "-" + df.getNumberBetween(1, 28);
                    String localizacion = df.getItem(localizaciones, 100);
                    int intensidad = df.getNumberBetween(1,10);
                    int horas = df.getNumberBetween(0,24);
                    
                    
                    System.out.println("INSERT INTO APP.EPISODIODOLOR (ID, FECHA, \"HORASDESUEÑO\", INTENSIDADDOLOR, LOCALIZACION, PACIENTE_NOIDENTIFICACION) VALUES ("+idE+", '"+fecha4+"', "+horas+", "+intensidad+", '"+localizacion+"', "+id1+");");
                    
                    
                    int intensidadMax = df.getNumberBetween(1, 10);
                    int intensidadMin = df.getNumberBetween(1, 10);
                    String localizacionRg = df.getItem(localizaciones, 70);
                        
                    System.out.println("INSERT INTO APP.REGLA (ID, INTENSIDADDOLORMAX, INTENSIDADDOLORMIN, LOCALIZACIONDOLOR) VALUES ("+idRg+", "+intensidadMax+", "+intensidadMin+", '"+localizacionRg+"');");
                   
                    //Lista Catalizadores
                    for( int p = 0; p<3;p++){
                        String tipo1 = df.getItem(tipos, 100);
                        int frecuencia1 = df.getNumberBetween(1,10);
                        String especificacion1 = df.getRandomText(60);
                        
                        System.out.println("INSERT INTO APP.CATALIZADOR (ID, ESPECIFICACION, FRECUENCIA, TIPO) VALUES ("+idH+", '"+especificacion1+"', "+frecuencia1+", '"+tipo1+"');");
                        System.out.println("INSERT INTO APP.EPISODIODOLOR_CATALIZADOR (EPISODIODOLOR_ID, CATALIZADORES_ID) VALUES ("+idE+", "+idH+");");
                        
                        
                        System.out.println("INSERT INTO APP.REGLA_CATALIZADOR (REGLA_ID, EVITABLES_ID) VALUES ("+idRg+", "+idH+");");
                        
                        idH++;
                        
                    }
                    idRg++;
                    //Lista Medicamentos Actuales
                    for( int r = 0; r<2;r++){
                        String nombre5 = df.getItem(nombres5, 100);
                        int cantidad5 = df.getNumberBetween(1, 10);
                        int intervalo5 = df.getNumberBetween(1, 48);
                        int miligramos5 = df.getNumberBetween(1, 1000);
                        String fechaR5 = df.getNumberBetween(2014, 2015) + "-" + df.getNumberBetween(1, 12)+ "-" + df.getNumberBetween(1, 28);
                        
                        System.out.println("INSERT INTO APP.MEDICAMENTO (ID, CANTIDADVECESALDIA, FECHARECETADO, INTERVALOHORAS, MILIGRAMOS, NOMBRE) VALUES ("+idM+", "+cantidad5+", '"+fechaR5+"', "+intervalo5+", "+miligramos5+", '"+nombre5+"');");
                        System.out.println("INSERT INTO APP.EPISODIODOLOR_MEDICAMENTO (EPISODIODOLOR_ID, MEDICAMENTOSACTUALES_ID) VALUES ("+idE+", "+idM+");");
                        idM++;
                    }
                    //Lista Sintomas
                    for( int t = 0; t<3;t++){
                        String nombre6 = df.getItem(nombres6, 100);
                        int intensidad6 = df.getNumberBetween(1,10);
                        String localizacion6 = df.getItem(localizaciones, 100);
                        System.out.println("INSERT INTO APP.SINTOMA (ID, INTENSIDAD, LOCALIZACION, NOMBRE) VALUES ("+idH6+", "+intensidad6+", '"+localizacion6+"', '"+nombre6+"');");
                        System.out.println("INSERT INTO APP.EPISODIODOLOR_SINTOMA (EPISODIODOLOR_ID, SINTOMAS_ID) VALUES ("+idE+", "+idH6+");");
                        idH6++;
                    }
                    idE++;
                }
               
            }
            
	}
    }
}
