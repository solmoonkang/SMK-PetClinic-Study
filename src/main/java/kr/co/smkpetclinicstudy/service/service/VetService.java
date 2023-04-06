package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Specialty;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.smkpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.smkpetclinicstudy.persistence.repository.VetRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VetResDTO;
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

    @Transactional
    public void createVet(VetReqDTO.CREATE create) {
        Vet vet = Vet.dtoToEntity(create, Collections.emptyList());

        final List<VetSpecialty> vetSpecialties =
                getOrCreateVetSpecialties(create.getVetSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);

        vetRepository.save(vet);
    }

    public VetResDTO.READ getVetById(Long vetId) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Not Found Vet"));

        final List<String> specialtiesName = getSpecialtiesNameByVet(vet);

        return Vet.entityToDto(vet, specialtiesName);
    }

    @Transactional
    public void updateVet(VetReqDTO.UPDATE update) {
        Vet vet = vetRepository.findById(update.getVetId())
                        .orElseThrow(() -> new RuntimeException("Not Found Vet"));

        final List<VetSpecialty> vetSpecialties =
                getOrCreateVetSpecialties(update.getVetSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);
    }


    private List<Specialty> getOrCreateSpecialtiesByName(List<String> names) {
        List<Specialty> specialties = specialtyRepository.findAllByName(names);

        final Set<String> existName = specialties.stream()
                .map(Specialty::getName)
                .collect(Collectors.toSet());

        final List<Specialty> createSpecialties = names.stream()
                .filter(name -> !existName.contains(name))
                .map(Specialty::paramToEntity)
                .collect(Collectors.toList());

        specialties.addAll(createSpecialties);

        return specialties;
    }

    private List<String> getSpecialtiesNameByVet(Vet vet) {
        return vet.getVetSpecialties().stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getName)
                .collect(Collectors.toList());
    }

    private List<VetSpecialty> getOrCreateVetSpecialties(List<String> names,
                                                         Vet vet) {
        final List<Specialty> specialties = getOrCreateSpecialtiesByName(names);

        return specialties.stream()
                .map(specialty -> VetSpecialty.builder()
                        .specialty(specialty)
                        .vet(vet).build())
                .collect(Collectors.toList());
    }


    @Transactional
    public void deleteVetById(Long vetId) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Not Found Vet"));

        vetRepository.delete(vet);
    }
}
