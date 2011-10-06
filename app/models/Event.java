package models;

import java.util.Date;

import java.util.*;
import javax.persistence.*;

import org.joda.time.DateTime;
 
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Event extends Model {
	@Required
	@Column(columnDefinition="TEXT")
	public DateTime startDate;
	
	@Required
	@Column(columnDefinition="TEXT")
	@CheckWith(EndAfterBeginCheck.class)
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
	
	 static class EndAfterBeginCheck extends Check {
        
        public boolean isSatisfied(Object event_, Object end_) {
        	Event event = (Event) event_;
        	DateTime end = (DateTime) end_;
            return event.startDate.isBefore(end);
        }
    }
	
}
