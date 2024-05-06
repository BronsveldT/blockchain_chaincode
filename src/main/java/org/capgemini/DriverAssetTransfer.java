package org.capgemini;

import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;

@Contract(
        name = "driverAsset",
        info = @Info(
                title = "Driver Asset Transfer",
                description = "Capgemini initial contract for driver assets",
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
public class DriverAssetTransfer {

}
