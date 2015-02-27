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
    public static ReglaDTO entityToDto(Regla entity){
        ReglaDTO resp = new ReglaDTO();
        resp.setId(entity.getId());
        resp.setIntensidadDolorMax(entity.getIntensidadDolorMax());
        resp.setIntensidadDolorMin(entity.getIntensidadDolorMin());
        resp.setLocalizacionDolor(entity.getLocalizacionDolor());
        List<CatalizadorDTO> catsDto = new ArrayList<CatalizadorDTO>();
        for( Catalizador catEn : entity.getEvitables() ){
            catsDto.add( CatalizadorConverter.entityToDTO(catEn) );
        }
        resp.setEvitables(catsDto);
        return resp;
    }
    
    public static Regla dtoToEntity(ReglaDTO dto){
        Regla resp = new Regla();
        
        resp.setId(dto.getId());
        resp.setIntensidadDolorMax(dto.getIntensidadDolorMax());
        resp.setIntensidadDolorMin(dto.getIntensidadDolorMin());
        resp.setLocalizacionDolor(dto.getLocalizacionDolor());
        
        List<Catalizador> catsEn = new ArrayList<Catalizador>();
        for( CatalizadorDTO catDto : dto.getEvitables() ){
            catsEn.add( CatalizadorConverter.dtoToEntity(catDto) );
        }
        
        resp.setEvitables(catsEn);
        
        return resp;
    }
}
