package com.abhishek.joblisting.repository;

import com.abhishek.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends MongoRepository<Post, String> {


    @Query("{'$or':[ {'title': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'}}] }")
    List<Post> findByText(String text);
    //List<Post> findByText(String text);

}