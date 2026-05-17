package com.srimathi.project1sb;

import com.srimathi.project1sb.model.User;
import com.srimathi.project1sb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public String register(
            @RequestBody User user
    ) {

        if (
                userRepository
                        .findByUsername(
                                user.getUsername()
                        )
                        .isPresent()
        ) {

            return "Username Already Exists";
        }

        if (
                userRepository
                        .findByEmail(
                                user.getEmail()
                        )
                        .isPresent()
        ) {

            return "Email Already Exists";
        }

        userRepository.save(user);

        return "Registration Successful";
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public User login(
            @RequestBody User loginUser
    ) {

        return userRepository

                .findByUsername(
                        loginUser.getUsername()
                )

                .filter(

                        user -> user
                                .getPassword()
                                .equals(
                                        loginUser.getPassword()
                                )
                )

                .orElse(null);
    }

    // =========================
    // GET PROFILE
    // =========================

    @GetMapping("/profile/{username}")
    public User getProfile(
            @PathVariable String username
    ) {

        return userRepository
                .findByUsername(username)
                .orElse(null);
    }

    // =========================
    // UPDATE PROFILE
    // =========================

    @PutMapping("/profile/{id}")
    public User updateProfile(

            @PathVariable Long id,

            @RequestBody User updatedUser
    ) {

        User user = userRepository
                .findById(id)
                .orElseThrow();

        user.setUsername(
                updatedUser.getUsername()
        );

        user.setEmail(
                updatedUser.getEmail()
        );

        user.setPhone(
                updatedUser.getPhone()
        );

        user.setAddress(
                updatedUser.getAddress()
        );

        return userRepository.save(user);
    }

    // =========================
    // GET ALL USERS
    // =========================

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}