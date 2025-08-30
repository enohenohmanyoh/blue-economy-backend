package com.blue_economy.controller;


import com.blue_economy.model.*;
import com.blue_economy.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
//@CrossOrigin(origins = "http://localhost:5175")

public class AdminController{


    private final CourseService courseService;
    private final PaymentService paymentService;
    private final EventService eventService;
    private final UserService userService;
    private  final OnlineCourseService onlineCourseService;

    public AdminController(CourseService courseService, PaymentService paymentService,
                           EventService eventService, UserService userService,OnlineCourseService onlineCourseService) {
        this.paymentService = paymentService;
        this.courseService = courseService;
        this.eventService = eventService;
        this.userService = userService;
        this.onlineCourseService = onlineCourseService;
    }


    //PAYMENT SERVICE
    @GetMapping("/get/all/payment")
    public List<Payment> getAllPayments() {
        return paymentService.listPayments();
    }

    @DeleteMapping("/delete/payment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }



    ///USER ---------------------------------------------------

    @GetMapping("/get/all/user")
    public  List<User> getAllUsers (){
        return   userService.getAllUsers();
    }
    @GetMapping("/get/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id); // throws CustomerException if not found
        return ResponseEntity.ok(user); // 200 OK with user JSON
    }


    @DeleteMapping("/delete/user/{id}")
    public void deleteUserById (@PathVariable Long id){
        userService.getUserById(id);
    }



    //COURSE THINGS
    // Create a new course
    @PostMapping("/create/course")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Get all courses
    @GetMapping("/get/all/course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    // Get a course by ID
    @GetMapping("/get/course/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id);
    }

    @DeleteMapping("/delete/course/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/course/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

///  ONLINE COURSES --------------------------
// Create a new course
@PostMapping("/create/online/course")
public OnlineCourse createOnlineCourse(@RequestBody OnlineCourse onlineCourse) {
    return onlineCourseService.createOnlineCourse(onlineCourse);
}

    @GetMapping("/get/all/online/course")
    public List<OnlineCourse> getAllOnlineCourses() {
        return onlineCourseService.getAllOnlineCourse();
    }


    @PutMapping("/update/online/course/{id}")
    public OnlineCourse updateOnlineCourse(@PathVariable Long id, @RequestBody OnlineCourse onlineCourse) {
        return onlineCourseService.updateOnlineCourse(id,onlineCourse);
    }


    @DeleteMapping("/delete/online/course/{id}")
    public ResponseEntity<Void> deleteOnlineCourse(@PathVariable Long id) {
        onlineCourseService.deleteOnlineCourse(id);
        return ResponseEntity.noContent().build();
    }






//CREATE EVENT THINGS

    @PostMapping ("/create/event")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }



    @GetMapping("/get/all/event")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }


    @PutMapping("/update/event/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/delete/event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    /// ------------REGISTER EVEN THINGS

    @GetMapping("/get/all/event/member")
    public List<EventRegisterMember> getAllEventsMem() {
        return eventService.getAllEventMembers();
    }

    @GetMapping("/get/member/{id}")
    public EventRegisterMember getEventMemberById(@PathVariable Long id) {
        return eventService.getEventMemberById(id);
    }

    @DeleteMapping("/delete/member/{id}")
    public void deleteRegisterMemById(@PathVariable Long id) {
        eventService.deleteEventMember(id);
    }

    @PutMapping("/update/event/member/{id}")
    public Event updateEventMember(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

}
