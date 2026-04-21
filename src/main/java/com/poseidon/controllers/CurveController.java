package com.poseidon.controllers;

import com.poseidon.domain.CurvePoint;
import com.poseidon.services.CurvePointService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Controller
public class CurveController {
    // DONE: Inject Curve Point service

    private static final Logger logger = LogManager.getLogger(CurveController.class);

    private final CurvePointService curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // DONE: find all Curve Point, add to model
        logger.info("/curvePoint/list");
        model.addAttribute("curvePoint", curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        logger.info("/curvePoint/add");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, @ModelAttribute("curvePoint") BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Curve list
        logger.info("/curvePoint/validate");
        if (result.hasErrors()) {
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/add";
        }
        curvePointService.save(curvePoint);
        return "redirect:curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get CurvePoint by Id and to model then show to the form
        logger.info("/curvePoint/update/{}", id);
        model.addAttribute("curvePoint", curvePointService.findById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Curve and return Curve list
        logger.info("/curvePoint/update/{}", id);
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoint", curvePoint);
        return "redirect:/curvePoint/list";

    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Curve by Id and delete the Curve, return to Curve list
        logger.error("/curvePoint/delete/{}", id);
        curvePointService.delete(id);
        model.addAttribute("users", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }
}
