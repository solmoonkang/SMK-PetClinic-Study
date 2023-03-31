package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import kr.co.smkpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.smkpetclinicstudy.service.model.request.OwnerRequest;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;

    @Transactional
    public void signUp(OwnerRequest ownerRequest) {
        final Owners owners = Owners.of(ownerRequest);
        ownersRepository.save(owners);
    }

    @Transactional(readOnly = true)
    public OwnerResponse getInfo(String telephone) {
       Optional<Owners> owners = ownersRepository.findByTelephone(telephone);
       return Owners.of(owners.get());
    }

    @Transactional(readOnly = true)
    public List<OwnerResponse> getAllInfo() {
        return ownersRepository.findOwnersListBy();
    }

    @Transactional
    public void editInfo(OwnerRequest ownerRequest) {
        Optional<Owners> owners = ownersRepository.findById(ownerRequest.getOwnerId());
        owners.get().edit(
                ownerRequest.getFirstName(),
                ownerRequest.getLastName(),
                ownerRequest.getAddress(),
                ownerRequest.getCity(),
                ownerRequest.getTelephone());

        ownersRepository.save(owners.get());
    }

    @Transactional
    public void deleteInfo(Long ownerId) {
        ownersRepository.deleteById(ownerId);
    }
}