import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


public class yuanzi {

    private static int sleepMinutes = 0;
    private static final long EPOCH_OFFSET_MILLIS;
    private static final String[] hostName = {"time-a.nist.gov", "time-nw.nist.gov", "time.nist.gov"};


    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        // Java使用的参照标准是1970年，而时间服务器返回的秒是相当1900年的，算一下偏移  
        calendar.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
        EPOCH_OFFSET_MILLIS = Math.abs(calendar.getTime().getTime());
    }

    public static void main(String[] args) {
        GetWebTime();
    }

    private static Date getNetDate(String hostName) {
        Date date = null;
        long result = 0;
        try {
            Socket socket = new Socket(hostName, 37);
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream(),
                    socket.getReceiveBufferSize());
            int b1 = bis.read();
            int b2 = bis.read();
            int b3 = bis.read();
            int b4 = bis.read();
            if ((b1 | b2 | b3 | b3) < 0) {
                return null;
            }
            result = (((long) b1) << 24) + (b2 << 16) + (b3 << 8) + b4;
            date = new Date(result * 1000 - EPOCH_OFFSET_MILLIS);
            socket.close();
        } catch (UnknownHostException ex) {

        } catch (IOException ex) {

        }
        return date;
    }

    public static boolean offLine() {
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("ping www.hao123.com");
            InputStream s = process.getInputStream();
            BufferedReader bis = new BufferedReader(new InputStreamReader(s));
            String str = bis.readLine();
            while (str != null) {
                if (str.startsWith("Reply from")) {
                    return false;
                }
                str = bis.readLine();
            }
            process.waitFor();
        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return true;
    }


    private static void showtime(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.print(year+"."+month+"."+day+"."+hour+"."+minute+"."+second);
    }

    public static void GetWebTime()
    {

        while (offLine() && sleepMinutes < 30) {
            try {
                Thread.sleep(120000);
                sleepMinutes += 2;
            } catch (InterruptedException ex) {

            }
        }

        if (sleepMinutes >= 30)
        {
            System.exit(0);
        }

        Date date = null;
        for (int i = 0; i < hostName.length; i++) {
            date = getNetDate(hostName[i]);
            if (date != null) {
                break;
            }
        }
        showtime(date);

    }


}  