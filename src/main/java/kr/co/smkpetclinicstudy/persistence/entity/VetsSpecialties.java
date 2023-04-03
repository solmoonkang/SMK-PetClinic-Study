package kr.co.smkpetclinicstudy.persistence.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class VetsSpecialties {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vetsId")
    private Vets vets;

    @JoinColumn(name = "specialtiesId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Specialties specialties;
}
