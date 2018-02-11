package com.survey.services;

import java.util.List;

import com.survey.mongo.model.SurveyAspect;
import com.survey.mongo.model.SurveyRating;

public interface SurveyRatingService {
	List<SurveyRating> findAll();
	List<SurveyRating>findBySurveyAspect(SurveyAspect surveyAspect);
	SurveyRating getById(String id);
	SurveyRating save(SurveyRating surveyRating);
	void delete(SurveyRating surveyRating);
	Integer getAvgRatingBySurveyAspect(SurveyAspect surveyAspect);
}
