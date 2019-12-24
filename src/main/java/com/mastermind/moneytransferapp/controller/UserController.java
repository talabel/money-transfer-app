package com.mastermind.moneytransferapp.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mastermind.moneytransferapp.model.Transaction;
import com.mastermind.moneytransferapp.model.User;
import com.mastermind.moneytransferapp.repository.TransactionRepository;
import com.mastermind.moneytransferapp.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    @ExceptionHandler({NoSuchElementException.class, JsonMappingException.class})
    public ModelAndView handleException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("custom-error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Iterable<User>> findAll() {
        Iterable<User> savedUsers = userRepository.findAll();

        if(!savedUsers.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedUsers);
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> create(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User newUser = optionalUser.get();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());

        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);


    }

    @GetMapping(value = "/search")
    public ResponseEntity<Iterable<User>> search(@RequestParam(required = false, name = "firstName") String firstName,
                                                 @RequestParam(required = false, name = "lastName") String lastName) {

        if(!userRepository.findTop10ByFirstNameAndLastName(firstName, lastName).iterator().hasNext() && !userRepository.findTop10ByFirstName(firstName).iterator().hasNext() && !userRepository.findTop10ByLastName(lastName).iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }


        Iterable<User> iterable;
        if(firstName != null && lastName != null) {
            iterable = userRepository.findTop10ByFirstNameAndLastName(firstName, lastName);
        }
        else if(firstName != null) {
            iterable = userRepository.findTop10ByFirstName(firstName);
        }
        else if(lastName != null) {
            iterable = userRepository.findTop10ByLastName(lastName);
        }
        else {
            iterable = userRepository.findAllTopTen();
        }

        return ResponseEntity.ok(iterable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{id}/transactions/")
    public ResponseEntity<Iterable<Transaction>> findTransactionsByUserId(@PathVariable Long id) {
        Iterable<Transaction> transactions = transactionRepository.findByUserId(id);
        if(!transactions.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transactions);

    }



















}
