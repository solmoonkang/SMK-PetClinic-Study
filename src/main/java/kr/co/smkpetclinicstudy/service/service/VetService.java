package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.smkpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.smkpetclinicstudy.persistence.repository.VetRepository;
import kr.co.smkpetclinicstudy.persistence.repository.VetSpecialtyRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.smkpetclinicstudy.service.model.mappers.SpecialtyMapper;
import kr.co.smkpetclinicstudy.service.model.mappers.VetMapper;
import kr.co.smkpetclinicstudy.service.model.mappers.VetSpecialtyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    private final VetSpecialtyRepository vetSpecialtyRepository;

    private final VetMapper vetMapper;

    private final SpecialtyMapper specialtyMapper;

    private final VetSpecialtyMapper vetSpecialtyMapper;

    /** Create Vet Service
     *
     */
    @Transactional
    public void createVet(VetReqDTO.CREATE create) {

        Vet vet = vetMapper.vetCreateDtoToEntity(create, Collections.emptyList());

        final List<VetSpecialty> vetSpecialties =
                getOrCreateVetSpecialties(create.getVetSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);

        vetRepository.save(vet);
    }

    /** Get Vet By vetId Service
     *
     */
    public VetResDTO.READ getVetById(Long vetId) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_VET));

        final List<String> specialtiesName = getSpecialtiesNameByVet(vet);

        return vetMapper.vetEntityToReadDto(vet, specialtiesName);
    }

    /** Get All Specialties List Service
     *
     */
    public Set<String> getVetSpecialtiesName() {

        final List<VetSpecialty> vetSpecialties = vetSpecialtyRepository.findAll();

        return vetSpecialties
                .stream()
//                .map(vetSpecialty -> vetSpecialty.getSpecialty().getSpecialtyName())
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());
    }


    /** Update Vet Service
     *
     */
    @Transactional
    public void updateVet(VetReqDTO.UPDATE update) {

        final Vet vet = vetRepository.findById(update.getVetId())
                        .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_VET));

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(update.getVetSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);
    }

    /** Delete Vet Service
     *
     */
    @Transactional
    public void deleteVetById(Long vetId) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_VET));

        vetRepository.delete(vet);
    }



    private List<Specialty> getOrCreateSpecialtiesByName(List<String> specialtiesName) {

        List<Specialty> specialties = specialtyRepository.findAllBySpecialtyNameIn(specialtiesName);

        final Set<String> existNames = specialties
                .stream()
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());

        final List<Specialty> createSpecialties = specialtiesName
                .stream()
                .filter(specialtyName -> !existNames.contains(specialtyName))
                .map(specialtyMapper::nameToSpecialtyEntity)
                .collect(Collectors.toList());

        specialties.addAll(createSpecialties);

        return specialties;
    }

    private List<String> getSpecialtiesNameByVet(Vet vet) {

        return vet.getVetSpecialties().stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toList());
    }

    private List<VetSpecialty> getOrCreateVetSpecialties(List<String> specialtiesName,
                                                         Vet vet) {

        final List<Specialty> specialties = getOrCreateSpecialtiesByName(specialtiesName);

        return specialties
                .stream()
                .map(specialty -> vetSpecialtyMapper.paramToVetSpecialtyEntity(specialty, vet))
                .collect(Collectors.toList());
    }
}
