package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.entity.Pet;
import kr.co.smkpetclinicstudy.persistence.entity.Visit;
import kr.co.smkpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.smkpetclinicstudy.persistence.repository.PetRepository;
import kr.co.smkpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.smkpetclinicstudy.service.model.mappers.PetMapper;
import kr.co.smkpetclinicstudy.service.model.mappers.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {

    private final VisitRepository visitRepository;

    private final PetRepository petRepository;

    private final OwnerRepository ownerRepository;

    private final VisitMapper visitMapper;

    private final PetMapper petMapper;

    /** Create Visit Service
     * @Param VisitReqDTO.CREATE : create visit info
     */
    @Transactional
    public void createVisit(VisitReqDTO.CREATE create) {

        final Pet pet = petRepository.findById(create.getPetId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PET));

        final Visit visit = visitMapper.visitCreateDtoToEntity(create, pet);

        visitRepository.save(visit);
    }

    /** Get Visit By PetId Service
     * @Param petId : get visit info
     * @Return VisitResDTO.READ List
     */
    public List<VisitResDTO.READ> getVisitByPetId(Long petId) {

        final Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_PET));

        return visitRepository.findByPet(pet).stream()
                .map(visitMapper::visitEntityToReadDto)
                .collect(Collectors.toList());
    }

    /** Get Visit By VisitId Service
     * @Param visitId : get visit info
     * @Return VisitResDTO.READ
     */
    public VisitResDTO.READ getVisitByVisitId(Long visitId) {

        final Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_VISIT));

        return visitMapper.visitEntityToReadDto(visit);
    }

    /** Get Visit By OwnerId Service
     * @Param ownerId : get all visit info
     * @Return VisitResDTO.READ List
     */
    public List<VisitResDTO.READ> getAllVisitByOwnerId(Long ownerId) {

        final List<VisitResDTO.READ> visits = new ArrayList<>();
        final List<Pet> pets = petRepository.findAllByOwnerId(ownerId);
        for (Pet pet : pets) {
            visits.addAll(visitRepository.findAllByPetId(pet.getId()).stream()
                    .map(visitMapper::visitEntityToReadDto)
                    .collect(Collectors.toList()));
        }
        return visits;
    }

    /** Update Visit Service
     * @Param VisitReqDTO.UPDATE : update visit info
     */
    @Transactional
    public void updateVisit(VisitReqDTO.UPDATE update) {

        final Visit visit = visitRepository.findById(update.getVisitId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_VISIT));

        visit.updatePetDescription(update);
    }

    /** Delete Visit Service
     * @Param visitId : delete visit info
     */
    @Transactional
    public void deleteVisitById(Long visitId) {

        final Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_VISIT));

        visitRepository.delete(visit);
    }
}
