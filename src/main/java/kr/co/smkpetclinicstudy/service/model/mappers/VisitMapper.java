package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VisitResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    // VistDTO와 VisitEntity 간의 데이터 전환을 담당

    // VisitReqDTO.CREATE, Pet -> Visit Entity
    @Mapping(source = "create.visitDate", target = "visitDate")
    @Mapping(source = "create.description", target = "description")
    @Mapping(source = "pet", target = "pet")
    Visit visitCreateDtoToEntity(VisitReqDTO.CREATE create, Pet pet);

    // Visit Entity -> VisitResDTO.READ
    @Mapping(source = "visit.visitDate", target = "visitDate")
    @Mapping(source = "visit.description", target = "description")
    @Mapping(source = "visit.pet.name", target = "petName")
    VisitResDTO.READ visitEntityToReadDto(Visit visit);
}
