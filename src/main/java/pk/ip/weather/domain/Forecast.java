package pk.ip.weather.domain;

import java.util.Date;

public class Forecast
{
    private City city;
    private Date date;
    
    private Float maxTemperature;
    private Float minTemperature;
    
    private Float windSpeed;
    private Float humidity;
    
    private Float snowAll;
    private Float snowDay;
    private Float snowNight;
    
    private Float rainAll;
    private Float rainDay;
    private Float rainNight;

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Float getHumidity()
    {
        return humidity;
    }

    public void setHumidity(Float humidity)
    {
        this.humidity = humidity;
    }

    public Float getMaxTemperature()
    {
        return maxTemperature;
    }

    public void setMaxTemperature(Float maxTemperature)
    {
        this.maxTemperature = maxTemperature;
    }

    public Float getMinTemperature()
    {
        return minTemperature;
    }

    public void setMinTemperature(Float minTemperature)
    {
        this.minTemperature = minTemperature;
    }

    public Float getRainAll()
    {
        return rainAll;
    }

    public void setRainAll(Float rainAll)
    {
        this.rainAll = rainAll;
    }

    public Float getRainDay()
    {
        return rainDay;
    }

    public void setRainDay(Float rainDay)
    {
        this.rainDay = rainDay;
    }

    public Float getRainNight()
    {
        return rainNight;
    }

    public void setRainNight(Float rainNight)
    {
        this.rainNight = rainNight;
    }

    public Float getSnowAll()
    {
        return snowAll;
    }

    public void setSnowAll(Float snowAll)
    {
        this.snowAll = snowAll;
    }

    public Float getSnowDay()
    {
        return snowDay;
    }

    public void setSnowDay(Float snowDay)
    {
        this.snowDay = snowDay;
    }

    public Float getSnowNight()
    {
        return snowNight;
    }

    public void setSnowNight(Float snowNight)
    {
        this.snowNight = snowNight;
    }

    public Float getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed)
    {
        this.windSpeed = windSpeed;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Forecast))
        {
            return false;
        }
        
        Forecast forecast = (Forecast) o;
        
        return (forecast.city == null && city == null || forecast.city.equals(city)) 
                && (forecast.date == null && date == null || forecast.date.equals(date));
    }
}
