package cz.uhk.ppro.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping(value = "statistiky")
public class StatistikyController {
	@RequestMapping(value = "/mesic/{rok}/{mesic}/{aktivita}", method = RequestMethod.GET)
	public String showStatistiky(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic,@PathVariable("aktivita")String vybranaaktivita, Model model) throws ParseException {
		new HomeModel().getModel(model);

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
		
		User u = (User)model.asMap().get("user");

		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitYearMonthType(u.getId(), vybranyrok, vybranymesic, vybranaaktivita);
		
		
		double kalorie = 0;
		double vzdalenost = 0;
		int hodiny = 0;
		int minuty = 0;
		int vteriny = 0;
		int pocetAktivit = 0;
		
		double[] grafVzdalenost = new double[31];
		Arrays.fill(grafVzdalenost, 0.0); 
		double[] grafKalorie = new double[31];
		Arrays.fill(grafKalorie, 0.0); 
		double[] grafRychlost = new double[31];
		Arrays.fill(grafRychlost, 0.0); 
		
		int den = 0;
		Calendar cal = Calendar.getInstance();
		  
		for (Aktivita aktivita : aktivity) {
			
			kalorie = kalorie + aktivita.getKalorie();
			vzdalenost = vzdalenost + aktivita.getVzdalenost();
			hodiny = hodiny + aktivita.getDobaTrvani().getHours();
			minuty = minuty + aktivita.getDobaTrvani().getMinutes();
			vteriny = vteriny + aktivita.getDobaTrvani().getSeconds();
			pocetAktivit++;
			
			cal.setTime(aktivita.getDatum());
			den = cal.get(Calendar.DAY_OF_MONTH);
			grafVzdalenost[den - 1] = grafVzdalenost[den - 1] + aktivita.getVzdalenost(); 
			grafKalorie[den - 1] = grafKalorie[den - 1] + aktivita.getKalorie();
			grafRychlost[den - 1] = grafRychlost[den - 1] + (double)aktivita.getVzdalenost()/(aktivita.getDobaTrvani().getHours() + (double)aktivita.getDobaTrvani().getMinutes()/60 + (double)aktivita.getDobaTrvani().getSeconds()/360);
			
		}
		
		
		for (int i = 0; i < grafVzdalenost.length; i++) {
			model.addAttribute("den" + String.valueOf(i+1), grafVzdalenost[i]);
			model.addAttribute("den" + String.valueOf(i+1) + "k", grafKalorie[i]);
			model.addAttribute("den" + String.valueOf(i+1) + "r", grafRychlost[i]);
		}
		
		model.addAttribute("kalorie", kalorie);
		model.addAttribute("vzdalenost", vzdalenost);
		model.addAttribute("hodiny", hodiny);
		model.addAttribute("minuty", minuty);
		model.addAttribute("vteriny", vteriny);
		model.addAttribute("pocetAktivit", pocetAktivit);
		
		model.addAttribute("aktivity", aktivity);

		model.addAttribute("mesicData", mesicData);
		model.addAttribute("rokData", rokData);
		model.addAttribute("aktivityData", aktivityData);
		model.addAttribute("aktualniRok", vybranyrok);
		model.addAttribute("aktualniMesic", vybranymesic - 1);
		model.addAttribute("aktualniAktivita", vybranaaktivita);
		return "statistiky";
	}

	@RequestMapping(value = "/rocni/{rok}", method = RequestMethod.GET)
	public String showRocni(@PathVariable("rok") int rok,Model model) throws ParseException {
		new HomeModel().getModel(model);
		int aktualniRok = Calendar.getInstance().get(Calendar.YEAR);
		User u = (User)model.asMap().get("user");

		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao
				.getAllAktivitYear(u.getId(), rok);

		int leden = 0;
		int unor = 0;
		int brezen = 0;
		int duben = 0;
		int kveten = 0;
		int cerven = 0;
		int cervenec = 0;
		int srpen = 0;
		int zari = 0;
		int rijen = 0;
		int listopad = 0;
		int prosinec = 0;

		int kolo = 0;
		int beh = 0;
		int chuze = 0;

		double kalorie = 0;
		int hodiny = 0;
		int minuty = 0;
		int vteriny = 0;
		
		double kalorieKolo = 0;
		double vzdalenostKolo = 0;
		int hodinyKolo = 0;
		int minutyKolo = 0;
		int vterinyKolo = 0;
		int pocetAktivitKolo = 0;
		
		double kalorieBeh = 0;
		double vzdalenostBeh = 0;
		int hodinyBeh = 0;
		int minutyBeh = 0;
		int vterinyBeh = 0;
		int pocetAktivitBeh = 0;
		
		double kalorieChuze = 0;
		double vzdalenostChuze = 0;
		int hodinyChuze = 0;
		int minutyChuze = 0;
		int vterinyChuze = 0;
		int pocetAktivitChuze = 0;
		
		Aktivita nejnAktivita = new Aktivita();
		Aktivita nejkomAktivita = new Aktivita();
		
		for (Aktivita aktivita : aktivity) {
			if (aktivita.getKalorie()>nejnAktivita.getKalorie()) {
				nejnAktivita = aktivita;
			}
			if (aktivita.getKomentare().size() > nejkomAktivita.getKomentare().size()) {
				nejkomAktivita = aktivita;
			}
			
			kalorie += aktivita.getKalorie();

			hodiny += aktivita.getDobaTrvani().getHours();
			minuty += aktivita.getDobaTrvani().getMinutes();
			vteriny += aktivita.getDobaTrvani().getSeconds();
			
			switch (aktivita.getDatum().getMonth()) {
			case 0:
				leden++;
				break;
			case 1:
				unor++;
				break;
			case 2:
				brezen++;
				break;
			case 3:
				duben++;
				break;
			case 4:
				kveten++;
				break;
			case 5:
				cerven++;
				break;
			case 6:
				cervenec++;
				break;
			case 7:
				srpen++;
				break;
			case 8:
				zari++;
				break;
			case 9:
				rijen++;
				break;
			case 10:
				listopad++;
				break;
			case 11:
				prosinec++;
				break;

			default:
				break;
			}

			if (aktivita.getTyp().equalsIgnoreCase("Kolo")) {
				kolo++;
			} else if (aktivita.getTyp().equalsIgnoreCase("Beh")) {
				beh++;
			} else if (aktivita.getTyp().equalsIgnoreCase("Chuze")) {
				chuze++;
			}
			
			if (aktivita.getTyp().equalsIgnoreCase("Kolo")) {
				
				kalorieKolo = kalorieKolo + aktivita.getKalorie();
				vzdalenostKolo = vzdalenostKolo + aktivita.getVzdalenost();
				hodinyKolo = hodinyKolo + aktivita.getDobaTrvani().getHours();
				minutyKolo = minutyKolo + aktivita.getDobaTrvani().getMinutes();
				vterinyKolo = vterinyKolo + aktivita.getDobaTrvani().getSeconds();
				pocetAktivitKolo = pocetAktivitKolo + 1;
				
			} else if (aktivita.getTyp().equalsIgnoreCase("Beh")) {
				
				kalorieBeh = kalorieBeh + aktivita.getKalorie();
				vzdalenostBeh = vzdalenostBeh + aktivita.getVzdalenost();
				hodinyBeh = hodinyBeh + aktivita.getDobaTrvani().getHours();
				minutyBeh = minutyBeh + aktivita.getDobaTrvani().getMinutes();
				vterinyBeh = vterinyBeh + aktivita.getDobaTrvani().getSeconds();
				pocetAktivitBeh = pocetAktivitBeh + 1;
				
			} else if (aktivita.getTyp().equalsIgnoreCase("Chuze")) {
				
				kalorieChuze = kalorieChuze + aktivita.getKalorie();
				vzdalenostChuze = vzdalenostChuze + aktivita.getVzdalenost();
				hodinyChuze = hodinyChuze + aktivita.getDobaTrvani().getHours();
				minutyChuze = minutyChuze + aktivita.getDobaTrvani().getMinutes();
				vterinyChuze = vterinyChuze + aktivita.getDobaTrvani().getSeconds();
				pocetAktivitChuze = pocetAktivitChuze + 1;
				
			}
		}

		ArrayList<Integer> rokData = new ArrayList<Integer>();
		for (int i = aktualniRok; i > 2010; i--) {
			rokData.add(i);
		}

		long casInSec = hodiny*60*60*1000 + minuty*60*1000 + vteriny*1000;
		
		long timeMillis = casInSec;
		long time = timeMillis / 1000;
		vteriny = (int)(time % 60);
		minuty = (int)(time % 3600) / 60;
		hodiny = (int)(time / 3600);
		
		long casInSecKolo = hodinyKolo*60*60*1000 + minutyKolo*60*1000 + vterinyKolo*1000;
		
		long timeMillisKolo = casInSecKolo;
		long timeKolo = timeMillisKolo / 1000;
		vterinyKolo = (int)(timeKolo % 60);
		minutyKolo = (int)(timeKolo % 3600) / 60;
		hodinyKolo = (int)(timeKolo / 3600);
		
		long casInSecBeh = hodinyBeh*60*60*1000 + minutyBeh*60*1000 + vterinyBeh*1000;
		
		long timeMillisBeh = casInSecBeh;
		long timeBeh = timeMillisBeh / 1000;
		vterinyBeh = (int)(timeBeh % 60);
		minutyBeh = (int)(timeBeh % 3600) / 60;
		hodinyBeh = (int)(timeBeh / 3600);
		
		long casInSecChuze = hodinyChuze*60*60*1000 + minutyChuze*60*1000 + vterinyChuze*1000;
		
		long timeMillisChuze = casInSecChuze;
		long timeChuze = timeMillisChuze / 1000;
		vterinyChuze = (int)(timeChuze % 60);
		minutyChuze = (int)(timeChuze % 3600) / 60;
		hodinyChuze = (int)(timeChuze / 3600);
		
		model.addAttribute("aktivity", aktivity);
		
		model.addAttribute("rokData", rokData);
		model.addAttribute("leden", leden);
		model.addAttribute("unor", unor);
		model.addAttribute("brezen", brezen);
		model.addAttribute("duben", duben);
		model.addAttribute("kveten", kveten);
		model.addAttribute("cerven", cerven);
		model.addAttribute("cervenec", cervenec);
		model.addAttribute("srpen", srpen);
		model.addAttribute("zari", zari);
		model.addAttribute("rijen", rijen);
		model.addAttribute("listopad", listopad);
		model.addAttribute("prosinec", prosinec);
		
		model.addAttribute("kolo",kolo);
		model.addAttribute("beh",beh);
		model.addAttribute("chuze",chuze);

		model.addAttribute("hodiny",hodiny);
		model.addAttribute("minuty",minuty);
		model.addAttribute("vteriny",vteriny);
		model.addAttribute("kalorie",kalorie);
		model.addAttribute("nejnAktivita",nejnAktivita);
		model.addAttribute("nejkomAktivita",nejkomAktivita);
		model.addAttribute("pocetaktivit", aktivity.size());
		
		model.addAttribute("vybranyrok", rok);
		
		model.addAttribute("kalorieKolo", kalorieKolo);
		model.addAttribute("vzdalenostKolo", vzdalenostKolo);
		model.addAttribute("hodinyKolo", hodinyKolo);
		model.addAttribute("minutyKolo", minutyKolo);
		model.addAttribute("vterinyKolo", vterinyKolo);
		model.addAttribute("pocetAktivitKolo", pocetAktivitKolo);
		
		model.addAttribute("kalorieBeh", kalorieBeh);
		model.addAttribute("vzdalenostBeh", vzdalenostBeh);
		model.addAttribute("hodinyBeh", hodinyBeh);
		model.addAttribute("minutyBeh", minutyBeh);
		model.addAttribute("vterinyBeh", vterinyBeh);
		model.addAttribute("pocetAktivitBeh", pocetAktivitBeh);
		
		model.addAttribute("kalorieChuze", kalorieChuze);
		model.addAttribute("vzdalenostChuze", vzdalenostChuze);
		model.addAttribute("hodinyChuze", hodinyChuze);
		model.addAttribute("minutyChuze", minutyChuze);
		model.addAttribute("vterinyChuze", vterinyChuze);
		model.addAttribute("pocetAktivitChuze", pocetAktivitChuze);
		
		return "rocniprehled";
	}
}
