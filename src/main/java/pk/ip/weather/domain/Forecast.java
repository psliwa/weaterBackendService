package pk.ip.weather.domain;

import java.util.Date;

public class Forecast
{
    private City city;
    private Date date;
    
    private float maxTemperature;
    private float minTemperature;
    
    private float windSpeed;
    private float humidity;
    
    private float snowAll;
    private float snowDay;
    private float snowNight;
    
    private float rainAll;
    private float rainDay;
    private float rainNight;

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

    public float getHumidity()
    {
        return humidity;
    }

    public void setHumidity(float humidity)
    {
        this.humidity = humidity;
    }

    public float getMaxTemperature()
    {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature)
    {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature()
    {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature)
    {
        this.minTemperature = minTemperature;
    }

    public float getRainAll()
    {
        return rainAll;
    }

    public void setRainAll(float rainAll)
    {
        this.rainAll = rainAll;
    }

    public float getRainDay()
    {
        return rainDay;
    }

    public void setRainDay(float rainDay)
    {
        this.rainDay = rainDay;
    }

    public float getRainNight()
    {
        return rainNight;
    }

    public void setRainNight(float rainNight)
    {
        this.rainNight = rainNight;
    }

    public float getSnowAll()
    {
        return snowAll;
    }

    public void setSnowAll(float snowAll)
    {
        this.snowAll = snowAll;
    }

    public float getSnowDay()
    {
        return snowDay;
    }

    public void setSnowDay(float snowDay)
    {
        this.snowDay = snowDay;
    }

    public float getSnowNight()
    {
        return snowNight;
    }

    public void setSnowNight(float snowNight)
    {
        this.snowNight = snowNight;
    }

    public float getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed)
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
