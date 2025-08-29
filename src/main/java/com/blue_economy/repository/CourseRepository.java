package com.blue_economy.repository;

import com.blue_economy.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
