/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Regla {
    
    private int intensidadDolorMin;
    private int intensidadDolorMax;
    private String localizacionDolor;
    List<Catalizador> evitables;
    
    public Regla(){ evitables = new ArrayList<Catalizador>();}

    public int getIntensidadDolorMin() {
        return intensidadDolorMin;
    }

    public void setIntensidadDolorMin(int intensidadDolorMin) {
        this.intensidadDolorMin = intensidadDolorMin;
    }

    public int getIntensidadDolorMax() {
        return intensidadDolorMax;
    }

    public void setIntensidadDolorMax(int intensidadDolorMax) {
        this.intensidadDolorMax = intensidadDolorMax;
    }

    public String getLocalizacionDolor() {
        return localizacionDolor;
    }

    public void setLocalizacionDolor(String localizacionDolor) {
        this.localizacionDolor = localizacionDolor;
    }

    public List<Catalizador> getEvitables() {
        return evitables;
    }

    public void setEvitables(List<Catalizador> evitables) {
        this.evitables = evitables;
    }
    
    
}
