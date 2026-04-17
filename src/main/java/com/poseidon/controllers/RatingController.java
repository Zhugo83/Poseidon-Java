package com.poseidon.controllers;

import com.poseidon.domain.Rating;
import com.poseidon.repositories.RatingRepository;
import com.poseidon.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class RatingController {
    // DONE: Inject Rating service

    private static final Logger logger = LogManager.getLogger(RatingController.class);

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.findAll());
        return  "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("/rating/add");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Rating list
        logger.info("/rating/validate");
        if (result.hasErrors()) {
            logger.error("/rating/validate");
            ratingService.save(rating);
            model.addAttribute("rating", ratingService.findAll());
            return "redirect:rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Rating by Id and to model then show to the form
        logger.info("/rating/update/ {}", id);
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Rating and return Rating list
        logger.info("/rating/update/ {}", id);
        if (result.hasErrors()) {
            logger.error("/rating/update/ {}", id);
            return "rating/update";
        }
        rating.setId(id);
        ratingService.save(rating);
        model.addAttribute("rating", rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Rating by Id and delete the Rating, return to Rating list
        logger.info("/rating/delete/ {}", id);
        Rating rating = ratingService.findById(id);
        ratingService.delete(rating);
        model.addAttribute("users", ratingService.findAll());
        return "redirect:/rating/list";
    }
}
