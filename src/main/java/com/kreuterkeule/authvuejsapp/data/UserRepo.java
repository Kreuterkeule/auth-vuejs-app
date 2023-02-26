package com.kreuterkeule.authvuejsapp.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    // SELECT 1 FROM users u where u.email = :email
    Optional<UserEntity> findByEmail(String email);

}
