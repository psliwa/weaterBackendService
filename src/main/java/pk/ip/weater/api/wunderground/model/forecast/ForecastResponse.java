package pk.ip.weater.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import pk.ip.weater.api.wunderground.model.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastResponse
{
    public Response response;
    public Forecast forecast;
}
