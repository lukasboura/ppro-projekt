package cz.uhk.ppro.service;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageResizer {

	public ImageResizer() {
		// TODO Auto-generated constructor stub
	}
	
public byte[] resizeImage(byte[] originalImage,int sirka,int vyska) throws IOException{
		
		InputStream in = new ByteArrayInputStream(originalImage);
        BufferedImage image = ImageIO.read(in);
		
        
        
		double vyslednaSirka=image.getWidth();
		double vyslednaVyska=image.getHeight();
		
		int puvodniSirka = image.getWidth();
		int puvodniVyska = image.getHeight();
		
		if (puvodniSirka>sirka) {
			vyslednaSirka = sirka;
			vyslednaVyska = ((double)sirka/puvodniSirka)*puvodniVyska;
		}
		if (puvodniVyska>vyska) {
			vyslednaVyska = vyska;
			vyslednaSirka = ((double)vyska/puvodniVyska)*puvodniSirka;
		}
       /* int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();*/
        BufferedImage resized = resizeImage(image, (int)vyslednaSirka,(int)vyslednaVyska);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( resized, "jpg", baos ); 
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
		
		return imageInByte;
	}
public static BufferedImage resizeImage(BufferedImage image, int width, int height) throws IOException {
    int imageWidth  = image.getWidth();
    int imageHeight = image.getHeight();

    double scaleX = (double)width/imageWidth;
    double scaleY = (double)height/imageHeight;
    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

    return bilinearScaleOp.filter(
        image,
        new BufferedImage(width, height, image.getType()));
}
	
}
