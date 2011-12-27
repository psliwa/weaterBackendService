package pk.ip.weather.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Observation
{
    public DateInformation date;
    
    /**
     * temp w C
     */
    public float tempm;
    
    /**
     * wilgotność
     */
    public int hum;
    
    /**
     * Kierunek wiatru w stopniach
     */
    public float wdird;
    
    /**
     * opis kierunku wiatru (np. SW)
     */
    public String wdire;
    
    /**
     * Siła wiatru w km/h
     */
    public float wspdm;
    
    /**
     * widoczność w Km
     */
    public float vism;
    
    /**
     * Ciśnienie w mBar
     */
    public float pressurem;
    
    /**
     * Ciśnienie w inHg
     */
    public float pressurei;
    
    /**
     * Odczuwalna temperatura w C
     */
    public float windchillm;

    /**
     * współczynnik ciepła w C (?)
     */
    public float heatindexm;

    public String conds;
    public String icon;
    
    public int fog;
    public int rain;
    public int snow;
    public int hail;
    public int thunder;
    public int tornado;
    public String metar;
}
