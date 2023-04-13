package kr.co.smkpetclinicstudy.service.model.mappers;

import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {
    // VetDTO와 VetEntity 간의 데이터 전환을 담당

    // VetReqDTO.CREATE, List<VetSpecialty> -> Vet Entity
    Vet vetCreateDtoToEntity(VetReqDTO.CREATE create, List<VetSpecialty> vetSpecialties);

    // Vet, List<String> -> VetResDTO.READ
    VetResDTO.READ vetEntityToReadDto(Vet vet, List<String> specialtiesName);
}
