package com.survey.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.survey.model.SurveyCategory;

@Service
public interface SurveyCategoryService {
	List<SurveyCategory> findAll();
	List<SurveyCategory>findByIsParent();
	SurveyCategory getById(String id);
	SurveyCategory save(SurveyCategory surveyCategory);
	SurveyCategory saveOrUpdate(SurveyCategory surveyCategory);
	void delete(SurveyCategory surveyCategory);

}
