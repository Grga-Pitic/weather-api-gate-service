package com.pet.main.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pet.main.api.model.Api;

public interface ApiRepository extends CrudRepository<Api, Integer>{


    List<Api> findByName(String name);

}
