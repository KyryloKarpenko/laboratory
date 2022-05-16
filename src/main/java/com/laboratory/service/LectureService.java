package com.laboratory.service;

import com.laboratory.model.Lecture;
import com.laboratory.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public List<Lecture> findAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture findLectureById(Long id) {
        return lectureRepository.getById(id);
    }

    public void saveLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    public void deleteLectureById(Long id) {
        lectureRepository.deleteById(id);
    }

}
