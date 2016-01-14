angular.module('bibimovie.controllers', [])
  .controller('HomeCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, Geolocation) {

    $ionicLoading.show({template: '加载中...'});
    var currTime = new Date();
    if (window.localStorage['curr_city_id'] && window.localStorage['curr_city_name'] &&
      window.localStorage['curr_lat'] && window.localStorage['curr_lng'] &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      $scope.currLat = window.localStorage['curr_lat'];
      $scope.currLng = window.localStorage['curr_lng'];
      loadHotMovies();
      loadNearCinemas();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        $scope.currLat = window.localStorage['curr_lat'];
        $scope.currLng = window.localStorage['curr_lng'];
        loadHotMovies();
        loadNearCinemas();
      }, function () {
        alert("无法得到当前城市信息");
        $ionicLoading.hide();
      })
    }

    function loadHotMovies() {
      var url = ApiEndpoint.server_url + "home/hotMovies?cityId=" + $scope.currCityId;
      $http.get(url)
        .success(function (data) {
          var movies = angular.fromJson(data)
          $scope.movieList = movies;
        })
        .error(function (data, header, config, status) {
          alert("读取电影信息错误");
          $ionicLoading.hide();
        });
    }

    function loadNearCinemas() {
      var url = ApiEndpoint.server_url + "home/getNearCinemas?lat=" + $scope.currLat + "&lng=" + $scope.currLng;
      $http.get(url)
        .success(function (data) {
          var cinemas = angular.fromJson(data)
          $scope.nearCinemaList = cinemas;
          $ionicLoading.hide();
        })
        .error(function (data, header, config, status) {
          alert("读取附近影院信息错误");
          $ionicLoading.hide();
        });
    }
  })

  .controller('MovieListCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, MovieListService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadMovies();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadMovies();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    function loadMovies() {
      var promise = MovieListService.getMovies($scope.currCityId);
      promise.then(function (val) {
          $scope.movies = val;
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取上映影片信息");
        });
    }
  })

  .controller('CinemaListCtrl', function ($scope, $http, $ionicPopover, $ionicLoading, ApiEndpoint, CinemaListService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    $scope.orderBy = 'len';
    $scope.nameLike = '';
    $scope.currDistinctId = null;
    $scope.currDistinctName = '全部';
    $scope.distanceOrderStyle = {color: 'red'};

    var currTime = new Date();
    if (window.localStorage['curr_city_id'] && window.localStorage['curr_city_name'] &&
      window.localStorage['curr_lat'] && window.localStorage['curr_lng'] &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      $scope.currLat = window.localStorage['curr_lat'];
      $scope.currLng = window.localStorage['curr_lng'];
      loadCinemas();
      loadAreas();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        $scope.currLat = window.localStorage['curr_lat'];
        $scope.currLng = window.localStorage['curr_lng'];
        loadCinemas();
        loadAreas();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    $ionicPopover.fromTemplateUrl('my-popover.html', {
      scope: $scope
    }).then(function (popover) {
      $scope.popover = popover;
    });


    $scope.openPopover = function ($event) {
      $scope.popover.show($event);
    };

    $scope.closePopover = function () {
      $scope.popover.hide();
    };

    $scope.filterByDistinct = function (distinctId, distinctName) {
      $scope.closePopover();
      $scope.currDistinctId = distinctId;
      $scope.currDistinctName = distinctName;
      if (distinctId)
        $scope.distinctFilterStyle = {color: 'red'};
      else
        $scope.distinctFilterStyle = {color: 'blue'};
    }

    $scope.orderByDistance = function () {
      $scope.orderBy = "len";
      $scope.distanceOrderStyle = {color: 'red'};
      $scope.priceOrderStyle = {color: 'blue'};
    }

    $scope.orderByPrice = function () {
      $scope.orderBy = "minPrice";
      $scope.distanceOrderStyle = {color: 'blue'};
      $scope.priceOrderStyle = {color: 'red'};
    }

    function loadAreas() {
      var promise = CinemaListService.getCityInfo($scope.currCityId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.areas = jsonObject['areas'];
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取影院信息");
        });
    }

    function loadCinemas() {
      var promise = CinemaListService.getCinemas($scope.currCityId, $scope.currLat, $scope.currLng);
      promise.then(function (val) {
          $scope.cinemas = val;
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取影院信息");
        });
    }


    $scope.getItemHeight = function (item, index) {
      //使索引项平均都有10px高，例如
      return (index % 2) === 0 ? 80 : 90;
    };
  })
  .directive('repeatDone', function () {
    return function (scope, element, attrs) {
      if (scope.$last) {
        scope.$eval(attrs.repeatDone);
      }
    }
  })

  .controller('MovieCinemaCtrl', function ($scope, $timeout, $ionicSlideBoxDelegate, $ionicPopover, $stateParams, $http, $ionicLoading, ApiEndpoint, MovieCinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    $scope.nameLike = '';
    $scope.orderBy = 'len';
    $scope.currDistinctId = null;
    $scope.currDistinctName = '全部';
    $scope.distanceOrderStyle = {color: 'red'};

    var currTime = new Date();
    if (window.localStorage['curr_city_id'] && window.localStorage['curr_city_name'] &&
      window.localStorage['curr_lat'] && window.localStorage['curr_lng'] &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      $scope.currLat = window.localStorage['curr_lat'];
      $scope.currLng = window.localStorage['curr_lng'];
      loadCityMovieCinemasWithDates();
      loadAreas();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        $scope.currLat = window.localStorage['curr_lat'];
        $scope.currLng = window.localStorage['curr_lng'];
        loadCityMovieCinemasWithDates();
        loadAreas();
      }, function () {
        alert("无法得到当前位置");
      })
    }

    $ionicPopover.fromTemplateUrl('my-popover.html', {
      scope: $scope
    }).then(function (popover) {
      $scope.popover = popover;
    });


    $scope.openPopover = function ($event) {
      $scope.popover.show($event);
    };

    $scope.closePopover = function () {
      $scope.popover.hide();
    };


    $scope.filterByDistinct = function (distinctId, distinctName) {
      $scope.closePopover();
      $scope.currDistinctId = distinctId;
      $scope.currDistinctName = distinctName;
      if (distinctId)
        $scope.distinctFilterStyle = {color: 'red'};
      else
        $scope.distinctFilterStyle = {color: 'blue'};
      //loadMovieCinemaDates($scope.currDistinctId);
      //$ionicSlideBoxDelegate.slide(0);
      //$ionicSlideBoxDelegate.update();
      //if ($scope.showDates)
      //  loadMovieCinemasByDate($scope.showDates[$ionicSlideBoxDelegate.currentIndex()]);
    }

    $scope.orderByDistance = function () {
      $scope.orderBy = "len";
      $scope.distanceOrderStyle = {color: 'red'};
      $scope.priceOrderStyle = {color: 'blue'};

    }

    $scope.orderByPrice = function () {
      $scope.orderBy = "minPrice";
      $scope.distanceOrderStyle = {color: 'blue'};
      $scope.priceOrderStyle = {color: 'red'};
    }

    function loadAreas() {
      var promise = MovieCinemaService.getCityInfo($scope.currCityId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.areas = jsonObject['areas'];
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取影院信息");
        });
    }

    $scope.slideHasChanged = function (index) {
      $ionicLoading.show({template: '加载中...'})
      loadMovieCinemasByDate($scope.showDates[index], $scope.orderBy, $scope.currDistinctId);
    }

    $scope.repeatDone = function () {
      $ionicLoading.show({template: '加载中...'})
      $ionicSlideBoxDelegate.update();
      loadMovieCinemasByDate($scope.showDates[$ionicSlideBoxDelegate.currentIndex()], $scope.orderBy, $scope.currDistinctId);
    };

    $scope.lastSlide = function () {
      $ionicSlideBoxDelegate.previous();
    };

    $scope.nextSlide = function () {
      $ionicSlideBoxDelegate.next();
    };

    function loadCityMovieCinemasWithDates() {
      $ionicLoading.show({template: '加载中...'})
      var promise = MovieCinemaService.getCityMovieCinemasWithDates($scope.currCityId, $stateParams.movieId);
      promise.then(function (data) {
          $scope.movieId = $stateParams.movieId;
          var jsonObject = angular.fromJson(data);
          if (jsonObject) {
            $scope.movieName = jsonObject['movieName'];
            $scope.showDates = jsonObject['showDates'];
          }
          else {
            $scope.showDates = null;
          }
          $ionicLoading.hide();
        }
        , function () {
          alert("无法获取影片影院信息");
          $ionicLoading.hide();
        })
    }
  })

  .controller('ScreeningCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, $stateParams, ScreeningService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadScreening();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadScreening();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    function loadScreening() {
      var promise = ScreeningService.getScreening($scope.currCityId, $stateParams.cinemaId, $stateParams.movieId, $stateParams.showDate);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.cinemaId = $stateParams.cinemaId;
          $scope.movieId = $stateParams.movieId;
          $scope.cinemaName = jsonObject['cinemaName'];
          $scope.movieName = jsonObject['movieName'];
          $scope.showDate = jsonObject['showDate'];
          $scope.Screenings = jsonObject['minSource'];
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取排片信息");
        });
    }
  })

  .controller('MovieCtrl', function ($scope, $http, $ionicLoading, $filter, ApiEndpoint, $stateParams, MovieService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadMovie();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadMovie();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    function loadMovie() {
      var promise = MovieService.getMovie($stateParams.movieId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.movie = jsonObject;
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取排片信息");
        });
    }
  })


  .controller('CinemaCtrl', function ($scope, $http, $ionicLoading, $ionicSlideBoxDelegate, $filter, ApiEndpoint, $stateParams, CinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadCinema();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadCinema();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    function loadCinema() {
      var promise = CinemaService.getCinema($stateParams.cinemaId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.cinema = jsonObject['cinema'];
          $scope.movies = jsonObject['showingMovies'];
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取影院详情信息");
        });
    }

    $scope.currMovieHasChanged = function (index) {
      $ionicLoading.show({template: '加载中...'})
      $scope.currMovie = $scope.movies[index];
      loadCinemaMovieDates($scope.cinema.id, $scope.currMovie.id);
    }

    $scope.repeatMovieDone = function () {
      $ionicLoading.show({template: '加载中...'})
      $ionicSlideBoxDelegate.$getByHandle("slideMovie").update();

      if ($scope.movies.length > 0) {
        var index = $ionicSlideBoxDelegate.$getByHandle("slideMovie").currentIndex();
        $scope.currMovie = $scope.movies[index];
        loadCinemaMovieDates($scope.cinema.id, $scope.currMovie.id);
      }
    };

    $scope.currDateHasChanged = function (index) {
      $ionicLoading.show({template: '加载中...'})
      if ($scope.showDates.length > 0) {
        $scope.currShowDate = $scope.showDates[index];
        loadCinemaMovieSourcesByDate($scope.cinema.id, $scope.currMovie.id, $scope.showDates[index]);
      }
    }

    $scope.repeatDateDone = function () {
      $ionicLoading.show({template: '加载中...'})
      $ionicSlideBoxDelegate.$getByHandle("slideDate").update();
      if ($scope.showDates.length > 0) {
        var index = $ionicSlideBoxDelegate.$getByHandle("slideDate").currentIndex();
        loadCinemaMovieSourcesByDate($scope.cinema.id, $scope.currMovie.id, $scope.showDates[index]);
      }
    };

    $scope.lastDateSlide = function () {
      $ionicSlideBoxDelegate.$getByHandle("slideDate").previous();
    };
    $scope.nextDateSlide = function () {
      $ionicSlideBoxDelegate.$getByHandle("slideDate").next();
    };

    function loadCinemaMovieDates(cinemaId, movieId) {
      var promise = CinemaService.getCinemaMovieDates(cinemaId, movieId);
      promise.then(function (data) {
          $scope.showDates = angular.fromJson(data);
          $ionicSlideBoxDelegate.$getByHandle("slideDate").slide(0);
          $ionicSlideBoxDelegate.$getByHandle("slideDate").update();
          $scope.currDateHasChanged(0);
          $ionicLoading.hide();
        }
        , function () {
          alert("无法获取影院影片日期信息");
          $ionicLoading.hide();
        })
    }

    function loadCinemaMovieSourcesByDate(cinemaId, movieId, date) {
      var promise = CinemaService.getCinemaMovieScreeningByDate(cinemaId, movieId, date);
      promise.then(function (data) {
          var jsonObject = angular.fromJson(data);
          $scope.currScreenings = jsonObject['minSource'];

          $ionicLoading.hide();
        }
        , function () {
          alert("无法获取该日影院影片信息");
          $ionicLoading.hide();
        })
    }
  })

  .controller('SourceCtrl', function ($scope, $http, $ionicLoading, $filter, ApiEndpoint, $stateParams, SourceService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadSource();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadSource();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }


    function loadSource() {
      var promise = SourceService.getSource($scope.currCityId, $stateParams.cinemaId,
        $stateParams.movieId, $stateParams.showDate, $stateParams.startTime);
      promise.then(function (val) {
          $scope.sources = angular.fromJson(val);
          $scope.showDate = $stateParams.showDate;
          $scope.startTime = $stateParams.startTime.substr(0, 5);
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取来源信息");
        });
    }
  })

  .controller('CitiesCtrl', function ($scope, $http, $location, $anchorScroll, $ionicLoading, ApiEndpoint, $stateParams, CitiesService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadCities();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadCities();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    $scope.goto = function (id) {
      $location.hash(id);
      $anchorScroll();
    }

    function loadCities() {
      var promise = CitiesService.getAllCities();
      promise.then(function (val) {
          $scope.cityLists = angular.fromJson(val);
          $ionicLoading.hide();
        }
        , function () {
          $ionicLoading.hide();
          alert("无法获取来源信息");
        });
    }
  })

  .filter('orderCinema', function () {
    return function (items, orderBy) {
      if (items) {
        for (var i = 0; i < items.length; i++) {
          for (var j = i; j < items.length; j++) {
            if (items[i][orderBy] > items[j][orderBy]) {
              var temp = items[i];
              items[i] = items[j];
              items[j] = temp;
            }
          }
        }
      }
      return items;
    }
  })


  .filter('filterDistinctCinema', function () {
    return function (items, distinctId) {
      if (items && distinctId) {
        var array = [];
        for (var i = 0; i < items.length; i++) {
          if (items[i].districtId == distinctId) {
            array.push(items[i]);
          }
        }
        return array;
      }
      return items;
    }
  })

  .filter('filterNameCinema', function () {
    return function (items, namelike) {
      if (items && namelike) {
        var array = [];
        for (var i = 0; i < items.length; i++) {
          if (items[i].name.indexOf(namelike) != -1 || namelike.indexOf(items[i].name) != -1
            || items[i].pinyin.indexOf(namelike) != -1) {
            array.push(items[i]);
          }
        }
        return array;
      }
      return items;
    }
  })
