package Planet;

public class Country extends GeoUnit {
    private String code;
    private Integer continentId;

    public Country(Integer id, String name, String code, Integer continentId) {
        super(id, name);
        this.code = code;
        this.continentId = continentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }
}
