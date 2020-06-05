package ru.mai;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Tester {

    private HashMap<String, Long> testsResult;
    private FileWorker fileWorker;
    private static Scanner in = new Scanner(System.in);
    private Integer quantity;
    private Integer maxValue;
    private ArrayList<String> brands;

    Tester() throws Exception {
        System.out.println("Провести генерацию данных? Да - 1 Нет - 0:");
        if (in.next().equals("0")) {
            return;
        }

        testsResult = new HashMap<>();
        fileWorker = new FileWorker("output.txt", "input.txt", String.valueOf(StandardCharsets.UTF_8), "Cp1251");

        String[] buff = {"ауди", "бмв", "мерседес", "смарт", "ваз", "камаз", "тесла", "порше", "рено", "киа"};
        ArrayList<String> buffBrands = new ArrayList<String>(Arrays.asList(buff));

        System.out.print("Введите количество брендов: ");
        Integer quantity = in.nextInt();
        System.out.println("Введите максимальную возможную стоимость любой марки:");
        Integer maxValue = in.nextInt();
        /*System.out.println("Вводите название брендов построчно");
        in.nextLine();

        while (in.hasNextLine() && !in.hasNext("~")) {
            buffBrands.add(in.nextLine());
        }
        in.next();*/

        /*
        this.quantity = quantity;
        this.maxValue = maxValue;
        this.brands = buffBrands;
        */
        fileWorker.printData(generate(quantity, maxValue, buffBrands));
    }

    private List<String> generate(Integer quantity, Integer maxValue, List<String> brands) {
        Random random = new Random();
        ArrayList<String> res = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            StringBuilder buffStr = new StringBuilder();
            buffStr.append("\"" + brands.get(random.nextInt(brands.size())) + "\"");
            buffStr.append(":");
            buffStr.append("\"" + random.nextInt(maxValue) + "\"");

            res.add(String.valueOf(buffStr).strip());
        }

        return res;
    }
}
