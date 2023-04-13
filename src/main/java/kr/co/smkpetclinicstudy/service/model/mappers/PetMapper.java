package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    // PetDTO와 PetEntity 간의 데이터 전환을 담당

    // PetReqDTO.CREATE -> Pet Entity
    Pet petCreateDtoToEntity(PetReqDTO.CREATE create, Owner owner);

    // Pet Entity -> PetResDTO.READ
    PetResDTO.READ petEntityToReadDto(Pet pet);
}
