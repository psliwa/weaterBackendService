package pk.ip.weather.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature
{
    public float fahrenheit;
    public float celsius;
}
