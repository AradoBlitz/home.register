package home.register.rest.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;

import home.register.model.MonthlyPayment;

@ApplicationScoped
public class MonthlyPaymentRepository {

	MonthlyPayment expected;
	private BufferedImage image;

	public List<MonthlyPayment> getMonthlyPaymentList() {
		// TODO Auto-generated method stub
		return  Arrays.asList(expected);
	}

	public void addPaymentRecord(MonthlyPayment expected) {
		this.expected = expected;
		// TODO Auto-generated method stub
		
	}

	public void addImage(BufferedImage image) {
		this.image = image;
		// TODO Auto-generated method stub
		
	}

	public byte[] getImageAsByte() {
		// TODO Auto-generated method stub
		try {
			return toByteArray(image, "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private byte[] toByteArray(BufferedImage image, String formatName) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, formatName, baos);
		return baos.toByteArray();
	}
}
