import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java实现爬虫
 */
public class spider {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        PrintWriter pw = null;


        try {
            url = new URL("http://www.seu.edu.cn/");
            urlconn = url.openConnection();
            pw = new PrintWriter(new FileWriter("get.txt"), true);
                br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = " ";
            while ((line = br.readLine()) != null){
                buffer.append(line+"\n");
            }

            System.out.println(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }
}