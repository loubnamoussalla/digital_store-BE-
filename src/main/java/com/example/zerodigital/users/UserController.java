package com.example.zerodigital.users;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shared.CustomizedResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity registerUser(@RequestBody User user) {

        // Check if user with email already exists
        User ifExisted = userService.getUserByEmail(user.getEmail());
        if (ifExisted != null) {
            var response = new CustomizedResponse("User with this email is already existed", null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // Validate email format
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            var response = new CustomizedResponse("Invalid email format", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Validate password length
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            var response = new CustomizedResponse("Error Password must be at least 6 characters", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Validate firstName and lastName (alphabetical only)
        if (!user.getFirstName().matches("^[a-zA-Z]+$") || !user.getLastName().matches("^[a-zA-Z]+$")) {
            var response = new CustomizedResponse(
                    "First and Last names must contain only letters (A–Z, a–z)",null );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // All validations passed — save the user
        var response = new CustomizedResponse("User created successfully", Collections.singletonList(userService.register(user)));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {

        // Validate ObjectId format BEFORE hitting the DB
        if (!ObjectId.isValid(id)) {
            var response = new CustomizedResponse(
                    "Invalid user ID format",
                    Collections.singletonList("The provided ID is not a valid MongoDB ObjectId: " + id)
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //  Lookup user by ID
        User user = userService.getUserById(id);

        if (user == null) {
            var response = new CustomizedResponse(
                    "User not found",
                    Collections.singletonList("No user exists with ID: " + id)
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        var response = new CustomizedResponse("User with ID: " + id,Collections.singletonList(user)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
