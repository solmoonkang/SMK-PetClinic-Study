package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Vets;
import kr.co.smkpetclinicstudy.persistence.repository.VetsRepository;
import kr.co.smkpetclinicstudy.service.model.request.VetsRequest;
import kr.co.smkpetclinicstudy.service.model.response.VetsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public VetsResponse getInfo(Long vetsId) {
        Optional<Vets> vets = vetsRepository.findByVetsId(vetsId);
        return Vets.of(vets.get());
    }

    @Transactional(readOnly = true)
    public List<VetsResponse> getAllInfo() {
        return vetsRepository.findVetsListBy();
    }

    @Transactional
    public void updateVetInfo(VetsRequest vetsRequest) {
        Optional<Vets> vets = vetsRepository.findByVetsId(vetsRequest.getVetsId());
        vets.get().update(
                vetsRequest.getFirstName(),
                vetsRequest.getLastName());

        vetsRepository.save(vets.get());
    }

    @Transactional
    public void deleteVetInfo(Long vetsId) {
        vetsRepository.deleteById(vetsId);
    }
}
