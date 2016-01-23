angular.module('bibimovie.services', [])

  .provider('Geolocation', function () {
    this.$get = function ($q, $http, $ionicLoading, ApiEndpoint) {

      var service = {
        initCurrentCity: function () {
          var deferred = $q.defer();
          var deferredCityId = $q.defer();
          var promise = deferred.promise;
          if (navigator.geolocation) {
            var getOptions = {
              enableHighAccuracy: true,
              timeout: 3000,
              maximumAge: 0
            };
            navigator.geolocation.getCurrentPosition(getSuccess, getError, getOptions);
            function getSuccess(position) {
              window.localStorage['curr_lat'] = position.coords.latitude;
              window.localStorage['curr_lng'] = position.coords.longitude;
              var map = new BMap.Map("l-map");
              var myGeo = new BMap.Geocoder();
              myGeo.getLocation(new BMap.Point(window.localStorage['curr_lng'], window.localStorage['curr_lat']), function (result) {
                if (result) {
                  window.localStorage['curr_city_name'] = result.addressComponents.city;
                  deferred.resolve(result.addressComponents.city);
                }
              });
            }

            function getError(error) {
              alert('无法得到位置信息');
              window.localStorage['curr_city_name'] = '北京市';
              deferred.resolve(window.localStorage['curr_city_name']);
            }
          }

          promise
            .then(function (val) {
              var url = ApiEndpoint.server_url + "home/getCityIdFromName?cityName=" + val;
              $http.get(url)
                .success(function (data) {
                  window.localStorage['curr_city_id'] = data;
                  var expirationTime = new Date();
                  expirationTime.setMinutes(expirationTime.getMinutes() + 20, expirationTime.getSeconds(), 0);
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
      getMovieCinemaDates: function (cityId, movieId, distinctId) {
        var deferred = $q.defer();
        var promise = deferred.promise;

        var dates_url = ApiEndpoint.server_url + "cityScreening/CityMovieWithShowDates?" +
          "cityId=" + cityId + "&movieId=" + movieId;
        if (distinctId) dates_url += "&distinctId=" + distinctId;
        $http.get(dates_url)
          .success(function (data) {
            if (data) {
              var dates = angular.fromJson(data);
              deferred.resolve(dates);
            }
            else  deferred.resolve();
          })
          .error(function (data, header, config, status) {
            alert("读取电影影院上映日期信息错误");
            deferred.reject();
          });
        return promise;
      },
      getMovieCinemasByDate: function (cityId, movieId, date, lat, lng) {
        var deferred = $q.defer();
        var promise = deferred.promise;

        var cinema_url = ApiEndpoint.server_url + "cityCinemas/DateMovieCinemas?" +
          "cityId=" + cityId + "&movieId=" + movieId + "&showDate=" + date;
        if (lat) cinema_url += "&lat=" + lat;
        if (lng) cinema_url += "&lng=" + lng;
        $http.get(cinema_url).success(function (data) {
            deferred.resolve(data);
          })
          .error(function (data, header, config, status) {
            alert("读取电影影院信息错误");
            deferred.reject();
          });

        return promise;
      },
      getCityInfo: function (cityId) {
        var url = ApiEndpoint.server_url + "home/getCityInfo?cityId=" + cityId;
        var deferred = $q.defer();
        $http.get(url)
          .success(function (data) {
            var cityInfo = angular.fromJson(data);
            deferred.resolve(cityInfo);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      }

    }
  }])

  .factory('MovieListService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getMovies: function (cityId) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityMovies/CityMovies?cityId=" + cityId;
        $http.get(url)
          .success(function (data) {
            var movies = angular.fromJson(data)
            deferred.resolve(movies);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      }

    }
  }])

  .factory('CinemaListService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {
    return {
      getCinemas: function (cityId, lat, lng) {
        var url = ApiEndpoint.server_url + "cityCinemas/Cinemas?cityId=" + cityId;
        if (lat) url += "&lat=" + lat;
        if (lng) url += "&lng=" + lng;

        var deferred = $q.defer();
        $http.get(url)
          .success(function (data) {
            var cinemas = angular.fromJson(data)
            deferred.resolve(cinemas);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      },
      getCityInfo: function (cityId) {
        var url = ApiEndpoint.server_url + "home/getCityInfo?cityId=" + cityId;
        var deferred = $q.defer();
        $http.get(url)
          .success(function (data) {
            var cityInfo = angular.fromJson(data);
            deferred.resolve(cityInfo);
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
      },
      getCinemaMovieDates: function (cinemaId, movieId) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityCinemas/CinemaMovieDates?cinemaId=" + cinemaId + "&movieId=" + movieId;
        $http.get(url)
          .success(function (data) {
            var cinemas = angular.fromJson(data);
            deferred.resolve(cinemas);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      },
      getCinemaMovieScreeningByDate: function (cinemaId, movieId, date) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "cityScreening/CinemaScreening?cinemaId="
          + cinemaId + "&movieId=" + movieId + "&showDate=" + date;
        $http.get(url)
          .success(function (data) {
            // var cinemas = angular.fromJson(data);
            deferred.resolve(data);
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
        var url = ApiEndpoint.server_url + "cityScreening/CinemaScreening?cinemaId=" +
          cinemaId + "&movieId=" + movieId + "&showDate=" + showDate;
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
        var url = ApiEndpoint.server_url + "cityScreening/CinemaSource?cinemaId=" +
          cinemaId + "&movieId=" + movieId + "&showDate=" + showDate + "&startTime=" + startTime;
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

  .factory('CitiesService', ['$q', '$http', 'ApiEndpoint', function ($q, $http, ApiEndpoint) {

    return {
      getAllCities: function (cityId, cinemaId, movieId, showDate, startTime) {
        var deferred = $q.defer();
        var url = ApiEndpoint.server_url + "home/getAllCities";
        $http.get(url)
          .success(function (data) {
            var obj = angular.fromJson(data)
            deferred.resolve(obj);
          })
          .error(function (data, header, config, status) {
            deferred.reject();
          });

        return deferred.promise;
      },

      elmYPosition: function (eID) {
        var elm = document.getElementById(eID);
        var y = elm.offsetTop;
        var node = elm;
        while (node.offsetParent && node.offsetParent != document.body) {
          node = node.offsetParent;
          y += node.offsetTop;
        }
        return y;
      }
    }
  }])
