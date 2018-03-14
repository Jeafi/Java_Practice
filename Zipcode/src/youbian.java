import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class youbian {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        System.out.println("请输入要查询的邮政编号");
        BufferedReader br = new BufferedReader(isr);
        String str = br.readLine();
        br.close();
        URL url = new URL("http://opendata.baidu.com/post/s?wd="+str);
        URLConnection hul = url.openConnection();
        InputStream is = hul.getInputStream();
        byte[] by = new byte[1024];
        int len = 0;
        String address = null;
        while((len=is.read(by))!=-1){
            address += new String(by,0,len,"GBK");
        }
        try {
            System.out.println(address.substring(address.indexOf("</em>：")+6,address.indexOf("</h3>")));
        } catch (Exception e) {
            System.out.println("对不起，您的输入有误");
        }
    }

}