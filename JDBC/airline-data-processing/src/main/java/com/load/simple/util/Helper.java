package com.load.simple.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Helper {



    public static void initHeader(String path, Map<String, Integer> header) {

        try {
            String columns[] = new Scanner(new File(path)).useDelimiter("\n").next().split(",");
            IntStream
                    .range(0, columns.length)
                    .forEach(idx -> header.put(rmEncQuotes(columns[idx].toLowerCase()), idx));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }


    public static String rmEncQuotes(String str) {
        return str.replaceAll("^\"|\"$", "");
    }
}
