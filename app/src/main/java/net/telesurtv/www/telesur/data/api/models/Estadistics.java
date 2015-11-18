package net.telesurtv.www.telesur.data.api.models;

/**
 * Created by Jhordan on 29/07/15.
 */
public class Estadistics {

    private String visits;
    private String lastVisit;
    private String visitCounter;
    private String indiceViewsCounter;
    private String popularity;
    private String dataViewCount;

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(String visitCounter) {
        this.visitCounter = visitCounter;
    }

    public String getIndiceViewsCounter() {
        return indiceViewsCounter;
    }

    public void setIndiceViewsCounter(String indiceViewsCounter) {
        this.indiceViewsCounter = indiceViewsCounter;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getDataViewCount() {
        return dataViewCount;
    }

    public void setDataViewCount(String dataViewCount) {
        this.dataViewCount = dataViewCount;
    }



}
