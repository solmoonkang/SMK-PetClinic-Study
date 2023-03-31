package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Pets;
import kr.co.smkpetclinicstudy.persistence.repository.PetsRepository;
import kr.co.smkpetclinicstudy.service.model.request.PetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;

    @Transactional
    public void register(PetRequest petRequest) {
        final Pets pets = Pets.of(petRequest);
        petsRepository.save(pets);
    }
}
