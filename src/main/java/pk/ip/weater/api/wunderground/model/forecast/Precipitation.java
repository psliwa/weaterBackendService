package pk.ip.weater.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Precipitation
{
    public float in;
    public float mm;
    public float cm;
}
