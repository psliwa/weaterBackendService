package pk.ip.weather.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryObservation
{
    /**
     * Średnia temperatura w C
     */
    public float meantempm;
    
    /**
     * Średnie ciśnienie
     */
    public float meanpressurem;
    
    /**
     * Średni kierunek wiatru
     */
    public String meanwdire;
    
    /**
     * Średni kierunek wiatru w stopniach
     */
    public float meanwdird;
    
    /**
     * Średnia siła wiatru w km/h
     */
    public float meanwindspdm;
    
    /**
     * Średnia widoczność w km
     */
    public float meanvism;
    
    /**
     * średnia wilgotność w %
     */
    public int humidity;
    
    public int fog;
    public int rain;
    public int snow;
    public int hail;
    public int thunder;
    public int tornado;
}
