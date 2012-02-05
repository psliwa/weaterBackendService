package pk.ip.weather.service;

public enum Period
{
    YEAR("year"), DAY("DATE_FORMAT(date, '%Y-%m-%d')"), MONTH("month");

    private String proprty;

    private Period(String property)
    {
        this.proprty = property;
    }

    String getProperty()
    {
        return proprty;
    }
}
