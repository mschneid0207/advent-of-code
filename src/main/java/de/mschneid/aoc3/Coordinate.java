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

        public void moveRight() {
                this.x++;
        }

        public void moveLeft() {
                this.x--;
        }

        public void moveUp() {
                this.y++;
        }

        public void moveDown() {
                this.y--;
        }
}
