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
import migrainetracking.dto.PacienteDTO;
import migrainetracking.persistencia.Entities.Catalizador;
import migrainetracking.persistencia.Entities.EpisodioDolor;
import migrainetracking.persistencia.Entities.Medicamento;
import migrainetracking.persistencia.Entities.Paciente;

/**
 *
 * @author estudiante
 */
public class PacienteConverter {
    
    /**
     * Metodo que convierte un entity a dto
     * @param entity el entity a convertir
     * @return El dto que se obtuvo del entity
     */
    public static PacienteDTO entityToDto(Paciente entity){
        PacienteDTO convertido = new PacienteDTO();
        convertido.setNombre(entity.getNombre());
        convertido.setNoIdentificacion(entity.getNoIdentificacion());
        convertido.setFechaNacimiento(entity.getFechaNacimiento());
        convertido.setPeso(entity.getPeso());
        convertido.setEstatura(entity.getEstatura());
        convertido.setContrasenia(entity.getContrasenia());
//        ArrayList<MedicamentoDTO> med = new ArrayList<MedicamentoDTO>();
//        for(int i=0;i<entity.getMedicamentosDiarios().size();i++)
//        {
//            MedicamentoDTO nuevo = MedicamentoConverter.entityToDto(entity.getMedicamentosDiarios().get(i));
//            med.add(nuevo);
//        }
//        convertido.setMedicamentosDiarios(med);
//        ArrayList<CatalizadorDTO> cat = new ArrayList<CatalizadorDTO>();
//        for(int i=0;i<entity.getHabitos().size();i++)
//        {
//            CatalizadorDTO nuevo = CatalizadorConverter.entityToDTO(entity.getHabitos().get(i));
//            cat.add(nuevo);
//        }
//        convertido.setHabitos(cat);
//        ArrayList<EpisodioDolorDTO> eps = new ArrayList<EpisodioDolorDTO>();
//        for(int i=0;i<entity.getEpisodios().size();i++)
//        {
//            EpisodioDolorDTO nuevo = EpisodioDolorConverter.entityToDto(entity.getEpisodios().get(i));
//            eps.add(nuevo);
//        }
//        convertido.setEpisodios(eps);
        return convertido;
    }
    
    /**
     * Metodo que convierte un dto en un entity
     * @param dto el dto a convertir
     * @return el entity que se obtuvo del dto
     */
    public static Paciente dtoToEntity(PacienteDTO dto){
        Paciente convertido = new Paciente();
        convertido.setNombre(dto.getNombre());
        convertido.setNoIdentificacion(dto.getNoIdentificacion());
        convertido.setFechaNacimiento(dto.getFechaNacimiento());
        convertido.setPeso(dto.getPeso());
        convertido.setEstatura(dto.getEstatura());
        ArrayList<Medicamento> med = new ArrayList<Medicamento>();
        for(int i=0;i<dto.getMedicamentosDiarios().size();i++)
        {
            Medicamento nuevo = MedicamentoConverter.dtoToEntity(dto.getMedicamentosDiarios().get(i));
            med.add(nuevo);
        }
        convertido.setMedicamentosDiarios(med);
        ArrayList<Catalizador> cat = new ArrayList<Catalizador>();
        for(int i=0;i<dto.getHabitos().size();i++)
        {
            Catalizador nuevo = CatalizadorConverter.dtoToEntity(dto.getHabitos().get(i));
            cat.add(nuevo);
        }
        convertido.setHabitos(cat);
        ArrayList<EpisodioDolor> eps = new ArrayList<EpisodioDolor>();
        for(int i=0;i<dto.getEpisodios().size();i++)
        {
            EpisodioDolor nuevo = EpisodioDolorConverter.dtoToEntity(dto.getEpisodios().get(i));
            eps.add(nuevo);
        }
        convertido.setEpisodios(eps);
        convertido.setContrasenia(dto.getContrasenia());
        return convertido;
    }
    
   
    public static List<Paciente> dtoToEntityList(List<PacienteDTO> list){
        List<Paciente> resp = new ArrayList<Paciente>();
        for( PacienteDTO p : list){
            resp.add( dtoToEntity(p) );
        }
        return resp;
    }
    
    public static List<PacienteDTO> entityToDtoList(List<Paciente> list){
        List<PacienteDTO> resp = new ArrayList<PacienteDTO>();
        for( Paciente p : list ){
            resp.add( entityToDto(p) );
        }
        return resp;
    }
    
}
