package pk.ip.weather.core;

public enum Format
{
    JSON("json"), XML("xml");
    
    private String format;
    
    Format(String format)
    {
        this.format = format;
    }
    
    public String toString()
    {
        return format;
    }
}
