package pk.ip.weater.core;

import java.util.Date;

public class DateInterval
{
    private Date start;
    private Date end;
    
    public DateInterval(Date start, Date end)
    {
        this.start = start;
        this.end = end;
    }

    public Date getEnd()
    {
        return end;
    }

    public Date getStart()
    {
        return start;
    }
}
