import javax.tools.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * LoadClass - класс записывает в файл код загружает и компилирует код
 */

class LoadClass{

    Path path = Paths.get("src");

    /**
     * createSomeFile - создает файл SomeClass.java
     */

    public  void createSomeFile() {
        String relativePath = "src/SomeClass.java";
        try {
            File file = new File(relativePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * writeFile - метод читает и записывает в SomeClass код с консоли
     */
    public void writeToFile() {
        Scanner s = new Scanner(System.in);
        Path javaFilePath = path.resolve("SomeClass.java");
        try (BufferedWriter bw = Files.newBufferedWriter(javaFilePath)) {
            bw.write("public class SomeClass implements Worker {\n");
            bw.write("public void doWork() {\n");

            while (true) {
                String line = s.nextLine();
                if (line.equals("")) {
                    break;
                }
                bw.write(line);
            }

            bw.write("}\n");
            bw.write("}");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * instanceMethod - метод загружает и компилирует SomeClass
     * @param path - путь к файлу
     */
    public void instanceMethod (Path path) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File("out/production/lesson09")));

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays
                .asList(new File[] { new File("src/SomeClass.java") }));
        jc.getTask(null, fileManager, null, null, null, compilationUnits).call();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {path.toUri().toURL()});
        Class<?> cls = Class.forName("SomeClass", true, classLoader);
        Worker instance = (Worker) cls.newInstance();
        instance.doWork();
    }
}
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        LoadClass loadclass = new LoadClass();
        loadclass.createSomeFile();
        loadclass.writeToFile();
        loadclass.instanceMethod(loadclass.path);
    }
}