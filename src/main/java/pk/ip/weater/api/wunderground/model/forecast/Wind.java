package pk.ip.weater.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind
{
    public float mph;
    public float kph;
    public String dir;
    public float degrees;
}
