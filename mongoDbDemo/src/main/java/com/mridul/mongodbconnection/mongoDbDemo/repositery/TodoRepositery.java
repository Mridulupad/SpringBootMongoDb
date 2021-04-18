package com.mridul.mongodbconnection.mongoDbDemo.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mridul.mongodbconnection.mongoDbDemo.model.TodoDTO;

@Repository
public interface TodoRepositery extends MongoRepository<TodoDTO,String> {

}
