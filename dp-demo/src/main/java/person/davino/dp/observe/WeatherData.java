package person.davino.dp.observe;

import person.davino.dp.observe.wrongexample.Display;
import person.davino.dp.observe.wrongexample.OneDisplay;
import person.davino.dp.observe.wrongexample.TwoDisplay;

public class WeatherData {

    private Display one = new OneDisplay();

    private Display two = new TwoDisplay();

    // 平常的做法是就是直接写业务逻辑
    // 这里会引出一些问题, 很难拓展, 或者说相互之间关联太大
    // 如果这里的Displays 能够动态, 首先想到的是将他们保存在一个List里面, 然后去调相对应的方式
    public void measurementsChanged() {
        float temp = getTempurature();
        float humidity = getHumidity();
        float pressure = getPressure();

        one.update(temp, humidity, pressure);

        two.update(temp, humidity, pressure);

    }


    private float getTempurature() {
        return 1.0F;
    }

    private float getHumidity() {
        return 2.0F;
    }

    private float getPressure() {
        return 3.0F;
    }

}
