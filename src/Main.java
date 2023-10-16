import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static AtomicInteger counet3 = new AtomicInteger(0);
    public static AtomicInteger counet4 = new AtomicInteger(0);
    public static AtomicInteger counet5 = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }


        new Thread(() -> {
            for (String text : texts) {
                if (text.equalsIgnoreCase(new StringBuilder(text).reverse().toString())) {
                    counet4.addAndGet(1);
                }
            }
        }).start();

        new Thread(() -> {
            for (String text : texts) {
                if (text.length() == 3 &&
                        text.charAt(0) == text.charAt(1) &&
                        text.charAt(2) == text.charAt(1) &&
                        text.charAt(0) == text.charAt(2)) {
                    counet3.addAndGet(1);
                }

            }
        }).start();
        Integer wordNum = 0;

        new Thread(() -> {
            for (String text : texts) {
                if (text.length() == 5) {

                    for (int i = 0; i < 4; i++)
                        if (Character.getNumericValue((text.charAt(i))) >=
                                Character.getNumericValue((text.charAt(i + 1)))) {
                            counet5.addAndGet(1);
                        } else break;


                }
            }
        }).start();
        System.out.println(counet4);
        System.out.println(counet3);
        System.out.println(counet5);


    }
}