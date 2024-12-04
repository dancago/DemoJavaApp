/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Set;
import net.nightst0rm.entities.Token;

/**
 *
 * @author Demo
 */
public class WhitelistedObjectInputStream extends ObjectInputStream {



    public WhitelistedObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass cls) throws IOException, ClassNotFoundException {
        if (!cls.getName().equals(Token.class.getName())) {
            throw new InvalidClassException(
                    "Unauthorized deserialization attempt",
                    cls.getName());
        }
        return super.resolveClass(cls);
    }

}
