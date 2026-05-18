package com.srimathi.project1sb;

import com.srimathi.project1sb.model.Feedback;
import com.srimathi.project1sb.repository.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackRepository
            feedbackRepository;

    // ======================
    // ADD FEEDBACK
    // ======================

    @PostMapping("/add")
    public Feedback addFeedback(

            @RequestBody
            Feedback feedback
    ) {

        return feedbackRepository
                .save(feedback);
    }

    // ======================
    // GET ALL FEEDBACK
    // ======================

    @GetMapping("/all")
    public List<Feedback>
    getAllFeedback() {

        return feedbackRepository
                .findAll();
    }
}