package person.davino.stream;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Vo v = new Vo();
        Optional.ofNullable(v.getName()).orElse("");
    }

    private static class Vo {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
