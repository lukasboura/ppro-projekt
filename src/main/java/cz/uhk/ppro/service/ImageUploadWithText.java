package cz.uhk.ppro.service;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadWithText {
	
	private MultipartFile image = null;
	private String text = "";
	public ImageUploadWithText() {
		// TODO Auto-generated constructor stub
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
