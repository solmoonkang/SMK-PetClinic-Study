package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Vets;
import kr.co.smkpetclinicstudy.persistence.repository.VetsRepository;
import kr.co.smkpetclinicstudy.service.model.request.VetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VetsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetsService {

    private final VetsRepository vetsRepository;

    @Transactional
    public void signUp(VetsRequest vetsRequest) {
        final Vets vets = Vets.of(vetsRequest);
        vetsRepository.save(vets);
    }

    @Transactional(readOnly = true)
    public List<VetsResponse> getAllInfo() {
        return vetsRepository.findVetsListBy();
    }
}
