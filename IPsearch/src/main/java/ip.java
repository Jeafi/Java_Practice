import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class ip {


    public String getAddressByIP() {
        try {
            String strIP = "0.0.0.0";
            URL url = new URL("http://ip.qq.com/cgi-bin/searchip?searchip1=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            strIP = result.substring(result.indexOf("该IP所在地为："));
            strIP = strIP.substring(strIP.indexOf("：") + 1);
            String province = strIP.substring(6, strIP.indexOf("省"));
            String city = strIP.substring(strIP.indexOf("省") + 1, strIP.indexOf("市"));
        return province + city;

        } catch (IOException e) {
            return "读取失败";
        }

    }
}