package com.poseidon.services;

import com.poseidon.domain.BidList;
import com.poseidon.domain.Rating;
import com.poseidon.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating findById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Id not found"));
    }

    public Rating save(Rating rating){
        return ratingRepository.save(rating);
    }

    public void delete(Integer id) {
        ratingRepository.deleteById(id);
    }
}
