/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.persistencia.Entities.Medicamento;

/**
 *
 * @author estudiante
 */
public class MedicamentoConverter {
    
    /**
     * Metodo que se encarga de transformar un medicamentoEntity a un medicamentoDTO
     * @param entity el medicamentoEntity a transformar
     * @return el medicamentoDTO resultante
     */
    public static MedicamentoDTO entityToDto(Medicamento entity){
        MedicamentoDTO resp = new MedicamentoDTO();
        resp.setId(entity.getId());
        resp.setCantidadVecesAlDia(entity.getCantidadVecesAlDia());
        resp.setIntervaloHoras(entity.getIntervaloHoras());
        resp.setMiligramos(entity.getMiligramos());
        resp.setNombre(entity.getNombre());
        return resp;
    }
    
    /**
     * Metodo que se encarga de convertir un medicmanetoDTO a un medicamentoEntity
     * @param dto el medicamentoDTO a transformar
     * @return el medicamentoEntity resultante
     */
    public static Medicamento dtoToEntity(MedicamentoDTO dto){
        Medicamento resp = new Medicamento();
        resp.setId(dto.getId());
        resp.setCantidadVecesAlDia(dto.getCantidadVecesAlDia());
        resp.setIntervaloHoras(dto.getIntervaloHoras());
        resp.setMiligramos(dto.getMiligramos());
        resp.setNombre(dto.getNombre());
        return resp;
    }
}
