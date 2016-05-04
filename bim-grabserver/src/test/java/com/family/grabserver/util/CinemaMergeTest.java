package com.family.grabserver.util;

import com.sun.tools.javac.util.Assert;
import javafx.util.Pair;
import org.junit.Test;

/**
 * Created by Administrator on 2016/5/4.
 */
public class CinemaMergeTest {

  @Test
  public void testIsMatched() throws Exception {
    String a = "北京市朝阳区管庄京通苑30号L307(杨闸环岛PLUS华润3层)";
    String b = "京通苑30号楼L307";
    float similarityRatio = Levenshtein.getSimilarityRatio(a, b);

    Pair cinemaPair1 = new Pair<>("17.5影城管庄店", "北京17.5影城管庄店");
    Pair cinemaPair2 = new Pair<>("北京市朝阳区管庄京通苑30号L307(杨闸环岛PLUS华润3层)", "京通苑30号楼L307");
    boolean result = CinemaMerge.isMatched(cinemaPair1, cinemaPair2);
    Assert.check(result);
  }
}
