package cz.uhk.ppro.service;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.User;

public class HomeModel {
	
	public HomeModel() {
		// TODO Auto-generated constructor stub
	}
	public Model getModel(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
        Collection<User> sledovan = u.getPritelem();
        //String image = new sun.misc.BASE64Encoder().encode(u.getPhoto());
        AktivitaDAO aktivitadao = new AktivitaDAO();
        int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);
		model.addAttribute("search", new StringValue());
        model.addAttribute("user", u);
        model.addAttribute("sledovan", sledovan);
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		return model;
	}
}
