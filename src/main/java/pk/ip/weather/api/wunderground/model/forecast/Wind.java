package pk.ip.weather.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind
{
    public Float mph;
    public Float kph;
    public String dir;
    public Float degrees;
}
