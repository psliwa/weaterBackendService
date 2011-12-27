package pk.ip.weather.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Observation
{
    private City city;
    private Date date;
    
    private int year;
    private int month;
    private int dayOfMonth;
    
    private float windSpeed;
    private float temperature;
    private float windchillTemperature;
    private int humidity;
    private float visibility;
    private float pressure;
    
    private int fog;
    private int rain;
    private int snow;
    private int hail;
    private int thunder;
    private int tornado;
    
    private Type type;

    public City getCity()
    {
        return city;
    }

    public Date getDate()
    {
        return date;
    }

    public int getFog()
    {
        return fog;
    }

    public int getHail()
    {
        return hail;
    }

    public int getHumidity()
    {
        return humidity;
    }

    public float getPressure()
    {
        return pressure;
    }

    public int getRain()
    {
        return rain;
    }

    public int getSnow()
    {
        return snow;
    }

    public float getTemperature()
    {
        return temperature;
    }

    public int getThunder()
    {
        return thunder;
    }

    public int getTornado()
    {
        return tornado;
    }

    public float getVisibility()
    {
        return visibility;
    }

    public float getWindSpeed()
    {
        return windSpeed;
    }

    public float getWindchillTemperature()
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

    public void setFog(int fog)
    {
        this.fog = fog;
    }

    public void setHail(int hail)
    {
        this.hail = hail;
    }

    public void setHumidity(int humidity)
    {
        this.humidity = humidity;
    }

    public void setPressure(float pressure)
    {
        this.pressure = pressure;
    }

    public void setRain(int rain)
    {
        this.rain = rain;
    }

    public void setSnow(int snow)
    {
        this.snow = snow;
    }

    public void setTemperature(float temperature)
    {
        this.temperature = temperature;
    }

    public void setThunder(int thunder)
    {
        this.thunder = thunder;
    }

    public void setTornado(int tornado)
    {
        this.tornado = tornado;
    }

    public void setVisibility(float visibility)
    {
        this.visibility = visibility;
    }

    public void setWindSpeed(float windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public void setWindchillTemperature(float windchillTemperature)
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

    public int getDayOfMonth()
    {
        return dayOfMonth;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
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
        int hash = 5;
        hash = 43 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 43 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 43 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
}
