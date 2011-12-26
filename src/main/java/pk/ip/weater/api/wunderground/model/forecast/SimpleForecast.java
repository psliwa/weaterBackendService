package pk.ip.weater.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleForecast
{
    public ForecastDay[] forecastday;
}
