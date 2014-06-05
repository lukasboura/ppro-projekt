package cz.uhk.ppro.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HomeModel;

@Controller
@RequestMapping(value="pratele")
public class PrateleController {
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String showStatistiky(Model model){
		new HomeModel().getModel(model);
		
		User u = (User)model.asMap().get("user");
		
        AktivitaDAO aktivitadao = new AktivitaDAO();
        List<Aktivita> aktivitypratel = aktivitadao.getAllAktivitPratel(u.getPratele());
		
        model.addAttribute("aktivitypratel", aktivitypratel);
		return "pratele";
	}
	@RequestMapping(value="/sledovani", method=RequestMethod.GET)
	public String showSledovani(Model model){
		new HomeModel().getModel(model);
		return "sledovani";
	}
	@RequestMapping(value="/sledovan", method=RequestMethod.GET)
	public String showSledovan(Model model){
		new HomeModel().getModel(model);
		return "sledovan";
	}
}