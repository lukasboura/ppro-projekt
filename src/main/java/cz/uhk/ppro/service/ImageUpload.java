package cz.uhk.ppro.service;

import org.springframework.web.multipart.MultipartFile;

public class ImageUpload {

	private MultipartFile image;
	public ImageUpload() {
		// TODO Auto-generated constructor stub
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
