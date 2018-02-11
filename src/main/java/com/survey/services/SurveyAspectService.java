package com.survey.services;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.survey.model.SurveyAspect;
import com.survey.model.SurveyCategory;

public interface SurveyAspectService {
	List<SurveyAspect> findAll();
	@Query(value="{ 'surveyCategory.$id' : ?0 }", fields="{ 'surveyCategory.$id' : 1 }")
	List<SurveyAspect>findBySurveyCategory(SurveyCategory surveyCategory);
	SurveyAspect getById(String id);
	SurveyAspect save(SurveyAspect surveyAspect);
	void delete(SurveyAspect surveyAspect);
}
