package org.capgemini;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;

@DataType()
public class RoadAsset {
    @Property() private final String roadId;
    @Property() private final String roadAdminType;
    @Property() private final String streetName;
    @Property() private final int adminNumber;
    @Property() private final double distanceTravelledOn;
    @Property() private final String adminName;
    @Property() private final String roadAdminName;
    @Property() private final String municipality;
    @Property() private final String state;


    public RoadAsset( @JsonProperty("roadAssetId")String roadId,
                      @JsonProperty("roadAdminType") String roadAdminType,
                      @JsonProperty("streetName") String streetName,
                      @JsonProperty("adminNumber") int adminNumber,
                      @JsonProperty("distanceTravelledOn") double distanceTravelledOn,
                      @JsonProperty("adminName") String adminName,
                      @JsonProperty("roadAdminName") String roadAdminName,
                      @JsonProperty("municipality") String municipality,
                      @JsonProperty("state") String state) {

        this.roadId = roadId;
        this.roadAdminType = roadAdminType;
        this.streetName = streetName;
        this.adminNumber = adminNumber;
        this.distanceTravelledOn = distanceTravelledOn;
        this.adminName = adminName;
        this.roadAdminName = roadAdminName;
        this.municipality = municipality;
        this.state = state;
    }

    public String getRoadId() {
        return roadId;
    }

    public String getRoadAdminType() {
        return roadAdminType;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getAdminNumber() {
        return adminNumber;
    }

    public double getDistanceTravelledOn() {
        return distanceTravelledOn;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getRoadAdminName() {
        return roadAdminName;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoadAsset roadAsset = (RoadAsset) o;
        return getAdminNumber() == roadAsset.getAdminNumber() && Double.compare(getDistanceTravelledOn(), roadAsset.getDistanceTravelledOn()) == 0 && Objects.equals(getRoadAdminType(), roadAsset.getRoadAdminType()) && Objects.equals(getStreetName(), roadAsset.getStreetName()) && Objects.equals(getAdminName(), roadAsset.getAdminName()) && Objects.equals(getRoadAdminName(), roadAsset.getRoadAdminName()) && Objects.equals(getMunicipality(), roadAsset.getMunicipality()) && Objects.equals(getState(), roadAsset.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoadAdminType(), getStreetName(), getAdminNumber(), getDistanceTravelledOn(), getAdminName(), getRoadAdminName(), getMunicipality(), getState());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [roadAssetId=" +
            roadId + ", roadAdminType=" + roadAdminType + ", streetName=" + streetName
                + ", adminNumber=" + adminNumber
                + ", distanceTravelledOn=" + distanceTravelledOn
                + ", adminName=" + adminName
                + ", roadAdminName=" + roadAdminName
                + ", municipality=" + municipality
                + ", state=" + state + "]";

    }
}
