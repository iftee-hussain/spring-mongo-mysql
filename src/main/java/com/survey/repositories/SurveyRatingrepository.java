package com.survey.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.survey.model.SurveyRating;

public interface SurveyRatingrepository extends MongoRepository<SurveyRating, String>{

}
