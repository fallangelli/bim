angular.module('bibimovie.services', [])

  .provider('Geolocation', function () {
    this.$get = function ($q, $http, $ionicLoading, ApiEndpoint) {
      var service = {
        initCurrentCity: function () {
          var deferred = $q.defer();
          var deferredCityId = $q.defer();
          var promise = deferred.promise;
          var geolocation = new BMap.Geolocation();
          geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
              window.localStorage['curr_lat'] = r.point.lat;
              window.localStorage['curr_lng'] = r.point.lng;
              window.localStorage['curr_city_name'] = r.address.city;
              deferred.resolve(window.localStorage['curr_city_name']);
            }
            else {
              alert('查询当前坐标错误' + this.getStatus());
              deferred.reject();
            }
          }, {enableHighAccuracy: false})

          promise
            .then(function (val) {
              var url = ApiEndpoint.server_url + "home/getCityIdFromName?cityName=" + val;
              $http.get(url)
                .success(function (data) {
                  window.localStorage['curr_city_id'] = data;
                  var curTime = new Date()
                  var expirationTime = new Date(curTime.getTime() + 30 * 60 * 1000)
                  window.localStorage['expiration_time'] = expirationTime;
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
      getMovieCinemaDates: function (cityId, movieId) {
        var deferred = $q.defer();
        var promise = deferred.promise;

        var dates_url = ApiEndpoint.server_url + "cityScreening/cityMovieWithShowDates?" +
          "cityId=" + cityId + "&movieId=" + movieId;
        $http.get(dates_url)
          .success(function (data) {
            var dates = angular.fromJson(data);
            deferred.resolve(dates);
          })
          .error(function (data, header, config, status) {
            alert("读取电影影院上映日期信息错误");
            deferred.reject();
          });
        return promise;
      },
      getMovieCinemasByDate: function (cityId, movieId, date) {
        var deferred = $q.defer();
        var promise = deferred.promise;

        var cinema_url = ApiEndpoint.server_url + "cityCinemas/DateMovieCinemas?" +
          "cityId=" + cityId + "&movieId=" + movieId + "&showDate=" + date;
        $http.get(cinema_url).success(function (data) {
            deferred.resolve(data);
          })
          .error(function (data, header, config, status) {
            alert("读取电影影院信息错误");
            deferred.reject();
          });

        return promise;
      }

    }
  }])

  .factory('CinemaListService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
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

  .factory('CinemaService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getCinema: function (cinemaId) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityCinemas/Cinema?cinemaId=" + cinemaId;
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

  .factory('MovieService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getMovie: function (moiveId) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityMovies/Movie?moiveId=" + moiveId;
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

  .factory('ScreeningService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getScreening: function (cityId, cinemaId, movieId, showDate) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityScreening/cityScreening?cityId=" +
          cityId + "&cinemaId=" + cinemaId + "&movieId=" + movieId + "&showDate=" + showDate;
        $http.get(url)
          .success(function (data) {
            var obj = angular.fromJson(data)
            deferred.resolve(obj);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      }
    }
  }])


  .factory('SourceService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getSource: function (cityId, cinemaId, movieId, showDate, startTime) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityScreening/citySource?cityId=" +
          cityId + "&cinemaId=" + cinemaId + "&movieId=" + movieId + "&showDate=" + showDate + "&startTime=" + startTime;
        ;
        $http.get(url)
          .success(function (data) {
            var obj = angular.fromJson(data)
            deferred.resolve(obj);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      }
    }
  }])
