package com.family.grabserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.LoggerFactory;


/**
 * User: cairne
 * Date: 13-5-13
 * Time: 下午8:33
 */
public class CinemaMaoyanJsonParseTest {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  //    @Ignore
  @Test
  public void test() {
    String context = "{\"control\":{\"expires\":120},\"status\":0,\"data\":{\"Dates\":[{\"text\":\"今天 12月07日\",\"slug\":\"2015-12-07\"}],\"currentMovie\":{\"nm\":\"怦然星动\",\"preferential\":0,\"sc\":8.1,\"ver\":\"2D\",\"img\":\"http://p0.meituan.net/165.220/movie/67b19527083a477f5d0e1876ce40dd7d227974.jpg\",\"isShowing\":true,\"id\":338503},\"cinemaDetailModel\":{\"price\":26.0,\"addr\":\"回民区中山西路1号维多利购物中心7楼\",\"ct\":\"呼和浩特\",\"nm\":\"万达国际影城(维多利店)\",\"preferential\":0,\"sellmin\":1,\"sell\":true,\"area\":\"回民区\",\"bus\":\"乘1/3/4/19/58/60/76路至海亮广场下车即是。\",\"park\":\"维多利停车场\",\"imax\":0,\"snum\":11,\"s\":9.272727,\"s1\":8.090909,\"s2\":7.909091,\"s3\":8.090909,\"featureTags\":[{\"desc\":\"限网上选座后取票\",\"type\":1,\"tag\":\"取票机\"},{\"desc\":\"每位成人可携带1名1.3米以下儿童免费（无座）观影\",\"type\":7,\"tag\":\"儿童优惠\"},{\"desc\":\"维多利停车场\",\"type\":5,\"tag\":\"可停车\"},{\"desc\":\"免押金\",\"type\":4,\"tag\":\"3D眼镜\"},{\"desc\":\"影院大厅有休息区\",\"type\":10,\"tag\":\"休息区\"},{\"desc\":\"前台购票可刷卡付款\",\"type\":8,\"tag\":\"可刷卡\"}],\"deals\":[{\"price\":26.0,\"title\":\"26元，2D/3D电影票，可观看2D/3D，免预约\",\"id\":0,\"type\":1}],\"callboard\":\"\",\"brd\":\"万达影城\",\"dis\":\"维多利\",\"lng\":111.668594,\"lat\":40.81652,\"tel\":[\"400-080-6060\"],\"suw\":\"暂无\",\"dri\":\"沿中山路向西行驶至中山西路与锡林郭勒北路十字路口西北角维多利购物中心即是。\",\"note\":\"周一至周五13:00以前，周六至周日周末12:00以前全场电影一律30元，周二周三全天电影特价放映，周四情侣观影特价放映\",\"dealtp\":1,\"id\":1322},\"movies\":[{\"nm\":\"怦然星动\",\"preferential\":0,\"sc\":8.1,\"ver\":\"2D\",\"img\":\"http://p0.meituan.net/165.220/movie/67b19527083a477f5d0e1876ce40dd7d227974.jpg\",\"isShowing\":true,\"id\":338503},{\"nm\":\"寻龙诀\",\"preferential\":0,\"sc\":0.0,\"ver\":\"3D/IMAX 3D\",\"img\":\"http://p1.meituan.net/165.220/movie/4a8d5adbbf1757600ea4dedea4e8f8fd214051.jpg\",\"isShowing\":false,\"id\":78701},{\"nm\":\"极盗者\",\"preferential\":0,\"sc\":8.3,\"ver\":\"2D/3D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/b8a6b0b27bdc633378ad047746bc4f49387554.jpg\",\"isShowing\":true,\"id\":249141},{\"nm\":\"从天儿降\",\"preferential\":0,\"sc\":7.9,\"ver\":\"2D\",\"img\":\"http://p0.meituan.net/165.220/movie/55cb9d44cda167314f30e920cca5502a209601.jpg\",\"isShowing\":true,\"id\":246364},{\"nm\":\"不可思异\",\"preferential\":0,\"sc\":7.3,\"ver\":\"2D/3D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/d2f636cbbbfb60680de9d0f5e2887f261476005.jpg\",\"isShowing\":true,\"id\":78380},{\"nm\":\"火星救援\",\"preferential\":0,\"sc\":8.9,\"ver\":\"3D/IMAX 3D/中国巨幕/全景声\",\"img\":\"http://p0.meituan.net/165.220/movie/f7df200378e3725a4bc825397f5c9956130126.jpg\",\"isShowing\":true,\"id\":246369},{\"nm\":\"杜拉拉追婚记\",\"preferential\":0,\"sc\":8.3,\"ver\":\"2D/中国巨幕\",\"img\":\"http://p1.meituan.net/165.220/movie/27b802ecc9a2ed1fd679814242b85132399461.jpg\",\"isShowing\":true,\"id\":342576},{\"nm\":\"我的少女时代\",\"preferential\":0,\"sc\":9.2,\"ver\":\"2D\",\"img\":\"http://p1.meituan.net/165.220/movie/19e87124de0929c5df86cb760776afc5655939.jpg\",\"isShowing\":true,\"id\":341569}],\"DateShow\":{\"2015-12-07\":[{\"end\":\"19:43\",\"showDate\":\"2015-12-07\",\"embed\":1,\"dt\":\"2015-12-07\",\"showId\":56539,\"preferential\":0,\"tm\":\"18:05\",\"sell\":true,\"lang\":\"国语\",\"tp\":\"2D\",\"grayDesc\":\"\",\"seatStatus\":1,\"isMorrow\":false,\"th\":\"1号厅(6FL)\",\"sellPrStr\":\"2893330\",\"prStr\":\"\"},{\"end\":\"22:23\",\"showDate\":\"2015-12-07\",\"embed\":1,\"dt\":\"2015-12-07\",\"showId\":56537,\"preferential\":0,\"tm\":\"20:45\",\"sell\":true,\"lang\":\"国语\",\"tp\":\"2D\",\"grayDesc\":\"\",\"seatStatus\":1,\"isMorrow\":false,\"th\":\"3号厅\",\"sellPrStr\":\"4903308\",\"prStr\":\"\"},{\"end\":\"23:43\",\"showDate\":\"2015-12-07\",\"embed\":1,\"dt\":\"2015-12-07\",\"showId\":56538,\"preferential\":0,\"tm\":\"22:05\",\"sell\":true,\"lang\":\"国语\",\"tp\":\"2D\",\"grayDesc\":\"\",\"seatStatus\":1,\"isMorrow\":false,\"th\":\"1号厅(6FL)\",\"sellPrStr\":\"6693373\",\"prStr\":\"\"}]},\"cssLink\":\"http://p0.meituan.net/movie/d5b6c9d5387e4fb303465c1294119c433145.css\"}}";
    JSONObject ob = JSON.parseObject(context);
    logger.info(((JSONObject) ob.get("data")).get("cinemaDetailModel").toString());
  }
}
