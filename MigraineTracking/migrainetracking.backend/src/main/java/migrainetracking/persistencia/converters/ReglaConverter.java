/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.persistencia.Entities.Catalizador;
import migrainetracking.persistencia.Entities.Regla;

/**
 *
 * @author estudiante
 */
public class ReglaConverter {
    
    /**
     * Metodo que se encarga de convertir una reglaEntity a una reglaDTO
     * @param entity la reglaEntity a transformar
     * @return la regla DTO resultante
     */
    public static ReglaDTO entityToDto(Regla entity){
        ReglaDTO resp = new ReglaDTO();
        resp.setId(entity.getId());
        resp.setIntensidadDolorMax(entity.getIntensidadDolorMax());
        resp.setIntensidadDolorMin(entity.getIntensidadDolorMin());
        resp.setLocalizacionDolor(entity.getLocalizacionDolor());
       
        
        return resp;
    }
    
    /**
     * Metodo que se encarga de transformar una reglaDTO en una reglaEntity
     * @param dto la reglaDTO a convertir
     * @return la reglaEntity resultante
     */
    public static Regla dtoToEntity(ReglaDTO dto){
        Regla resp = new Regla();
        resp.setId(dto.getId());
        resp.setIntensidadDolorMax(dto.getIntensidadDolorMax());
        resp.setIntensidadDolorMin(dto.getIntensidadDolorMin());
        resp.setLocalizacionDolor(dto.getLocalizacionDolor());
        List<Catalizador> catsEn = new ArrayList<Catalizador>();
       
        return resp;
    }
}
