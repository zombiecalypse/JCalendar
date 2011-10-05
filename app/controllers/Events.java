package controllers;

import models.Calendar;
import models.Event;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;

public class Events extends Controller {
	
	public static void edit(long id, long eventid) {
		Event event = Event.findById(eventid);
		Calendar calendar = Calendar.findById(id);
		assert calendar != null;
		assert event != null;
		render(calendar, event);
	}
	
	public static void update(long id, long eventid, 
			@Required String title, 
				@Required String start,@Required String end,
			@Required boolean visibility) {
		DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();
		Event event = Event.findById(eventid);
		event.name = title;
		event.startDate = parser.parseDateTime(start);
		event.endDate = parser.parseDateTime(end);
		event.visible = visibility;
		if (event.validateAndSave())
			Calendars.show(id, null, null);
		else {
			params.flash(); // add http parameters to the flash scope
		    validation.keep(); // keep the errors for the next request
			edit(id,eventid);
		}
	}
	
	public static void make(long id, String title, 
			DateTime start, DateTime end, boolean visibility) {
		Calendar calendar = Calendar.findById(id);
		assert calendar != null;
		Event event = new Event(calendar);
		event.name = title;
		event.startDate = start;
		event.endDate = end;
		event.visible = visibility;
		if (event.validateAndSave())
			Calendars.show(id, null, null);
		else {
			params.flash(); // add http parameters to the flash scope
		    validation.keep(); // keep the errors for the next request
			Calendars.createEvent(id);
		}
	}
}
