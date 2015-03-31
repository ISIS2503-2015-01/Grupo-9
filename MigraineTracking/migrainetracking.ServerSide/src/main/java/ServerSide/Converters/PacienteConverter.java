/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Converters;

import ServerSide.Models.DTOs.PacienteDTO;
import ServerSide.Models.Entities.PacienteEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class PacienteConverter {

     public static PacienteEntity dtoToEntity(PacienteDTO dto){
        
       PacienteEntity entity = new PacienteEntity();
       entity.setName( dto.getName() );
       entity.setPassword( dto.getPassword() );
       entity.setUsername( dto.getUsername() );
       entity.setBirthdate( dto.getBirthdate() );
       
       return entity ;
    }
    
    public static PacienteDTO entityToDto(PacienteEntity entity ){
        PacienteDTO dto = new PacienteDTO();
        dto.setName( entity.getName() );
        dto.setPassword( entity.getPassword() );
        dto.setUsername( entity.getUsername() );
        dto.setBirthdate( entity.getBirthdate() );
        return dto ;
    }
    
    public static List<PacienteDTO> entityToDtoList(List<PacienteEntity> entities) {
        List<PacienteDTO> dtos = new ArrayList<PacienteDTO>();
        for (int i = 0; i < entities.size(); i++) {
            dtos.add( entityToDto( entities.get(0) ) );
        }
        return dtos;
    }
    
    
}
