package home.register.rest;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import home.register.model.MonthlyPayment;

@RequestScoped
public class MonthlyPaymentService {

	public List<MonthlyPayment> getMonthlyPaymentList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPaymentRecord(int rent, Date date, InputStream image, double t1, double t2, int toPay) {
		// TODO Auto-generated method stub
		
	}

}
