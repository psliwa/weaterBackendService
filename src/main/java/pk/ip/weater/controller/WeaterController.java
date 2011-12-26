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
    
    @RequestMapping("/test")
    @ResponseBody()
    public String test()
    {
        task.collectWeaterHistory();
        
        return "abc";
    }
}
