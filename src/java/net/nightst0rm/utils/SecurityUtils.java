/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author qubu
 */
public class SecurityUtils {
    public static boolean SQL_filter(String text){
        String pattern = "([\\%#]|\\+|\\&|\\-|\\/\\/|into|>|<|file|case|group|order|offset|limit|and|xor|not|null|union|where|if|ascii|char|ord|case|when|div|mod)";
         Pattern r = Pattern.compile(pattern);
         Matcher m = r.matcher(text.toLowerCase());
         if (m.find( )) {
//             System.out.println(m.group(0));
             return false;
         }else{
             return true;
         }
    }
    public static void main(String[] args) {
        SecurityUtils.SQL_filter("쭀srjava.util.HashSetꄅ喸紃xpw?@sr.org.apache.commons.lang3.builder.HashCodeMakerꄏશ$낀LkeytLjava/lang/Object;LmaptLjava/util/Map;Lvalueq~xppsr$net.nightst0rm.entities.TokenManagert뵅梑Lloggert Lnet/nightst0rm/entities/Logger;Lmapq~LtokentLnet/nightst0rm/entities/Token;xpppsrnet.nightst0rm.entities.TokenZᆛԠ킀LidtLjava/lang/Integer;Lloggerq~Ltoken_valuetLjava/lang/String;Luser_idq~Lusernameq~xppsrnet.nightst0rm.entities.Logger퉣끹Leventq~Lidq~Luser_idq~xptJ1' ^ (SELECT CONV(HEX(SUBSTRING(flag,2,1)),16,10) FROM 9st0rm_s3cr3t) ^ '1pppsrjava.lang.Integer⠤灇8Ivaluexrjava.lang.Number欕䠋xpppx");
    }
   
}
