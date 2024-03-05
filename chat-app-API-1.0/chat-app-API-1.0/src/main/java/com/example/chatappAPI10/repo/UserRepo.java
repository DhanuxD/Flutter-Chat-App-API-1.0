package com.example.chatappAPI10.repo;

import com.example.chatappAPI10.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<Users,String> {
    Users findByEmail(String email);
    @Query("{ email: { $regex: '^?0'} }")
    List<Users> findByUserEmailContaining(String requestInputs);


}
