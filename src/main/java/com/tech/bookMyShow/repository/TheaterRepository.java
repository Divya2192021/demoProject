package com.tech.bookMyShow.repository;

import com.tech.bookMyShow.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {
    Theater findByName(String name);

}
