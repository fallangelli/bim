package com.family.webserver.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转换位汉语拼音，英文字符不变
 *
 * @author xuke
 */
public class Cn2Spell {

  public static String getFirstLetter(String chines) {
    try {
      String pinyinName = "";
      char[] nameChar = chines.toCharArray();
      HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
      defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
      defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
      if (nameChar[0] > 128) {
        try {
          String[] pyString = PinyinHelper.toHanyuPinyinStringArray(nameChar[0], defaultFormat);
          if (pyString != null)
            pinyinName += pyString[0].charAt(0);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
          e.printStackTrace();
        }
      } else {
        pinyinName += nameChar[0];
      }

      return pinyinName.toUpperCase();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }


  /**
   * 汉字转换位汉语拼音首字母，英文字符不变
   *
   * @param chines 汉字
   * @return 拼音
   */
  public static String converterToFirstSpell(String chines) {
    try {
      String pinyinName = "";
      char[] nameChar = chines.toCharArray();
      HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
      defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
      defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
      for (int i = 0; i < nameChar.length; i++) {
        if (nameChar[i] > 128) {
          try {
            String[] pyString = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
            if (pyString != null)
              pinyinName += pyString[0].charAt(0);
          } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
          }
        } else {
          pinyinName += nameChar[i];
        }
      }
      return pinyinName;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * 汉字转换位汉语拼音，英文字符不变
   *
   * @param chines 汉字
   * @return 拼音
   */
  public static String converterToSpell(String chines) {
    try {
      String pinyinName = "";
      char[] nameChar = chines.toCharArray();
      HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
      defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
      defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
      for (int i = 0; i < nameChar.length; i++) {
        if (nameChar[i] > 128) {
          try {
            pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
          } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
          }
        } else {
          pinyinName += nameChar[i];
        }
      }
      return pinyinName;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static void main(String[] args) {
    System.out.println(converterToFirstSpell("欢迎来到最棒的Java中文社区"));
  }
}
