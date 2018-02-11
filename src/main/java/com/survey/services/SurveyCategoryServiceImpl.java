package com.survey.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.survey.model.SurveyCategory;
import com.survey.repositories.SurveyCategoryRepository;

@Service
public class SurveyCategoryServiceImpl implements SurveyCategoryService{
	
	private SurveyCategoryRepository surveyCategoryRepository;
	private MongoOperations mongoOperation;
    
   @Autowired
    public SurveyCategoryServiceImpl(SurveyCategoryRepository surveyCategoryRepository, MongoOperations mongoOperation) {
        this.surveyCategoryRepository = surveyCategoryRepository;
        this.mongoOperation = mongoOperation;
       
    }

	@Override
	public List<SurveyCategory> findAll() {
		List<SurveyCategory> surveyCategoriesArray = new ArrayList<>();
		surveyCategoryRepository.findAll().forEach(surveyCategoriesArray::add);
		return surveyCategoriesArray;
	}

	@Override
	public SurveyCategory getById(String id) {
		return surveyCategoryRepository.findById(id).orElse(null);
	}

	@Override
	public SurveyCategory save(SurveyCategory surveyCategory) {
		surveyCategoryRepository.save(surveyCategory);
        return surveyCategory;
	}

	@Override
	public SurveyCategory saveOrUpdate(SurveyCategory surveyCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SurveyCategory surveyCategory) {
		surveyCategoryRepository.delete(surveyCategory);
		
	}
	@Override
	public List<SurveyCategory> findByIsParent() {
		List<SurveyCategory> surveyCategoriesArray = new ArrayList<>();
		Query query = new Query(Criteria.where("isParent").is(1));
		System.out.println(query.toString());
		mongoOperation.find(query, SurveyCategory.class).forEach(surveyCategoriesArray::add);
		return surveyCategoriesArray;
	}



}
