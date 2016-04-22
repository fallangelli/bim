package com.family.grabserver.util;


import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaoyanPriceDecoder {
  private static org.slf4j.Logger logger = LoggerFactory.getLogger("MaoyanPriceDecoder");

  public static Float decode(String html, String decodeCss) {
    if (html.length() <= 0) {
      return (float) -1;
    }
    try {
      int splitIndex = html.indexOf('.');
      List<String> contexts = new ArrayList<>();
      if (splitIndex > 0) {
        contexts.add(html.substring(0, splitIndex));
        contexts.add(html.substring(splitIndex + 1, html.length()));
      } else {
        contexts.add(html);
      }
      String resStr = "";
      for (int index = 0; index < contexts.size(); index++) {
        Pattern p1 = Pattern.compile("<i>([\\d]*?)</i>");
        Matcher m1 = p1.matcher(contexts.get(index));
        Integer itemCount = 0;
        List<String> itemList = new ArrayList<String>();
        while (m1.find()) {
          itemCount++;
          itemList.add(m1.group(1));
        }
        Pattern p2 = Pattern.compile("(true[\\d])");
        Matcher m2 = p2.matcher(contexts.get(index));
        String type = "";
        if (m2.find()) {
          type = m2.group(1);
        }


        for (int i = 1; i <= itemCount; i++) {
          String patStr = "." + type;
          patStr += ">i\\:nth-of-type\\(";
          patStr += i + "\\)";
          patStr += "\\{text-indent\\:-(\\d*.\\d*)em;width\\:(\\d*.\\d*)em;";
          Pattern p3 = Pattern.compile(patStr);
          Matcher m3 = p3.matcher(decodeCss);
          if (m3.find()) {
            int left = (int) (Double.parseDouble(m3.group(1)) / 0.55);
            int length = (int) (Double.parseDouble(m3.group(2)) / 0.55);
            String item = itemList.get(i - 1);

            String resItem = "";
            if (left < item.length())
              resItem = item.substring(left, left + length);
            resStr += resItem;
          } else {
            logger.error("无法解析解码css html:" + html + "css:" + decodeCss);
            return (float) 0;
          }
        }

        if (contexts.size() > 1 && index == 0)
          resStr += ".";
      }
      float retVal = Float.parseFloat(resStr);
      return retVal;
    } catch (Exception e) {
      logger.error("解码css html:" + html + "css:" + decodeCss);
    }
    return (float) 0;
  }

}
