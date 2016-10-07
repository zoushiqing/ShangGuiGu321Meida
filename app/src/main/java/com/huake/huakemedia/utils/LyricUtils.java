package com.huake.huakemedia.utils;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/5 20:40
 * @描述	    解析歌词工具类，比较复杂
 */

import com.huake.huakemedia.domain.Lyric;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LyricUtils {

    private ArrayList<Lyric> mLyrics;
    private boolean isExistsLyric;

    public boolean isExistsLyric() {
        return isExistsLyric;
    }

    public ArrayList<Lyric> getLyrics() {
        return mLyrics;
    }

    public void setLyrics(ArrayList<Lyric> lyrics) {
        mLyrics = lyrics;
    }

    //读取歌曲文件，顺便转换成我们要的歌词
    public void readLyricFile(File file) {

        if ((file == null) || !file.exists()) {
            isExistsLyric = false;
            mLyrics = null;
        } else {
            isExistsLyric = true;
            //读流吧
            mLyrics = new ArrayList<>();
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file),getCharset(file)));
                String content = "";
                while ((content = buffer.readLine()) != null) {
                    //解析歌词并且加到集合中喔
                    parseLyric(content);

                }
                buffer.close();
            } catch (FileNotFoundException e) {
                isExistsLyric = false;
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //2.把所有歌词排序，不过经过从上到下读取后应该就是正确的顺序，这个方法也许多此一举
        if (mLyrics != null) {
            Collections.sort(mLyrics, new Comparator<Lyric>() {
                @Override
                public int compare(Lyric o1, Lyric o2) {
                    if (o1.getTimePoint() < o2.getTimePoint()) {
                        return -1;
                    } else if (o1.getTimePoint() > o2.getTimePoint()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }

        //3.计算每句高亮显示的时间
        //后一句减前面一句
        if (mLyrics != null) {
            for (int i = 0; i < mLyrics.size(); i++) {
                int next = i + 1;
                if (next < mLyrics.size()) {
                    long sleepTime = mLyrics.get(next).getTimePoint() - mLyrics.get(i).getTimePoint();
                    mLyrics.get(i).setSleepTime(sleepTime);

                }

            }
        }


    }

    /**
     * 解析一行歌词
     * 简单情况 [02:04.12]我在这里欢笑
     * 复杂情况  [02:04.12][03:37.32][00:59.73]我在这里欢笑
     *
     * @return
     */
    private void parseLyric(String content) {

        //判断有几句歌词
        int countTag = getCountTag(content);
        //代表正常的一句话
        if (countTag != -1) {
            int post2 = content.indexOf("]");
            int post1 = content.indexOf("[");
            String timePoint = content.substring(post1 + 1, post2);
            String lyricContent = content.substring(post2 + 1);
            Lyric lyric = new Lyric();
            //显然我们需要long型
            lyric.setTimePoint(strTime2LongTime(timePoint));
            lyric.setContent(lyricContent);
            mLyrics.add(lyric);
        }
    }

    /**
     * 判断有多少句歌词,实际测试以左边括号算出来的正确，右括号算出来的会多一，不过数组多了不担心，就是担心少的情况
     *
     * @param line [02:04.12]asdas[03:37.32]asdfas[00:59.73]我在这里欢笑
     * @return 返回的实际多一
     */
    private int getCountTag(String line) {
        //这里的坑好多，对于[10:10.10]如果从左边分割就是两个数组，如果是从右边分割就是一个数组
        //但是会把该句作为只含一个元素的数组，所以长度为0根本不可能存在，至少为一
        int result = -1;
        if (line != null) {
            String[] right = line.split("\\]");
            result = right.length;
        }
        //减去1正好判断精准无误
        return result - 1;
    }

    /**
     * 把String类型是时间转换成long类型
     *
     * @param strTime 02:04.12
     * @return
     */
    private long strTime2LongTime(String strTime) {
        long result = -1;
        //切割
        String[] left = strTime.split(":");
        String[] right = left[1].split("\\.");
        long min = Long.parseLong(left[0]);
        long second = Long.parseLong(right[0]);
        long mills = Long.parseLong(right[1]);//毫秒
        result = min * 60 * 1000 + second * 1000 + mills * 10;
        return result;
    }

    /**
     * 判断文件编码  这个非常复杂，就不说了
     *
     * @param file 文件
     * @return 编码：GBK,UTF-8,UTF-16LE
     */
    public String getCharset(File file) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF)
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF)
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

}