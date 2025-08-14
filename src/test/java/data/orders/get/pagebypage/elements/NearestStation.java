package data.orders.get.pagebypage.elements;

import java.util.List;

public class NearestStation {

    private List<String> nearestStation;

    public NearestStation(List<String> nearestStation) {
        this.nearestStation = nearestStation;
    }

    public NearestStation() {
    }

    public List<String> getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(List<String> nearestStation) {
        this.nearestStation = nearestStation;
    }

}
