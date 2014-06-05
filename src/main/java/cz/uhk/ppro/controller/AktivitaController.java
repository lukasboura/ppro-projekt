package cz.uhk.ppro.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.FotogalerieDAO;
import cz.uhk.ppro.dao.KomentarDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.Foto;
import cz.uhk.ppro.domain.Komentar;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HomeModel;
import cz.uhk.ppro.service.ImageResizer;
import cz.uhk.ppro.service.ImageUploadWithText;
import cz.uhk.ppro.service.PrihlasenyUser;

@Controller
@RequestMapping(value="aktivity")
public class AktivitaController {

	AktivitaDAO aktivitadao;

	public void setAktivitadao(AktivitaDAO aktivitadao) {
		this.aktivitadao = aktivitadao;
	}
	
	@RequestMapping(value="/pridatAktivitu", method = RequestMethod.GET)
	public String pridatAktivituForm(Model model){
		new HomeModel().getModel(model);
        
        Aktivita a = new Aktivita();
		model.addAttribute("aktivita", a);
		return "pridataktivitu";
	}
	
	@RequestMapping(value="/upravitAktivitu/{id}", method = RequestMethod.GET)
	public String upravitAktivitu(@PathVariable("id") int id,Model model){
		
		
		new HomeModel().getModel(model);

		
		Aktivita a = aktivitadao.getById(id);
		
		if (!new PrihlasenyUser().get().equals(a.getUser())) {
			return "nepovoleny";
		}
		
		model.addAttribute("aktivitaKUpraveni", a);
		model.addAttribute("imageupload", new ImageUploadWithText());
		return "upravitaktivitu";
		
		
	}
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
	public String detailAktivity(@PathVariable("id") int id,Model model){
		new HomeModel().getModel(model);

		Aktivita a = aktivitadao.getById(id);
		
        User useraktivity = a.getUser();
        User u = (User)model.asMap().get("user");
        
        boolean jesledovan = false;
        Collection<User> sledovaniUzivatele = u.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(useraktivity.getUsername())) {
				jesledovan = true;
				break;
			}
		}
        
		int kolo_pocet2 = aktivitadao.getPocetAktivit("Kolo", useraktivity.getId());
        int beh_pocet2 = aktivitadao.getPocetAktivit("Beh", useraktivity.getId());
        int chuze_pocet2 = aktivitadao.getPocetAktivit("Chuze", useraktivity.getId());
        
        model.addAttribute("jeHomeUser", useraktivity.equals(u));
        model.addAttribute("jesledovan", jesledovan);
        
        model.addAttribute("homeuser", u);
        model.addAttribute("aktivitauser", useraktivity);
        
		model.addAttribute("aktivita", a);
		model.addAttribute("newkomentar", new Komentar());
		
		model.addAttribute("kolo_pocet2",kolo_pocet2);
        model.addAttribute("beh_pocet2",beh_pocet2);
        model.addAttribute("chuze_pocet2",chuze_pocet2);
        
		return "aktivitadetail";
	}
	@RequestMapping(value="/detail/{id}/pridatKomentar", method = RequestMethod.POST)
	public String pridatKomentar(@PathVariable("id") int id,Model model,@ModelAttribute("newkomentar") Komentar command,BindingResult result,HttpServletRequest req){
		
		KomentarDAO komentardao = new KomentarDAO();
		
		UserDAO userdao = new UserDAO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User u = userdao.getUserByUserName(username);
		
        command.setAktivita(aktivitadao.getById(id));
        command.setUser(u);
        
        command.setDatum(new Date());
        
		komentardao.addKomentar(command);
		return "redirect:../../detail/" + id;
	}
	@RequestMapping(value="/smazatAktivitu/{id}", method = RequestMethod.GET)
	public String smazatAktivitu(@PathVariable("id") int id,Model model){
		new HomeModel().getModel(model);
		
		if (!new PrihlasenyUser().get().equals(aktivitadao.getById(id).getUser())) {
			return "nepovoleny";
		}
		
		aktivitadao.delete(aktivitadao.getById(id));
		
		model.asMap().clear();
		
		return "redirect:../../aktivity/all";
	}
		
	
	@RequestMapping(value="/upravitAktivitu/{id}/upravAktivitu", method = RequestMethod.POST)
	public String upravAktivitu(@PathVariable("id") int id,Model model,@ModelAttribute("aktivitaKUpraveni") Aktivita command,BindingResult result,HttpServletRequest req){
	
		UserDAO userdao = new UserDAO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User u = userdao.getUserByUserName(username);
        
		double casMin = (command.getDobaTrvani().getHours() * 60) + command.getDobaTrvani().getMinutes() + (command.getDobaTrvani().getSeconds() / 60);
		double casHod = casMin / 60;
		double rychlost = (command.getVzdalenost() / casHod);
		double kalorie = 0;
		if (command.getTyp().equalsIgnoreCase("Beh")) {
			kalorie = (((rychlost / 2.8)*3.5*u.getHmotnost()*casMin*2)/50);
		} else
		if (command.getTyp().equalsIgnoreCase("Kolo")) {
			kalorie = (((rychlost / 2.8)*3.5*u.getHmotnost()*casMin)/200);
		} else
		if (command.getTyp().equalsIgnoreCase("Chuze")) {
			kalorie = (((rychlost / 2.8)*3.5*u.getHmotnost()*casMin)/250);
		}
		
		command.setId(id);
		command.setUser(u);
		command.setKalorie(kalorie);
		command.setRychlost(rychlost);
		
		aktivitadao.update(command);
		return "redirect:../../../aktivity/all";
	}
	@RequestMapping(value="/pridejAktivitu", method = RequestMethod.POST)
	public String addAktivata(Model model,@ModelAttribute("aktivita") @Valid Aktivita command,BindingResult result,HttpServletRequest req){
		if (result.hasErrors()) {
			new HomeModel().getModel(model);
			model.addAttribute("aktivita",command);
            return "pridataktivitu";
		}
		
		UserDAO userdao = new UserDAO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User u = userdao.getUserByUserName(username);
		
		System.out.println(command.getDatum() + "  " + command.getDobaTrvani());
        
		double casMin = (command.getDobaTrvani().getHours() * 60) + command.getDobaTrvani().getMinutes() + (command.getDobaTrvani().getSeconds() / 60);
		double casHod = casMin / 60;
		double rychlost = (command.getVzdalenost() / casHod);
		
		double kalorie = 0;
		if (command.getTyp().equalsIgnoreCase("Beh")) {
			kalorie = (((rychlost / 2.8)*3.5*u.getHmotnost()*casMin*2)/50);
		} else
		if (command.getTyp().equalsIgnoreCase("Kolo")) {
			kalorie = (((rychlost / 2.8)*3.5*u.getHmotnost()*casMin)/200);
		} else
		if (command.getTyp().equalsIgnoreCase("Chuze")) {
			kalorie = (((rychlost / 2.8)*3.5*u.getHmotnost()*casMin)/250);
		}
		
		
		command.setUser(u);
		command.setKalorie(kalorie);
		command.setRychlost(rychlost);
		
		aktivitadao.create(command);
		return "redirect:../home";
	}
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String showAktivity(Model model){
		new HomeModel().getModel(model);
		
		User u = (User)model.asMap().get("user");
		
		List<Aktivita> aktivity = aktivitadao.getAllAktivitById(u.getId());
		
		model.addAttribute("jakeaktivity", new String("vse"));
		model.addAttribute("aktivity", aktivity);
		
		return "aktivity";
	}
	@RequestMapping(value="/kolo", method=RequestMethod.GET)
	public String showAktivityKolo(Model model){
		new HomeModel().getModel(model);
		
		User u = (User)model.asMap().get("user");
		
		List<Aktivita> aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(), "Kolo");
		
		model.addAttribute("jakeaktivity", new String("kolo"));
		model.addAttribute("aktivity", aktivity);
		
		return "aktivity";
	}
	@RequestMapping(value="/beh", method=RequestMethod.GET)
	public String showAktivityBeh(Model model){
		new HomeModel().getModel(model);
		
		User u = (User)model.asMap().get("user");
		
		List<Aktivita> aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(), "Beh");
		
		model.addAttribute("jakeaktivity", new String("beh"));
		model.addAttribute("aktivity", aktivity);
		
		return "aktivity";
	}
	@RequestMapping(value="/chuze", method=RequestMethod.GET)
	public String showAktivityChuze(Model model){
		new HomeModel().getModel(model);
		
		User u = (User)model.asMap().get("user");
		
		List<Aktivita> aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(), "Chuze");
		
		model.addAttribute("jakeaktivity", new String("chuze"));
		model.addAttribute("aktivity", aktivity);
		
		return "aktivity";
	}
	@RequestMapping(value="/upravitAktivitu/{id}/pridatFoto", method = RequestMethod.POST)
	public String pridatFoto(@PathVariable("id") int id,Model model,@ModelAttribute("imageupload") ImageUploadWithText command,BindingResult result,HttpServletRequest req) throws IOException{
	
		
		Foto foto = new Foto();
		
		Aktivita a = aktivitadao.getById(id);
		
		if (!new PrihlasenyUser().get().equals(a.getUser())) {
			return "nepovoleny";
		}
		
		foto.setAktivita(a);
		foto.setText(command.getText());
		
		ImageResizer ir = new ImageResizer();
		
		try {
			foto.setFotka(ir.resizeImage(command.getImage().getBytes(), 800, 600) );
		} catch (Exception e) {
			return "redirect:../../upravitAktivitu/"+id; 
		}
		
		
		FotogalerieDAO dao = new FotogalerieDAO();
		dao.addFoto(foto);
		
		return "redirect:../../upravitAktivitu/"+id;
	}
	@RequestMapping(value="/upravitAktivitu/{id}/smazatFoto/{foto_id}", method = RequestMethod.POST)
	public String pridatFoto(@PathVariable("id") int id,@PathVariable("foto_id") int foto_id,Model model,@ModelAttribute("imageupload") ImageUploadWithText command,BindingResult result,HttpServletRequest req) throws IOException{
	
		
		if (!new PrihlasenyUser().get().equals(aktivitadao.getById(id).getUser())) {
			return "nepovoleny";
		}
		
		FotogalerieDAO dao = new FotogalerieDAO();
		dao.deleteFotoById(foto_id);
		
		return "redirect:../../../upravitAktivitu/"+id;
	}
	
}
