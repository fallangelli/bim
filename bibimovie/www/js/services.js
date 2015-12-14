angular.module('bibimovie.services', [])

  .provider('Geolocation', function () {
    this.$get = function ($q, $http, $ionicLoading, ApiEndpoint) {
      var service = {
        currCityId: window.localStorage['curr_city_id'],
        currCityName: window.localStorage['curr_city_name'],
        currLat: window.localStorage['curr_Lat'],
        currLng: window.localStorage['curr_Lng'],

        getCurrentCityId: function () {
          var currCityId = window.localStorage['curr_city_id'];
          if (currCityId)
            return currCityId;

          var promiseCity = service.getCurrentCity();
          promiseCity.then(function () {
            window.localStorage['curr_city_id'] = service.currCityId;
            window.localStorage['curr_city_name'] = service.currCityName;
            return window.localStorage['curr_city_id'];
          }, function () {
            window.localStorage['curr_city_id'] = 1;
            window.localStorage['curr_city_name'] = '';
            alert("无法定位当前城市");
          })
        },
        getCurrentPosition: function () {
          var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行
          var geolocation = new BMap.Geolocation();
          geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
              //var mk = new BMap.Marker(r.point);
              window.localStorage['curr_Lat'] = r.point.lat;
              window.localStorage['curr_Lng'] = r.point.lng;
              deferred.resolve();
            }
            else {
              alert('failed' + this.getStatus());
              deferred.reject();
            }
          }, {enableHighAccuracy: false})
          return deferred.promise;
        },
        getCurrentCity: function () {
          var deferred = $q.defer();
          var deferredCityId = $q.defer();
          var promise = deferred.promise;
          var myCity = new BMap.LocalCity();
          myCity.get(function (result) {
            var cityName = result.name;
            window.localStorage['curr_city_name'] = cityName;
            deferred.resolve(cityName);
          })
          promise
            .then(function (val) {
              var url = ApiEndpoint.server_url + "home/getCityIdFromName?cityName=" + encodeURI(encodeURI(val));
              $http.get(url)
                .success(function (data) {
                  window.localStorage['curr_city_id'] = data;
                  deferredCityId.resolve(data);
                });
            })
          return deferredCityId.promise;
        }
      };
      return service;
    }
  })

  .factory('MovieCinemaService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getMovieCinemas: function (cityId, movieId) {
        var deferred = $q.defer();
        var deferredCinemas = $q.defer();
        var promise = deferred.promise;

        var dates_url = ApiEndpoint.server_url + "cityScreening/MovieCinemaShowDates?" +
          "cityId=" + cityId + "&movieId=" + movieId + "&cinemaId=933";
        $http.get(dates_url)
          .success(function (data) {
            var dates = angular.fromJson(data);
            //
            //$scope.dates = dates;
            //$scope.currDate = dates[0];
            deferred.resolve(dates);
          })
          .error(function (data, header, config, status) {
            alert("读取电影影院上映日期信息错误");
            deferred.reject();
          });
        promise
          .then(function (dates) {
            var cinema_url = ApiEndpoint.server_url + "cityCinemas/DateMovieCinemas?" +
              "cityId=" + cityId + "&movieId=" + movieId + "&showDate=" + dates[0];
            $http.get(cinema_url)
              .success(function (data) {
                deferredCinemas.resolve(data);
              })
              .error(function (data, header, config, status) {
              });
          })
        return deferredCinemas.promise;
      }

    }
  }])

  .factory('CinemaService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getCinemas: function (cityId) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityCinemas/Cinemas?cityId=" + cityId;
        $http.get(url)
          .success(function (data) {
            var cinemas = angular.fromJson(data)
            deferred.resolve(cinemas);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      }

    }
  }])
