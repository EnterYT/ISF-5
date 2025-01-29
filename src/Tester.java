import java.util.Random;

public class Tester {
    public static void main(String[] args) {
//        char[] test = new char[] {'a', 'b', 'c', 'd', 'e'};
//        test = removeElement(test, 2);
//        System.out.println(test);
//        System.out.println(test.length);

//        DiceResult result = dice(20, 2);
//        System.out.println(result.getResults());
//        System.out.println(result.getTotal());


        String text = "watamelan";
        String a = 'a' + "";
        System.out.println(text);
        text = text.replaceAll(a, "");
        System.out.println(text);

    }

    private static char[] removeElement(char[] array, int id) {
        return new String(array).replaceFirst(Character.toString(array[id]), "").toCharArray();
    }
    private static DiceResult dice(int size, int amount){
        Random random = new Random();
        int[] res = new int[amount];
        int total = 0;
        for (int i = 0; i < amount; i++) {
            int n = random.nextInt(size) + 1;
            res[i] = n;
            total += n;
        }
        DiceResult result = new DiceResult(res, total);
        return result;
    }
}

