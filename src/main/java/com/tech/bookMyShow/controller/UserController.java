package com.tech.bookMyShow.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.bookMyShow.entity.Booking;
import com.tech.bookMyShow.entity.User;

import com.tech.bookMyShow.service.TheaterService;
import com.tech.bookMyShow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private TheaterService theaterService;
   // @Autowired
   // private MovieService movieService;

    @GetMapping("/login")
    public ModelAndView login(){
        log.info("Inside controller");
        System.out.println("login page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

 /*   @GetMapping("/seat")
    public ModelAndView seat(){
        log.info("Inside controller");
        System.out.println("seat page");
        Booking booking = new Booking();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("booking", booking);
        modelAndView.setViewName("seatBooking");
        return modelAndView;
    }*/
    @PostMapping(value = "/booking")
    public ModelAndView addBooking(@Valid Booking booking, BindingResult bindingResult, ModelMap modelMap) {
        System.out.println("inside addBooking post method");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(booking.getMovieId());
        System.out.println(booking.getSeats());
        modelAndView.setViewName("seatBooking");
        return modelAndView;
    }
    @GetMapping("/registration")
    public ModelAndView registration(){
        System.out.println("Inside registration getmethod");
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView home(){
        System.out.println("seat page");
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        System.out.println( loggedInUser.getName());
        User user=userService.findByUserName(loggedInUser.getName());
        Booking booking = new Booking();
        booking.setUserId(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("booking", booking);
        modelAndView.addObject("username",user.getUserName());
        modelAndView.addObject("userId",user.getId());
        System.out.println("getMovieList");
       // modelAndView.addObject("countryList", movieService.getMovie());
       // System.out.println("set movies into modeland view"+countryList);
        modelAndView.setViewName("seatBooking");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        System.out.println("inside registration post method");
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUserName(user.getUserName());
        //check for the validations
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
    @GetMapping("/admin")
    public ModelAndView adminHome(){
        System.out.println("admin getMethod");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }
   /* @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }*/



}
