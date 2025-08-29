package com.blue_economy.controller;

import com.blue_economy.dto.PaymentRequest;
import com.blue_economy.dto.PaymentResponse;
import com.blue_economy.model.Course;
import com.blue_economy.model.Event;
import com.blue_economy.model.EventRegisterMember;
import com.blue_economy.model.OnlineCourse;
import com.blue_economy.service.*;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173") // enable CORS for frontend
public class PaymentController {

    private final PaymentService paymentService;
    private final EventService eventService;
    private final CourseService courseService;
    private final UserService userService;
    private  final OnlineCourseService onlineCourseService;

    public PaymentController(PaymentService paymentService,
                             EventService eventService,
                             CourseService courseService,
                             UserService userService,OnlineCourseService onlineCourseService) {
        this.paymentService = paymentService;
        this.eventService = eventService;
        this.courseService = courseService;
        this.userService = userService;
        this.onlineCourseService = onlineCourseService;
    }

    // ✅ CREATE A PAYMENT
    @PostMapping("/create/payment")
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) throws StripeException {
        // The PaymentResponse returned now includes paymentId (local DB ID)
        return paymentService.processPayment(request);
    }

    // ✅ CONFIRM PAYMENT
    @PostMapping("/confirm/payment/{paymentId}")
    public ResponseEntity<String> confirmPayment(@PathVariable Long paymentId) {
        paymentService.confirmPayment(paymentId); // update DB status to confirmed
        return ResponseEntity.ok("Payment confirmed successfully!");
    }

    // ------------------- COURSES -------------------
    @GetMapping("/get/all/course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping("/get/course/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id);
    }

    // ------------------- EVENTS -------------------
    @GetMapping("/get/all/event")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/get/event/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }


    // ------------------- EVENT MEMBERS -------------------

    @PostMapping("/register/event/member")
    public EventRegisterMember registerEventMem(@RequestBody EventRegisterMember event) {
        return eventService.registerEvent(event);
    }



/// //ONLINE COURSE
@GetMapping("/get/all/online/course")
public List<OnlineCourse> getAllOnlineCourses() {
    return onlineCourseService.getAllOnlineCourse();
}


}
