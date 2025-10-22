package data.orders;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetOrderByTrackData {

    private Integer t;

    public GetOrderByTrackData(Integer t) {
        this.t = t;
    }

    public GetOrderByTrackData() {
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }
}
