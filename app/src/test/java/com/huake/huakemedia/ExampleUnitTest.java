package com.huake.huakemedia;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getCountTag() {
        int result=-1;
        String line1="[03:37.32]我在这里哭泣[00:59.73]我在这里欢笑";
        String line="[03:37.32]";
        //这里的坑好多，如果从左边分割就是两个数组，如果是从右边分割就是一个数组
        System.out.println(line.split("\\]").length);
        System.out.println(line.split("\\[").length);

//        System.out.println(line.indexOf("["));
//        System.out.println(line.indexOf("]"));
//        System.out.println(line.substring(9));//截取的内容包括他，显然我们不需要
//        System.out.println(line.substring(9+1));//包后
//        System.out.println(line.substring(0,9));//前包后不包
//        System.out.println(line.substring(0,9+1));//前包后不包
        //我们可以计算按左或者按右括号分组的两种情况
//        String[] left = line.split("\\[");
//        String[] reight = line.split("\\]");
//        for (String s : left) {
//            System.out.println("left:"+s);
//
//        }
//        for (String s : reight) {
//            System.out.println("reight:"+s);
//
//        }
//        System.out.println(left.length+":"+reight.length);
     /*   //假如分割后没有数组说明就是一句歌词，像这种情况  [00:59.73]我在这里欢笑
        if (left.length==0||reight.length==0){
            result=1;
        }
        //如果分割是大数那就取大的  [03:37.32]我在这里哭泣[00:59.73]我在这里欢笑
        if (left.length<reight.length){
            result =reight.length;
        }
        if (left.length>reight.length){
            result =left.length;
        }*/

    }


}