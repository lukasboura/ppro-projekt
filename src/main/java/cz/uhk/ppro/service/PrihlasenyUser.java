package cz.uhk.ppro.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.User;

public class PrihlasenyUser {

	public PrihlasenyUser() {
		// TODO Auto-generated constructor stub
	}
	public User get(){
		UserDAO dao = new UserDAO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return dao.getUserByUserName(username);
	}
}
