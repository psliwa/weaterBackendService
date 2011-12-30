package pk.ip.weather.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Observation
{
    private City city;
    private Date date;
    
    private Integer year;
    private Integer month;
    private Integer dayOfMonth;
    
    private Float windSpeed;
    private Float temperature;
    private Float windchillTemperature;
    private Integer humidity;
    private Float visibility;
    private Float pressure;
    
    private Integer fog;
    private Integer rain;
    private Integer snow;
    private Integer hail;
    private Integer thunder;
    private Integer tornado;
    
    private Type type;

    public City getCity()
    {
        return city;
    }

    public Date getDate()
    {
        return date;
    }

    public Integer getFog()
    {
        return fog;
    }

    public Integer getHail()
    {
        return hail;
    }

    public Integer getHumidity()
    {
        return humidity;
    }

    public Float getPressure()
    {
        return pressure;
    }

    public Integer getRain()
    {
        return rain;
    }

    public Integer getSnow()
    {
        return snow;
    }

    public Float getTemperature()
    {
        return temperature;
    }

    public Integer getThunder()
    {
        return thunder;
    }

    public Integer getTornado()
    {
        return tornado;
    }

    public Float getVisibility()
    {
        return visibility;
    }

    public Float getWindSpeed()
    {
        return windSpeed;
    }

    public Float getWindchillTemperature()
    {
        return windchillTemperature;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public void setDate(Date date)
    {
        this.date = date;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setFog(Integer fog)
    {
        this.fog = fog;
    }

    public void setHail(Integer hail)
    {
        this.hail = hail;
    }

    public void setHumidity(Integer humidity)
    {
        this.humidity = humidity;
    }

    public void setPressure(Float pressure)
    {
        this.pressure = pressure;
    }

    public void setRain(Integer rain)
    {
        this.rain = rain;
    }

    public void setSnow(Integer snow)
    {
        this.snow = snow;
    }

    public void setTemperature(Float temperature)
    {
        this.temperature = temperature;
    }

    public void setThunder(Integer thunder)
    {
        this.thunder = thunder;
    }

    public void setTornado(Integer tornado)
    {
        this.tornado = tornado;
    }

    public void setVisibility(Float visibility)
    {
        this.visibility = visibility;
    }

    public void setWindSpeed(Float windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public void setWindchillTemperature(Float windchillTemperature)
    {
        this.windchillTemperature = windchillTemperature;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public Integer getDayOfMonth()
    {
        return dayOfMonth;
    }

    public Integer getMonth()
    {
        return month;
    }

    public Integer getYear()
    {
        return year;
    }
    
    public static enum Type
    {
        DETAIL("DETAIL"), SUMMARY("SUMMARY");
        
        private String value;
        
        private Type(String value)
        {
            this.value = value;
        }
        
        public String getValue()
        {
            return value;
        }
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Observation))
        {
            return false;
        }
        
        Observation observation = (Observation) o;
        
        return (observation.city == null && city == null || observation.city.equals(city))
                && (observation.date == null && date == null || observation.date.equals(date))
                && observation.type == type;
    }

    @Override
    public int hashCode()
    {
        Integer hash = 5;
        hash = 43 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 43 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 43 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
}
