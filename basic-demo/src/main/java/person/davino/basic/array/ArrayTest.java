package person.davino.basic.array;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 13/03/2018
 */
public class ArrayTest {
    public static void main(String[] args) {
        // 声明数组
        int[] ints = {1, 2, 3, 4};
        for (int i = 0; i < ints.length; i++ ) {
            System.out.println(ints[i]);
        }

        //二维数组
        int[][] doubleInts = {{2},{3}};
        for (int i = 0; i < doubleInts.length; i++) {
            for (int j = 0; j < doubleInts[i].length; j++){
                System.out.println(doubleInts[i][j]);
            }
        }
    }
}
