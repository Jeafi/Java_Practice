import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class xiazai {

    public static void main(String[] argus){
        download("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","aaa",100);
    }
    public static boolean download(String urlString, String filename,int timeout){
        boolean ret = false;
        File file = new File(filename);
        try {
            if(file.exists()){
                ret = true;
            }else{
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection )url.openConnection();
                con.setConnectTimeout(timeout);
                con.setReadTimeout(timeout);
                con.connect();
                int contentLength = con.getContentLength();
                InputStream is = con.getInputStream();
                byte[] bs = new byte[1024];
                int len;
                File file2=new File(file.getParent());
                file2.mkdirs();
                if(file.isDirectory()){

                }else{
                    file.createNewFile();
                }
                OutputStream os = new FileOutputStream(file);
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                os.close();
                is.close();
                if(contentLength != file.length()){
                    file.delete();
                    ret = false;
                }else{
                    ret = true;
                }
            }
        } catch (IOException e) {
            file.delete();
            ret = false;

        }finally {
            return ret;
        }
    }


    public static boolean resumeDownload(String urlString, String filename,int timeout) throws Exception{
        boolean ret = false;
        File fileFinal = new File(filename);
        String tmpFileName = filename+".tmp";
        File file = new File(tmpFileName);

        try {
            if(fileFinal.exists()){
                ret = true;
            }else{
                long contentStart = 0;
                File file2=new File(file.getParent());

                if(file.exists()){
                    contentStart = file.length();
                }else{
                    file2.mkdirs();
                }

                URL url = new URL(urlString);

                HttpURLConnection con = (HttpURLConnection )url.openConnection();
                con.setConnectTimeout(timeout);
                con.setReadTimeout(timeout);

                if(contentStart>0){
                    con.setRequestProperty("RANGE","bytes="+contentStart+"-");
                }
                con.connect();
                int contentLength = con.getContentLength();
                InputStream is = con.getInputStream();
                byte[] bs = new byte[100*1024];
                int len;
                RandomAccessFile oSavedFile = new RandomAccessFile(tmpFileName,"rw");
                oSavedFile.seek(contentStart);
                while ((len = is.read(bs)) != -1) {
                    oSavedFile.write(bs, 0, len);
                }
                oSavedFile.close();
                is.close();
                file.renameTo(fileFinal);
                ret = true;
            }
        } catch (IOException e) {
            file.delete();
            ret = false;
            throw new Exception(e);
        }finally {
            return ret;
        }
    }

}
