package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Pets;
import kr.co.smkpetclinicstudy.persistence.repository.PetsRepository;
import kr.co.smkpetclinicstudy.service.model.request.PetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.PetsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;

    @Transactional
    public void register(PetsRequest petsRequest) {
        final Pets pets = Pets.of(petsRequest);
        petsRepository.save(pets);
    }

    @Transactional(readOnly = true)
    public PetsResponse getPetInfo(Long petsId) {
        Optional<Pets> pets = petsRepository.findByPetsId(petsId);
        return Pets.of(pets.get());
    }

    @Transactional(readOnly = true)
    public List<PetsResponse> getAllPetInfo() {
        return petsRepository.findPetsListBy();
    }

    @Transactional
    public void updatePetInfo(PetsRequest petsRequest) {
        Optional<Pets> pets = petsRepository.findById(petsRequest.getOwnersId().getId());
        pets.get().update(
                petsRequest.getName(),
                petsRequest.getOwnersId());
    }

    @Transactional
    public void deletePetInfo(Long petsId) {
        petsRepository.deleteById(petsId);
    }
}
