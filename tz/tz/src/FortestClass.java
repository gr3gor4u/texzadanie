import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FortestClass {
    public static List<String> animals = new ArrayList<>();

    public static void main(String[] args) {
        String filepath = "ffx.txt";// Загружаем правила из файла
        loadParamAnimal("ddj.txt");
        // Определяем правила
        String[] arrayVes = new String[0];
        String[] arrayRost = new String[0];
        String[] arrayType = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                // передаем в каждый массив разные параметры:
                switch (i) {
                    case 0:
                        arrayVes = words;
                        break;
                    case 1:
                        arrayRost = words;
                        break;
                    case 2:
                        arrayType = words;
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Животных травоядных: " + countAnimalsMatchingRule(arrayType[0]));

        System.out.println("Животных травоядных или плотоядных, маленьких: "
                + (countAnimalsMatchingRule(arrayRost[0], arrayType[1])
                        + countAnimalsMatchingRule(arrayRost[0], arrayType[0])));

        System.out.println("Животных всеядных, не высоких: " + (countAnimalsMatchingRule(arrayRost[0], arrayType[2])
                + countAnimalsMatchingRule(arrayRost[1], arrayType[2])));
    }

    public static void loadParamAnimal(String filename) {
        try {

            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                animals.add(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }

    }

    public static int countAnimalsMatchingRule(String... parameters) {
        int count = 0;
        for (String animal : animals) {
            boolean match = true;
            for (String param : parameters) {
                if (!animal.contains(param)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                count++;
            }
        }
        return count;
    }
}
