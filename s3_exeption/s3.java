package s3_exeption;
import java.io.*;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class s3 {
    public static void main(String[] args) throws IOException {

        try {
            Record();
            System.out.println("done");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void Record() throws Exception{
        System.out.println("Введите фамилию, имя, отчество, дату рождения (в формате 12.12.2012), номер телефона (без пробелов)");

        String text;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            text = bf.readLine();
        }catch (IOException e){
            throw new Exception("error read");
        }

        String[] array = text.split(" ");
        if (array.length != 5){
            throw new Exception("error split");
        }

        String surname = array[0];
        String name = array[1];
        String father_name = array[2];

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date birthdate;
        try {
            birthdate = format.parse(array[3]);
        }catch (ParseException e){
            throw new ParseException("error date",0);
        }

        int phone;
        try {
            phone = Integer.parseInt(array[4]);
        }catch (NumberFormatException e){
            throw new NumberFormatException("error phone");
        }

        String fileName = surname + ".txt";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)){
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s", surname, name, father_name, format.format(birthdate), phone));
        }catch (IOException e){
            throw new FileSystemException("error file");
        }

    }
}