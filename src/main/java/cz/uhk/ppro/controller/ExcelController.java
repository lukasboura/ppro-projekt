package cz.uhk.ppro.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.LIGHT_YELLOW;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Slice;


import cz.uhk.ppro.dao.AktivitaDAO;
import cz.uhk.ppro.dao.UserDAO;
import cz.uhk.ppro.domain.Aktivita;
import cz.uhk.ppro.domain.User;

@Controller
@RequestMapping(value="/download")
public class ExcelController {

	@RequestMapping(value="/aktivity/{jake}")
	public ModelAndView downloadVse(@PathVariable("jake") String jake, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
		
		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = new ArrayList<Aktivita>();
		if (jake.equalsIgnoreCase("vse")) {
			aktivity = aktivitadao.getAllAktivitById(u.getId());
		} else if (jake.equalsIgnoreCase("kolo")){
			aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(),"Kolo");
		} else if (jake.equalsIgnoreCase("beh")){
			aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(),"Běh");
		} else if (jake.equalsIgnoreCase("chuze")){
			aktivity = aktivitadao.getAllAktivitByIdAndTyp(u.getId(),"Chůze");
		}
		return new ModelAndView("ExcelAktivity","aktivity",aktivity);
	}
	@RequestMapping(value="/aktivity/{rok}/{mesic}/{aktivita}")
	public ModelAndView download(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic,@PathVariable("aktivita")String vybranaaktivita, Model model) throws ParseException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
		
        AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitYearMonthType(u.getId(), vybranyrok, vybranymesic, vybranaaktivita);
		
		
		return new ModelAndView("ExcelAktivity","aktivity",aktivity);
	}
	@RequestMapping(value="/aktivity/{rok}/{mesic}/Vse")
	public ModelAndView download(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic, Model model) throws ParseException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
		
        AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitYearMonth(u.getId(), vybranyrok, vybranymesic);
		
		
		return new ModelAndView("ExcelAktivity","aktivity",aktivity);
	}
	@RequestMapping(value="/all")
	public ModelAndView downloadd(Model model) throws ParseException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByUserName(username);
		
		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitById(u.getId());
		
		return new ModelAndView("ExcelAktivity","aktivity",aktivity);
	}
	
	@RequestMapping(value="/image/vzdalenosti/{rok}/{mesic}/{aktivita}")
	public @ResponseBody BufferedImage getFile(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic,@PathVariable("aktivita")String vybranaaktivita,HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{
		
		response.setContentType("image/png");
		response.setHeader( "Content-Disposition", "attachment;filename=chart-vzdalenost.png");
		
		double[] grafVzdalenost = new double[31];
		Arrays.fill(grafVzdalenost, 0); 
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);

		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitYearMonthType(u.getId(), vybranyrok, vybranymesic, vybranaaktivita);
		int den = 0;
		Calendar cal = Calendar.getInstance();
		
		for (Aktivita aktivita : aktivity) {
	
			
			cal.setTime(aktivita.getDatum());
			den = cal.get(Calendar.DAY_OF_MONTH);
			
			grafVzdalenost[den - 1] = grafVzdalenost[den - 1] + (aktivita.getVzdalenost()); 
			
		}
		double max = grafVzdalenost[0];
		for (int i = 0; i < grafVzdalenost.length; i++) {
			if (grafVzdalenost[i]>max) {
				max=grafVzdalenost[i];
			}
		}
		Data data = DataUtil.scale(grafVzdalenost);
		BarChartPlot plot = Plots.newBarChartPlot(data, Color.newColor("009ACD"));
		
		
		BarChart chart = GCharts.newBarChart(plot);
		chart.setBarWidth(12);
		chart.setSize(650, 300);

        // Adding axis info to chart.
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10.", "11.", "12.", "13.", "14.", "15.", "16.", "17.", "18.", "19.", "20.", "21.", "22.", "23.", "24.", "25.", "26.", "27.", "28.", "29.", "30.", "31."));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, max));
		
		chart.setTitle("Vzdálenosti " + vybranymesic + "/" + vybranyrok + "/" + vybranaaktivita);
		
		BufferedImage image = null;
        try {
            URL url = new URL(chart.toURLString());
            image = ImageIO.read(url);
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
        BufferedOutputStream baos = new BufferedOutputStream(response.getOutputStream());
        ImageIO.write(image, "png", baos );
        return image;
        
          
        
	}
	@RequestMapping(value="/image/kalorie/{rok}/{mesic}/{aktivita}")
	public @ResponseBody BufferedImage getFile2(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic,@PathVariable("aktivita")String vybranaaktivita,HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{
		
		response.setContentType("image/png");
		response.setHeader( "Content-Disposition", "attachment;filename=chart-kalorie.png");
		
		double[] grafKalorie = new double[31];
		Arrays.fill(grafKalorie, 0); 
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);

		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitYearMonthType(u.getId(), vybranyrok, vybranymesic, vybranaaktivita);
		int den = 0;
		Calendar cal = Calendar.getInstance();
		
		for (Aktivita aktivita : aktivity) {
	
			
			cal.setTime(aktivita.getDatum());
			den = cal.get(Calendar.DAY_OF_MONTH);
			
			grafKalorie[den - 1] = grafKalorie[den - 1] + (aktivita.getKalorie()); 
			
		}
		double max = grafKalorie[0];
		for (int i = 0; i < grafKalorie.length; i++) {
			if (grafKalorie[i]>max) {
				max=grafKalorie[i];
			}
		}
		Data data = DataUtil.scale(grafKalorie);
		BarChartPlot plot = Plots.newBarChartPlot(data, Color.newColor("C43838"));
		
		
		BarChart chart = GCharts.newBarChart(plot);
		chart.setBarWidth(12);
		chart.setSize(650, 300);

        // Adding axis info to chart.
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10.", "11.", "12.", "13.", "14.", "15.", "16.", "17.", "18.", "19.", "20.", "21.", "22.", "23.", "24.", "25.", "26.", "27.", "28.", "29.", "30.", "31."));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, max));
		
		chart.setTitle("Kalorie " + vybranymesic + "/" + vybranyrok + "/" + vybranaaktivita);
		
		BufferedImage image = null;
        try {
            URL url = new URL(chart.toURLString());
            image = ImageIO.read(url);
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
        BufferedOutputStream baos = new BufferedOutputStream(response.getOutputStream());
        ImageIO.write(image, "png", baos );
        return image;
        
          
        
	}
	@RequestMapping(value="/image/rychlost/{rok}/{mesic}/{aktivita}")
	public @ResponseBody BufferedImage getFile3(@PathVariable("rok")int vybranyrok,@PathVariable("mesic")int vybranymesic,@PathVariable("aktivita")String vybranaaktivita,HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{
		
		response.setContentType("image/png");
		response.setHeader( "Content-Disposition", "attachment;filename=chart-rychlost.png");
		
		double[] grafRychlost = new double[31];
		Arrays.fill(grafRychlost, 0); 
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		UserDAO userdao = new UserDAO();
		User u = userdao.getUserByUserName(username);

		AktivitaDAO aktivitadao = new AktivitaDAO();
		List<Aktivita> aktivity = aktivitadao.getAllAktivitYearMonthType(u.getId(), vybranyrok, vybranymesic, vybranaaktivita);
		int den = 0;
		Calendar cal = Calendar.getInstance();
		
		for (Aktivita aktivita : aktivity) {
	
			
			cal.setTime(aktivita.getDatum());
			den = cal.get(Calendar.DAY_OF_MONTH);
			
			grafRychlost[den - 1] = grafRychlost[den - 1] + (double)aktivita.getVzdalenost()/(aktivita.getDobaTrvani().getHours() + (double)aktivita.getDobaTrvani().getMinutes()/60 + (double)aktivita.getDobaTrvani().getSeconds()/360);
			
		}
		double max = grafRychlost[0];
		for (int i = 0; i < grafRychlost.length; i++) {
			if (grafRychlost[i]>max) {
				max=grafRychlost[i];
			}
		}
		Data data = DataUtil.scale(grafRychlost);
		//BarChartPlot plot = Plots.newBarChartPlot(data, Color.newColor("009ACD"));
		Line plot = Plots.newLine(data, Color.newColor("1E7228"));
		plot.setFillAreaColor(Color.newColor("38C472"));
		LineChart chart = GCharts.newLineChart(plot);
		chart.setSize(650, 300);

        // Adding axis info to chart.
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10.", "11.", "12.", "13.", "14.", "15.", "16.", "17.", "18.", "19.", "20.", "21.", "22.", "23.", "24.", "25.", "26.", "27.", "28.", "29.", "30.", "31."));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, max));
		
		chart.setTitle("Rychlost " + vybranymesic + "/" + vybranyrok + "/" + vybranaaktivita);
		
		BufferedImage image = null;
        try {
            URL url = new URL(chart.toURLString());
            image = ImageIO.read(url);
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
        BufferedOutputStream baos = new BufferedOutputStream(response.getOutputStream());
        ImageIO.write(image, "png", baos );
        return image;
        
          
        
	}
}
