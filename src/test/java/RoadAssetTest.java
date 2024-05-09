import org.capgemini.RoadAsset;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
                "G",
                "Havenstraat",
                945,
                22.05,
                "Zaandam",
                "Zaanstad",
                "Zaanstad",
                "Noord-Holland"
        );
        System.out.println(roadAsset.toString());
        assertThat(roadAsset.toString()).isEqualTo("RoadAsset@e04f6c53 [RoadAssetID=RoadAsset1, color=Blue, size=20, owner=Guy, appraisedValue=100]");
    }
}
