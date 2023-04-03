package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.repository.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;
}
