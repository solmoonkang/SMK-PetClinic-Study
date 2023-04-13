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

    @Transactional
    public void createOwner(OwnerReqDTO.CREATE create) {
        duplicateOwnerTelephone(create.getTelephone());

        final Owner owner = ownerMapper.ownerCreateDtoToEntity(create);

        ownerRepository.save(owner);
    }

    public OwnerResDTO.READ getOwnerById(Long ownerId) {
       Owner owner = ownerRepository.findById(ownerId)
               .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

       return ownerMapper.ownerEntityToReadDto(owner);
    }

    @Transactional
    public void updateOwner(OwnerReqDTO.UPDATE update) {
        Owner owner = ownerRepository.findById(update.getOwnerId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

        duplicateOwnerTelephone(update.getTelephone());

        owner.updateOwner(update);
    }

    @Transactional
    public void deleteOwnerById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_OWNER));

        ownerRepository.delete(owner);
    }



    // 전화번호 입력 시 중복 검사
    private void duplicateOwnerTelephone(String telephone) {
        if (ownerRepository.existsByOwnerTelephone(telephone)) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_OWNER_PHONE);
        }
    }
}