package com.blue_economy.service;


import com.blue_economy.model.Course;
import com.blue_economy.model.OnlineCourse;
import com.blue_economy.repository.OnlineCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineCourseService {

    @Autowired
    private OnlineCourseRepository onlineCourseRepository;


    /// ONLINE COURSES------------------------------------------

    public OnlineCourse createOnlineCourse (OnlineCourse onlineCourse){
        return  onlineCourseRepository.save(onlineCourse);
    }

    public List<OnlineCourse> getAllOnlineCourse(){
        return   onlineCourseRepository.findAll();
    }

    public OnlineCourse findOnlineCourseById (Long id){
        return   onlineCourseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Course not found"));

    }
    public void deleteOnlineCourse(Long id) {
        onlineCourseRepository.deleteById(id);
    }

    public OnlineCourse updateOnlineCourse(Long id, OnlineCourse updatedCourse) {
        OnlineCourse onlineCourse = onlineCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        onlineCourse.setCourseCode(updatedCourse.getCourseCode());
        onlineCourse.setCourseTitle(updatedCourse.getCourseTitle());
        onlineCourse.setCategory(updatedCourse.getCategory());
        onlineCourse.setInstructorName(updatedCourse.getInstructorName());
        onlineCourse.setDuration(updatedCourse.getDuration());

        // ⚠️ Do not update createdAt (kept immutable)
        return onlineCourseRepository.save(onlineCourse);
    }










}
