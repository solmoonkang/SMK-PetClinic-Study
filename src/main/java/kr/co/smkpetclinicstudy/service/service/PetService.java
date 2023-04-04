package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.repository.PetRepository;
import kr.co.smkpetclinicstudy.service.model.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.PetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @Transactional
    public void register(PetReqDTO petReqDto) {
        final Pet pet = Pet.of(petReqDto);
        petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    public PetResDTO getPetInfo(Long petsId) {
        Optional<Pet> pets = petRepository.findByPetsId(petsId);
        return Pet.of(pets.get());
    }

    @Transactional(readOnly = true)
    public List<PetResDTO> getAllPetInfo() {
        return petRepository.findPetsListBy();
    }

    @Transactional
    public void updatePetInfo(PetReqDTO petReqDto) {
        Optional<Pet> pets = petRepository.findById(petReqDto.getOwnerId().getId());
        pets.get().update(
                petReqDto.getName(),
                petReqDto.getOwnerId());
    }

    @Transactional
    public void deletePetInfo(Long petsId) {
        petRepository.deleteById(petsId);
    }
}
