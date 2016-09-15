package home.register.rest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;

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
		expected.imageUri="image/"+ image.hashCode(); 
		expected.t1=t1;
		expected.t2=t2;
		expected.toPay=toPay;
		
	}

}
