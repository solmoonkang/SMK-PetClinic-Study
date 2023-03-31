package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Pets;
import kr.co.smkpetclinicstudy.persistence.repository.PetsRepository;
import kr.co.smkpetclinicstudy.service.model.request.PetRequest;
import kr.co.smkpetclinicstudy.service.model.response.PetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;

    @Transactional
    public void register(PetRequest petRequest) {
        final Pets pets = Pets.of(petRequest);
        petsRepository.save(pets);
    }

    @Transactional(readOnly = true)
    public PetResponse getPetInfo(Long petsId) {
        Optional<Pets> pets = petsRepository.findById(petsId);
        return Pets.of(pets.get());
    }
}
