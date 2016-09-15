package home.register.model;

import java.awt.image.BufferedImage;
import java.util.Date;

public class MonthlyPayment {

	public int rent;
	public Date date;
	public BufferedImage image;
	public double t1;
	public double t2;
	public int toPay;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + rent;
		long temp;
		temp = Double.doubleToLongBits(t1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(t2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + toPay;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonthlyPayment other = (MonthlyPayment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (rent != other.rent)
			return false;
		if (Double.doubleToLongBits(t1) != Double.doubleToLongBits(other.t1))
			return false;
		if (Double.doubleToLongBits(t2) != Double.doubleToLongBits(other.t2))
			return false;
		if (toPay != other.toPay)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MonthlyPayment [rent=" + rent + ", date=" + date + ", image=" + image + ", t1=" + t1 + ", t2=" + t2
				+ ", toPay=" + toPay + "]";
	}
	
	

}
