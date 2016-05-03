import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by AlbertoBortolato on 28/04/2016.
 */
public class WriteFile {

    static WriteFile w = new WriteFile();

    public static void main(String[] args) throws Exception {
        String item="items-tmp.tmp";
        String output="";
        Path p=w.createFile(item);
        w.write(p,"testo");
        System.out.print(w.read(p));
        output=w.read(p)+" testa";
        w.write(p,output);
        System.out.print(w.read(p));
    }

    public Path createFile(String name){
        Path p =  Paths.get(name);
        boolean esito = false;
        try{
            if(Files.notExists(p)){
                Files.createFile(p);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return  p;
    }

    public void write(Path p, String testo){
        try (FileOutputStream fos = new FileOutputStream(p.toFile())) {
            fos.write(testo.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(Path p){
        String content = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(p.toFile()), "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                //sb.append('\n');
            }
            content = sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return content;
    }

}
