package com.blue_economy.service;

import com.blue_economy.model.Course;
import com.blue_economy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse (Course course){
        return  courseRepository.save(course);
    }

    public List<Course> getAllCourse(){
      return   courseRepository.findAll();
    }

    public Course findCourseById (Long id){
      return   courseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Course not found"));

    }
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        existingCourse.setCourseCode(updatedCourse.getCourseCode());
        existingCourse.setCourseTitle(updatedCourse.getCourseTitle());
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setDuration(updatedCourse.getDuration());

        // ⚠️ Do not update createdAt (kept immutable)
        return courseRepository.save(existingCourse);
    }












//EVENT COURSES-------------------------------------

    public Course updateEvent(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        existingCourse.setCourseCode(updatedCourse.getCourseCode());
        existingCourse.setCourseTitle(updatedCourse.getCourseTitle());
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setDuration(updatedCourse.getDuration());

        // ⚠️ Do not update createdAt (kept immutable)
        return courseRepository.save(existingCourse);
    }








}
