package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import kr.co.smkpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.smkpetclinicstudy.service.model.request.SignUpRequest;
import kr.co.smkpetclinicstudy.service.model.response.OwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        final Owners owners = Owners.of(signUpRequest);
        ownersRepository.save(owners);
    }

    @Transactional(readOnly = true)
    public OwnerResponse getInfo(String ownerId) {
       Optional<Owners> owners = ownersRepository.findById(ownerId);
       return new OwnerResponse(owners.get());
    }
}
