package pk.ip.weater.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import pk.ip.weater.core.DateInterval;
import pk.ip.weater.domain.City;
import pk.ip.weater.domain.Forecast;
import pk.ip.weater.service.Period;
import pk.ip.weater.service.StatisticsType;
import pk.ip.weater.service.WeaterService;
import pk.ip.weater.task.DataCollectorTask;

@Controller()
public class WeaterController implements ApplicationContextAware
{
    DataCollectorTask task;
    WeaterService weaterService;
    ApplicationContext context;
    
    public WeaterController(DataCollectorTask task, WeaterService weaterService)
    {
        this.task = task;
        this.weaterService = weaterService;
    }

    @RequestMapping("/history/{dateStart}/{dateEnd}/{city}/{type}/{period}")
    public ModelAndView findHistoricalStatistics(@PathVariable("dateStart") @DateTimeFormat(iso=ISO.DATE) Date dateStart, @PathVariable("dateEnd") @DateTimeFormat(iso=ISO.DATE) Date dateEnd, @PathVariable("city") City city, @PathVariable("type") StatisticsType type, @PathVariable("period") Period period)
    {
        Map<String, Float> results = weaterService.findHistoricalData(city, new DateInterval(dateStart, dateEnd), type, period);
        
        return createModelAndView(results);
    }
    
    private ModelAndView createModelAndView(Object results)
    {
        ModelAndView mav = new ModelAndView();
        mav.setView(context.getBean("jsonView", View.class));
        mav.addObject("responseCode", 200);
        mav.addObject("results", results);
        return mav;
    }
    
    @RequestMapping("/cities")
    public ModelAndView findCities()
    {
        List<City> cities = weaterService.findCities();
        
        return createModelAndView(cities.toArray());
    }
    
    @RequestMapping("/forecast/{city}")
    public ModelAndView findForecast(@PathVariable("city") City city)
    {
        List<Forecast> forecasts = weaterService.findForecast(city);

        return createModelAndView(forecasts.toArray());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        context = applicationContext;
    }
    
//    @RequestMapping("/test/{date}")
//    public void test(@PathVariable("date") @DateTimeFormat(iso=ISO.DATE) Date date)
//    {
//        task.collectWeaterHistory(date);
//    }
}
