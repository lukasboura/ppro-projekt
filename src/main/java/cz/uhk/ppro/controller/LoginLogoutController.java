/**
 * 
 */
package cz.uhk.ppro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.User;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, 
			ModelMap model) {
		logger.debug("Received request to show login page");

		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the authentication has failed. 
		// We declared this under the authentication-failure-url attribute inside the spring-security.xml
		/* See below:
		 <form-login 
				login-page="/krams/auth/login" 
				authentication-failure-url="/krams/auth/login?error=true" 
				default-target-url="/krams/main/common"/>
		 */
		if (error == true) {
			// Assign an error message
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		
		return "loginpage";
	}
	
	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a regular user
	 * tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		
		return "deniedpage";
	}
	@RequestMapping(value = "/newaccount", method = RequestMethod.GET)
 	public String registrovat(ModelMap model) {
		
		model.addAttribute("newuser",new User());
		return "registrace";
	}
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
 	public String registrovat(ModelMap model,@ModelAttribute("newuser") @Valid User command,BindingResult result,HttpServletRequest req) {
		if (!(command.getPassword().equals(command.getConfirmpassword()))) {
		result.rejectValue("password", "newuser.password", "Hesla se musí shodovat");
		}
		UserDAO dao = new UserDAO();
		if (dao.checkUsername(command.getUsername()) == true) {
			result.rejectValue("username", "newuser.username", "Uživatelské jméno není dostupné");
		}
		
		if (result.hasErrors()) {
			model.addAttribute("newuser",command);
            return "registrace";
		}
		
		
		
		logger.debug(command.getFirstName() + " " + command.getLastName());
		
		final String context = req.getSession().getServletContext().getRealPath("\\img");
		File file = new File(context + "\\userphoto.gif");
		
		logger.debug(file.getAbsolutePath());
		
        byte[] bFile = new byte[(int) file.length()];
         
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("newuser",command);
            return "registrace";
        }
        command.setPhoto(bFile);
		dao.createNewUser(command);
		return "regdone";
	}
}