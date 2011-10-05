package helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import play.data.binding.Global;
import play.data.binding.TypeBinder;

@Global
public class DateTimeBinder implements TypeBinder<DateTime> {
    public Object bind(String name, Annotation[] annotations, String value,
            Class actualClass, Type genericType) throws Exception {
    	System.out.println(value);
    	DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd/HH:mm");
    	return parser.parseDateTime(value.trim());
    }
}
