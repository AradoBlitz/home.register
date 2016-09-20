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
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import home.register.model.MonthlyPayment;

@Path("/")
@RequestScoped
public class MonthlyPaymentService {

	MonthlyPayment expected;
	
	BufferedImage image;
	
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MonthlyPayment> getMonthlyPaymentList() {
		// TODO Auto-generated method stub
		return Arrays.asList(expected);
	}

	@POST
	@Path("addMonthlyPayment")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void addPaymentRecord(@FormParam("rent") int rent,
			@FormParam("Date") String date, 
			@FormDataParam("image") InputStream image,
			@FormParam("t1") double t1,
			@FormParam("t2") double t2,
			@FormParam("toPay") int toPay) {
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

	@Path("image/{id:[0-9][0-9]*}")
	@GET
	@Produces("image/png")
	public Response getImage(@PathParam("id") int id) {
		
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
