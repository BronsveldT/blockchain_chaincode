import org.capgemini.RoadAsset;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.assertj.core.api.Assertions.assertThat;
@Testable
public final class RoadAssetTest {

    @Nested
    class Equality {

        @Test
        public void isReflexive() {
            RoadAsset roadAsset = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );

            assertThat(roadAsset).isEqualTo(roadAsset);
        }

        @Test
        public void isSymmetric() {
            RoadAsset roadAssetA = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );
            RoadAsset roadAssetB = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );

            assertThat(roadAssetA).isEqualTo(roadAssetB);
            assertThat(roadAssetB).isEqualTo(roadAssetA);
        }

        @Test
        public void isTransitive() {
            RoadAsset roadAssetA = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );
            RoadAsset roadAssetB = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );
            RoadAsset roadAssetC = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );

            assertThat(roadAssetA).isEqualTo(roadAssetB);
            assertThat(roadAssetB).isEqualTo(roadAssetC);
            assertThat(roadAssetA).isEqualTo(roadAssetC);
        }

        @Test
        public void handlesInequality() {
            RoadAsset roadAssetA = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );
            RoadAsset roadAssetB = new RoadAsset(
                    "G",
                    "Thorbeckeweg",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );
            assertThat(roadAssetA).isNotEqualTo(roadAssetB);
        }

        @Test
        public void handlesOtherObjects() {
            RoadAsset roadAssetA = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );
            String roadAssetB = "not a RoadAsset";

            assertThat(roadAssetA).isNotEqualTo(roadAssetB);
        }

        @Test
        public void handlesNull() {
            RoadAsset roadAsset = new RoadAsset(
                    "G",
                    "Havenstraat",
                    945,
                    22.05,
                    "Zaandam",
                    "Zaanstad",
                    "Zaanstad",
                    "Noord-Holland"
            );

            assertThat(roadAsset).isNotEqualTo(null);
        }
    }

    @Test
    public void toStringIdentifiesRoadAsset() {
        RoadAsset roadAsset = new RoadAsset(
                "RoadAsset1",
                "G",
                "Havenstraat",
                945,
                22.05,
                "Zaandam",
                "Zaanstad",
                "Zaanstad",
                "Noord-Holland"
        );
        assertThat(roadAsset.toString()).isEqualTo("RoadAsset@63ec22ba [roadAssetId=RoadAsset1, roadAdminType=G, " +
                "streetName=Havenstraat, adminNumber=945, " +
                "distanceTravelledOn=22.05, adminName=Zaandam, " +
                "roadAdminName=Zaanstad, municipality=Zaanstad, state=Noord-Holland]");
    }
}
