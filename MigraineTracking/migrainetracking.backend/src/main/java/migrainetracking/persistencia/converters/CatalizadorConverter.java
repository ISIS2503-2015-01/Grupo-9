/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.persistencia.Entities.Catalizador;

/**
 *
 * @author estudiante
 */
public class CatalizadorConverter {
    public static CatalizadorDTO entityToDTO(Catalizador entity){
        CatalizadorDTO resp = new CatalizadorDTO();
        resp.setId(entity.getId());
        resp.setFrecuencia(entity.getFrecuencia());
        resp.setEspecificacion(entity.getEspecificacion());
        resp.setTipo(entity.getTipo());
        return resp;
    }
    
    public static Catalizador dtoToEntity(CatalizadorDTO dto){
        Catalizador resp = new Catalizador();
        resp.setId(dto.getId());
        resp.setFrecuencia(dto.getFrecuencia());
        resp.setEspecificacion(dto.getEspecificacion());
        resp.setTipo(dto.getTipo());
        return resp;
    }
}
