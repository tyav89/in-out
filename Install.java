import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Install {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File file = null;

        File dir = new File("C:\\Users\\tdv\\Desktop\\Games\\src\\main");
        if (dir.mkdirs()) {
            sb
                    .append("Создана директория \"src\"")
                    .append("\n")
                    .append("Создана директория \"main\" в \"src\"")
                    .append("\n");

            file = new File("C:\\Users\\tdv\\Desktop\\Games\\src\\main\\Main.java");
            try {
                file.createNewFile();
                sb
                        .append("Создан файл \"Main.java\"")
                        .append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            file = new File("C:\\Users\\tdv\\Desktop\\Games\\src\\main\\Utils.java");
            try {
                file.createNewFile();
                sb
                        .append("Создан файл \"Utils.java\"")
                        .append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dir = new File("C:\\Users\\tdv\\Desktop\\Games\\src\\test");
        if (dir.mkdir()) {
            sb
                    .append("Создана директория \"test\" в \"src\"")
                    .append("\n");
        }
        dir = new File("C:\\Users\\tdv\\Desktop\\Games\\res\\drawables");
        if (dir.mkdirs()) {
            sb
                    .append("Создана директория \"res\"")
                    .append("\n")
                    .append("Создана директория \"drawables\" в \"res\"")
                    .append("\n");
        }
        dir = new File("C:\\Users\\tdv\\Desktop\\Games\\res\\vectors");
        if (dir.mkdir()) {
            sb
                    .append("Создана директория \"vectors\" в \"res\"")
                    .append("\n");
        }
        dir = new File("C:\\Users\\tdv\\Desktop\\Games\\res\\icons");
        if (dir.mkdir()) {
            sb
                    .append("Создана директория \"icons\" в \"res\"")
                    .append("\n");
        }
        dir = new File("C:\\Users\\tdv\\Desktop\\Games\\savegames");
        if (dir.mkdir()) {
            sb
                    .append("Создана директория \"savegames\"")
                    .append("\n");
        }
        dir = new File("C:\\Users\\tdv\\Desktop\\Games\\temp");
        if (dir.mkdir()) {
            sb
                    .append("Создана директория \"temp\"")
                    .append("\n");
            try (FileWriter fos = new FileWriter("C:\\Users\\tdv\\Desktop\\Games\\temp\\temp.txt");) {
                sb.append("Создан файл \"temp.txt\" в директории \"temp\"");
                fos.write(sb.toString());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}