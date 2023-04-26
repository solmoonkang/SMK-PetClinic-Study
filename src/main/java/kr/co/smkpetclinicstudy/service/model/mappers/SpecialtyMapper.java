package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    // String name -> Specialty
    @Mapping(source = "specialtyName", target = "specialtyName")
    Specialty toSpecialtyEntity(String specialtyName);
}
