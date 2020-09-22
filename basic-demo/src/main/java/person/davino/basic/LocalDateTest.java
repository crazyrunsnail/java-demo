package person.davino.basic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LocalDateTest {

    public static String getReviewTimeInterval(Date createTime, Date reviewTime) {
        if (createTime == null || reviewTime == null) return null;
        long createTs = createTime.getTime();
        long reviewTs = reviewTime.getTime();
        if (reviewTs <= createTs) return "0min";
        long intevalTs = (reviewTs - createTs) / 1000;

        long nd = 24 * 60 * 60;//每天秒数
        long nh = 60 * 60;//每小时秒数
        long nm = 60; //每分钟秒数

        long day = intevalTs / nd;   // 计算差多少天
        long hour = intevalTs % nd / nh; // 计算差多少小时
        long min = intevalTs % nd % nh / nm;  // 计算差多少分钟
        long sec = intevalTs % nd % nh % 60; // 计算秒数
        if (day > 5) {
            return day + "d";
        }
        StringBuilder sb = new StringBuilder();
        if (day > 0){
            sb.append(day).append("d");
        }
        if (hour > 0) {
            sb.append(hour).append("h");
        }
        if (min > 0) {
            sb.append(min).append("min");
        }
        if (sec > 0) {
            sb.append(sec).append("s");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LocalDateTime localdatetiem = LocalDateTime.of(2020, 04, 15, 17, 45, 0);
        Date from = Date.from(localdatetiem.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(getReviewTimeInterval(from, new Date()));
    }

}
