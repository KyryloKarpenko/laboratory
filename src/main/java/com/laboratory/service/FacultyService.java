package com.laboratory.service;

import com.laboratory.model.Faculty;
import com.laboratory.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty findFacultyById(Long id) {
        return facultyRepository.getById(id);
    }

    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

}
