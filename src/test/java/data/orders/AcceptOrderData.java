package data.orders;

public class AcceptOrderData {

    private Integer id;
    private Integer courierId;

    public AcceptOrderData(Integer id, Integer courierId) {
        this.id = id;
        this.courierId = courierId;
    }

    public AcceptOrderData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }
}
