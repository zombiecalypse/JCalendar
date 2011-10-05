package models;

import java.util.Date;

import java.util.*;
import javax.persistence.*;

import org.joda.time.DateTime;
 
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Event extends Model {
	@Required
	@Column(columnDefinition="TEXT")
	public DateTime startDate;
	
	@Required
	@Column(columnDefinition="TEXT")
	public DateTime endDate;
	
	@Required
	public String name;
	
	public boolean visible;
	
	@Required
	@ManyToOne(cascade = CascadeType.ALL)
	public Calendar calendar;
	
	public Event(Calendar c) {
		this.calendar = c;
	}
}
