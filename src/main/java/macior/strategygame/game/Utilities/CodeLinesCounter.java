package macior.strategygame.game.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CodeLinesCounter {

    private int counter;
    private static String path = "C:\\Users\\grzeg\\IdeaProjects\\strategy-game\\src\\main\\java\\macior\\strategygame";

    public int countLines(){
        File file = new File(path);
        for (File f : file.listFiles()){
            treatRecursively(f);
        }

        return counter;
    }

    public void treatRecursively(File file){
        if (file.isDirectory()){
            for (File f : file.listFiles()){
                treatRecursively(f);
            }
        } else if (file.isFile() && file.getName().matches(".*\\.java")){
            countLines(file);
        }
    }

    public void countLines(File file){
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            while (scanner.hasNextLine()){
                scanner.nextLine();
                counter++;
            }
        } catch (IOException exc){

        }


    }

    public static void main(String[] args){
        CodeLinesCounter counter = new CodeLinesCounter();
        System.out.println(counter.countLines());
    }
}
