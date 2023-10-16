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
public static void incrementCounter( int length){
        if (length==3){
            counet3.getAndIncrement();
        } else if (length==4) {
            counet4.getAndIncrement();
        }else if (length==5) {
            counet5.getAndIncrement();
        }
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


        Thread isPolindrom = new Thread(() -> {
            for (String text : texts) {
                if (text.equalsIgnoreCase(new StringBuilder(text).reverse().toString())) {
                    incrementCounter(text.length());
                }
            }
        });

        new Thread(() -> {
            for (String text : texts) {
                for (int i = 0; i < text.length()-1; i++)
                    if (text.charAt(i) == text.charAt(i + 1)) {
                        incrementCounter(text.length());
                    } else break;
                }
        }).start();


        new Thread(() -> {
            for (String text : texts) {
                for (int i = 0; i < text.length()-1; i++)
                    if (Character.getNumericValue((text.charAt(i))) >=
                            Character.getNumericValue((text.charAt(i + 1)))) {
                        incrementCounter(text.length());
                    } else break;

            }

        }).start();
        System.out.println("Красивых слов с длинной 4: " + counet4);
        System.out.println("Красивых слов с длинной 3: " + counet3);
        System.out.println("Красивых слов с длинной 5: " + counet5);


    }
}