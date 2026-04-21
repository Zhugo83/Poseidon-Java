package com.poseidon.controllers;

import com.poseidon.domain.Trade;
import com.poseidon.repositories.TradeRepository;
import com.poseidon.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Controller
public class TradeController {

    // TODO: Inject Trade service

    private static final Logger logger = LogManager.getLogger(TradeController.class);

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // DONE: find all Trade, add to model
        logger.info("/trade/list");
        model.addAttribute("trade", tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        logger.info("/trade/add");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, @ModelAttribute("trade") BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Trade list
        logger.info("/trade/validate");
        if (result.hasErrors()) {
            logger.error("/trade/validate");
            model.addAttribute("trade", trade);
            return "trade/add";
        }
        tradeService.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Trade by Id and to model then show to the form
        logger.error("/trade/update/{}", id);
        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Trade and return Trade list
        logger.info("/trade/update/{}", id);
        if (result.hasErrors()) {
            logger.error("/trade/update/{}", id);
            return "/trade/update";
        }
        trade.setTradeId(id);
        tradeService.save(trade);
        model.addAttribute("trade", tradeService.findAll());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Trade by Id and delete the Trade, return to Trade list
        logger.info("/trade/delete/{}", id);
        tradeService.delete(id);
        model.addAttribute("users", tradeService.findAll());
        return "redirect:/trade/list";
    }
}
