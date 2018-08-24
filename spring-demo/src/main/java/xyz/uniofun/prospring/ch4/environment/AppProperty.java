package xyz.uniofun.prospring.ch4.environment;

public class AppProperty {

    private String applicationHome;
    private String userHome;

    public String getUserHome() {
        return userHome;
    }

    public void setUserHome(String userHome) {
        this.userHome = userHome;
    }

    public String getApplicationHome() {
        return applicationHome;
    }

    public void setApplicationHome(String applicationHome) {
        this.applicationHome = applicationHome;
    }
}
