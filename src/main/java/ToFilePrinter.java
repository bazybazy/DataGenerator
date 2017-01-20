import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by admin on 06.01.2017.
 */
public class ToFilePrinter {
    public static void printIntsToFile(List<Integer> list, String path) throws IOException {
        StringBuilder text =new StringBuilder();
        list.forEach(e -> text.append(e.toString()).append('\n'));
        PrintWriter writer = new PrintWriter(path);
        writer.print(text);
        writer.close();
    }
    public static void printStringsToFile(List<String> list, String path) throws IOException {
        StringBuilder text =new StringBuilder();
        list.forEach(e -> text.append(e).append('\n'));
        PrintWriter writer = new PrintWriter(path);
        writer.print(text);
        writer.close();
    }
}
