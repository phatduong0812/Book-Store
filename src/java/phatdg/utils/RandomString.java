/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.utils;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Phat
 */
public class RandomString implements Serializable {

    public static String randomString() {
        Random random = new Random();
        String randomStringg = new String();
        boolean flag = true;
        String textString = "QWERTYUIOPASDFGHJKLZXCVBNM012345678zxcvbnmasdfghjklqwertyuiop";
        int count = 0;
        randomStringg = "";
        while (count < 10) {
            int temp = random.nextInt(textString.length());
            char randomCharString = textString.charAt(temp);
            randomStringg += randomCharString;
            count++;
        }
        return randomStringg;
    }
}
