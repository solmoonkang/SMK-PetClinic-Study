package kr.co.smkpetclinicstudy.service.service;

import kr.co.smkpetclinicstudy.persistence.repository.VetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetsService {

    private final VetsRepository vetsRepository;
}
