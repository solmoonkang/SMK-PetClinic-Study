package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import kr.co.smkpetclinicstudy.persistence.repository.PetRepository;
import kr.co.smkpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VisitResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {

    private final VisitRepository visitRepository;

    private final PetRepository petRepository;

    @Transactional
    public void createVisit(VisitReqDTO.CREATE create) {
        final Pet pet = petRepository.findById(create.getPetId())
                .orElseThrow(() -> new RuntimeException("Not Found Pet"));

        final Visit visit = Visit.dtoToEntity(create, pet);

        visitRepository.save(visit);
    }

    public List<VisitResDTO.READ> getVisitByPetId(Long petId) {
        final Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Not Found Pet"));

        return visitRepository.findByPet(pet).stream()
                .map(Visit::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateVisit(VisitReqDTO.UPDATE update) {
        Visit visit = visitRepository.findById(update.getVisitId())
                .orElseThrow(() -> new RuntimeException("Not Found Visit"));

        visit.updatePetDescription(update);
    }

    @Transactional
    public void deleteVisitById(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Not Found Visit"));

        visitRepository.delete(visit);
    }
}
