package com.Coupons.SingInservice.controller;

import com.Coupons.SingInservice.model.userData;
import com.Coupons.SingInservice.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/api")
public class UserDataController {

    @Autowired
    userRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity<userData> createUser(@RequestBody userData userData) {
        try {
            userData _userData = (userData) userRepository.save(new userData(userData.getName(), userData.getEmail(), userData.getPhno(), userData.getPassword()));
            return new ResponseEntity<>(_userData, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user")
    public ResponseEntity<List<userData>> getAllUsers(@RequestParam(required = false) String name) {
        List<userData> userData = new ArrayList<userData>();

        try {
            if (name == null)
                userRepository.findAll().forEach(userData::add);
            else
                userRepository.findByName(name).forEach(userData::add);

            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/sign-in")
    public ResponseEntity<userData> authenticateUser(@RequestParam(required = true) String email, @RequestParam(required = true) String password) {
        Optional<userData> userData = userRepository.findByEmail(email);

        if(userData.isPresent()) {

            if(userData.get().getPassword().equals(password)) {
                return new ResponseEntity<>(userData.get(),HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<userData> updateUser(@PathVariable("id") String id, @RequestBody userData userData) {
        Optional<userData> userData1 = userRepository.findById(id);

        if (userData1.isPresent()) {
            userData _userData = userData1.get();
            _userData.setName(userData1.get().getName());
            _userData.setEmail(userData1.get().getEmail());
            _userData.setPassword(userData1.get().getPassword());
            _userData.setPhno(userData1.get().getPhno());
            return new ResponseEntity<userData>((userData) userRepository.save(_userData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
