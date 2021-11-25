package com.tech.bookMyShow.service.impl;

import com.tech.bookMyShow.entity.Theater;
import com.tech.bookMyShow.exception.DuplicateRecordException;
import com.tech.bookMyShow.exception.RecordNotFoundException;
import com.tech.bookMyShow.repository.TheaterRepository;
import com.tech.bookMyShow.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TheaterServiceImpl implements TheaterService {

       @Autowired
        private TheaterRepository theaterRepo;

       @Override
       @Transactional
        public Integer addTheater(Theater theater) throws DuplicateRecordException {
            log.info("TheatreServiceImpl  add method start");
              // Optional<Theater> existTheater = theaterRepo.findByName(theater.getName());
               Theater existTheater= theaterRepo.findByName(theater.getName());
                if (existTheater!=null) {
                    System.out.println("Theatre already exist");
                   throw new DuplicateRecordException("Theatre is already Exist");
                }
                //long theaterId =
                     theater= theaterRepo.save(theater);
                log.info("TheatreServiceImpl add method end");
                return theater.getId();
        }

    @Override
    @Transactional
    public List<Theater> getAllTheaters()  {
        log.info("TheatreServiceImpl  add method start");
        return theaterRepo.findAll();
    }

        @Override
        @Transactional
        public void updateTheater(Theater theater) throws DuplicateRecordException {
            log.info("TheatreServiceImpl  update method start");
            Theater existTheater = theaterRepo.findByName(theater.getName());

            if (existTheater != null && existTheater.getId() != theater.getId()) {
                throw new DuplicateRecordException("Theatre is already Exist");
            }

            theaterRepo.save(theater);
            log.info("TheatreServiceImpl update method end");

        }

        @Override
        @Transactional
        public void deleteTheaterById(Integer id) throws RecordNotFoundException {
            log.info("TheatreServiceImpl  delete method start");
            Theater theater = theaterRepo.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("theater not found for this id :: " + id));

            theaterRepo.delete(theater);
        }

        @Override
        @Transactional
        public Theater getTheaterByName(String name) {
            log.info("TheatreServiceImpl  getTheaterByName method start");
            Theater theater = theaterRepo.findByName(name);
            log.info("TheatreServiceImpl getTheaterByName method end");
            return theater;
        }

        @Override
        @Transactional
        public Theater getTheaterById(Integer id) throws RecordNotFoundException {
            log.info("TheatreServiceImpl  getTheaterById method start");
            Theater theater = theaterRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("Theater not found for this id :: " + id));
            log.info("TheatreServiceImpl getTheaterById method end");
            return theater;
        }
//
//
//       /* @Override
//        @Transactional
//        public List<Theater> search(Theater theater) {
//            log.info("TheatreServiceImpl  search method start");
//            List<Theater> list = theaterRepo.search(theater);
//            log.info("TheatreServiceImpl search method end");
//            return list;
//        }*/
//
}
