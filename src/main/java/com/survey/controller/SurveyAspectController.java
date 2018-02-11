package com.survey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.mongo.model.SurveyAspect;
import com.survey.mongo.model.SurveyCategory;
import com.survey.mongo.model.SurveyRating;
import com.survey.services.SurveyAspectService;
import com.survey.services.SurveyCategoryService;

@RestController
public class SurveyAspectController {
	
	private SurveyAspectService surveyAspectService;
	private SurveyCategoryService surveyCategoryService;
	
	@Autowired
    public void setSurveyAspectService(SurveyAspectService surveyAspectService) {
        this.surveyAspectService = surveyAspectService;
	}
	
	@Autowired
    public void setSurveyCategoryService(SurveyCategoryService surveyCategoryService) {
        this.surveyCategoryService = surveyCategoryService;
	}
	/**
	 * This method return all the aspects
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/aspects")
    public List<SurveyAspect> surveyAspect() {
		List<SurveyAspect> surveyAspectList = new ArrayList<>();
        surveyAspectService.findAll().forEach(surveyAspectList::add);
        return surveyAspectList;
    }
	/**
	 * This method returns all aspects of a survey category
	 *  
	 * @param surveyCategoryId
	 * @return
	 * 
		 * [
	    {
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
	    },
	    {
	        "_id": "5a7fc97cb7318c1328e42c7b",
	        "aspectName": "Ham Burger",
	        "surveyCategory": {
	            "_id": "5a7e886eb7318c256a018079",
	            "name": "Burger",
	            "isParent": null,
	            "hasAspect": 1,
	            "childSurveyCategories": null,
	            "imageUrl": "/image/burger.jpg"
	        },
	        "imageUrl": "/images/hamburger.jpg"
	    }
	]
	 */
	@RequestMapping(method=RequestMethod.GET, value="/surveycategory/{surveyCategoryId}/aspects")
    public List<SurveyAspect> surveyAspectBySurveyCategory(@PathVariable String surveyCategoryId) {
		List<SurveyAspect> surveyAspectList = new ArrayList<>();
		SurveyCategory surveyCategory = surveyCategoryService.getById(surveyCategoryId);
		if(surveyCategory != null) {
			surveyAspectService.findBySurveyCategory(surveyCategory).forEach(surveyAspectList::add);
		}
		return surveyAspectList;
    }
	/**
	 * This method creates a new SurveyAspect object
	   1.
	   {
			"aspectName": "Ham Burger",
			"imageUrl" : "/images/hamburger.jpg",
			"surveyCategory" : 
				{
			    "_id": "5a7e886eb7318c256a018079",
			    "name": "Burger",
			    "isParent": null,
			    "hasAspect": 1,
			    "childSurveyCategories": null,
			    "imageUrl": "/image/burger.jpg"
			}
			
		}
		2.
		{
		"aspectName": "Chicken Burger",
		"imageUrl" : "/images/chickenburger.jpg",
		"surveyCategory" : 
			{
		    "_id": "5a7e886eb7318c256a018079",
		    "name": "Burger",
		    "isParent": null,
		    "hasAspect": 1,
		    "childSurveyCategories": null,
		    "imageUrl": "/image/burger.jpg"
			}
			
		}
	 * @param surveyAspect
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/aspects")
    public SurveyAspect save(@RequestBody SurveyAspect surveyAspect) {
		SurveyCategory surveyCategory = surveyAspect.getSurveyCategory();
		if(surveyCategory.getHasAspect() == 1 && surveyCategory.get_id() != null) {
			surveyCategoryService.save(surveyCategory);
			surveyAspectService.save(surveyAspect);
		}
		return surveyAspect;
    }
	/**
	 * This method updates a SurveyAspect object
	 * @param id
	 * @param surveyAspect
	 * @return
	 */
	 @RequestMapping(method=RequestMethod.PUT, value="/aspects/{id}")
	    public SurveyAspect update(@PathVariable String id, @RequestBody SurveyAspect surveyAspect) {
		 SurveyAspect surveyAsp= surveyAspectService.getById(id);
	        if(surveyAspect.getAspectName() != null)
	        	surveyAsp.setAspectName(surveyAspect.getAspectName());
	        if(surveyAspect.getImageUrl() != null)
	        	surveyAsp.setImageUrl(surveyAspect.getImageUrl());
	        if(surveyAspect.getSurveyCategory() != null)
	        	surveyAsp.setSurveyCategoryId(surveyAspect.getSurveyCategory());
	        
	        surveyAspectService.save(surveyAsp);
	        return surveyAsp;
	    }
	 /**
	  * This method deletes a SurveyAspect object
	  * @param id
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.DELETE, value="/aspects/{id}")
	    public String delete(@PathVariable String id) {
		 SurveyAspect surveyAspect = surveyAspectService.getById(id);
		 surveyAspectService.delete(surveyAspect);

	        return "Survey Rating Deleted";
	    }

}
