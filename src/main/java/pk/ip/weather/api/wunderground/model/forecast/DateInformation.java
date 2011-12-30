package pk.ip.weather.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateInformation
{
    public Long epoch;
    public String pretty;
    public String day;
    public String month;
    public String year;
    public String hour;
    public String min;
    public String sec;
    public String tz_short;
}
