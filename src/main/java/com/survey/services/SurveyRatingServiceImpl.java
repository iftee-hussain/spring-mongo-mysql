package com.survey.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.survey.model.SurveyAspect;
import com.survey.model.SurveyRating;
import com.survey.repositories.SurveyRatingrepository;

@Service
public class SurveyRatingServiceImpl implements SurveyRatingService{
	private SurveyRatingrepository surveyRatingrepository;
	private MongoOperations mongoOperation;
    
   @Autowired
    public SurveyRatingServiceImpl(SurveyRatingrepository surveyRatingrepository, MongoOperations mongoOperation) {
        this.surveyRatingrepository = surveyRatingrepository;
        this.mongoOperation = mongoOperation;
       
    }
	@Override
	public List<SurveyRating> findAll() {
		List<SurveyRating> surveyratingsArray = new ArrayList<>();
		surveyRatingrepository.findAll().forEach(surveyratingsArray::add);
		return surveyratingsArray;
	}

	@Override
	public List<SurveyRating> findBySurveyAspect(SurveyAspect surveyAspect) {
		List<SurveyRating> surveyRatingList = new ArrayList<>();
		Query query = new Query();
		String surveyAspectId =  surveyAspect.get_id();
		query.addCriteria(Criteria
				.where("surveyAspect.$id").is(new ObjectId(surveyAspectId)));
		query.with(new Sort(Sort.Direction.DESC,"createdDate"));
		
	    System.out.println(query.toString());
	    mongoOperation.find(query, SurveyRating.class).forEach(surveyRatingList::add); 
	    return surveyRatingList;
	}

	@Override
	public SurveyRating getById(String id) {
		return surveyRatingrepository.findById(id).orElse(null);
	}

	@Override
	public SurveyRating save(SurveyRating surveyRating) {
		surveyRatingrepository.save(surveyRating);
        return surveyRating;
	}

	@Override
	public void delete(SurveyRating surveyRating) {
		surveyRatingrepository.delete(surveyRating);
		
	}
	@Override
	public Integer getAvgRatingBySurveyAspect(SurveyAspect surveyAspect) {
		
		String surveyAspectId =  surveyAspect.get_id();
		Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("surveyAspect.$id").is(new ObjectId(surveyAspectId)))
                 ,Aggregation.group("surveyAspect.$id").sum("rating").as("rating").count().as("count")
        );
		System.out.println(agg.toString());
		AggregationResults<SurveyRating> groupResults = mongoOperation.aggregate(agg, "surveyRating", SurveyRating.class);
		List<SurveyRating>surveyAvgRating = groupResults.getMappedResults();
		Integer countRatings = groupResults.getMappedResults().get(0).getCount();
		Integer totalRating = groupResults.getMappedResults().get(0).getRating();
		if(countRatings >0 && totalRating != null) {
			return totalRating/countRatings;
		}
		return 0;
	}

}
