package home.register.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import home.register.model.MonthlyPayment;
import home.register.rest.data.MonthlyPaymentRepository;

@Path("/")
@RequestScoped
public class MonthlyPaymentService {
	
	@Inject
	private MonthlyPaymentRepository monthlyPaymentRepository;  
	
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MonthlyPayment> getMonthlyPaymentList() {
		// TODO Auto-generated method stub
		return monthlyPaymentRepository.getMonthlyPaymentList();
	}

	@POST
	@Path("addMonthlyPayment")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(MultipartFormDataInput input) {
		Map<String,List<InputPart>> uploadForm = input.getFormDataMap();
		try {
			MonthlyPayment newPaymentRecord = new MonthlyPayment();
			System.out.println(uploadForm.get("rent").get(0).getBodyAsString());
			newPaymentRecord.rent = Integer.parseInt(uploadForm.get("rent").get(0).getBodyAsString());
			newPaymentRecord.date = uploadForm.get("Date").get(0).getBodyAsString();
			ByteArrayOutputStream buff = new ByteArrayOutputStream();
			for(InputPart inputPart : uploadForm.get("Photo")){
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				buff.write(IOUtils.toByteArray(inputStream));
			}			
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(buff.toByteArray()));
			newPaymentRecord.imageUri="service/image/1";
			monthlyPaymentRepository.addImage(image);
		
			newPaymentRecord.t1 = Double.parseDouble(uploadForm.get("T1").get(0).getBodyAsString());
			newPaymentRecord.t2 = Double.parseDouble(uploadForm.get("T2").get(0).getBodyAsString());
			newPaymentRecord.toPay =Integer.parseInt(uploadForm.get("To pay").get(0).getBodyAsString());
			
			monthlyPaymentRepository.addPaymentRecord(newPaymentRecord);
			
			System.out.println(image);
			System.out.println(newPaymentRecord);
			return Response.ok().build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		} 
		
	}
	
	public void addPaymentRecord(@FormDataParam("rent") Integer rent,
			@FormDataParam("Date") String date, 
			@FormDataParam("image") InputStream imageSource,
			@FormDataParam("t1") Double t1,
			@FormDataParam("t2") Double t2,
			@FormDataParam("toPay") Integer toPay) {
		MonthlyPayment expected = new MonthlyPayment();
		expected.rent=rent;
		expected.date=date;
		BufferedImage image = null;
		try {
			image=ImageIO.read(imageSource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		expected.imageUri="image/1"; 
		expected.t1=t1;
		expected.t2=t2;
		expected.toPay=toPay;
		System.out.println("MonthlyPayment: " + expected);
		monthlyPaymentRepository.addPaymentRecord(expected);
		monthlyPaymentRepository.addImage(image);
		
	}

	@Path("image/{id:[0-9][0-9]*}")
	@GET
	@Produces("image/png")
	public Response getImage(@PathParam("id") int id) {
		
		return Response.ok(monthlyPaymentRepository.getImageAsByte()).build();
	}

}
