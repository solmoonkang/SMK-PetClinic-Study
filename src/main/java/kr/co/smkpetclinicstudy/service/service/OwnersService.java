package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.entity.Owners;
import kr.co.smkpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.smkpetclinicstudy.service.model.request.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;

    public void signUp(SignUpRequestDto signUpRequestDto) {
        final Owners owners = Owners.of(signUpRequestDto);
        ownersRepository.save(owners);
    }
}
