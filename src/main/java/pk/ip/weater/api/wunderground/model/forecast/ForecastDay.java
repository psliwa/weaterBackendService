package pk.ip.weater.api.wunderground.model.forecast;

class ForecastDay
{
    public DateInformation date;
    public int period;
    public Temperature high;
    public Temperature low;
    public String conditions;
    public int pop;
    
    public Precipitation qpf_allday;
    public Precipitation qpf_day;
    public Precipitation qpf_night;
    public Precipitation snow_allday;
    public Precipitation snow_day;
    public Precipitation snow_night;
    
    public Wind maxwind;
    public Wind avewind;
    
    public int avehumidity;
    public int maxhumidity;
    public int minhumidity;
}
