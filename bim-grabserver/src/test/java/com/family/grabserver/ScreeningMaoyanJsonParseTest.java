package com.family.grabserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.LoggerFactory;


/**
 * User: cairne
 * Date: 13-5-13
 * Time: 下午8:33
 */
public class ScreeningMaoyanJsonParseTest {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  //    @Ignore
  @Test
  public void test() {
    String context = "<body>\n" +
      " {\"control\":{\"expires\":120},\"status\":0,\"data\":{\"Dates\":[{\"text\":\"明天 12月04日\",\"slug\":\"2015-12-04\"},{\"text\":\"后天 12月05日\",\"slug\":\"2015-12-05\"}],\"currentMovie\":{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D/3D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/d2f636cbbbfb60680de9d0f5e2887f261476005.jpg\",\"nm\":\"不可思异\",\"isShowing\":false,\"id\":78380},\"cinemaDetailModel\":{\"price\":0.0,\"addr\":\"罗湖区新园路1号（东门步行街西口，地铁老街站F出口）\",\"preferential\":0,\"sellmin\":15,\"ct\":\"深圳\",\"nm\":\"深圳戏院\",\"sell\":true,\"area\":\"罗湖区\",\"bus\":\"乘3/10/12/29/59/64/85/103/113/203/204/211/214/220/223/302路公交车在门诊部站下车即是。\",\"park\":\"戏院门前收费停车\",\"imax\":0,\"snum\":71,\"s\":7.7605634,\"s1\":7.802817,\"s2\":7.633803,\"s3\":7.7746477,\"featureTags\":[{\"desc\":\"限网上选座后取票\",\"type\":1,\"tag\":\"取票机\"},{\"desc\":\"1.2米(不含)以下免费，1.2米以上儿童半价\",\"type\":7,\"tag\":\"儿童优惠\"},{\"desc\":\"戏院门前收费停车\",\"type\":5,\"tag\":\"可停车\"},{\"desc\":\"免押金\",\"type\":4,\"tag\":\"3D眼镜\"},{\"desc\":\"影院提供免费WIFI\",\"type\":9,\"tag\":\"WiFi\"},{\"desc\":\"1号厅、2号厅有情侣座\",\"type\":6,\"tag\":\"情侣座\"},{\"desc\":\"影院大厅、2厅、3厅有休息区，座椅\",\"type\":10,\"tag\":\"休息区\"},{\"desc\":\"前台购票可刷卡付款（信用卡，借记卡均可）\",\"type\":8,\"tag\":\"可刷卡\"}],\"brd\":\"其它\",\"dis\":\"东门\",\"lng\":114.11649,\"lat\":22.545383,\"tel\":[\"0755-82175808\"],\"suw\":\"乘3号线在老街站下车F口出即是。\",\"callboard\":\"《电商时代》9.8元特惠观影\",\"dri\":\"\",\"note\":\"\",\"dealtp\":0,\"deals\":\"\",\"id\":1},\"movies\":[{\"sc\":8.7,\"preferential\":0,\"ver\":\"2D\",\"img\":\"http://p0.meituan.net/165.220/movie/5c63e738ad2b930f77f5b34972d06571326349.jpg\",\"nm\":\"怦然星动\",\"isShowing\":true,\"id\":338503},{\"sc\":9.2,\"preferential\":0,\"ver\":\"2D\",\"img\":\"http://p1.meituan.net/165.220/movie/19e87124de0929c5df86cb760776afc5655939.jpg\",\"nm\":\"我的少女时代\",\"isShowing\":true,\"id\":341569},{\"sc\":8.9,\"preferential\":0,\"ver\":\"3D/IMAX 3D/中国巨幕/全景声\",\"img\":\"http://p0.meituan.net/165.220/movie/f7df200378e3725a4bc825397f5c9956130126.jpg\",\"nm\":\"火星救援\",\"isShowing\":true,\"id\":246369},{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D/3D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/b8a6b0b27bdc633378ad047746bc4f49387554.jpg\",\"nm\":\"极盗者\",\"isShowing\":false,\"id\":249141},{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D\",\"img\":\"http://p0.meituan.net/165.220/movie/55cb9d44cda167314f30e920cca5502a209601.jpg\",\"nm\":\"从天儿降\",\"isShowing\":false,\"id\":246364},{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/27b802ecc9a2ed1fd679814242b85132399461.jpg\",\"nm\":\"杜拉拉追婚记\",\"isShowing\":false,\"id\":342576},{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D/3D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/d2f636cbbbfb60680de9d0f5e2887f261476005.jpg\",\"nm\":\"不可思异\",\"isShowing\":false,\"id\":78380},{\"sc\":8.4,\"preferential\":0,\"ver\":\"3D/中国巨幕\",\"img\":\"http://p0.meituan.net/165.220/movie/b5a456ee150099b7f331004e1ea7df54390125.jpg\",\"nm\":\"海绵宝宝\",\"isShowing\":true,\"id\":246024},{\"sc\":8.1,\"preferential\":0,\"ver\":\"2D\",\"img\":\"http://p1.meituan.net/165.220/movie/43217430d96023ea4a2336efe4e367e0434347.jpg\",\"nm\":\"坏蛋必须死\",\"isShowing\":true,\"id\":246498},{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D\",\"img\":\"http://p1.meituan.net/165.220/movie/a423f7e45086eeef3feb87d85feb44f61088008.jpg\",\"nm\":\"电商时代\",\"isShowing\":false,\"id\":338528},{\"sc\":0.0,\"preferential\":0,\"ver\":\"2D\",\"img\":\"http://p0.meituan.net/165.220/movie/5d07add523718b310c6ddf425a6ea4eb241906.jpg\",\"nm\":\"师父\",\"isShowing\":false,\"id\":246080}],\"DateShow\":{\"2015-12-04\":[{\"end\":\"12:59\",\"showDate\":\"2015-12-04\",\"showId\":49748,\"dt\":\"2015-12-04\",\"embed\":0,\"preferential\":0,\"tm\":\"11:20\",\"sell\":true,\"lang\":\"国语\",\"grayDesc\":\"\",\"seatStatus\":1,\"isMorrow\":false,\"th\":\"三号厅\",\"sellPrStr\":\"\n" +
      " <span class=\"\\\"m3\" true2\\\"=\"\"><i>747434</i><i>047</i></span>\",\"prStr\":\"\n" +
      " <span class=\"\\\"m3\" true2\\\"=\"\"><i>393943</i><i>306</i></span>\",\"tp\":\"3D\"},{\"end\":\"17:44\",\"showDate\":\"2015-12-04\",\"showId\":49749,\"dt\":\"2015-12-04\",\"embed\":0,\"preferential\":0,\"tm\":\"16:05\",\"sell\":true,\"lang\":\"国语\",\"grayDesc\":\"\",\"seatStatus\":1,\"isMorrow\":false,\"th\":\"五号厅\",\"sellPrStr\":\"\n" +
      " <span class=\"\\\"m3\" true2\\\"=\"\"><i>651406</i><i>840</i></span>\",\"prStr\":\"\n" +
      " <span class=\"\\\"m3\" true2\\\"=\"\"><i>643927</i><i>002</i></span>\",\"tp\":\"3D\"}],\"2015-12-05\":[{\"end\":\"19:29\",\"showDate\":\"2015-12-05\",\"showId\":52001,\"dt\":\"2015-12-05\",\"embed\":0,\"preferential\":0,\"tm\":\"17:50\",\"sell\":true,\"lang\":\"国语\",\"grayDesc\":\"\",\"seatStatus\":1,\"isMorrow\":false,\"th\":\"五号厅\",\"sellPrStr\":\"\n" +
      " <span class=\"\\\"m3\" true2\\\"=\"\"><i>559409</i><i>247</i></span>\",\"prStr\":\"\n" +
      " <span class=\"\\\"m3\" true2\\\"=\"\"><i>845925</i><i>105</i></span>\",\"tp\":\"3D\"}]},\"cssLink\":\"http://p0.meituan.net/movie/053aa83e7685092123c25ab7f6087db33380.css\"}} ";
    context = context.trim().replace("\n", "").substring(6);
    context.substring(0, context.length() - 7);
    context.replace("\"\\\"", "'");
    JSONObject ob = JSON.parseObject(context);
    JSONObject data = (JSONObject) ob.get("data");
    JSONObject dateShow = (JSONObject) data.get("DateShow");
    for (String key : dateShow.keySet()) {
      String currDate = key;
      JSONArray shows = (JSONArray) dateShow.get(key);
      for (Object showOb : shows) {
        JSONObject show = (JSONObject) showOb;
        show.getInteger("showId");
        show.getDate("dt");
        show.getString("tm");
        show.getString("end");
        show.getString("lang");
        show.getString("th");
        show.getString("tp");
        show.getString("sellPrStr");
        show.getString("prStr");
      }
    }
  }
}
