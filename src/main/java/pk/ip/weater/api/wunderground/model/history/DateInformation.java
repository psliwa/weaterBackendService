package pk.ip.weater.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateInformation
{
    public String year;
    public String mon;
    public String mday;
    public String hour;
    public String min;
    public String tzname;
}
