package task01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для работы с ресурсами
 * @author Igor Evstifeev
 */

public class Main {

    static final String DIR = "src/task01";
    static final String FILE_IN = "fileTest.txt";
    static final String FILE_OUT = "fileTest_out.txt";

    public static void main(String[] args) {
        Path fileIn = buildPath(FILE_IN);
        Path fileOut = buildPath(FILE_OUT);
        final Set<String> words = new TreeSet<>();
        System.out.println(fileIn);
        try (BufferedReader br = Files.newBufferedReader(fileIn)) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String str : line.split("[\\s,]+")) {
                    words.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        words.stream().forEach(System.out::println);

        try (BufferedWriter bw = Files.newBufferedWriter(fileOut)) {
            for (String word : words) {
                bw.write(word);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * buildPath - метод возвращающий объект Path
     * @param fileName
     * @return
     */

    public static Path buildPath(String fileName) {
        return Paths.get(DIR).resolve(Paths.get(fileName));
    }
}
