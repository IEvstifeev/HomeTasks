import javax.tools.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * LoadClass - класс записывает в файл код загружает и компилирует код
 */

class LoadClass{

    final Path path =  Paths.get("src");
    final String cl   = "SomeClass";

    /**
     * createSomeFile - создает файл SomeClass.java
     */

    public static String getSomeClassPath() {
        String relativePath = "src/SomeClass.java";
        try {
            File file = new File(relativePath);
            return file.getPath();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * writeFile - метод читает и записывает в SomeClass код с консоли
     */
    public void writeToFile() {
        Scanner s = new Scanner(System.in);
        Path javaFilePath = Paths.get(getSomeClassPath());
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
     */
    public void instanceMethod () throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Path javaFilePath = Paths.get(getSomeClassPath());
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        jc.run(null, null, null, javaFilePath.toAbsolutePath().toString());
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {path.toUri().toURL()});
        Class<?> cls = Class.forName(cl, true, classLoader);
        Worker instance = (Worker) cls.newInstance();
        instance.doWork();
    }
}
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        LoadClass loadclass = new LoadClass();
        loadclass.writeToFile();
        loadclass.instanceMethod();
    }
}