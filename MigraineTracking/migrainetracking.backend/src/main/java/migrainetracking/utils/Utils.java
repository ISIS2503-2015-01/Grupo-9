/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.utils;

/**
 *
 * @author Personal
 */
public class Utils {
    
    /**
     * Metodo para imprimir un mensaje en consola en rosado
     * @param s el mensaje a imprimir
     */
    public static void printf( String s )
    {
       System.out.println( (char)27 + "[35mPersistence: "+s ) ;
    }
}
