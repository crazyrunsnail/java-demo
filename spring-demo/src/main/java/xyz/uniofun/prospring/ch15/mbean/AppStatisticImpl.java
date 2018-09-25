package xyz.uniofun.prospring.ch15.mbean;

import org.springframework.beans.factory.annotation.Autowired;

public class AppStatisticImpl implements AppStatistic {
    @Autowired
    SingerService singerService;
    @Override
    public int getTotalSingerCount() {
        return singerService.findAll().size();
    }
}
