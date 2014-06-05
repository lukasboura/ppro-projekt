package cz.uhk.ppro.controller;

import java.util.Collection;

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
import cz.uhk.ppro.service.StringValue;

@Controller
@RequestMapping(value="user")
public class UserController {

	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String showUser(@PathVariable("username") String username, Model model){
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);
		
		model.addAttribute("user", u);
		AktivitaDAO aktivitadao = new AktivitaDAO();
		int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);

        boolean jesledovan = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        
        Collection<User> sledovaniUzivatele = homeuser.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				jesledovan = true;
				break;
			}
		}
        Collection<Aktivita> aktivity = aktivitadao.getAllAktivitById(u.getId());     
        
        model.addAttribute("jeHomeUser", homeuser.equals(u));
        model.addAttribute("aktivity", aktivity);
        model.addAttribute("jesledovan", jesledovan);
		model.addAttribute("search", new StringValue());
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		
		return "user/user";
	}
	@RequestMapping(value="/{username}/kolo",method=RequestMethod.GET)
	public String showUserKolo(@PathVariable("username") String username, Model model){
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);
		
		model.addAttribute("user", u);
		AktivitaDAO aktivitadao = new AktivitaDAO();
		int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);

        boolean jesledovan = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        
        Collection<User> sledovaniUzivatele = homeuser.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				jesledovan = true;
				break;
			}
		}
        Collection<Aktivita> aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(),"Kolo");     
        model.addAttribute("jeHomeUser", homeuser.equals(u));
        model.addAttribute("aktivity", aktivity);
        model.addAttribute("jesledovan", jesledovan);
		model.addAttribute("search", new StringValue());
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		
		return "user/user";
	}
	@RequestMapping(value="/{username}/beh",method=RequestMethod.GET)
	public String showUserBeh(@PathVariable("username") String username, Model model){
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);
		
		model.addAttribute("user", u);
		AktivitaDAO aktivitadao = new AktivitaDAO();
		int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);

        boolean jesledovan = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        
        Collection<User> sledovaniUzivatele = homeuser.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				jesledovan = true;
				break;
			}
		}
        Collection<Aktivita> aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(),"Běh");     
        model.addAttribute("jeHomeUser", homeuser.equals(u));
        model.addAttribute("aktivity", aktivity);
        model.addAttribute("jesledovan", jesledovan);
		model.addAttribute("search", new StringValue());
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		
		return "user/user";
	}
	@RequestMapping(value="/{username}/chuze",method=RequestMethod.GET)
	public String showUserChuze(@PathVariable("username") String username, Model model){
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);
		
		model.addAttribute("user", u);
		AktivitaDAO aktivitadao = new AktivitaDAO();
		int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);

        boolean jesledovan = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        
        Collection<User> sledovaniUzivatele = homeuser.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				jesledovan = true;
				break;
			}
		}
        
        Collection<Aktivita> aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(),"Chůze");     
        model.addAttribute("jeHomeUser", homeuser.equals(u));
        model.addAttribute("aktivity", aktivity);
        model.addAttribute("jesledovan", jesledovan);
		model.addAttribute("search", new StringValue());
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		
		return "user/user";
	}
	@RequestMapping(value="/{username}/sledovani",method=RequestMethod.GET)
	public String showUserSledovani(@PathVariable("username") String username, Model model){
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);
		
		model.addAttribute("user", u);
		AktivitaDAO aktivitadao = new AktivitaDAO();
		int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);

        boolean jesledovan = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        
        Collection<User> sledovaniUzivatele = homeuser.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				jesledovan = true;
				break;
			}
		}
        model.addAttribute("jeHomeUser", homeuser.equals(u));
        model.addAttribute("jesledovan", jesledovan);
		model.addAttribute("search", new StringValue());
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		
		return "user/sledovani";
	}
	@RequestMapping(value="/{username}/sledovan",method=RequestMethod.GET)
	public String showUserSledovan(@PathVariable("username") String username, Model model){
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);
		
		model.addAttribute("user", u);
		AktivitaDAO aktivitadao = new AktivitaDAO();
		int kolo_pocet = aktivitadao.getPocetAktivit("Kolo", u.getId());
        int beh_pocet = aktivitadao.getPocetAktivit("Běh", u.getId());
        int chuze_pocet = aktivitadao.getPocetAktivit("Chůze", u.getId());
        
        //model.addAttribute("image",image);

        boolean jesledovan = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        
        Collection<User> sledovaniUzivatele = homeuser.getPratele();
        for (User user : sledovaniUzivatele) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				jesledovan = true;
				break;
			}
		}
        model.addAttribute("jeHomeUser", homeuser.equals(u));
        model.addAttribute("jesledovan", jesledovan);
		model.addAttribute("search", new StringValue());
        model.addAttribute("kolo_pocet",kolo_pocet);
        model.addAttribute("beh_pocet",beh_pocet);
        model.addAttribute("chuze_pocet",chuze_pocet);
		
		return "user/sledovan";
	}
	@RequestMapping(value="/{username}/odebratUzivatele",method=RequestMethod.GET)
	public String odebrat(@PathVariable("username") String username, Model model){
		//odebirany uživatel
		UserDAO userdao = new UserDAO();
		User user = userdao.getUserByUserName(username);
		//přihlášený uživatel
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String homeusername = auth.getName();
        User homeuser = userdao.getUserByUserName(homeusername);
        
        homeuser.getPratele().remove(user);
        userdao.updateUser(homeuser);
		
		model.asMap().clear();
		return "redirect:../" + username;
	}
	@RequestMapping(value="/{username}/sledovatUzivatele",method=RequestMethod.GET)
	public String sledovat(@PathVariable("username") String username, Model model){
		//odebirany uživatel
				UserDAO userdao = new UserDAO();
				User user = userdao.getUserByUserName(username);
				//přihlášený uživatel
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        String homeusername = auth.getName();
		        User homeuser = userdao.getUserByUserName(homeusername);
		        
		        homeuser.getPratele().add(user);
		        userdao.updateUser(homeuser);
		        
				
				model.asMap().clear();
				return "redirect:../" + username;
		
		
	}
}
