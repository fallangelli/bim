(function updateCityList() {

  var cityts = localStorage.getItem('city_ts', 0);
  var timestamp = (new Date()).valueOf();
  var basePath = getBasePath();
  if (timestamp - cityts > 1000 * 60 * 60 * 120) {
    mui.ajax(basePath + '/ajax/citylist', {
      type: 'get',
      timeout: 10000,
      dataType: 'text',
      success: function (data) {
        localStorage.setItem("city", data);
        var timestamp = (new Date()).valueOf();
        localStorage.setItem('city_ts', timestamp);
      }
    });
  }


})();
var fristFlg = false;
//百度定位
(function () {
  // 检测浏览器是否支持HTML5
  function supportsGeoLocation() {
    return !!navigator.geolocation;
  }

  // 单次位置请求执行的函数
  function getLocation() {
    navigator.geolocation.getCurrentPosition(mapIt, locationError);
  }

  //定位成功时，执行的函数
  function mapIt(position) {
    var lon = position.coords.longitude;
    var lat = position.coords.latitude;
    var map = new BMap.Map("allmap");
    var point = new BMap.Point("" + lon + "", "" + lat + "");
    var gc = new BMap.Geocoder();
    gc.getLocation(point, function (rs) {
      var addComp = rs.addressComponents;
      fristFlg = true;
      handleCity(lon, lat, addComp.city);
    });
  }

  // 定位失败时，执行的函数
  function locationError(error) {
    switch (error.code) {
      case error.PERMISSION_DENIED:
        alert("User denied the request for Geolocation.");
        break;
      case error.POSITION_UNAVAILABLE:
        alert("Location information is unavailable.");
        break;
      case error.TIMEOUT:
        alert("The request to get user location timed out.");
        break;
      case error.UNKNOWN_ERROR:
        alert("An unknown error occurred.");
        break;
    }
  }

  // 页面加载时执行getLocation函数
  window.onload = getLocation;
})();

setTimeout('defaultLocation()', 5000);

function defaultLocation() {
  if (fristFlg === false) {
    var cityName = "北京市";
    var lon = 116.48083869999999;
    var lat = 39.912500699999995;

    handleCity(lon, lat, cityName);

  }
}
function openArea() {
  if ($(".page_area")[0].childElementCount === 0) {
    var basePath = getBasePath();
    mui.ajax(basePath + '/ajax/area' + getchannel(), {
      type: 'get',
      timeout: 10000,
      dataType: 'html',
      success: function (data) {
        $(".page_area")[0].innerHTML = data;
        updateArea();
        $(".page_area").css("display", "block");
        $(".page_index").css("display", "none")
      }
    });
  } else {
    $(".page_area").css("display", "block");
    $(".page_index").css("display", "none")
  }
}
function closeArea() {
  $(".page_area").css("display", "none");
  $(".page_index").css("display", "block")
}
function updateArea() {
  var objs = $(".area-city");
  var cityName = localStorage.getItem("cityName");
  var citydata = localStorage.getItem('city');
  var cityjson = eval('(' + citydata + ')');
  for (var x = 0; x < objs.length; x++) {
    if (objs[x].id == "gps-city") {
      objs[x].innerHTML = "<h4>" + cityName + "</h4>";
    } else if (objs[x].id == "hot-city") {
      var hotcity = "";
      for (var i = 0; i < cityjson.hot.length; i++) {
        hotcity += ("<h4 class=\"zimu_h4\" city-code=\"" + cityjson.hot[i].cityid + "\">" + cityjson.hot[i].cityname + "</h4>");
      }
      objs[x].innerHTML = hotcity;
    }
  }

  //更新城市
  var lastletter = "";
  var html = "";
  for (var i = 0; i < cityjson.letter.length; i++) {
    if (cityjson.letter[i].firstletter !== lastletter) {
      if (lastletter !== "") {
        html += "</ul></div>";
      }
      html += "<a name=\"" + cityjson.letter[i].firstletter + "\"><div class=\"frist\">" + cityjson.letter[i].firstletter + "</div></a>";
      html += "<ul class=\"zimubiao-city\">";
      html += "<li class=\"zimu\" city-code=\"" + cityjson.letter[i].cityid + "\">" + cityjson.letter[i].cityname + "</li>";
    } else {
      html += "<li class=\"zimu\" city-code=\"" + cityjson.letter[i].cityid + "\">" + cityjson.letter[i].cityname + "</li>";
    }
    lastletter = cityjson.letter[i].firstletter;
  }

  $(".city-zimu")[0].innerHTML = html;
  $(".zimu").click(selectArea);
  $(".zimu_h4").click(selectArea);
  $(".mui-bar-back-button").click(closeArea);
}

function selectArea(obj) {
  localStorage.setItem("user_select_cityName", obj.currentTarget.innerText);
  localStorage.setItem("user_select_cityCode", obj.currentTarget.attributes[1].value);
  closeArea();

  updateCityName(getCityName());
  var pagetype = getPageType();
  if (pagetype === "cinamermovie") {
    updateCinamerMovieList(null);
  } else {
    var current = getCurrentTab();
    if (current === 0) {
      updateMovieList(getCityId());
    } else {
      updateCinamerList(null);
    }
  }

}

function selectAreaByLocation(cityname) {
}

function handleCity(lon, lat, cityname) {
  var city = cityname.substring(0, cityname.indexOf('市'));
  localStorage.setItem('lon', lon);
  localStorage.setItem('lat', lat);
  localStorage.setItem('cityName', city);
  var cityId = getCityIdByName(city);
  localStorage.setItem('cityCode', cityId);
  //

  updateCityName(getCityName());
  var type = GetQueryString('type');
  if (type === 'cinamer') {
    updateCinamerList(null);
  } else {
    updateMovieList(getCityId());
  }

}

function getCityIdByName(city) {
  var citydata = localStorage.getItem('city');
  var obj = eval('(' + citydata + ')');
  var cityid = "2";
  for (var i = 0; i < obj.letter.length; i++) {
    if (obj.letter[i].cityname === city) {
      cityid = obj.letter[i].cityid;
      break;
    }
  }
  return cityid;
}
function updateCityName(cityname) {
  $(".city-select")[0].innerHTML = cityname;
}

function updateRegionName(regionname) {
  $(".cinamer-select-disct")[0].innerHTML = regionname;
}
function updateMovieList(cityid) {
  var loading = "<div class=\"mui-loading\"><div class=\"mui-spinner\"></div></div>";
  document.getElementById('item1mobile').innerHTML = loading;
  mui.post('ajax/movielist' + getchannel(), {"cityid": cityid}, function (data) {
    document.getElementById('item1mobile').innerHTML = data;
  }, 'html');
}

function updateCinamerList(region) {
  var item2 = document.getElementById('item2mobile');
  var cityCode = getCityId();
  var lon = localStorage.getItem('lon', lon);
  var lat = localStorage.getItem('lat', lat);
  var pos = lon + "," + lat;
  var loading = "<div class=\"mui-loading\"><div class=\"mui-spinner\"></div></div>";
  item2.innerHTML = loading;
  var basePath = getBasePath();
  if (region === null || region === "全部") {
    mui.post(basePath + '/ajax/cinamerlist' + getchannel(), {"cityid": cityCode, "pos": pos}, function (data) {
      item2.innerHTML = data;
      updateRegionName("全部");
    }, 'html');
  } else {
    mui.post(basePath + '/ajax/cinamerlist' + getchannel(), {
      "cityid": cityCode,
      "pos": pos,
      "regionname": region
    }, function (data) {
      item2.innerHTML = data;
      updateRegionName(region);
    }, 'html');
  }

}

function updateCinamerMovieList(region) {
  var item2 = document.getElementById('item2mobile');
  var cityCode = getCityId();
  var lon = localStorage.getItem('lon', lon);
  var lat = localStorage.getItem('lat', lat);
  var pos = lon + "," + lat;
  var loading = "<div class=\"mui-loading\"><div class=\"mui-spinner\"></div></div>";
  item2.innerHTML = loading;
  var movieid = getCurrentMovieId();
  var basePath = getBasePath();
  if (region === null || region === "全部") {
    mui.post(basePath + '/ajax/cinamerlist' + getchannel(), {
      "cityid": cityCode,
      "pos": pos,
      "movieid": movieid
    }, function (data) {
      item2.innerHTML = data;
      updateRegionName("全部");
    }, 'html');
  } else {
    mui.post(basePath + '/ajax/cinamerlist' + getchannel(), {
      "cityid": cityCode,
      "pos": pos,
      "movieid": movieid,
      "regionname": region
    }, function (data) {
      item2.innerHTML = data;
      updateRegionName(region);
    }, 'html');
  }

}

function showRegion() {
  $(".mui-backdrop").css("display", "block");
  $(".cinamer-area-dialog").css("display", "block");
  $(".cinamer-area-dialog2").css("display", "block");
  $(".mui-backdrop").click(function (e) {
    $(".mui-backdrop").css("display", "none");
    $(".cinamer-area-dialog").css("display", "none");
    $(".cinamer-area-dialog2").css("display", "none");
  });
}

function getCityId() {
  var cityCode = localStorage.getItem("user_select_cityCode");
  if (cityCode === null) {
    cityCode = localStorage.getItem("cityCode");
  }
  return cityCode;
}

function getCityName() {
  var cityName = localStorage.getItem("user_select_cityName");
  if (cityName === null) {
    cityName = localStorage.getItem("cityName");
  }
  return cityName;
}

function openMovieChannel(cinamerid, movieid, typeid, day) {
  var basePath = getBasePath();
  mui.post(basePath + '/ajax/redirect', {"cid": cinamerid, "mid": movieid, "tid": typeid, "day": day}, function (data) {
    window.location.href = data;
  }, 'text');
}

function getCurrentTab() {
  var obj = $(".mui-slider-item");
  if (obj[0].className.indexOf("mui-active") !== -1) {
    return 0;
  } else {
    return 1;
  }
}

function getBasePath() {
  var location = (window.location + '').split('/');
  var basePath = location[0] + '//' + location[2] + '/' + location[3];
  return basePath;
}

function getPageType() {
  var location = (window.location + '').split('/');
  return location[4];
}

function GetQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return (r[2]);
  return null;
}

function setCurrentTab(index) {

}
