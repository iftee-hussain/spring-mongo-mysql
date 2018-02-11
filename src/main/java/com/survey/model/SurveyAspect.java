package com.survey.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class SurveyAspect {
	@Id
	private String _id;
	private String aspectName;
	@DBRef
	private SurveyCategory surveyCategory;
	private String imageUrl;
	
	public SurveyAspect(){
		
	}
	
	public SurveyAspect(String _id, String aspectName, SurveyCategory surveyCategory, String imageUrl) {
		this._id = _id;
		this.aspectName = aspectName;
		this.surveyCategory = surveyCategory;
		this.imageUrl = imageUrl;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAspectName() {
		return aspectName;
	}
	public void setAspectName(String aspectName) {
		this.aspectName = aspectName;
	}
	public SurveyCategory getSurveyCategory() {
		return surveyCategory;
	}
	public void setSurveyCategoryId(SurveyCategory surveyCategory) {
		this.surveyCategory = surveyCategory;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
