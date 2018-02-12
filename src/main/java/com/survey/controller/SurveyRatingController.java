package com.survey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.model.SurveyAspect;
import com.survey.model.SurveyRating;
import com.survey.services.SurveyAspectService;
import com.survey.services.SurveyRatingService;

@RestController
public class SurveyRatingController {

	@Autowired
	private SurveyRatingService surveyRatingService;
	
	@Autowired
	private SurveyAspectService surveyAspectService;
	
	 /**
	  * This method returns all ratings
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.GET, value="/ratings")
	    public Iterable<SurveyRating> surveyRating() {
	        return surveyRatingService.findAll();
	    }
	/**
	 * This method creates a new SurveyRating object upon POST request
	 * {
		"rating" : 3,
		"comment" : "This is another demo comment on chicken burger.",
		"userId" : "1234",
		"surveyAspect": {
	        "_id": "5a7fc965b7318c1328e42c7a",
	        "aspectName": "Chicken Burger",
	        "surveyCategory": {
	            "_id": "5a7e886eb7318c256a018079",
	            "name": "Burger",
	            "isParent": null,
	            "hasAspect": 1,
	            "childSurveyCategories": null,
	            "imageUrl": "/image/burger.jpg"
	        },
	        "imageUrl": "/images/chickenburger.jpg"
	    }
	}
	 * @param surveyRating
	 * @return
	 */
	    @RequestMapping(method=RequestMethod.POST, value="/ratings")
	    public SurveyRating save(@RequestBody SurveyRating surveyRating) {
	    	surveyRatingService.save(surveyRating);
	    	return surveyRating;
	    }
	    
	    /**
	     * This method returns a SurveyRating object by id
	     * @param id
	     * @return
	     */
	    @RequestMapping(method=RequestMethod.GET, value="/ratings/{id}")
	    public SurveyRating show(@PathVariable String id) {
	        return surveyRatingService.getById(id);
	    }
	  /**
	   *  This method updates a SurveyRating object by id
	   * @param id
	   * @param surveyRating
	   * @return
	   */
	    @RequestMapping(method=RequestMethod.PUT, value="/ratings/{id}")
	    public SurveyRating update(@PathVariable String id, @RequestBody SurveyRating surveyRating) {
	    	SurveyRating surveyRat = surveyRatingService.getById(id);
	        if(surveyRating.getComment()!= null)
	        	surveyRat.setComment(surveyRating.getComment());
	        if(surveyRating.getRating()!= null)
	        	surveyRat.setRating(surveyRating.getRating());
	        
	        surveyRatingService.save(surveyRat);
	        return surveyRat;
	    }
	    /**
	     * This method deletes a SurveyRating object
	     * @param id
	     * @return
	     */
	    @RequestMapping(method=RequestMethod.DELETE, value="/ratings/{id}")
	    public String delete(@PathVariable String id) {
	    	SurveyRating surveyRating = surveyRatingService.getById(id);
	    	surveyRatingService.delete(surveyRating);

	        return "Survey Rating Deleted";
	    }
	    /**
	     * This method returns all the ratings of an aspect by id
	     * Query is sorted by createdDate in DESC order
	     * @param id
	     * @return
	     */
	    @RequestMapping(method=RequestMethod.GET, value="/aspects/{id}/ratings")
	    public Iterable<SurveyRating> surveyRatingBySurveyAspect(@PathVariable String id) {
	    	SurveyAspect surveyAspect = surveyAspectService.getById(id);
	    	List<SurveyRating> surveyRatinglist = new ArrayList<>();
	    	if(surveyAspect != null) {
	    		surveyRatingService.findBySurveyAspect(surveyAspect).forEach(surveyRatinglist::add);
	    	}
	        return surveyRatinglist;
	    }
	    
	    /**
	     * This method returns an average rating point of a particular aspect
	     * Used **Aggregation** of Spring Mongo for this
	     * @param id
	     * @return
	     */
	    @RequestMapping(method=RequestMethod.GET, value="/aspects/{id}/ratingpoint")
	    public Integer surveyAvgRatingBySurveyAspect(@PathVariable String id) {
	    	SurveyAspect surveyAspect = surveyAspectService.getById(id);
	    	if(surveyAspect != null) {
	    		return surveyRatingService.getAvgRatingBySurveyAspect(surveyAspect);
	    	}
	        return 0;
	    }
	    
	   
	   
}
