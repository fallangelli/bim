package com.family.grabserver.util;

import javafx.util.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/4.
 */
public class CinemaMerge {

  public static Boolean isMatched(Pair<String, String> namePair, Pair<String, String> addressPair) {
    String nameA = namePair.getKey();
    String nameB = namePair.getValue();

    String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(nameA);
    nameA = m.replaceAll("").trim();
    nameA = nameA.toUpperCase();
    m = p.matcher(nameB);
    nameB = m.replaceAll("").trim();
    nameB = nameB.toUpperCase();

    if (nameA.length() > 0 && nameB.length() > 0 &&
      (Levenshtein.getSimilarityRatio(nameA, nameB) > 0.9 ||
        nameA.contains(nameB) || nameB.contains(nameA))) {
      return true;
    } else {
      String addressA = addressPair.getKey();
      String addressB = addressPair.getValue();
      if (addressA.length() > 0 && addressB.length() > 0 &&
        (Levenshtein.getSimilarityRatio(addressA, addressB) > 0.5 ||
          addressA.contains(addressB) || addressB.contains(addressA))) {
        return true;
      }
    }

    return false;
  }
}
