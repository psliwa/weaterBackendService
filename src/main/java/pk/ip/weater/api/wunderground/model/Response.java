package pk.ip.weater.api.wunderground.model;

import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response
{
    public double version;
    public String termsofService;
    public Map<String, String> features;
}
