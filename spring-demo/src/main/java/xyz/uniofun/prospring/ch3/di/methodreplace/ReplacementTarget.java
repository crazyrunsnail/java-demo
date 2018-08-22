package xyz.uniofun.prospring.ch3.di.methodreplace;

public class ReplacementTarget {

    public String formatMessage(String message) {
        return "<h1>" + message + "</h1>";
    }

    public String formatMessage(Object object) {
        return "<h1>" + object + "</h1>";
    }
}
