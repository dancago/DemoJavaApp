/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import net.nightst0rm.filter.WhitelistedObjectInputStream;

/**
 *
 * @author qubu
 */
public class DataUtils {
    public static <T extends Serializable> T getObject(String data) {
        try {
            byte[] dataBytes = Base64.getDecoder().decode(data);
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dataBytes);
//            final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            final WhitelistedObjectInputStream objectInputStream = new WhitelistedObjectInputStream(byteArrayInputStream);

            @SuppressWarnings({"unchecked"})
            final T obj = (T) objectInputStream.readObject();

            objectInputStream.close();
            byteArrayInputStream.close();
            return obj;
        } catch (IOException e) {
            throw new Error(e);
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        }
    }
    
    
    public static <T extends Serializable> String createObject(T item) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(item);
            objectOutputStream.close();
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new Error(e);
        }
    }
    
}
