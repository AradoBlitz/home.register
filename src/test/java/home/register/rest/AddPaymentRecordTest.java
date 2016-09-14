package home.register.rest;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AddPaymentRecordTest {

	@Deployment
	public static WebArchive createDeployment(){
		return ShrinkWrap.create(WebArchive.class, AddPaymentRecordTest.class.getName() + ".war")
				.addAsResource("arquillian.xml")
				.addAsResource("Screenshot from 2016-08-29 21-07-15.png")
				.addClass(AddPaymentRecordService.class);
	}
	
	@Test
	public void checkIsImageAvailableFromClasspath() throws Exception {
		
		assertNotNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("/Screenshot from 2016-08-29 21-07-15.png"));
	}
}


