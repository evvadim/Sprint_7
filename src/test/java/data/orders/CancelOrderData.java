package data.orders;

public class CancelOrderData {

    private Integer track;

    public CancelOrderData(Integer track) {
        this.track = track;
    }

    public CancelOrderData() {
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }
}
