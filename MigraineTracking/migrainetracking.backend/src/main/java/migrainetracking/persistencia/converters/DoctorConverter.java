/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.converters;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.DoctorDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.persistencia.Entities.Doctor;
import migrainetracking.persistencia.Entities.Paciente;

/**
 *
 * @author estudiante
 */
public class DoctorConverter {
    public static DoctorDTO entityToDTO(Doctor entity){
        DoctorDTO dto = new DoctorDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setNoIdentificacion(entity.getNoIdentificacion());
        dto.setEspecialidad(entity.getEspecialidad());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        
        List<PacienteDTO> pacs = new ArrayList<PacienteDTO>();
        for( Paciente p : entity.getPacientes() ){
            // convierto paciente a dto y agrego.
        }
        return dto;
    }
    
    
}
