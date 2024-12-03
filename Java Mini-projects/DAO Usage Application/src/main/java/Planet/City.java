package Planet;

public class City extends GeoUnit {

    private Integer countryId;
    private boolean capital;
    private float latitude;
    private float longitude;

    public City(Integer id, String name, Integer countryId, boolean capital, float latitude, float longitude) {
        super(id, name);
        this.countryId = countryId;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "countryId=" + countryId +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
