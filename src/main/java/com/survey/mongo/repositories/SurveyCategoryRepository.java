package com.survey.mongo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.survey.model.SurveyCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyCategoryRepository extends CrudRepository<SurveyCategory, String>{

}
