import java.io.*;
import java.net.*;

public class whois {
    public static void main(String[] args) {
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("shu ru ip dian zhi:");
        try {
            while (true) {
                /* 输入流提示控制 */
                System.out.print("> ");
                String host = (args.length > 0) ? args[0] : systemIn.readLine();
                if (host.isEmpty()) {
                    continue;
                }
                whois(host);
                if (args.length > 0)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void whois(String host) {
        try {
            /* 设置服务器地址和端口号 */
            InetAddress server = InetAddress.getByName("whois.markmonitor.com");
            int port = 43;
            /* 建立Socket连接 */
            Socket socket = new Socket(server, port);
            /* 写入发送消息到Socket */
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(host);
            out.flush();
            /* 从Socket读取消息 */
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            /* 格式化输出 */
            StringBuilder ret = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                ret.append(line + "\r\n");
            }
            /* 安全关闭Socket */
            socket.close();
            /* 结果输出判断 */
            if (ret.toString().trim().isEmpty()) {
                System.out.println("该域名不在whois上注册");
            } else {
                System.out.println(ret.toString());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}