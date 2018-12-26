package bda1.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String dateString) throws Exception {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
    }
}
