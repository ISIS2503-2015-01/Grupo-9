/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.persistencia.Entities.Catalizador;

/**
 *
 * @author estudiante
 */
public class CatalizadorConverter {
   
    /**
     * Metodo que convierte un catalizador entity a dto
     * @param entity el entity a convertir
     * @return el dto resultante
     */
    public static CatalizadorDTO entityToDTO(Catalizador entity){
        CatalizadorDTO resp = new CatalizadorDTO();
        resp.setId(entity.getId());
        resp.setFrecuencia(entity.getFrecuencia());
        resp.setEspecificacion(entity.getEspecificacion());
        resp.setTipo(entity.getTipo());
        return resp;
    }
    
    /**
     * Metodo que convierte un dto en un entity
     * @param dto el dto que se va a convertir
     * @return el entity resultante
     */
    public static Catalizador dtoToEntity(CatalizadorDTO dto){
        Catalizador resp = new Catalizador();
        resp.setId(dto.getId());
        resp.setFrecuencia(dto.getFrecuencia());
        resp.setEspecificacion(dto.getEspecificacion());
        resp.setTipo(dto.getTipo());
        return resp;
    }

    public static List<CatalizadorDTO> entityToDtoList(List<Catalizador> list) {
        List<CatalizadorDTO> resp = new ArrayList<CatalizadorDTO>();
        for( Catalizador c : list )
            resp.add ( entityToDTO(c) );
        return resp;
    }
    
    public static List<Catalizador> dtoToEntityList(List<CatalizadorDTO> list) {
        List<Catalizador> resp = new ArrayList<Catalizador>();
        for( CatalizadorDTO c : list )
            resp.add ( dtoToEntity(c) );
        return resp;
    }
}
