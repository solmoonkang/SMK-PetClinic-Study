package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VisitResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    // VistDTO와 VisitEntity 간의 데이터 전환을 담당

    VisitMapper INSTANCE = Mappers.getMapper(VisitMapper.class);

    // VisitReqDTO.CREATE, Pet -> Visit Entity
    Visit visitCreateDtoToEntity(VisitReqDTO.CREATE create, Pet pet);

    // Visit Entity -> VisitResDTO.READ
    VisitResDTO.READ visitEntityToReadDto(Visit visit);
}
