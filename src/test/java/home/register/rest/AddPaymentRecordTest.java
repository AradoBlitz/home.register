package home.register.rest;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import home.register.model.MonthlyPayment;

@RunWith(Arquillian.class)
public class AddPaymentRecordTest {

	@Deployment
	public static WebArchive createDeployment(){
		return ShrinkWrap.create(WebArchive.class, AddPaymentRecordTest.class.getName() + ".war")
				.addAsResource("arquillian.xml")
				.addAsResource("Screenshot from 2016-08-29 21-07-15.png")
				.addClass(MonthlyPaymentService.class)
				.addClass(MonthlyPayment.class);
	}
	
	@Inject
	private MonthlyPaymentService monthlyPaymentService; 
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	
	private BufferedImage image;
	
	@Before
	public void prepareTestImage() throws Exception{
		InputStream imageStream = Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("/Screenshot from 2016-08-29 21-07-15.png");
		image = ImageIO.read(imageStream);
	}
	
	@Test
	public void addPaymentRecord() throws Exception {
		
		
		MonthlyPayment expected = new MonthlyPayment();
		expected.rent=25000;
		expected.date=dateFormatter.parse("20-Aug-1985");
		expected.imageUri="image/1";
		expected.t1=700.005;
		expected.t2=612.340;
		expected.toPay=3700;
		
		Response expectedResponse = Response.ok(toByteArray(image,"png")).build();
		
		monthlyPaymentService.addPaymentRecord(25000
				,dateFormatter.parse("20-Aug-1985")
				,toInputStream(image,"png")
				,700.005
				,612.340
				,3700);
		
		assertEquals(asList(expected),monthlyPaymentService.getMonthlyPaymentList());
		
		
		Response actualResponse =  monthlyPaymentService.getImage(1);
		assertTrue(actualResponse.getEntity() instanceof byte[]);
		assertArrayEquals((byte[])expectedResponse.getEntity(), (byte[])actualResponse.getEntity());
		
	}

	

	private byte[] toByteArray(BufferedImage image, String formatName) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, formatName, baos);
		return baos.toByteArray();
	}

	private InputStream toInputStream(BufferedImage image,String formatName) throws Exception {
		return new ByteArrayInputStream(toByteArray(image,formatName));
	}
}


