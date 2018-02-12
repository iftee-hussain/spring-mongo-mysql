package com.survey.services;

import java.util.List;
import com.survey.model.User;

public interface UserService {
	List<User> findAll();
	User getById(Long id);
	User save(User surveyAspect);
	void delete(User user);
}
