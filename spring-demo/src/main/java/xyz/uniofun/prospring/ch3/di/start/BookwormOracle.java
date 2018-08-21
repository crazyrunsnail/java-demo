package xyz.uniofun.prospring.ch3.di.start;

public class BookwormOracle implements Oracle {
    private Encyclopedia encyclopedia;
    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - go see the world instead";
    }

    private class Encyclopedia {
    }
}
