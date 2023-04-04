package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Vet;
import kr.co.smkpetclinicstudy.persistence.repository.VetRepository;
import kr.co.smkpetclinicstudy.service.model.request.VetReqDTO;
import kr.co.smkpetclinicstudy.service.model.response.VetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    @Transactional
    public void signUp(VetReqDTO vetReqDTO) {
        final Vet vet = Vet.of(vetReqDTO);
        vetRepository.save(vet);
    }

    @Transactional(readOnly = true)
    public VetResDTO getInfo(Long vetsId) {
        Optional<Vet> vets = vetRepository.findByVetsId(vetsId);
        return Vet.of(vets.get());
    }

    @Transactional(readOnly = true)
    public List<VetResDTO> getAllInfo() {
        return vetRepository.findVetsListBy();
    }

    @Transactional
    public void updateVetInfo(VetReqDTO vetReqDTO) {
        Optional<Vet> vets = vetRepository.findByVetsId(vetReqDTO.getVetsId());
        vets.get().update(
                vetReqDTO.getFirstName(),
                vetReqDTO.getLastName());

        vetRepository.save(vets.get());
    }

    @Transactional
    public void deleteVetInfo(Long vetsId) {
        vetRepository.deleteById(vetsId);
    }
}
