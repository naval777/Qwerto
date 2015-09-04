package in.qwerto.qwerto.common;

/**
 * Created by sandeep on 27/8/15.
 */
public class ChatLocation extends ChatClass {

    double latitude;
    double longitude;
    String name;

    public ChatLocation(int side, int type){
        super(side,type);
    }

    public ChatLocation(int side, int type, String name, double lat,double lon){
        super(side,type);
        this.name=name;
        this.latitude=lat;
        this.longitude=lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
