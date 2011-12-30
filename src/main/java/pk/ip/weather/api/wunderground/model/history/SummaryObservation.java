package pk.ip.weather.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryObservation
{
    /**
     * Średnia temperatura w C
     */
    public Float meantempm;
    
    /**
     * Średnie ciśnienie
     */
    public Float meanpressurem;
    
    /**
     * Średni kierunek wiatru
     */
    public String meanwdire;
    
    /**
     * Średni kierunek wiatru w stopniach
     */
    public Float meanwdird;
    
    /**
     * Średnia siła wiatru w km/h
     */
    public Float meanwindspdm;
    
    /**
     * Średnia widoczność w km
     */
    public Float meanvism;
    
    /**
     * średnia wilgotność w %
     */
    public Integer humidity;
    
    public Integer fog;
    public Integer rain;
    public Integer snow;
    public Integer hail;
    public Integer thunder;
    public Integer tornado;
}
