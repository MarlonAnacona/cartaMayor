package cartaMayor;

import java.util.Random;

public class carta {

        private int cara;

        public carta() {
        }

        public int getCara() {
            Random aleatorio = new Random();
            this.cara = aleatorio.nextInt(12) + 1;
            return this.cara;
        }
}
