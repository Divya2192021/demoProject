package com.tech.bookMyShow.controller;

import com.tech.bookMyShow.entity.Theater;
import com.tech.bookMyShow.exception.DuplicateRecordException;
import com.tech.bookMyShow.exception.RecordNotFoundException;
import com.tech.bookMyShow.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/adminHome")
public class TheaterController {
    @Autowired
    TheaterService service;

    @GetMapping("/theaters")
    public ResponseEntity<List<Theater>> getAllTheaters() {
        System.out.println("1");
        try{
            List<Theater> theaterList = service.getAllTheaters();
            if (theaterList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(theaterList, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/theaters/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable("id") int id)
            throws RecordNotFoundException {
        Theater theater = service.getTheaterById(id);

        return new ResponseEntity<Theater>(theater, HttpStatus.OK);
    }

    @GetMapping("/theaters/{name}")
    public ResponseEntity<Theater> getTheaterByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        Theater theater = service.getTheaterByName(name);

        return new ResponseEntity<Theater>(theater, HttpStatus.OK);
    }

    @PostMapping("/theaters/new" )
    public ResponseEntity<Integer> addNewTheater(@Valid @RequestBody Theater theater)
    {
        System.out.println("inside theaterController");
        try{
            int _theater= service.addTheater(theater);
            return new ResponseEntity<>(_theater, HttpStatus.CREATED);
    } catch (DuplicateRecordException e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @PutMapping("/theaters/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable(value = "id") Integer theaterId,
                                                   @Valid @RequestBody Theater theaterDetails) throws RecordNotFoundException {
        Theater theater = service.getTheaterById(theaterId);

        return ResponseEntity.ok(theater);
    }

    @DeleteMapping("/theaters/{id}")
    public Map<String, Boolean> deleteTheaterById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        service.deleteTheaterById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
