import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Save {
    public static void main(String[] args) {
        String pathArhiv = "C:\\Users\\tdv\\Desktop\\Games\\savegames\\zip.zip";

        GameProgress gp1 = new GameProgress(100, 80, 10, 50.0);
        GameProgress gp2 = new GameProgress(90, 90, 9, 40.0);
        GameProgress gp3 = new GameProgress(80, 100, 8, 30.0);

        File file1 = new File("C:\\Users\\tdv\\Desktop\\Games\\savegames\\save1.data");
        File file2 = new File("C:\\Users\\tdv\\Desktop\\Games\\savegames\\save2.data");
        File file3 = new File("C:\\Users\\tdv\\Desktop\\Games\\savegames\\save3.data");

        saveGame(file1, gp1);
        saveGame(file2, gp2);
        saveGame(file3, gp3);

        List<File> listObject = Arrays.asList(file1, file2, file3);

        zipFiles(pathArhiv, listObject);
        openZip(pathArhiv, "C:\\Users\\tdv\\Desktop\\Games\\savegames\\");

        GameProgress gp = openProgress(listObject, 1);
        System.out.println(gp);

    }

    public static void saveGame(File file, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String path, List<File> listObject) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
            for (int i = 0; i < listObject.size(); i++) {
                FileInputStream fis = new FileInputStream(listObject.get(i).getPath());
                ZipEntry ze = new ZipEntry(listObject.get(i).getName());
                zos.putNextEntry(ze);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
                fis.close();
            }
            ListIterator<File> li = listObject.listIterator();
            while (li.hasNext()) {
                li.next().delete();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openZip(String pathFile, String pathFolder) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(pathFolder + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fos.write(c);
                }
                fos.flush();
                zin.closeEntry();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameProgress openProgress(List<File> list, int number) {
        GameProgress gp = null;
        try (FileInputStream fis = new FileInputStream(list.get(number).getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gp = (GameProgress) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gp;
    }
}
