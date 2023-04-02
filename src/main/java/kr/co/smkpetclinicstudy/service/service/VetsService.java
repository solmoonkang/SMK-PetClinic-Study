package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Vets;
import kr.co.smkpetclinicstudy.persistence.repository.VetsRepository;
import kr.co.smkpetclinicstudy.service.model.request.VetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VetsService {

    private final VetsRepository vetsRepository;

    @Transactional
    public void signUp(VetRequest vetRequest) {
        final Vets vets = Vets.of(vetRequest);
        vetsRepository.save(vets);
    }
}
