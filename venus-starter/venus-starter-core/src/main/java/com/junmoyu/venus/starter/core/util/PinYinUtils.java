package com.junmoyu.venus.starter.core.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
public class PinYinUtils {

    /**
     * 将汉字转换为全拼
     *
     * @param src 输入名
     * @return 拼音
     */
    public static String getPinYin(String src) {
        char[] hz = null;
        //该方法的作用是返回一个字符数组，该字符数组中存放了当前字符串中的所有字符
        hz = src.toCharArray();
        //该数组用来存储
        String[] py = new String[hz.length];
        //设置汉子拼音输出的格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        //存放拼音字符串
        String pys = "";
        int len = hz.length;

        try {
            for (int i = 0; i < len; i++) {
                //先判断是否为汉字字符
                if (Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    //将汉字的几种全拼都存到py数组中
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i], format);
                    //取出改汉字全拼的第一种读音，并存放到字符串pys后
                    pys += py[0];
                } else {
                    //如果不是汉字字符，间接取出字符并连接到 pys 后
                    pys += Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pys;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str 输入名
     * @return 拼音
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            //提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }


    /**
     * 第一个全拼 后面首字母
     *
     * @param src 输入名
     * @return 拼音
     */
    public static String getPinYinAAA(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        int t0 = t1.length;
        if (t0 > 2) {
            return getPinYin(src.substring(0, 1)) + getPinYinHeadChar(src.substring(1, t0));
        } else {
            return getPinYin(src);
        }
    }

    /**
     * 生成登录名  生成规则支持 如：
     * 张三   ——》  san.zhang
     * 诸葛亮  ——》  gl.zhu
     * 欧阳夏丹  ——》  xd.oy
     * 只要包含数字或是字母将不转换
     *
     * @param src 输入名
     * @return 登录名
     */
    public static String getLoginName(String src) {
        char[] t1 = null;
        String regex = "^[a-z0-9A-Z]+$";
        boolean matches = src.matches(regex);
        t1 = src.toCharArray();
        int t0 = t1.length;
        if (!matches && t0 == 4) {
            return getPinYinHeadChar(src.substring(2, t0)) + "." + getPinYinHeadChar(src.substring(0, 2));
        } else if (!matches && t0 == 3) {
            return getPinYinHeadChar(src.substring(1, t0)) + "." + getPinYin(src.substring(0, 1));
        } else if (!matches && t0 == 2) {
            return getPinYin(src.substring(1, 2)) + "." + getPinYin(src.substring(0, 1));
        } else {
            return getPinYin(src);
        }
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param str 字符串
     * @return ASCII码
     */
    public static String getCnAscii(String str) {
        StringBuffer buf = new StringBuffer();
        //将字符串转换成字节序列
        byte[] bGbk = str.getBytes();
        for (int i = 0; i < bGbk.length; i++) {
            //将每个字符转换成ASCII码
            buf.append(Integer.toHexString(bGbk[i] & 0xff));
        }
        return buf.toString();
    }
}
