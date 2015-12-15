angular.module('bibimovie.services', [])

  .provider('Geolocation', function () {
    this.$get = function ($q, $http, $ionicLoading, ApiEndpoint) {
      var service = {
        curr_city_id: '',
        curr_city_name: '',
        curr_lat: '',
        curr_lng: '',

        initCurrentCity: function () {
          var deferred = $q.defer();
          var deferredCityId = $q.defer();
          var promise = deferred.promise;
          var geolocation = new BMap.Geolocation();
          geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
              service.curr_lat = r.point.lat;
              service.curr_lng = r.point.lng;
              service.curr_city_name = r.address.city;
              deferred.resolve([service.curr_city_name, service.curr_lat, service.curr_lng]);
            }
            else {
              alert('查询当前坐标错误' + this.getStatus());
              deferred.reject();
            }
          }, {enableHighAccuracy: false})

          promise
            .then(function (val) {
              var url = ApiEndpoint.server_url + "home/getCityIdFromName?cityName=" + encodeURI(encodeURI(val));
              $http.get(url)
                .success(function (data) {
                  service.curr_city_id = data;
                  deferredCityId.resolve(data);
                })
                .error(function (data, header, config, status) {
                  deferredCityId.reject();
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
