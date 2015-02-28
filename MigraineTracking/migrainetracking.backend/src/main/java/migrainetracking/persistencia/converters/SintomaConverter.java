/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import migrainetracking.dto.SintomaDTO;
import migrainetracking.persistencia.Entities.Sintoma;

/**
 *
 * @author estudiante
 */
public class SintomaConverter {
    
    /**
     * Metodo que se encarga de transformar un sintomaEntity en un sintomaDTO
     * @param entity el sintomaEntity a transformar
     * @return el sintomaDTO resultante
     */
    public static SintomaDTO entityToDto(Sintoma entity){
        SintomaDTO dto = new SintomaDTO();
        dto.setId(entity.getId());
        dto.setIntensidad(entity.getIntensidad());
        dto.setLocalizacion(entity.getLocalizacion());
        dto.setNombre(entity.getNombre());
        return dto;
    }
    
    /**
     *Metodo que se encarga de transformar un sintomaDTO en un sintomaEntity 
     * @param dto el sintomaDTO a transformar
     * @return el sintomaEntity resultante
     */
    public static Sintoma dtoToEntity(SintomaDTO dto){
        Sintoma entity = new Sintoma();
        entity.setId(dto.getId());
        entity.setIntensidad(dto.getIntensidad());
        entity.setLocalizacion(dto.getLocalizacion());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}
