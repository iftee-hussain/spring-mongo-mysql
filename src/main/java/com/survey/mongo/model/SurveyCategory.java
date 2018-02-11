package com.survey.mongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Document
public class SurveyCategory {
	 @Id
    private String _id;
    private String name;
    private Integer isParent;
    private Integer hasAspect;
    @DBRef
    private Set<SurveyCategory> childSurveyCategories;
    
	private String imageUrl;
	
	public SurveyCategory() {
		
	}
	
	public SurveyCategory(String _id, String name, Integer isParent, Integer hasAspect,Set<SurveyCategory> childSurveyCategories) {
		this._id = _id;
		this.name = name;
		this.isParent = isParent;
		this.hasAspect = hasAspect;
		this.childSurveyCategories = childSurveyCategories;
	}
    
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public Integer getHasAspect() {
		return hasAspect;
	}
	public void setHasAspect(Integer hasAspect) {
		this.hasAspect = hasAspect;
	}
	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsParent() {
		return isParent;
	}
	public void Integer(Integer isParent) {
		this.isParent = isParent;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Set<SurveyCategory> getChildSurveyCategories() {
		return childSurveyCategories;
	}
	public void setChildSurveyCategories(Set<SurveyCategory> childSurveyCategories) {
		this.childSurveyCategories = childSurveyCategories;
	}
	
	

    
}
