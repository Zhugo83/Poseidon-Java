package com.poseidon.controllers;

import com.poseidon.domain.BidList;
import com.poseidon.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Controller
public class BidListController {
    // DONE: Inject Bid service

    private static final Logger logger = LogManager.getLogger(BidListController.class);

    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        logger.info("/bidList/list");
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(@PathVariable Integer id, Model model, BidList bid) {
        logger.info("/bidList/add");
        bidListService.findById(id);
        model.addAttribute("bidList", bid);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid,
                           BindingResult result,
                           Model model) {
        // DONE: check data valid and save to db, after saving return bid list
        logger.info("/bidList/validate");
        if (result.hasErrors()) {;
            return "bidList/add";
        }
        bidListService.save(bid);
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect: /bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Bid by Id and to model then show to the form
        logger.info("/bidList/update/{}", id);
        model.addAttribute("bidList", bidListService.findById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Bid and return list Bid
        logger.info("/bidList/update/{}", id);
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidList.setBidListId(id);
        bidListService.save(bidList);
        model.addAttribute("bidList", bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Bid by Id and delete the bid, return to Bid list
        logger.info("/bidList/delete/{}", id);
        bidListService.delete(id);
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }
}
