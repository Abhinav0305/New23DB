package nav.library.new23db.model;

import java.io.Serializable;

/**
 * Created by abhin on 4/5/2017.
 */

public class Address implements Serializable {
    private String city;
    private String state;
    private String address;
    private int zipCode;

    public Address() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }
}
