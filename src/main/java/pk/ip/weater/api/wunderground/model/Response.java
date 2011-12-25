package pk.ip.weater.api.wunderground.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Response
{
    private double version;
    private String termsofService;
    private Map<String, String> features = new HashMap<String, String>();

    public void addFeature(String name, String value)
    {
        features.put(name, value);
    }
    
    public Map<String, String> getFeatures()
    {
        return Collections.unmodifiableMap(features);
    }
    
    public String getTermsOfService()
    {
        return termsofService;
    }

    public void setTermsOfService(String termsofService)
    {
        this.termsofService = termsofService;
    }

    public double getVersion()
    {
        return version;
    }

    public void setVersion(double version)
    {
        this.version = version;
    }
}
