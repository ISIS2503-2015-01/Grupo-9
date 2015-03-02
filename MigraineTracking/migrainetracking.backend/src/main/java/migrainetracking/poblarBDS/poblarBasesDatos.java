/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.poblarBDS;

import java.util.Date;
import org.eclipse.persistence.expressions.ExpressionOperator;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author estudiante
 */
public class poblarBasesDatos {
DataFactory df;
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
        //Lista Doctores
        for(int i =0;i<300;i++){
            String name = df.getFirstName() +" "+df.getLastName();
            int id = Integer.parseInt(df.getNumberText(8));
            Date fecha = df.getBirthDate();
            String especialidad = df.getItem(values, 70);
            
            //Lista Pacientes
            for( int j= 0; j<5;j++){
                String name1 = df.getFirstName() +" "+df.getLastName();
                int id1 = Integer.parseInt(df.getNumberText(8));
                Date fecha1 = df.getBirthDate();
                int peso=df.getNumberBetween(40, 100);
                int estatura = df.getNumberBetween(140, 200);
                
                //Lista Medicamentos
                for( int k = 0; k<3;k++){
                    String nombres[] = {"ibuprofeno","corticoide","acetaminofen", "eutirox", "hidroclorotiazida"};
                    String nombre = df.getItem(nombres, 70);
                    int cantidad = df.getNumberBetween(1, 10);
                    int intervalo = df.getNumberBetween(1, 48);
                    int miligramos = df.getNumberBetween(1, 1000);
                    Date fechaR = df.getDateBetween(df.getDate(2014, 1, 1), new Date());
                }
                //Lista Habitos
                for( int m = 0; m<3;m++){
                    String tipos[] = {"alimento", "actividad fisica", "bebida", "habito"};
                    String tipo = df.getItem(tipos, 70);
                    int frecuencia = df.getNumberBetween(1,10);
                    String especificacion = df.getRandomText(60);
                }
                //Lista Episodios
                for( int n = 0; n<5;n++){
                    
                    Date fecha4 = df.getDateBetween(df.getDate(2014, 1, 1), new Date());
                    String localizaciones[] = {"frente","cabeza","cuello", "ojos", "oidos"};
                    String localizacion = df.getItem(localizaciones, 70);
                    int intensidad = df.getNumberBetween(1,10);
                    int horas = df.getNumberBetween(0,24);
                    
                    //Lista Catalizadores
                    for( int p = 0; p<3;p++){
                        String tipos[] = {"alimento", "actividad fisica", "bebida", "habito"};
                        String tipo = df.getItem(tipos, 70);
                        int frecuencia = df.getNumberBetween(1,10);
                        String especificacion = df.getRandomText(60);
                    }
                    //Lista Medicamentos Actuales
                    for( int r = 0; r<2;r++){
                        String nombres5[] = {"ibuprofeno","corticoide","acetaminofen", "eutirox", "hidroclorotiazida"};
                        String nombre5 = df.getItem(nombres5, 70);
                        int cantidad5 = df.getNumberBetween(1, 10);
                        int intervalo5 = df.getNumberBetween(1, 48);
                        int miligramos5 = df.getNumberBetween(1, 1000);
                        Date fechaR5 = df.getDateBetween(df.getDate(2014, 1, 1), new Date());
                    }
                    //Lista Sintomas
                    for( int t = 0; t<3;t++){
                        String nombres6[] = {"mareo","nauseas","escalofrios", "temblor", "zumbido"};
                        String nombre6 = df.getItem(nombres6, 70);
                        int intensidad6 = df.getNumberBetween(1,10);
                        String localizaciones6[] = {"frente","cabeza","cuello", "ojos", "oidos"};
                        String localizacion6 = df.getItem(localizaciones6, 70);
                    }
                }
               
            }
            
	}
    }
}
