package cz.uhk.ppro.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.PolozkaTreninkovehoPlanu;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HomeModel;
import cz.uhk.ppro.service.ImageResizer;
import cz.uhk.ppro.service.ImageUpload;
import cz.uhk.ppro.service.StringValue;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model) throws ParseException{
		
		new HomeModel().getModel(model);
		User u = (User)model.asMap().get("user");
		//statistiky za posledních 7 dní
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		
		Date today = new Date();
		Date weekago = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
		Date tommorow = new Date(System.currentTimeMillis() + (1 * DAY_IN_MS));
		AktivitaDAO dao = new AktivitaDAO();
		List<Aktivita> vsechnyaktivity = dao.getAllAktivitByIdAndTime(u.getId(), weekago, today);
		
		double kalorieVse = 0;
		double vzdalenostVse = 0;
		int hodinyVse = 0;
		int minutyVse = 0;
		int vterinyVse = 0;
		int pocetAktivitVse = 0;
		
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
		
		for (Aktivita aktivita : vsechnyaktivity) {
			kalorieVse = kalorieVse + aktivita.getKalorie();
			vzdalenostVse = vzdalenostVse + aktivita.getVzdalenost();
			hodinyVse = hodinyVse + aktivita.getDobaTrvani().getHours();
			minutyVse = minutyVse + aktivita.getDobaTrvani().getMinutes();
			vterinyVse = vterinyVse + aktivita.getDobaTrvani().getSeconds();
			pocetAktivitVse = pocetAktivitVse + 1;
			
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
		
long casInSec = hodinyVse*60*60*1000 + minutyVse*60*1000 + vterinyVse*1000;
		
		long timeMillis = casInSec;
		long time = timeMillis / 1000;
		vterinyVse = (int)(time % 60);
		minutyVse = (int)(time % 3600) / 60;
		hodinyVse = (int)(time / 3600);
		
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
		
		
		
		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitById(u.getId());
		
		model.addAttribute("aktivity", aktivity);
		
		List<Aktivita> aktivitypratel = aktivitadao.getAllAktivitPratel(u.getPratele());
		
        model.addAttribute("aktivitypratel", aktivitypratel);
        
		model.addAttribute("kalorieVse", kalorieVse);
		model.addAttribute("vzdalenostVse", vzdalenostVse);
		model.addAttribute("hodinyVse", hodinyVse);
		model.addAttribute("minutyVse", minutyVse);
		model.addAttribute("vterinyVse", vterinyVse);
		model.addAttribute("pocetAktivitVse", pocetAktivitVse);
		
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
		
		String dnes = "";
		String zitra = "";
		boolean jeDnes = false;
		boolean jeZitra = false;
		switch (today.getDay()) {
        case 0:  dnes = "Neděle";zitra = "Pondělí";
                 break;
        case 1:  dnes = "Pondělí";zitra = "Úterý";
                 break;
        case 2:  dnes = "Úterý";zitra = "Středa";
                 break;
        case 3:  dnes = "Středa";zitra = "Čtvrtek";
                 break;
        case 4:  dnes = "Čtvrtek";zitra = "Pátek";
                 break;
        case 5:  dnes = "Pátek";zitra = "Sobota";
                 break;
        case 6:  dnes = "Sobota";zitra = "Neděle";
                 break;
    }
		for (PolozkaTreninkovehoPlanu polozka : u.getTreninkovyplan()) {
			if (polozka.getDen().equalsIgnoreCase(dnes)) {
				jeDnes = true;
			}
			if (polozka.getDen().equalsIgnoreCase(zitra)) {
				jeZitra = true;
			}
		}
		model.addAttribute("dnes", dnes);
		model.addAttribute("zitra", zitra);
		model.addAttribute("jednes", jeDnes);
		model.addAttribute("jezitra", jeZitra);
		return "home";
	}
	@RequestMapping(value="/upravitProfil", method = RequestMethod.GET)
	public String upravitProfilForm(Model model){
		new HomeModel().getModel(model);
		
		
		User u = (User)model.asMap().get("user");
		
		model.addAttribute("userforupdate", new User(u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(), u.getConfirmpassword(), u.getEmail(), u.getBio(), u.getHmotnost(), u.getVek(), u.getPhoto(), u.getAktivity(), u.getPratele(), u.getPritelem(), u.getTreninkovyplan()));
		model.addAttribute("imageupload", new ImageUpload());
		return "upravitprofil";
	}
	@RequestMapping(value="/upravProfil", method = RequestMethod.POST)
	public String upravProfil(Model model,@ModelAttribute("userforupdate") @Valid User command,BindingResult result,HttpServletRequest req){
		UserDAO userdao = new UserDAO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User u = userdao.getUserByUserName(username);
        command.setId(u.getId());
        command.setPratele(u.getPratele());
        command.setPritelem(u.getPritelem());
        
		
		if (result.hasErrors()) {
			
				List<ObjectError> chyby = result.getAllErrors();
				for (ObjectError objectError : chyby) {
					System.out.println(objectError.toString());
				}
			
				new HomeModel().getModel(model).asMap();
				model.addAttribute("userforupdate",command);
				model.addAttribute("imageupload", new ImageUpload());
	            return "upravitprofil";
		}
		
		
		
        userdao.updateUser(command);      
		
		return "redirect:../home";
	}
	@RequestMapping(value="/zmenitFoto", method = RequestMethod.POST)
	public String upravFotoProfil(Model model,@ModelAttribute("imageupload") @Valid ImageUpload command,BindingResult result,HttpServletRequest req) throws IOException{
		
		
		/*if (command.getImage().getContentType().equalsIgnoreCase("image/jp")) {
			result.rejectValue("image", "umageupload.image", "Obrázek je příliš velký!!");
			new HomeModel().getModel(model);
			User u = (User)model.asMap().get("user");
			
			model.addAttribute("userforupdate", new User(u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(), u.getConfirmpassword(), u.getEmail(), u.getBio(), u.getHmotnost(), u.getVek(), u.getPhoto(), u.getAktivity(), u.getPratele(), u.getPritelem(), u.getTreninkovyplan()));
			
			return "upravitprofil";
		}*/
		
		System.out.println(command.getImage().getContentType());
		
		if (!command.getImage().getContentType().equalsIgnoreCase("image/jpeg")) {
		result.rejectValue("image", "umageupload.image", "Obrázek není ve formátu JPG!!");
		new HomeModel().getModel(model);
		User u = (User)model.asMap().get("user");
		
		model.addAttribute("userforupdate", new User(u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(), u.getConfirmpassword(), u.getEmail(), u.getBio(), u.getHmotnost(), u.getVek(), u.getPhoto(), u.getAktivity(), u.getPratele(), u.getPritelem(), u.getTreninkovyplan()));
		
		return "upravitprofil"; 
	}
		
		
		
		UserDAO userdao = new UserDAO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User u = userdao.getUserByUserName(username);
        
        if (command.getImage().getSize() == 0) {
			return "redirect:../home/upravitProfil";
		}
        ImageResizer ir = new ImageResizer();
        byte[] blob = ir.resizeImage(command.getImage().getBytes(), 200, 150); 
		u.setPhoto(blob);
        userdao.updateUser(u);
		
		
		return "redirect:../home";
	}
}
