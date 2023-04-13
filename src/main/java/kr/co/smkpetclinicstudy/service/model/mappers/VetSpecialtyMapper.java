package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VetSpecialtyMapper {

    // Specialty, Vet Parameter -> VetSpecialty Entity
    VetSpecialty paramToVetSpecialtyEntity(Specialty specialty, Vet vet);
}
