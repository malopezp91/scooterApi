package com.miguel.demo.repository;

import com.miguel.demo.model.Scooter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends CrudRepository<Scooter, String> {

}
