package com.srimathi.project1sb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.srimathi.project1sb.model.User;
import com.srimathi.project1sb.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // check username
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists ❌");
        }

        // check email
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists ❌");
        }

        user.setRole("USER");

        return ResponseEntity.ok(userRepo.save(user));
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {

        String username = data.get("username");
        String password = data.get("password");

        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.status(401).body("Invalid credentials ❌");
    }
}