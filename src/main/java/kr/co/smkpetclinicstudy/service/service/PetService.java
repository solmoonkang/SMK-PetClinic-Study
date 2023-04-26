package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.smkpetclinicstudy.persistence.repository.PetRepository;
import kr.co.smkpetclinicstudy.persistence.repository.VetRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.smkpetclinicstudy.service.model.enums.PetType;
import kr.co.smkpetclinicstudy.service.model.mappers.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    private final PetMapper petMapper;

    private final OwnerRepository ownerRepository;

    /** Create Pet Service
     *
     */
    @Transactional
    public void createPet(PetReqDTO.CREATE create) {

        final Owner owner = ownerRepository.findById(create.getOwnerId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

        final Pet pet = petMapper.toPetEntity(create, owner);

        petRepository.save(pet);
    }

    /** Get All PetTypes Service
     *
     */
    public Set<PetType> getAllPetTypes() {

//        List<String> petTypes = petMapper.getAllPetTypes();
//
//        Set<PetType> petTypeSet = petTypes
//                .stream()
//                .map(PetType::of)
//                .collect(Collectors.toSet());
//
//        return petTypeSet;

        return Arrays.stream(PetType.values())
                .collect(Collectors.toSet());
    }

    /** Get Pet Detail By petId Service
     *
     */
    public PetResDTO.READ_DETAIL getDetailPetById(Long petId) {

        final Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PET));

        return petMapper.toPetReadDetailDto(pet);
    }

    /** Get Owner's Pets By ownerId Service
     *
     */
    public List<PetResDTO.READ> getOwnerPetsByOwnerId(Long ownerId) {

        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

        return petRepository.findByOwner(owner)
                .stream()
                .map(petMapper::toPetReadDto)
                .collect(Collectors.toList());
    }

    /** Update Pet Service
     *
     */
    @Transactional
    public void updatePet(PetReqDTO.UPDATE update) {

        final Pet pet = petRepository.findById(update.getPetId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PET));

        pet.updatePet(update);
    }

    /** Delete Pet Service
     *
     */
    @Transactional
    public void deletePetById(Long petId) {

        final Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PET));

        petRepository.delete(pet);
    }
}
