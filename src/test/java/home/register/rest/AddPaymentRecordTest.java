package home.register.rest;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Inject
	private MonthlyPaymentService monthlyPaymentService; 
	
	@Test
	public void checkIsImageAvailableFromClasspath() throws Exception {
		
		InputStream image = Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("/Screenshot from 2016-08-29 21-07-15.png");
		
		monthlyPaymentService.addPaymentRecord(25000
				,dateFormatter.parse("20-Aug-1985")
				,image
				,700.005
				,612.340
				,3700);
		MonthlyPayment expected = new MonthlyPayment();
		expected.rent=25000;
		expected.date=dateFormatter.parse("20-Aug-1985");
		expected.image=ImageIO.read(image);
		expected.t1=700.005;
		expected.t2=612.340;
		expected.toPay=3700;
		assertEquals(asList(expected),monthlyPaymentService.getMonthlyPaymentList());
		
	}
}


