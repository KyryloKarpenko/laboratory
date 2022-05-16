package com.laboratory.service;

import com.laboratory.model.Specialization;
import com.laboratory.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public List<Specialization> findAllSpecializations() {
        return specializationRepository.findAll();
    }

    public Specialization findSpecializationById(Long id) {
        return specializationRepository.getById(id);
    }

    public void saveSpecialization(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    public void deleteSpecializationById(Long id) {
        specializationRepository.deleteById(id);
    }

}
