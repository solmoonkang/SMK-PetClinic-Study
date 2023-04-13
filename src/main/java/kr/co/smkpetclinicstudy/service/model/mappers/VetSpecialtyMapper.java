package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VetSpecialtyMapper {

    VetSpecialtyMapper INSTANCE = Mappers.getMapper(VetSpecialtyMapper.class);

    // Specialty, Vet Parameter -> VetSpecialty Entity
    VetSpecialty paramToVetSpecialtyEntity(Specialty specialty, Vet vet);
}
