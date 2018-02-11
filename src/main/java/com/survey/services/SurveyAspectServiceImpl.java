package com.survey.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.survey.mongo.model.SurveyAspect;
import com.survey.mongo.model.SurveyCategory;
import com.survey.repositories.SurveyAspectRepository;
@Service
public class SurveyAspectServiceImpl implements SurveyAspectService{
	private SurveyAspectRepository surveyAspectRepository;
    private MongoOperations mongoOperation;
	
    @Autowired
	public SurveyAspectServiceImpl(SurveyAspectRepository surveyAspectRepository, MongoOperations mongoOperation) {
		this.surveyAspectRepository = surveyAspectRepository;
		this.mongoOperation = mongoOperation;
	}

	@Override
	public List<SurveyAspect> findAll() {
		List<SurveyAspect> surveyAspectList = new ArrayList<>();
		surveyAspectRepository.findAll().forEach(surveyAspectList::add);
		return surveyAspectList;
	}

	@Override
	public List<SurveyAspect> findBySurveyCategory(SurveyCategory surveyCategory) {
		List<SurveyAspect> surveyAspectList = new ArrayList<>();
		Query query = new Query();
		String surveyCategoryId =  surveyCategory.get_id();
		System.out.println(surveyCategoryId);
		query.addCriteria(Criteria
				.where("surveyCategory.$id").is(new ObjectId(surveyCategoryId)));
	    System.out.println(query.toString());
	    mongoOperation.find(query, SurveyAspect.class).forEach(surveyAspectList::add); 
	    return surveyAspectList;
		
	}
	

	@Override
	public SurveyAspect getById(String id) {
		return surveyAspectRepository.findById(id).orElse(null);
	}

	@Override
	public SurveyAspect save(SurveyAspect surveyAspect) {
		surveyAspectRepository.save(surveyAspect);
        return surveyAspect;
	}

	@Override
	public void delete(SurveyAspect surveyAspect) {
		surveyAspectRepository.delete(surveyAspect);
		
	}

}
