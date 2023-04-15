package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.infra.global.error.enums.ErrorCode;
import kr.co.smkpetclinicstudy.infra.global.exception.DuplicatedException;
import kr.co.smkpetclinicstudy.infra.global.exception.NotFoundException;
import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.smkpetclinicstudy.service.model.mappers.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    /** Create Owner Service
     *
     */
    @Transactional
    public void createOwner(OwnerReqDTO.CREATE create) {

        final Owner owner = ownerMapper.toOwnerEntity(create);

        existOwnerTelephone(create.getTelephone());

        ownerRepository.save(owner);
    }

    /** Get Owner By OwnerId Service
     *
     */
    public OwnerResDTO.READ getOwnerById(Long ownerId) {

       final Owner owner = ownerRepository.findById(ownerId)
               .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

       return ownerMapper.toOwnerReadDto(owner);
    }

    /** Update Owner Service
     *
     */
    @Transactional
    public void updateOwner(OwnerReqDTO.UPDATE update) {

        final Owner owner = ownerRepository.findById(update.getOwnerId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

        duplicateOwnerTelephone(owner.getTelephone(), update.getTelephone());

        owner.updateOwner(update);
    }

    /** Delete Owner Service
     *
     */
    @Transactional
    public void deleteOwnerById(Long ownerId) {

        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

        ownerRepository.delete(owner);
    }


    private void existOwnerTelephone(String telephone) {

        if (ownerRepository.existsByTelephone(telephone)) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_OWNER_PHONE);
        }
    }

    private void duplicateOwnerTelephone(String telephone, String checkTelephone) {

        if (!telephone.equals(checkTelephone)) {
            existOwnerTelephone(checkTelephone);
        }
    }
}