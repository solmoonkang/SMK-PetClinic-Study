package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Owner;
import kr.co.smkpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.smkpetclinicstudy.service.model.request.OwnerReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public void signUp(OwnerReqDTO ownerReqDto) {
        final Owner owner = Owner.of(ownerReqDto);
        ownerRepository.save(owner);
    }

    public OwnerResDTO getInfo(Long ownerId) {
       Optional<Owner> owners = ownerRepository.findByOwnerId(ownerId);
       return Owner.of(owners.get());
    }


    public List<OwnerResDTO> getAllInfo() {
        return ownerRepository.findOwnersListBy();
    }

    @Transactional
    public void editInfo(OwnerReqDTO ownerReqDto) {
        Optional<Owner> owners = ownerRepository.findByOwnerId(ownerReqDto.getOwnerId());
        owners.get().edit(
                ownerReqDto.getFirstName(),
                ownerReqDto.getLastName(),
                ownerReqDto.getAddress(),
                ownerReqDto.getCity(),
                ownerReqDto.getTelephone());

        ownerRepository.save(owners.get());
    }

    @Transactional
    public void deleteInfo(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }
}