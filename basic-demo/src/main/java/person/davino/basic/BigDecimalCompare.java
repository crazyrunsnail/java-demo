package person.davino.basic;

import java.math.BigDecimal;

/**
 * 测试BigDecimal的比较方法;
 */
public class BigDecimalCompare {

    public static void main(String[] args) {
        BigDecimal totolFee = new BigDecimal("306.00");
        // [错误示范]！ 返回false
        System.out.println(totolFee.equals(BigDecimal.valueOf(306.0d)));
        // ➤
        System.out.println(totolFee.compareTo(BigDecimal.valueOf(306.0d)));
        // ➤
        System.out.println(totolFee.doubleValue() == (BigDecimal.valueOf(306.0d)).doubleValue());
    }
}
