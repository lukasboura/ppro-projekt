package cz.uhk.ppro.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HomeModel;

@Controller
@RequestMapping(value="staktivity")
public class StAktivityController {
	 
	@RequestMapping(value="/{rok}/{mesic}/{aktivita}", method=RequestMethod.GET)
	public String showAktivity(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic,@PathVariable("aktivita")String vybranaaktivita,Model model) throws ParseException{
		new HomeModel().getModel(model);
		
		User u = (User)model.asMap().get("user");
		
		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = null;
		if (vybranaaktivita.equals("Vse")) {
			aktivity = aktivitadao.getAllAktivitYearMonth(u.getId(), vybranyrok, vybranymesic);
		} else {
			aktivity = aktivitadao.getAllAktivitYearMonthType(u.getId(), vybranyrok, vybranymesic, vybranaaktivita);
		}
		
		
		ArrayList<Integer> mesicData = new ArrayList<Integer>();
		for (int i = 0; i < 12; i++) {
			mesicData.add(i);
		}
		int aktualniRok = Calendar.getInstance().get(Calendar.YEAR);
		ArrayList<Integer> rokData = new ArrayList<Integer>();
		for (int i = aktualniRok; i > 2010; i--) {
			rokData.add(i);
		}

		ArrayList<String> aktivityData = new ArrayList<String>();
		aktivityData.add("Kolo");
		aktivityData.add("Beh");
		aktivityData.add("Chuze");
		
		model.addAttribute("mesicData", mesicData);
		model.addAttribute("rokData", rokData);
		model.addAttribute("aktivityData", aktivityData);
		
		model.addAttribute("aktualniRok", vybranyrok);
		model.addAttribute("aktualniMesic", vybranymesic - 1);
		
		model.addAttribute("aktualniAktivita", vybranaaktivita);
		
		model.addAttribute("jakeaktivity", new String("vse"));
		model.addAttribute("aktivity", aktivity);
		
		return "staktivity";
	}
}
