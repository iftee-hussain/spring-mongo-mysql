package com.survey.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.survey.mongo.model.SurveyCategory;
import com.survey.services.SurveyCategoryService;

@RestController
public class SurveyCatergoryController {
	
	
	private SurveyCategoryService surveyCategoryService;
	
	  @Autowired
	    public void setSurveyCategoryService(SurveyCategoryService surveyCategoryService) {
	        this.surveyCategoryService = surveyCategoryService;
	  }
	/**
	 * This function returns all the categories and subcategories upto aspects from a single document surveyCategory
	 * @return
	 */
	 @RequestMapping(method=RequestMethod.GET, value="/categories")
	    public Iterable<SurveyCategory> surveyCategory() {
	        return surveyCategoryService.findAll();
	    }
	/**
	 * This function takes input a SurveyCategory object and stores it in the document.
	 * SurveyCategory category object can be nested by sub categories
	 * -category1
	 * --subcategory1.1
	 * ---subcategory1.1.1
	 * ---subcategory1.1.2
	 * --subcategory1.2
	 * ---subcategory1.2.1
	 * ---subcategory1.2.2 
	 * Category and Sub category objects are created recusrsively
	 * 
		{
		"name" : "Restaurant",
		"isParent": 1,
		"hasAspect" : 0,
		"imageUrl" : "/image/restaurant.jpg",
		"childSurveyCategories" : [
			{
				"name" : "Food",
				"isParent": 0,
				"hasAspect" : 0,
				"imageUrl" : "/image/food.jpg",
				"childSurveyCategories" : [
					{
							"name" : "Burger",
							"isParent": 0,
							"hasAspect" : 0,
							"imageUrl" : "/image/burger.jpg",
							"childSurveyCategories" : null
					},
					{
							"name" : "Sandwitch",
							"isParent": 0,
							"hasAspect" : 0,
							"imageUrl" : "/image/sandwitch.jpg",
							"childSurveyCategories" : null
					}
					]
							
			}
			]
		}
	 * 
	 * @param surveyCategory
	 * @return
	 */
	    @RequestMapping(method=RequestMethod.POST, value="/categories")
	    public SurveyCategory save(@RequestBody SurveyCategory surveyCategory) {
	    	if(surveyCategory.getChildSurveyCategories() != null) {
	    		this.createChildServeyCategory(surveyCategory);
	    	}
	    	surveyCategoryService.save(surveyCategory);
	    	return surveyCategory;
	    }
	    /**
	     * This function is for creating n level child sub categories recursively
	     * @param surveyCategory
	     * @return
	     */
	    public SurveyCategory createChildServeyCategory(SurveyCategory surveyCategory) {
	    	if(surveyCategory.getChildSurveyCategories() != null) {
		    	Set<SurveyCategory> childSurveyCategories = new HashSet();
	    		childSurveyCategories = surveyCategory.getChildSurveyCategories();
	    		Iterator<SurveyCategory> iterator = childSurveyCategories.iterator();
	    		while(iterator.hasNext()) {
	    			SurveyCategory childSurveyObject = iterator.next();
	    			this.createChildServeyCategory(childSurveyObject);
	    			surveyCategoryService.save(childSurveyObject);
	    		}
	    	}
			return surveyCategory;
}
	    /**
	     * This function returns a SurveyCategory object by Id
	     */
	    @RequestMapping(method=RequestMethod.GET, value="/categories/{id}")
	    public SurveyCategory show(@PathVariable String id) {
	        return surveyCategoryService.getById(id);
	    }
	    /**
	     * This function updates a SurveyCategory object
	     {
		    "name": "Ham Burger",
		    "isParent": 0,
		    "childSurveyCategories": null,
		    "imageUrl": "/image/hamburger.jpg"
		}
	     */
	    @RequestMapping(method=RequestMethod.PUT, value="/categories/{id}")
	    public SurveyCategory update(@PathVariable String id, @RequestBody SurveyCategory surveyCategory) {
	    	SurveyCategory surveyCat = surveyCategoryService.getById(id);
	        if(surveyCategory.getName() != null)
	        	surveyCat.setName(surveyCategory.getName());
	        if(surveyCategory.getImageUrl() != null)
	        	surveyCat.setImageUrl(surveyCategory.getImageUrl());
	        if(surveyCategory.getChildSurveyCategories() != null)
	        	surveyCat.setChildSurveyCategories(surveyCategory.getChildSurveyCategories());
	        if(surveyCategory.getImageUrl() != null)
	        	surveyCat.setImageUrl(surveyCategory.getImageUrl());
	        surveyCategoryService.save(surveyCat);
	        return surveyCat;
	    }
	    /**
	     * This function deletes a SurveyCategory object
	     */
	    @RequestMapping(method=RequestMethod.DELETE, value="/categories/{id}")
	    public String delete(@PathVariable String id) {
	    	SurveyCategory surveyCategory = surveyCategoryService.getById(id);
	    	surveyCategoryService.delete(surveyCategory);

	        return "product deleted";
	    }
	    /**
	     * This function returns all the parent categories {'isParent' : 1}
	     * To represent demo use of Mongotemplate and custom mongo queries
	     * @return
	     */
	    @RequestMapping(method=RequestMethod.GET, value="/parentcategories")
	    public Iterable<SurveyCategory> parentSurveyCategory() {
	        return surveyCategoryService.findByIsParent();
	    }

}
