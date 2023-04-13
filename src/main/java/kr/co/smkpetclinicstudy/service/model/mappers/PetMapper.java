package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {
    // PetDTO와 PetEntity 간의 데이터 전환을 담당

    // PetReqDTO.CREATE -> Pet Entity
    @Mapping(source = "create.name", target = "name")
    @Mapping(source = "create.birthDate", target = "birthDate")
    @Mapping(source = "create.petType", target = "petType")
    @Mapping(source = "owner", target = "owner")
    Pet petCreateDtoToEntity(PetReqDTO.CREATE create, Owner owner);

    // Pet Entity -> PetResDTO.READ
    @Mapping(source = "pet.name", target = "name")
    @Mapping(source = "pet.birthDate", target = "birthDate")
    @Mapping(source = "pet.petType", target = "petType")
    PetResDTO.READ petEntityToReadDto(Pet pet);
}
