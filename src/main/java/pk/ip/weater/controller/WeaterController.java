package pk.ip.weater.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pk.ip.weater.core.DateInterval;
import pk.ip.weater.domain.City;
import pk.ip.weater.service.Period;
import pk.ip.weater.service.StatisticsType;
import pk.ip.weater.service.WeaterService;
import pk.ip.weater.task.DataCollectorTask;

@Controller()
public class WeaterController
{
    DataCollectorTask task;
    WeaterService weaterService;
    
    public WeaterController(DataCollectorTask task, WeaterService weaterService)
    {
        this.task = task;
        this.weaterService = weaterService;
    }
    
    @RequestMapping("/test1")
    @ResponseBody()
    public String test1()
    {
        task.collectWeaterHistory();
        
        return "test1";
    }
    
    @RequestMapping("/test2")
    @ResponseBody()
    public String test2()
    {
        task.collestYesterdayHistory();
        
        return "test2";
    }
    
    @RequestMapping("/test3")
    @ResponseBody()
    public String test3()
    {
        task.collectForecast();
        
        return "test3";
    }
    
    @RequestMapping("/test4")
    @ResponseBody()
    public String test4(@RequestParam() Long cityId, @RequestParam() StatisticsType type, @RequestParam() Period period)
    {
        City city = new City();
        city.setId(cityId);
        
        Calendar calendar = new GregorianCalendar();
        calendar.set(2010, 0, 1);
        Date dateStart = calendar.getTime();
        calendar.set(2011, 11, 31);
        Date dateEnd = calendar.getTime();
        
        DateInterval interval = new DateInterval(dateStart, dateEnd);
        
        return weaterService.findHistoricalData(city, interval, type, period).toString();
    }
}
