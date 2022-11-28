package contact;

import java.util.Date;

public class Contact {

	
	private Date date;
	private String procedure;
	private String description;
	public Contact(Date date, String procedure, String description) {
		super();
		this.date = date;
		this.procedure = procedure;
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
