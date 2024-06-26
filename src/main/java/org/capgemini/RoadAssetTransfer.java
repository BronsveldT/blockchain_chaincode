package org.capgemini;

import com.owlike.genson.Genson;
import org.hyperledger.fabric.contract.Context;
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;

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
