package org.capgemini;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;

@DataType
public class DriverAsset {

    @Property()
    private final String driverAssetId;

    @Property()
    private String licensePlate;

    @Property()
    private String brand;
    @Property()
    private String emissionType;
    @Property()
    private double[] drivenKilometersOnRoad;
    @Property()
    private double rideCosts;

    public DriverAsset(@JsonProperty("driverAssetId") String driverAssetId) {
        this.driverAssetId = driverAssetId;
    }

    public DriverAsset(@JsonProperty("driverAssetId") String driverAssetId,
                       @JsonProperty("licensePlate") String licensePlate,
                       @JsonProperty("brand") String brand,
                       @JsonProperty("emissionType") String emissionType,
                       @JsonProperty("drivenKilometersOnRoad") double[] drivenKilometersOnRoad,
                       @JsonProperty("rideCosts") double rideCosts) {

        this.driverAssetId = driverAssetId;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.emissionType = emissionType;
        this.drivenKilometersOnRoad = drivenKilometersOnRoad;
        this.rideCosts = rideCosts;
    }

    public String getDriverAssetId() {
        return driverAssetId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getEmissionType() {
        return emissionType;
    }

    public double[] getDrivenKilometersOnRoad() {
        return drivenKilometersOnRoad;
    }

    public void addDrivenKilometersOnRoad(double[] drivenKilometers) {

        for (int i = 0; i < drivenKilometersOnRoad.length; i++) {
            drivenKilometersOnRoad[i] += drivenKilometers[i];
        }
    }

    public double getRideCosts() {
        return rideCosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverAsset that = (DriverAsset) o;

        return Objects.deepEquals(getDriverAssetId(), that.getDriverAssetId()) && Objects.equals(getLicensePlate(), that.getLicensePlate()) && Objects.equals(getBrand(), that.getBrand()) && Objects.equals(getEmissionType(), that.getEmissionType()) && Objects.equals(getDrivenKilometersOnRoad(), that.getDrivenKilometersOnRoad()) && Objects.equals(getRideCosts(), that.getRideCosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDriverAssetId(), getLicensePlate(), getBrand(), getEmissionType(), getDrivenKilometersOnRoad(), getRideCosts());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode())
                + "[driverAssetId=" + driverAssetId + ", licensePlate=" + licensePlate
                + ", brand=" + brand + ", emissionType=" + emissionType
                + ", drivenKilometersOnRoad=" + drivenKilometersOnRoad + ", rideCosts="
                + rideCosts + "]";
    }
}