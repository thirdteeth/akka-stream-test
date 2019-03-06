package com.github.thirdteeth.akka.steam.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Sample4 {
    public static void main(String[] args) {
        List<String[]> stringList = new ArrayList<String[]>();
        String[] str1 = {"aaaa", "aaa1"};
        String[] str2 = {"bbbb", "bbb1"};
        String[] str3 = {"cccc", "ccc1"};

        stringList.stream();
        stringList.add(str1);
        stringList.add(str2);
        stringList.add(str3);
        StringBuffer result = new StringBuffer();
//        List buffers = stringList.stream().
        String aaa = stringList.stream().flatMap(x -> Arrays.stream(x)).reduce((a,b) -> a+b).get();

        System.out.println(aaa);
    }
}
