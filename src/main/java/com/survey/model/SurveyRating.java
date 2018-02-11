package com.survey.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class SurveyRating {
	@Id
	private String _id;
	private Integer rating;
	private String comment;
	private String userId;
	@DBRef
	private SurveyAspect surveyAspect;
	@CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
    private Integer count;
    public SurveyRating() {
    	
    }
    
	public SurveyRating(String _id, Integer rating, String comment, String userId, Date createdDate,
			Date lastModifiedDate,SurveyAspect surveyAspect) {
		super();
		this._id = _id;
		this.rating = rating;
		this.comment = comment;
		this.userId = userId;
		this.surveyAspect = surveyAspect;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}
	public SurveyAspect getSurveyAspect() {
		return surveyAspect;
	}
	public void setSurveyAspect(SurveyAspect surveyAspect) {
		this.surveyAspect = surveyAspect;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "_id : "+_id + " rating : "+rating + " comment : "+ comment ; 
	}
}
