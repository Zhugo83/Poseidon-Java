package com.poseidon.services;

import com.poseidon.domain.BidList;
import com.poseidon.repositories.BidListRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Id not found"));
    }

    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public void delete(Integer id) {
        bidListRepository.deleteById(id);
    }
}
