/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.persistencia.Entities.Catalizador;
import migrainetracking.persistencia.Entities.EpisodioDolor;
import migrainetracking.persistencia.Entities.Medicamento;
import migrainetracking.persistencia.Entities.Paciente;
import migrainetracking.persistencia.Entities.Sintoma;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.persistencia.mock.ServicioPersistenciaPaciente;

/**
 *
 * @author estudiante
 */
public class EpisodioDolorConverter {
    
    /**
     * Metodo que se encarga de transformar un episodioEntity a un episodioDTO
     * @param entity el episodioEntity a transformar
     * @return el EpisodioDTO resultante
     */
    public static EpisodioDolorDTO entityToDto(EpisodioDolor entity){
        EpisodioDolorDTO resp = new EpisodioDolorDTO();
        resp.setId(entity.getId());
        resp.setFecha(entity.getFecha());
        resp.setHorasDeSueño(entity.getHorasDeSueño());
        resp.setIntensidadDolor(entity.getIntensidadDolor());
        resp.setLocalizacion(entity.getLocalizacion());
        List<CatalizadorDTO> catDtoList = new ArrayList<CatalizadorDTO>();
        for( Catalizador catEn : entity.getCatalizadores() ){
            catDtoList.add( CatalizadorConverter.entityToDTO(catEn) ); 
        }
        resp.setCatalizadores(catDtoList);
        List<MedicamentoDTO> medDtolist = new ArrayList<MedicamentoDTO>();
        for( Medicamento meden : entity.getMedicamentosActuales() ){
            medDtolist.add( MedicamentoConverter.entityToDto(meden) );
        }
        resp.setMedicamentosActuales(medDtolist);
        List<SintomaDTO> sinDtoList = new ArrayList<SintomaDTO>();
        for( Sintoma sinEn : entity.getSintomas() ){
            sinDtoList.add( SintomaConverter.entityToDto(sinEn) );
        }
        resp.setSintomas(sinDtoList);
        resp.setPacienteId(entity.getPaciente().getId());  
        return resp;
    }
    
    /**
     * Metodo que se encarga de transformar un episodioDTO a un episodioEntity
     * @param dto el episodioDTO a transformar
     * @return el episodioEntity resultante
     */
    public static EpisodioDolor dtoToEntity(EpisodioDolorDTO dto){
        EpisodioDolor resp = new EpisodioDolor();
        resp.setId(dto.getId());
        resp.setFecha(dto.getFecha());
        resp.setHorasDeSueño(dto.getHorasDeSueño());
        resp.setIntensidadDolor(dto.getIntensidadDolor());
        resp.setLocalizacion(dto.getLocalizacion());
        List<Catalizador> catList = new ArrayList<Catalizador>();
        for( CatalizadorDTO catDTO : dto.getCatalizadores() )
        {
            catList.add( CatalizadorConverter.dtoToEntity(catDTO) ); 
        }
        resp.setCatalizadores(catList);
        List<Medicamento> medlist = new ArrayList<Medicamento>();
        for( MedicamentoDTO medDTO : dto.getMedicamentosActuales() )
        {
            medlist.add( MedicamentoConverter.dtoToEntity(medDTO) );
        }
        resp.setMedicamentosActuales(medlist);
        List<Sintoma> sinList = new ArrayList<Sintoma>();
        for( SintomaDTO sinDto : dto.getSintomas() )
        {
            sinList.add( SintomaConverter.dtoToEntity(sinDto));
        }
        resp.setSintomas(sinList);
        IServicioPersistenciaPaciente serv = new ServicioPersistenciaPaciente();
        resp.setPaciente( (Paciente)serv.findById(Paciente.class, dto.getPacienteId()) );
        return resp;
     }
}
