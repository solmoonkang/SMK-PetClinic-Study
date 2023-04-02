package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import kr.co.smkpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.smkpetclinicstudy.service.model.request.OwnersRequest;
import kr.co.smkpetclinicstudy.service.model.response.OwnersResponse;
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
    public void signUp(OwnersRequest ownersRequest) {
        final Owners owners = Owners.of(ownersRequest);
        ownersRepository.save(owners);
    }

    @Transactional(readOnly = true)
    public OwnersResponse getInfo(Long id) {
       Optional<Owners> owners = ownersRepository.findById(id);
       return Owners.of(owners.get());
    }

    @Transactional(readOnly = true)
    public List<OwnersResponse> getAllInfo() {
        return ownersRepository.findOwnersListBy();
    }

    @Transactional
    public void editInfo(OwnersRequest ownersRequest) {
        Optional<Owners> owners = ownersRepository.findById(ownersRequest.getOwnerId());
        owners.get().edit(
                ownersRequest.getFirstName(),
                ownersRequest.getLastName(),
                ownersRequest.getAddress(),
                ownersRequest.getCity(),
                ownersRequest.getTelephone());

        ownersRepository.save(owners.get());
    }

    @Transactional
    public void deleteInfo(Long ownerId) {
        ownersRepository.deleteById(ownerId);
    }
}