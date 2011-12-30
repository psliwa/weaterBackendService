package pk.ip.weather.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Observation
{
    public DateInformation date;
    
    /**
     * temp w C
     */
    public Float tempm;
    
    /**
     * wilgotność
     */
    public Integer hum;
    
    /**
     * Kierunek wiatru w stopniach
     */
    public Float wdird;
    
    /**
     * opis kierunku wiatru (np. SW)
     */
    public String wdire;
    
    /**
     * Siła wiatru w km/h
     */
    public Float wspdm;
    
    /**
     * widoczność w Km
     */
    public Float vism;
    
    /**
     * Ciśnienie w mBar
     */
    public Float pressurem;
    
    /**
     * Ciśnienie w inHg
     */
    public Float pressurei;
    
    /**
     * Odczuwalna temperatura w C
     */
    public Float windchillm;

    /**
     * współczynnik ciepła w C (?)
     */
    public Float heatindexm;

    public String conds;
    public String icon;
    
    public Integer fog;
    public Integer rain;
    public Integer snow;
    public Integer hail;
    public Integer thunder;
    public Integer tornado;
    public String metar;
}
