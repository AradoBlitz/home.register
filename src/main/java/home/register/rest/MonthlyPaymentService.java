package home.register.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;

import home.register.model.MonthlyPayment;

@RequestScoped
public class MonthlyPaymentService {

	MonthlyPayment expected;
	
	BufferedImage image;
	
	public List<MonthlyPayment> getMonthlyPaymentList() {
		// TODO Auto-generated method stub
		return Arrays.asList(expected);
	}

	public void addPaymentRecord(int rent, Date date, InputStream image, double t1, double t2, int toPay) {
		expected = new MonthlyPayment();
		expected.rent=rent;
		expected.date=date;
		
		try {
			this.image=ImageIO.read(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		expected.imageUri="image/1"; 
		expected.t1=t1;
		expected.t2=t2;
		expected.toPay=toPay;
		
	}

	public Response getImage(int hashCode) {
		
		try {
			return Response.ok(toByteArray(image, "png")).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private byte[] toByteArray(BufferedImage image, String formatName) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, formatName, baos);
		return baos.toByteArray();
	}

}
