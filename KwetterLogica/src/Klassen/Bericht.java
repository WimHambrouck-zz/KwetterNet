package Klassen;

import java.util.Date;

/**
        * Deze klasse stelt een bericht voor.
        */

public class Bericht extends Verzendbaar{


    private int ontvangersId;
    private String inhoud;
    private Date tijdstip;


    public Bericht() {
    }


    /**
     * @param ontvangersId
     * @param inhoud
     * @param tijdstip
     */
    public Bericht(int ontvangersId, String inhoud, Date tijdstip) {
        setOntvangersId(ontvangersId);
        setInhoud(inhoud);
        setTijdstip(tijdstip);
    }

    public int getOntvangersId() {
        return ontvangersId;
    }

    public void setOntvangersId(int ontvangersId) {
        if (ontvangersId >=0){
            this.ontvangersId = ontvangersId;
        }else{
            throw new IllegalArgumentException("Id lager dan 0");
        }
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        if (!inhoud.isEmpty()) {
            this.inhoud = inhoud;
        } else {
            throw new IllegalArgumentException("Inhoud is leeg");
        }
    }

    public Date getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(Date tijdstip) {
        if (tijdstip != null) {
            this.tijdstip = tijdstip;
        } else {
            throw new IllegalArgumentException("Tijdstip is null");
        }
    }
}