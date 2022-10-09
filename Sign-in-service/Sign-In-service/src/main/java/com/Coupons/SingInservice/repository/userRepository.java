package com.Coupons.SingInservice.repository;

import com.Coupons.SingInservice.model.userData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface userRepository extends MongoRepository<userData, String> {

    List<userData> findByName(String name);

    Optional<userData> findByEmail(String email);


}
