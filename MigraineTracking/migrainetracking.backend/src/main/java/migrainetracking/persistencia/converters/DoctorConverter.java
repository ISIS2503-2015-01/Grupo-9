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

    public static DoctorDTO entityToDTO(Doctor entity) {
        DoctorDTO dto = new DoctorDTO();        
        dto.setNombre(entity.getNombre());
        dto.setNoIdentificacion(entity.getNoIdentificacion());
        dto.setEspecialidad(entity.getEspecialidad());
        dto.setFechaNacimiento(entity.getFechaNacimiento());

        List<PacienteDTO> pacs = new ArrayList<PacienteDTO>();
        for (Paciente p : entity.getPacientes()) {
            pacs.add(PacienteConverter.entityToDto(p));
        }

        dto.setPacientes(pacs);

        return dto;
    }

    public static Doctor dtoToEntity(DoctorDTO dto) {
        Doctor resp = new Doctor();
        resp.setNombre(dto.getNombre());
        resp.setNoIdentificacion(dto.getNoIdentificacion());
        resp.setEspecialidad(dto.getEspecialidad());
        resp.setFechaNacimiento(dto.getFechaNacimiento());

        List<Paciente> pacs = new ArrayList<Paciente>();
        for (PacienteDTO p : dto.getPacientes()) {
            pacs.add(PacienteConverter.dtoToEntity(p));
        }

        resp.setPacientes(pacs);

        return resp;
    }

    public static List<Doctor> dtoToEntityList(List<DoctorDTO> list) {
        List<Doctor> resp = new ArrayList<Doctor>();
        for (DoctorDTO d : list) 
            resp.add(dtoToEntity(d));
        return resp;
    }

    public static List<DoctorDTO> entityToDtoList(List<Doctor> list) {
        List<DoctorDTO> resp = new ArrayList<DoctorDTO>();
        for (Doctor d : list) 
            resp.add(entityToDTO(d));
        return resp;
    }
}
