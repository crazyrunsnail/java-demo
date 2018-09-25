package xyz.uniofun.prospring.ch15.mbean;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class SingerServiceImpl implements SingerService {
    @Override
    public Collection<?> findAll() {
        return Arrays.asList("a", "b");
    }
}
