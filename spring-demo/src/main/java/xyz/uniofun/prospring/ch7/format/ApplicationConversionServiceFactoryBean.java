package xyz.uniofun.prospring.ch7.format;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormatter;
    private String datePattern = DEFAULT_DATE_PATTERN;
    private Set<Formatter<?>> formatters = new HashSet<>();
    public String getDatePattern() {
        return datePattern;
    }

    @PostConstruct
    public void init() {
        dateFormatter = DateTimeFormat.forPattern(datePattern);
        formatters.add(getDateTimeFormatter());
        setFormatters(formatters);
    }


    public Formatter<DateTime> getDateTimeFormatter() {
        return new Formatter<DateTime>() {
            @Override
            public DateTime parse(String text, Locale locale) throws ParseException {
                return dateFormatter.parseDateTime(text);
            }

            @Override
            public String print(DateTime object, Locale locale) {
                return dateFormatter.print(object);
            }
        };
    }
}
