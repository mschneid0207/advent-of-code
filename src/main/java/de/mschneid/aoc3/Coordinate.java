package de.mschneid.aoc3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Coordinate {

        private int x;
        private int y;

        public String getCoordinateKey() {
                return "x" + this.x + "y" + this.y;
        }
}
