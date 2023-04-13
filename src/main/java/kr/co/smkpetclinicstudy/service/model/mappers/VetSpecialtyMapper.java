package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VetSpecialtyMapper {

    // Specialty, Vet Parameter -> VetSpecialty Entity
    @Mapping(source = "specialty", target = "specialty")
    @Mapping(source = "vet", target = "vet")
    VetSpecialty paramToVetSpecialtyEntity(Specialty specialty, Vet vet);
}
