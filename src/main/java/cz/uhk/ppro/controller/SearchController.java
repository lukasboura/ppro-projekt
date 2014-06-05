package cz.uhk.ppro.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.uhk.ppro.dao.SearchDAO;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HomeModel;
import cz.uhk.ppro.service.StringValue;

@Controller
public class SearchController {
	
	@RequestMapping(value="/hledat",method=RequestMethod.POST)
	public String search(Model model,@ModelAttribute("search") StringValue command,BindingResult result,HttpServletRequest req) {
		new HomeModel().getModel(model);
		
		SearchDAO searchdao = new SearchDAO();
		Collection<User> uzivatele = searchdao.search(command.getValue());
		
		model.addAttribute("uzivatele", uzivatele);
		return "vysledkyhledani";
	}
}
