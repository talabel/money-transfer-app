package com.mastermind.moneytransferapp.repository;

import com.mastermind.moneytransferapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Iterable<User> findTop10ByFirstNameAndLastName(String firstName, String lastName);

    Iterable<User> findTop10ByFirstName(String firstName);

    Iterable<User> findTop10ByLastName(String lastName);

    @Query(nativeQuery = true, value = "SELECT * FROM users limit 10")
    Iterable<User> findAllTopTen();
}
