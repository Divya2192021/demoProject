package com.tech.bookMyShow.service;

import com.tech.bookMyShow.entity.Theater;
import com.tech.bookMyShow.exception.DuplicateRecordException;
import com.tech.bookMyShow.exception.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TheaterService {

     List<Theater> getAllTheaters();

     Integer addTheater(Theater theater) throws DuplicateRecordException;
     void updateTheater(Theater theater) throws DuplicateRecordException;
    void deleteTheaterById(Integer theater) throws RecordNotFoundException;

//    Optional<Theater> getTheaterByName(String name);
Theater getTheaterByName(String name);

    Theater getTheaterById(Integer id) throws RecordNotFoundException;
//
//
//   // public List<Theater> search(Theater theater);
//
//
//
//
}
