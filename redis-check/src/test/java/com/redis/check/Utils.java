package com.redis.check;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.DigestUtils;
import org.testcontainers.shaded.org.bouncycastle.util.Strings;

import java.util.Random;
import java.util.function.Function;

public class Utils {

    private static Random random = new Random();

    @NotNull
    public static String generateUrlAndEncode() {
        return Utils.encode(generateURL());
    }

    @NotNull
    static String encode(String str) {
        return DigestUtils.md5DigestAsHex(Strings.toByteArray(str));
    }

    private static String generateURL() {

        String[] prefix1 = new String[] { "products", "sales" , "purchases", "trials", "records", "buys"  };
        String[] prefix2 = new String[] { "2017", "2018" , "2019", "2020", "2021" };
        String[] prefix3 = new String[] { "id" , "key" , "part", "rec", "token" };
        String[] prefix4 = new String[] { "very-long-string-number1" , "very-very-long-string-number2" };
        String[] prefix5 = new String[] { "12" , "13", "14", "15", "16" };
        String[] prefix6 = new String[] { "very-long-string-number1" , "very-very-long-string-number2" };
        String[] prefix7 = new String[] { "3017", "3018" , "3019", "3020", "3021" };
        String[] prefix8 = new String[] { "productsX", "salesX" , "purchasesX", "trialsX", "recordsX", "buysX"  };

        final Function<String[], String> pickStrFunc = prefix -> prefix[random.nextInt(prefix.length)];

        return pickStrFunc.apply(prefix1) + "/"
                +  pickStrFunc.apply(prefix2) + "/"
                +  pickStrFunc.apply(prefix3) + "/"
                +  pickStrFunc.apply(prefix4) + "/"
                +  pickStrFunc.apply(prefix5) + "/"
                +  pickStrFunc.apply(prefix6) + "/"
                +  pickStrFunc.apply(prefix7) + "/"
                +  pickStrFunc.apply(prefix8) + "/"
                ;
    }
}
