package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {
    // VetDTO와 VetEntity 간의 데이터 전환을 담당

    // VetReqDTO.CREATE, List<VetSpecialty> -> Vet Entity
    @Mapping(source = "create.firstName", target = "firstName")
    @Mapping(source = "create.lastName", target = "lastName")
    Vet toVetEntity(VetReqDTO.CREATE create);

    // Vet, List<String> -> VetResDTO.READ
    @Mapping(source = "vet.firstName", target = "firstName")
    @Mapping(source = "vet.lastName", target = "lastName")
    @Mapping(source = "specialtiesName", target = "vetSpecialtiesName")
    VetResDTO.READ toVetReadDto(Vet vet, List<String> specialtiesName);
}
