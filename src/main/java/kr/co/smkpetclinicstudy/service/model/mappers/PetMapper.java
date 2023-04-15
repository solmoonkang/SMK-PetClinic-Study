package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.smkpetclinicstudy.service.model.enums.PetType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {
    // PetDTO와 PetEntity 간의 데이터 전환을 담당

    // PetReqDTO.CREATE -> Pet Entity
    @Mapping(source = "create.name", target = "name")
    @Mapping(source = "create.birthDate", target = "birthDate")
    @Mapping(source = "create.petType", target = "petType")
    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "vet", target = "vet")
    Pet toPetEntity(PetReqDTO.CREATE create, Owner owner, Vet vet);

    // Pet Entity -> PetResDTO.READ
    @Mapping(source = "pet.name", target = "name")
    @Mapping(source = "pet.birthDate", target = "birthDate")
    @Mapping(source = "pet.petType", target = "petType")
    @Mapping(source = "pet.owner.firstName", target = "ownerFirstName")
    @Mapping(source = "pet.owner.lastName", target = "ownerLastName")
    PetResDTO.READ toPetReadDto(Pet pet);

    // Pet Entity -> PetResDTO.READ_DETAIL
    @Mapping(source = "pet.name", target = "name")
    @Mapping(source = "pet.birthDate", target = "birthDate")
    @Mapping(source = "pet.petType", target = "petType")
    @Mapping(source = "pet.owner.firstName", target = "ownerFirstName")
    @Mapping(source = "pet.owner.lastName", target = "ownerLastName")
    @Mapping(source = "pet.owner.address", target = "ownerAddress")
    @Mapping(source = "pet.owner.telephone", target = "ownerTelephone")
    PetResDTO.READ_DETAIL toPetReadDetailDto(Pet pet);

//    List<String> getAllPetTypes();
}
