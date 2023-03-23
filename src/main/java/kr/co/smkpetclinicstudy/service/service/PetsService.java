package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;
}
