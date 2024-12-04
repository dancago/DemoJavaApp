/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author buxuqua
 */
public class helper {

    public static String saveImage(String imageUrl, String filesDirectory, String filename) throws IOException {
        try {

            URL url = new URL(imageUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            InputStream is = new ByteArrayInputStream(response.toString().getBytes());
            File dir = new File(filesDirectory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String targetPath = dir.getPath() + File.separator + filename;
            File desFile = new File(targetPath);
            OutputStream os = new FileOutputStream(desFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
            return targetPath;
        } catch (Exception e) {
            return null;
        }
    }

    public static void saveStream(String mURL, String folder, String filename) throws Exception {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(mURL);
            URLConnection urlConn = url.openConnection();
            in = urlConn.getInputStream();
            File dir = new File(folder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            out = new FileOutputStream(folder+File.separator+filename);
            int c;
            byte[] b = new byte[1024];
            while ((c = in.read(b)) != -1) {
                out.write(b, 0, c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}
