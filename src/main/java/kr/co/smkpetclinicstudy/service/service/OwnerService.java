package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.smkpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public void createOwner(OwnerReqDTO.CREATE create) {
        final Owner owner = Owner.dtoToEntity(create);

        ownerRepository.save(owner);
    }

    public OwnerResDTO.READ getOwnerById(Long ownerId) {
       Owner owner = ownerRepository.findById(ownerId)
               .orElseThrow(() -> new RuntimeException("Not Found Owner"));

       return Owner.entityToDto(owner);
    }

    @Transactional
    public void updateOwner(OwnerReqDTO.UPDATE update) {
        Owner owner = ownerRepository.findById(update.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        owner.updateOwner(update);
    }

    @Transactional
    public void deleteOwnerById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        ownerRepository.delete(owner);
    }
}