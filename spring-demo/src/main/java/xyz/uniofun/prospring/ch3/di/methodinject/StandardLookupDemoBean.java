package xyz.uniofun.prospring.ch3.di.methodinject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {
    private Singer singer;

    @Autowired
    @Qualifier("singer")
    public void setMySinger(Singer singer) {
        this.singer = singer;

    }

    @Override
    public Singer getSinger() {
        return this.singer;
    }

    @Override
    public void doSomething() {
        this.singer.sing();
    }
}
