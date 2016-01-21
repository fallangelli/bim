package com.family.grabserver.util;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/21.
 */
public class CityMerge {

  public static String[] KEY_WORDS = {"朝鲜族自治州", "蒙古族藏族自治州", "黎族自治县", "哈萨克自治州",
    "黎族苗族自治县", "土家族苗族自治县", "土家族苗族自治州", "苗族自治县", "侗族自治县", "苗族侗族自治县",
    "苗族侗族自治州", "布依族苗族自治州", "布依族苗族自治州", "苗族土家族自治县", "回族区", "蒙古自治州",
    "傣族景颇族自治州", "傣族自治州", "藏族自治州",
    "林区", "地区", "市", "区", "县", "镇", "盟"
  };

  public static List<Pair<String, Pair<String, String>>> AREA_TRAN_LIST = new ArrayList<>();

  static {
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("北京", new Pair<String, String>("宣武", "西城区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("北京", new Pair<String, String>("崇文", "东城区")));

    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("南京", new Pair<String, String>("白下", "秦淮区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("南京", new Pair<String, String>("白下", "秦淮区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("南京", new Pair<String, String>("下关", "鼓楼区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("上海", new Pair<String, String>("卢湾", "黄浦区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("连云港", new Pair<String, String>("新浦", "海州区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("沈阳", new Pair<String, String>("东陵", "浑南区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("苏州", new Pair<String, String>("沧浪", "姑苏区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("苏州", new Pair<String, String>("平江", "姑苏区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("苏州", new Pair<String, String>("金阊", "姑苏区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("苏州", new Pair<String, String>("工业园区", "吴中区")));

    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("石家庄", new Pair<String, String>("桥东", "桥西区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("淮安", new Pair<String, String>("楚州", "淮安区")));
    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("深圳", new Pair<String, String>("龙华区", "龙岗区")));

    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("合肥", new Pair<String, String>("政务文化新区", "蜀山区")));

    AREA_TRAN_LIST.add(new Pair<String, Pair<String, String>>("长春", new Pair<String, String>("经济开发区", "南关区")));

  }

  public static boolean compareWithouKeyWord(String strA, String strB) {
    for (String word : KEY_WORDS) {
      strA = strA.replace(word, "");
      strB = strB.replace(word, "");
    }
    if (strA.compareToIgnoreCase(strB) == 0)
      return true;
    else
      return false;
  }

  public static String getAreaWithTrans(String cityName, String areaName) {
    for (Pair<String, Pair<String, String>> item : AREA_TRAN_LIST) {
      if (compareWithouKeyWord(item.getKey(), cityName)) {
        Pair<String, String> pair = item.getValue();
        if (compareWithouKeyWord(pair.getKey(), areaName)) {
          return pair.getValue();
        }
      }
    }
    return areaName;
  }
}
