package ar.edu.usal.programacion.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class FileUtil {

    public static List<String> readLines(String filePath) throws IOException {
        String localPath = System.getProperty("user.dir");
        List<String> lines = new ArrayList<>();
        File folder = new File(localPath + filePath); // Abro la carpeta
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Path path = file.toPath();
                    List<String> fileLines = Files.readAllLines(path);
                    lines.addAll(fileLines);
                }
            }
        }
        return lines;
    }

    public static void writeLines(String filePath, List<String> lines) throws IOException {
        String localPath = System.getProperty("user.dir");
        FileWriter fileWriter = new FileWriter(localPath + filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String line : lines) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
    }

    public static void guardar(String path, List<String> lines, String operacion) throws IOException {
        String localPath = System.getProperty("user.dir");
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(localPath + path + operacion + formatFecha() + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatFecha() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        return LocalDateTime.now().format(myFormatObj);
    }
}
