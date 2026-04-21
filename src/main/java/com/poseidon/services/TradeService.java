package com.poseidon.services;

import com.poseidon.domain.Trade;
import com.poseidon.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade findById(Integer id) {
        return tradeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Id not found"));
    }

    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void delete(Integer id) {
        tradeRepository.deleteById(id);
    }

}
