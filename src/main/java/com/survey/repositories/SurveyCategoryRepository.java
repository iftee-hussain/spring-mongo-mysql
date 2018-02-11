package com.survey.repositories;

import org.springframework.data.repository.CrudRepository;

import com.survey.model.SurveyCategory;

public interface SurveyCategoryRepository extends CrudRepository<SurveyCategory, String>{

}
