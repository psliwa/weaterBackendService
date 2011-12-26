package pk.ip.weater.service;

public enum Period
{
    YEAR("year"), DAY("date"), MONTH("month");

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
