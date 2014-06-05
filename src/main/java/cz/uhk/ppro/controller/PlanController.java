package cz.uhk.ppro.controller;

import java.util.Collection;

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
import cz.uhk.ppro.dao.PlanDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.PolozkaTreninkovehoPlanu;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.HomeModel;

@Controller
@RequestMapping(value="plan")
public class PlanController {

	@RequestMapping(value="/aktualni",method = RequestMethod.GET)
	public String showPlan(Model model){
		new HomeModel().getModel(model);
		
		PlanDAO dao = new PlanDAO();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User user = userdao.getUserByUserName(username);
		
		Collection<PolozkaTreninkovehoPlanu> pondeli = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Pondělí");
		Collection<PolozkaTreninkovehoPlanu> utery = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Úterý");
		Collection<PolozkaTreninkovehoPlanu> streda = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Středa");
		Collection<PolozkaTreninkovehoPlanu> ctvrtek = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Čtvrtek");
		Collection<PolozkaTreninkovehoPlanu> patek = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Pátek");
		Collection<PolozkaTreninkovehoPlanu> sobota = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Sobota");
		Collection<PolozkaTreninkovehoPlanu> nedele = dao.getAllPolozkyByUserIdAndDay(user.getId(), "Neděle");
		
		model.addAttribute("pondeli", pondeli);
		model.addAttribute("utery", utery);
		model.addAttribute("streda", streda);
		model.addAttribute("ctvrtek", ctvrtek);
		model.addAttribute("patek", patek);
		model.addAttribute("sobota", sobota);
		model.addAttribute("nedele", nedele);
		
		return "plan/plan";
	}
	@RequestMapping(value="/upravit",method = RequestMethod.GET)
	public String upravitPlan(Model model){
		new HomeModel().getModel(model);
		
		model.addAttribute("novapolozka", new PolozkaTreninkovehoPlanu());
		
		return "plan/upravit";
	}
	@RequestMapping(value="/pridatPolozku", method = RequestMethod.POST)
	public String addAktivata(@ModelAttribute("novapolozka") @Valid PolozkaTreninkovehoPlanu command,BindingResult result,HttpServletRequest req){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
		
        command.setUser(u);
        
        PlanDAO plandao = new PlanDAO();
        
        plandao.addPlolozka(command);
		
		return "redirect:upravit";
	}
	@RequestMapping(value="/smazatPlan",method = RequestMethod.POST)
	public String smazatPlan(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
        
		PlanDAO dao = new PlanDAO();
		dao.deletePlan(u.getId());
		
		return "redirect:aktualni";
	}
	@RequestMapping(value="/smazatPolozku/{id}",method = RequestMethod.GET)
	public String smazatPolozku(@PathVariable("id")int id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
        
		PlanDAO dao = new PlanDAO();
		dao.deletePolozku(id);
		
		return "redirect:../aktualni";
	}
}
