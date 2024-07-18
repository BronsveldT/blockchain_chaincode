package org.capgemini;

import com.owlike.genson.Genson;
import org.hyperledger.fabric.contract.Context;
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import java.util.ArrayList;
import java.util.List;

@Contract(
        name = "basic",
        info = @Info(
                title = "Road Asset transfer",
                description = "Capgemini initial contract for road assets",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Capgemini"
                ),
                contact = @Contact(
                        email = "Bronsveld.T@gmail.com", //placeholder
                        name = "Thomas Bronsveld" //placeholder
                )
        )
)
@Default
public class RoadAssetTransfer implements ContractInterface {

    private final Genson genson = new Genson();

    private enum AssetTransferErrors {
        ASSET_NOT_FOUND,
        ASSET_ALREADY_EXISTS
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public boolean roadAssetExists(final Context ctx, final String roadId) {
        ChaincodeStub stub = ctx.getStub();
        String assetJSON = stub.getStringState(roadId);

        return (assetJSON != null && !assetJSON.isEmpty());
    }
    /***
     *
     * Creates a new asset on the ledger
     *
     * @param ctx
     * @param roadAdminType
     * @param streetName
     * @param adminNumber
     * @param distanceTravelledOn
     * @param adminName
     * @param roadAdminName
     * @param municipality
     * @param state
     * @return the created asset
     */
    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public RoadAsset createRoadAsset(final Context ctx,
                                     final String roadId,
                                     final String roadAdminType,
                                     final String streetName,
                                     final int adminNumber,
                                     final double distanceTravelledOn,
                                     final String adminName,
                                     final String roadAdminName,
                                     final String municipality,
                                     final String state) {

        ChaincodeStub stub = ctx.getStub();
        if(roadAssetExists(ctx, roadId)){
            String errorMessages = String.format("Asset %s already exists", roadId);
            System.out.println(errorMessages);
            throw new ChaincodeException(errorMessages, AssetTransferErrors.ASSET_ALREADY_EXISTS.toString());
        }

        RoadAsset roadAsset = new RoadAsset(
                roadId,
                roadAdminType,
                streetName,
                adminNumber,
                distanceTravelledOn,
                adminName,
                roadAdminName,
                municipality,
                state
        );

        String sortedJson = genson.serialize(roadAsset);
        stub.putStringState(roadId, sortedJson);
        return roadAsset;
    }

    /**
     * Retrieves an asset with the specified ID from the ledger
     * @param ctx
     * @param roadId
     * @return
     */
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public RoadAsset readRoadAsset(final Context ctx, final String roadId) {
        ChaincodeStub stub = ctx.getStub();
        String assetJSON = stub.getStringState(roadId);

        if(assetJSON != null && !assetJSON.isEmpty()){
            String errorMessage = String.format("Asset %s does not exist", roadId);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, AssetTransferErrors.ASSET_NOT_FOUND.toString());
        }

        RoadAsset roadAsset = genson.deserialize(assetJSON, RoadAsset.class);
        return roadAsset;
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public RoadAsset updateRoadAsset(final Context ctx,
                                     final String roadId,
                                     final double distanceTravelledOn) {

        ChaincodeStub stub = ctx.getStub();

        if(!roadAssetExists(ctx, roadId)){
            String errorMessages = String.format("Asset %s does not exist", roadId);
            System.out.println(errorMessages);
            throw new ChaincodeException(errorMessages, AssetTransferErrors.ASSET_NOT_FOUND.toString());
        }
        String assetJSON =  stub.getStringState(roadId);
        RoadAsset roadAsset = genson.deserialize(assetJSON, RoadAsset.class);
        roadAsset.addDistanceTravelledOn(distanceTravelledOn);
        String sortedJson = genson.serialize(roadAsset);
        stub.putStringState(roadAsset.getRoadId(), sortedJson);
        return roadAsset;
    }

    /**
     *
     * @param ctx
     * @param roadName
     * @return A list of roadAssets that go by the roadName
     */
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public List<RoadAsset> retrieveRoadAssetsByName(Context ctx, String roadName) {


        String queryString = String.format("{\"selector\":{\"streetName\":\"%s\"}}", roadName);

        QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(queryString);

        List<RoadAsset> roads = new ArrayList<>();

        for (KeyValue keyValue : results) {
            RoadAsset roadAsset = genson.deserialize(keyValue.getValue(), RoadAsset.class);
            roads.add(roadAsset);
        }

        return roads;
    }

    /**
     *
     * @param ctx
     * @param municipalityName
     * @return A list of roadAssets that go by the roadName
     */
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public List<RoadAsset> retrieveRoadAssetsByMunicipality(Context ctx, String municipalityName) {


        String queryString = String.format("{\"selector\":{\"municipality\":\"%s\"}}", municipalityName);

        QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(queryString);

        List<RoadAsset> roads = new ArrayList<>();
        System.out.println(results);
        for (KeyValue keyValue : results) {
            System.out.println(keyValue);
            RoadAsset roadAsset = genson.deserialize(keyValue.getValue(), RoadAsset.class);
            System.out.println(roadAsset);
            roads.add(roadAsset);
        }
        System.out.println(roads);
        return roads;
    }

    /**
     *
     * @param ctx
     * @param roadAdminType
     * @return A list of roadAssets that go by the roadName
     */
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public List<RoadAsset> retrieveRoadAssetsByAdminType(Context ctx, String roadAdminType) {


        String queryString = String.format("{\"selector\":{\"roadAdminType\":\"%s\"}}", roadAdminType);

        QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(queryString);

        List<RoadAsset> roads = new ArrayList<>();

        for (KeyValue keyValue : results) {
            RoadAsset roadAsset = genson.deserialize(keyValue.getValue(), RoadAsset.class);
            roads.add(roadAsset);
        }

        return roads;
    }

    /**
     *
     * @param ctx
     * @param state
     * @return A list of roadAssets that go by the roadName
     */
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public List<RoadAsset> retrieveRoadAssetsByState(Context ctx, String state) {


        String queryString = String.format("{\"selector\":{\"state\":\"%s\"}}", state);

        QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(queryString);

        List<RoadAsset> roads = new ArrayList<>();

        for (KeyValue keyValue : results) {
            RoadAsset roadAsset = genson.deserialize(keyValue.getValue(), RoadAsset.class);
            roads.add(roadAsset);
        }

        return roads;
    }
    /**
     *
     * @param ctx
     * @param roadId
     */
    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void deleteRoadAsset(final Context ctx, final String roadId) {
        ChaincodeStub stub = ctx.getStub();

        if(!roadAssetExists(ctx,roadId)) {
            String errorMessage = String.format("Road asset %s does not exist", roadId);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, AssetTransferErrors.ASSET_NOT_FOUND.toString());
        }

        stub.delState(roadId);
    }
}
