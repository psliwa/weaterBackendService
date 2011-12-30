package pk.ip.weather.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Precipitation
{
    public Float in;
    public Float mm;
    public Float cm;
}
