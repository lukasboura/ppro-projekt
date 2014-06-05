package cz.uhk.ppro.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.uhk.ppro.dao.FotogalerieDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Foto;
import cz.uhk.ppro.domain.User;
import cz.uhk.ppro.service.ImageResizer; 

@Controller
@RequestMapping(value="/image")
public class ImageController { 

	@RequestMapping(value="/user")
	@ResponseBody
	public ResponseEntity<byte[]> showUserImage(final HttpServletResponse response) throws IOException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
         
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
       
        HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.parseMediaType("image/png"));
        
        return new ResponseEntity<byte[]>(u.getPhoto(),
                responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> showUserImage(final HttpServletResponse response, @PathVariable("id")
	int id) throws IOException{
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserById(id);
       
        HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.parseMediaType("image/jpg"));
        
        return new ResponseEntity<byte[]>(u.getPhoto(),
                responseHeaders, HttpStatus.OK);
	}
	@RequestMapping(value="/fotogalerie/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> showImageSmall(final HttpServletResponse response, @PathVariable("id")
	int id) throws IOException{
        
       FotogalerieDAO dao = new FotogalerieDAO();
       Foto foto = dao.getFotoById(id);
       
        HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.parseMediaType("image/jpg"));
        
	    ImageResizer ir = new ImageResizer();
	    
        return new ResponseEntity<byte[]>(ir.resizeImage(foto.getFotka(), 90, 800000000),
                responseHeaders, HttpStatus.OK);
	}
	@RequestMapping(value="/fotogalerie/{id}/big")
	@ResponseBody
	public ResponseEntity<byte[]> showImageBig(final HttpServletResponse response, @PathVariable("id")
	int id) throws IOException{
        
       FotogalerieDAO dao = new FotogalerieDAO();
       Foto foto = dao.getFotoById(id);
       
        HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.parseMediaType("image/jpg"));
        
        return new ResponseEntity<byte[]>(foto.getFotka(),
                responseHeaders, HttpStatus.OK);
	}
}
