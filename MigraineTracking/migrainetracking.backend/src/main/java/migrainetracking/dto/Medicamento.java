/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

/**
 *
 * @author estudiante
 */
public class Medicamento {
    
    private String nombre;
    
    private int cantidadVecesAlDia;
    
    private int intervaloHoras;
    
    private int miligramos;

    public Medicamento(String nombre, int cantidadVecesAlDia, int intervaloHoras, int miligramos) {
        this.nombre = nombre;
        this.cantidadVecesAlDia = cantidadVecesAlDia;
        this.intervaloHoras = intervaloHoras;
        this.miligramos = miligramos;
    }

    public Medicamento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadVecesAlDia() {
        return cantidadVecesAlDia;
    }

    public void setCantidadVecesAlDia(int cantidadVecesAlDia) {
        this.cantidadVecesAlDia = cantidadVecesAlDia;
    }

    public int getIntervaloHoras() {
        return intervaloHoras;
    }

    public void setIntervaloHoras(int intervaloHoras) {
        this.intervaloHoras = intervaloHoras;
    }

    public int getMiligramos() {
        return miligramos;
    }

    public void setMiligramos(int miligramos) {
        this.miligramos = miligramos;
    }
    
    
    
    
}
