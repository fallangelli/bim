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
public class MovieshowingMaoyanJsonParseTest {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  //    @Ignore
  @Test
  public void test() {
    String context = "{\"image\":\"http://img31.mtime.cn/mt/2015/11/17/130840.83198898_1280X720X2.jpg\",\"titleCn\":\"藏羚王之雪域精灵\",\"titleEn\":\"The King of Tibetan Antelope\",\"rating\":\"6.5\",\"scoreCount\":\"38\",\"year\":\"2015\",\"content\":\"本片是全球首部藏羚羊题材动画电影。在青藏高原雪域冰川的动物王国里，母藏羚羊群进行着一年一度的迁移繁殖之旅，新出生的小羊呶噜与卓玛在母亲们身边度过了快乐的时光；然而在回归美丽故乡的途中，却遭遇到人类凶狠盗猎者的围捕与猎杀，妈妈了为救呶噜坠落悬崖生死未卜，幸存下来的小呶噜将在广袤无垠的藏域雪国独自踏上归乡之路。结识了小伙伴兔鼠卡拉和角百灵西达，向岩羊、耗牛学习了攀岩决斗的本领，经历了漫长冬季长出小角的呶噜在回乡之路却要面对天敌雪山狼群和秃鹰的追袭，高原雪崩、冰裂、火山、喷泉的天险，以及开着越野车端着猎枪的盗猎者们……面对这危机四伏的重重凶险，呶噜和他的小伙伴们该如何克敌制胜逃出生天、如何成长为&quot;小小藏羚王&quot;呢？他能否与卓玛重逢与家人团聚呢？\",\"type\":[\"动画\",\"冒险\",\"喜剧\"],\"runTime\":\"83分钟\",\"url\":\"http://movie.mtime.com/223996/\",\"wapUrl\":\"http://movie.wap.mtime.com/223996/\",\"isEggHunt\":false,\"commonSpecial\":\"中国版狮子王\",\"hotRanking\":116,\"weekBoxOffice\":\"\",\"totalBoxOffice\":\"\",\"weekBoxOfficeUnit\":\"\",\"totalBoxOfficeUnit\":\"\",\"director\":{\"directorId\":1837699,\"directorName\":\"刘宇军\",\"directorNameEn\":\"\",\"directorImg\":\"http://img31.mtime.cn/ph/1699/1837699/1837699_1280X720X2.jpg\"},\"actorList\":[],\"isTicket\":false,\"showCinemaCount\":-1,\"showtimeCount\":-1,\"showDay\":-1,\"style\":{\"ifLeadPage\":0,\"leadUrl\":\"\",\"leadImag\":\"http://img2.mtime.cn/mg/.jpg\"},\"is3D\":false,\"isIMAX\":false,\"isIMAX3D\":false,\"isDMAX\":false,\"festivals\":[],\"awards\":[],\"totalWinAward\":0,\"totalNominateAward\":0,\"directors\":[\"刘宇军\"],\"actors\":[\"李扬\",\"鞠萍\"],\"release\":{\"location\":\"中国\",\"date\":\"2015-11-20\"},\"imageCount\":75,\"images\":[\"http://img31.mtime.cn/pi/2015/04/24/144416.99952261_1280X720X2.jpg\",\"http://img31.mtime.cn/pi/2015/05/06/172903.79296483_1280X720X2.jpg\",\"http://img31.mtime.cn/pi/2015/04/24/144426.98785472_1280X720X2.jpg\",\"http://img31.mtime.cn/pi/2015/05/06/172905.33456935_1280X720X2.jpg\"],\"video\":\"http://vfx.mtime.cn/Video/2015/11/17/mp4/151117132827559740.mp4\",\"videoId\":57517,\"videoCount\":3,\"videos\":[{\"url\":\"http://vfx.mtime.cn/Video/2015/11/17/mp4/151117132827559740.mp4\",\"image\":\"http://img31.mtime.cn/mg/2015/11/17/132814.13893878.jpg\",\"length\":93,\"title\":\"藏羚王之雪域精灵 终极版预告片\",\"videoId\":57517},{\"url\":\"http://vfx.mtime.cn/Video/2015/10/23/mp4/151023095638361378.mp4\",\"image\":\"http://img31.mtime.cn/mg/2015/10/23/095601.52605929.jpg\",\"length\":64,\"title\":\"藏羚王之雪域精灵 剧场版预告片\",\"videoId\":57061},{\"url\":\"http://vfx.mtime.cn/Video/2015/09/25/mp4/150925093624524270.mp4\",\"image\":\"http://img31.mtime.cn/mg/2015/09/25/093738.14592787.jpg\",\"length\":30,\"title\":\"藏羚王之雪域精灵 先行版预告片\",\"videoId\":56609}],\"personCount\":5}\n";
    JSONObject ob = JSON.parseObject(context);
//        JSONObject data = (JSONObject) ob.get("data");
//        JSONObject movie = (JSONObject) data.get("MovieDetailModel");
    logger.info(ob.toString());
  }
}
