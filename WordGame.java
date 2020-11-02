import java.util.Random;
import java.util.Scanner;

class WordGame {
    public static void main(String[] args) {

        String data = "Małgosia;je;jabłko;Ma;rumiane;policzki;Niepewność;zrywają;jabłka z jabłoni;" +
                "Jedzą;dojrzałe, żółte gruszki;Piją zimną;krystaliczną wodę;Ania;zrywa;truskawki;Żeby;później;" +
                "zrobić z nich dżem;Paweł i Robert;uwielbiają;dżem Ani;Gdy nadchodzi jesień;cała kuchnia;" +
                "pachnie konfiturami;Ani Robert;otwiera;słoik z kompotem;z czereśni;Paweł;piecze;malinową;tartę;" +
                "Jamajka na sterydach;wąż udusił Ewę;niemoralność Pani Dulskiej;miałka mąka;zelektryfikowany górnik";

        double maxPoints = 0;
        double userPoints = 0;
        {
            for (int i = 0; i < data.length(); i++) {
                char letter = data.charAt(i);
                if (letter != ';') {
                    maxPoints++;
                }
            }
        }

        double gameTime = 0;
        {
            System.out.print("Ile czasu ma trwać gra? [podaj czas w sekundach]: ");
            Scanner scanner = new Scanner(System.in);
            double gameTimeInSeconds = scanner.nextDouble();
            gameTime = gameTimeInSeconds * 1000;
        }

        long startTime = System.currentTimeMillis();
        {
            while (true) {

                int position;
                {
                    String separator = ";";

                    int lastTypedSign = data.lastIndexOf(separator);

                    Random random = new Random();
                    position = random.nextInt(lastTypedSign);
                }

                String text;
                {
                    String separator = ";";
                    int firstPositionIndex = data.indexOf(separator, position);
                    int secondPositionIndex = data.indexOf(separator, firstPositionIndex + 1);

                    if (secondPositionIndex == -1) {
                        text = data.substring(firstPositionIndex + 1);
                    } else
                        text = data.substring(firstPositionIndex + 1, secondPositionIndex);
                    System.out.println(text);
                }

                String userText;
                {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Przepisz tekst: ");
                    userText = sc.nextLine();
                    double points = 0;
                    for (int i = 0; i < text.length(); i++) {
                        char userSign = text.charAt(i);
                        if (i >= userText.length()) {
                            break;
                        }
                        char stockSign = userText.charAt(i);
                        if (userSign == stockSign) {
                            points++;
                        } else if (Character.toLowerCase(stockSign) == Character.toLowerCase(userSign)) {
                            points = points + 0.5;
                        }

                    }
                    userPoints += points;
                }

                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime > gameTime) {
                    System.out.println("Czas zakończony w: " + ((currentTime - startTime) / 1000) + " sek.");
                    break;
                }
            }
        }
        System.out.println("Uzyskałeś: " + userPoints + " na " + maxPoints + " punktów możliwych.");
    }
}