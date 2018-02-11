package com.survey.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.survey.model.SurveyAspect;


public interface SurveyAspectRepository extends MongoRepository<SurveyAspect, String>{

}
