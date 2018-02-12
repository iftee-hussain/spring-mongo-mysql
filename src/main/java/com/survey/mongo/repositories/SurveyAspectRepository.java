package com.survey.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import com.survey.model.SurveyAspect;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyAspectRepository extends MongoRepository<SurveyAspect, String>{

}
