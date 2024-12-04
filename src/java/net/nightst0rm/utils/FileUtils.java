/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 *
 * @author buxuqua
 */
public class FileUtils {

    public static void saveFile(File file, String fileName, String filesDirectory) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        File dir = new File(filesDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String targetPath = dir.getPath() + File.separator + fileName;
        System.out.println("source file path ::" + file.getAbsolutePath());
        System.out.println("saving file to ::" + targetPath);
        File destinationFile = new File(targetPath);
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(destinationFile);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
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

    public static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
    public static void copyFileUsingFileChannels(File source, File dest)
		throws IOException {
	FileChannel inputChannel = null;
	FileChannel outputChannel = null;
	try {
		inputChannel = new FileInputStream(source).getChannel();
		outputChannel = new FileOutputStream(dest).getChannel();
		outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
	} finally {
		inputChannel.close();
		outputChannel.close();
	}
}

}
