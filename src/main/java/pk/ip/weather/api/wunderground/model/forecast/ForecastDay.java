package pk.ip.weather.api.wunderground.model.forecast;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDay
{
    public DateInformation date;
    public Integer period;
    public Temperature high;
    public Temperature low;
    public String conditions;
    public Integer pop;
    
    public Precipitation qpf_allday;
    public Precipitation qpf_day;
    public Precipitation qpf_night;
    public Precipitation snow_allday;
    public Precipitation snow_day;
    public Precipitation snow_night;
    
    public Wind maxwind;
    public Wind avewind;
    
    public Float avehumidity;
    public Integer maxhumidity;
    public Integer minhumidity;
}
