package pk.ip.weater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pk.ip.weater.task.DataCollectorTask;

@Controller()
public class WeaterController
{
    DataCollectorTask task;
    
    public WeaterController(DataCollectorTask task)
    {
        this.task = task;
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
}
