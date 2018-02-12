package com.survey.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.survey.model.SurveyRating;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRatingrepository extends MongoRepository<SurveyRating, String>{

}
