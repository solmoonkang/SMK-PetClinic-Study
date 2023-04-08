package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {
    // PetDTO와 PetEntity 간의 데이터 전환을 담당

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    // PetReqDTO.CREATE -> Pet Entity
    Pet petCreateDtoToEntity(PetReqDTO.CREATE create, Owner owner);

    // Pet Entity -> PetResDTO.READ
    PetResDTO.READ petEntityToReadDto(Pet pet);
}
