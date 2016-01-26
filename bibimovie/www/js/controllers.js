angular.module('bibimovie.controllers', [])
  .config(function ($anchorScrollProvider) {
    $anchorScrollProvider.disableAutoScrolling();
  })

  .controller('HomeCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, Geolocation) {

    $scope.doRefresh = function () {
      $ionicLoading.show({template: '加载中...'});
      var currTime = new Date();
      if (window.localStorage['curr_city_id'] && window.localStorage['curr_city_name'] &&
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
        }, function (error) {
          alert("无法得到当前城市信息" + error);
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        })
      }
    }

    $scope.doRefresh();

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
          $scope.$broadcast('scroll.refreshComplete');
        });
    }

    function loadNearCinemas() {
      var url = ApiEndpoint.server_url + "home/getNearCinemas?cityId=" + $scope.currCityId;
      if ($scope.currLat && $scope.currLng) url += "&lat=" + $scope.currLat + "&lng=" + $scope.currLng;
      $http.get(url)
        .success(function (data) {
          var cinemas = angular.fromJson(data)
          $scope.nearCinemaList = cinemas;
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        })
        .error(function (data, header, config, status) {
          alert("读取附近影院信息错误");
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        });
    }
  })

  .controller('MovieListCtrl', function ($scope, $http, $location, $ionicHistory, $ionicLoading, ApiEndpoint, MovieListService, Geolocation) {
    $scope.doRefresh = function () {
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
    }
    $scope.doRefresh();

    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
    }

    function loadMovies() {
      var promise = MovieListService.getMovies($scope.currCityId);
      promise.then(function (val) {
          $scope.movies = val;
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取上映影片信息");
        });
    }


  })

  .controller('CinemaListCtrl', function ($scope, $http, $location, $ionicHistory, $ionicPopover, $ionicLoading, ApiEndpoint, CinemaListService, Geolocation) {

    $scope.orderBy = 'len';
    $scope.nameLike = '';
    $scope.currDistrictId = null;
    $scope.currDistrictName = '全部';

    $scope.cinemas = {
      hasMore: true,
      messages: [],
      pagination: {
        perPage: 10,
        currentPage: 1
      }
    };
    if ($scope.currLat && $scope.currLng)
      $scope.distanceOrderStyle = {color: 'red'};
    else
      $scope.priceOrderStyle = {color: 'red'};

    /* state:1初始化和刷新，2加载更多 */
    $scope.doRefresh = function (state) {
      $ionicLoading.show({template: '加载中...'})

      var currTime = new Date();
      if (window.localStorage['curr_city_id'] && window.localStorage['curr_city_name'] &&
        window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
          $scope.currCityId = window.localStorage['curr_city_id'];
          $scope.currCityName = window.localStorage['curr_city_name'];
          $scope.currLat = window.localStorage['curr_lat'];
          $scope.currLng = window.localStorage['curr_lng'];
        loadCinemas(state);
          loadAreas();
      } else {
        var promiseCity = Geolocation.initCurrentCity();
        promiseCity.then(function () {
          $scope.currCityId = window.localStorage['curr_city_id'];
          $scope.currCityName = window.localStorage['curr_city_name'];
          $scope.currLat = window.localStorage['curr_lat'];
          $scope.currLng = window.localStorage['curr_lng'];

          loadCinemas(state);
          loadAreas();
        }, function () {
          alert("无法得到当前城市信息");
        })
      }
      }


    $scope.doRefresh(1);

    $ionicPopover.fromTemplateUrl('my-popover.html', {
      scope: $scope
    }).then(function (popover) {
      $scope.popover = popover;
    })

    $scope.loadMore = function () {
      $scope.doRefresh(2);
    };

    $scope.openPopover = function ($event) {
      $scope.popover.show($event);
    };

    $scope.closePopover = function () {
      $scope.popover.hide();
    };


    $scope.filterByName = function (nameLike) {
      $ionicLoading.show({template: '加载中...'})
      $scope.nameLike = nameLike;
      $scope.doRefresh(1);
    }

    $scope.filterByDistrict = function (districtId, districtName) {
      $ionicLoading.show({template: '加载中...'})
      $scope.closePopover();
      $scope.currDistrictId = districtId;
      $scope.currDistrictName = districtName;
      if (districtId)
        $scope.districtFilterStyle = {color: 'red'};
      else
        $scope.districtFilterStyle = {color: 'blue'};
      $scope.doRefresh(1);
    }

    $scope.orderByDistance = function () {
      $ionicLoading.show({template: '加载中...'})
      $scope.orderBy = "len";
      $scope.distanceOrderStyle = {color: 'red'};
      $scope.priceOrderStyle = {color: 'blue'};
      $scope.doRefresh(1);
    }

    $scope.orderByPrice = function () {
      $ionicLoading.show({template: '加载中...'})
      $scope.orderBy = "minPrice";
      $scope.distanceOrderStyle = {color: 'blue'};
      $scope.priceOrderStyle = {color: 'red'};
      $scope.doRefresh(1);
    }

    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
    }

    function loadAreas() {
      var promise = CinemaListService.getCityInfo($scope.currCityId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.areas = jsonObject['areas'];
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取影院信息");
        });
    }

    function loadCinemas(state) {
      if (state == 1) {
        $scope.cinemas.pagination.currentPage = 1;
      }
      var promise = CinemaListService.getCinemas($scope.currCityId, $scope.currLat, $scope.currLng,
        $scope.orderBy, $scope.currDistrictId, $scope.nameLike, $scope.cinemas.pagination.currentPage,
        $scope.cinemas.pagination.perPage);
      promise.then(function (val) {
          //加载更多
          if (state == 2) {
            $scope.cinemas.messages = $scope.cinemas.messages.concat(val);
            if (val == null || val.length == 0) {
              $scope.cinemas.hasMore = false;
            }
            else {
              $scope.cinemas.pagination.currentPage += 1;
            }
          }
          //初始化和刷新
          else {
            $scope.cinemas.pagination.currentPage = 1;
            //if (val == null || val.length == 0) {
            //  $scope.cinemas.hasMore = false;
            //}
            $scope.cinemas.messages = val;

          }
          $scope.$broadcast('scroll.infiniteScrollComplete');
          $scope.$broadcast('scroll.refreshComplete');
          $ionicLoading.hide();
        }
        , function () {
          $scope.$broadcast('scroll.infiniteScrollComplete');
          $scope.$broadcast('scroll.refreshComplete');
          $ionicLoading.hide();
          alert("无法获取影院信息");
        });
      }
    }
  )

  .controller('MovieCinemaCtrl', function ($scope, $timeout, $location, $ionicHistory, $ionicSlideBoxDelegate, $ionicPopover, $stateParams, $http, $ionicLoading, ApiEndpoint, MovieCinemaService, Geolocation) {
    $scope.doRefresh = function () {
      $ionicLoading.show({template: '加载中...'})

      $scope.nameLike = '';
      $scope.orderBy = 'len';
      $scope.currDistrictId = null;
      $scope.currDistrictName = '全部';
      $scope.distanceOrderStyle = {color: 'red'};

      var currTime = new Date();

      if (window.localStorage['curr_city_id'] && window.localStorage['curr_city_name'] &&
        window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        $scope.currLat = window.localStorage['curr_lat'];
        $scope.currLng = window.localStorage['curr_lng'];
        loadMovieCinemaDates();
        loadAreas();
      } else {
        var promiseCity = Geolocation.initCurrentCity();
        promiseCity.then(function () {
          $scope.currCityId = window.localStorage['curr_city_id'];
          $scope.currCityName = window.localStorage['curr_city_name'];
          $scope.currLat = window.localStorage['curr_lat'];
          $scope.currLng = window.localStorage['curr_lng'];
          loadMovieCinemaDates();
          loadAreas();
        }, function () {
          alert("无法得到当前位置");
        })
      }
    }
    $scope.doRefresh();
    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
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

    $scope.filterByDistrict = function (districtId, districtName) {
      $scope.closePopover();
      $scope.currDistrictId = districtId;
      $scope.currDistrictName = districtName;
      if (districtId)
        $scope.districtFilterStyle = {color: 'red'};
      else
        $scope.districtFilterStyle = {color: 'blue'};
      loadMovieCinemaDates($scope.currDistrictId);
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

    $scope.slideHasChanged = function (index) {
      $ionicLoading.show({template: '加载中...'})
      loadMovieCinemasByDate($scope.showDates[index], $scope.orderBy, $scope.currDistrictId);
    }

    $scope.repeatDone = function () {
      $ionicLoading.show({template: '加载中...'})
      $ionicSlideBoxDelegate.update();
      loadMovieCinemasByDate($scope.showDates[$ionicSlideBoxDelegate.currentIndex()], $scope.orderBy, $scope.currDistrictId);
    };

    $scope.lastSlide = function () {
      $ionicSlideBoxDelegate.previous();
    };

    $scope.nextSlide = function () {
      $ionicSlideBoxDelegate.next();
    };


    function loadMovieCinemaDates(districtId) {
      $ionicLoading.show({template: '加载中...'})
      var promise = MovieCinemaService.getMovieCinemaDates($scope.currCityId, $stateParams.movieId, districtId);
      promise.then(function (data) {
          $scope.movieId = $stateParams.movieId;
          var jsonObject = angular.fromJson(data);
          if (jsonObject) {
            $scope.movieName = jsonObject['movieName'];
            $scope.showDates = jsonObject['showDates'];
            $ionicSlideBoxDelegate.slide(0);
            $ionicSlideBoxDelegate.update();
            if ($scope.showDates)
              loadMovieCinemasByDate($scope.showDates[0]);
          }
          else {
            $scope.showDates = null;
          }
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          alert("无法获取影片影院信息");
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        })
    }

    function loadMovieCinemasByDate(date) {
      if (!date)
        $scope.cinemas
      var promise = MovieCinemaService.getMovieCinemasByDate($scope.currCityId, $stateParams.movieId, date,
        $scope.currLat, $scope.currLng);
      promise.then(function (data) {
          var cinemas = angular.fromJson(data);
          $scope.cinemas = cinemas;
          $scope.showDate = date;
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          alert("无法获取影片影院信息");
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        })
    }

    function loadAreas() {
      var promise = MovieCinemaService.getCityInfo($scope.currCityId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.areas = jsonObject['areas'];
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取影院信息");
        });
    }

  })


  .controller('ScreeningCtrl', function ($scope, $http, $ionicLoading, $ionicHistory, $location, ApiEndpoint, $stateParams, ScreeningService, Geolocation) {
    $scope.doRefresh = function () {
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
    }

    $scope.doRefresh();

    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
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
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取排片信息");
        });
    }
  })

  .controller('MovieCtrl', function ($scope, $http, $ionicLoading, $filter, $ionicHistory, $location, ApiEndpoint, $stateParams, MovieService, Geolocation) {
    $scope.doRefresh = function () {
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
    }

    $scope.doRefresh();

    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
    }
    function loadMovie() {
      var promise = MovieService.getMovie($stateParams.movieId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.movie = jsonObject;
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取排片信息");
        });
    }
  })


  .controller('CinemaCtrl', function ($scope, $http, $ionicLoading, $location, $ionicHistory, $ionicSlideBoxDelegate, $filter, ApiEndpoint, $stateParams, CinemaService, Geolocation) {
    $scope.doRefresh = function () {
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
    }

    $scope.doRefresh();

    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
    }

    function loadCinema() {
      var promise = CinemaService.getCinema($stateParams.cinemaId);
      promise.then(function (val) {
          var jsonObject = angular.fromJson(val);
          $scope.cinema = jsonObject['cinema'];
          $scope.movies = jsonObject['showingMovies'];
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
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
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          alert("无法获取影院影片日期信息");
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        })
    }

    function loadCinemaMovieSourcesByDate(cinemaId, movieId, date) {
      var promise = CinemaService.getCinemaMovieScreeningByDate(cinemaId, movieId, date);
      promise.then(function (data) {
          if (!data) {
            $scope.currScreenings = null;
            $ionicLoading.hide();
            $scope.$broadcast('scroll.refreshComplete');
            return;
          }

          var jsonObject = angular.fromJson(data);
          $scope.currScreenings = jsonObject['minSource'];

          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          alert("无法获取该日影院影片信息");
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        })
    }
  })

  .controller('SourceCtrl', function ($scope, $http, $ionicLoading, $ionicHistory, $location, $filter, ApiEndpoint, $stateParams, SourceService, Geolocation) {
    $scope.doRefresh = function () {
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
    }

    $scope.doRefresh();

    $scope.goBack = function () {
      var history = $ionicHistory.viewHistory();
      if (history.backView)
        history.backView.go();
      $location.path("/");
    }

    function loadSource() {
      var promise = SourceService.getSource($scope.currCityId, $stateParams.cinemaId,
        $stateParams.movieId, $stateParams.showDate, $stateParams.startTime);
      promise.then(function (val) {
          $scope.sources = angular.fromJson(val);
          $scope.showDate = $stateParams.showDate;
          $scope.startTime = $stateParams.startTime.substr(0, 5);
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取来源信息");
        });
    }
  })

  .controller('CitiesCtrl', function ($scope, $http, $location, $ionicScrollDelegate, $ionicLoading, ApiEndpoint, $stateParams, CitiesService, Geolocation) {
    $scope.oriPath = $stateParams.oriPath;

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
    $scope.letterGroupA = ['A', 'B', 'C', 'D', 'E', 'F'];
    $scope.letterGroupB = ['G', 'H', 'J', 'K', 'L', 'M'];
    $scope.letterGroupC = ['N', 'P', 'Q', 'R', 'S', 'T'];
    $scope.letterGroupD = ['W', 'X', 'Y', 'Z'];

    $scope.gotoElement = function (eID) {
      var stopY = CitiesService.elmYPosition(eID)
      $ionicScrollDelegate.scrollTo(0, stopY - 40);
    };

    $scope.goto = function (id) {
      $location.hash(id);
      $anchorScroll();
    }
    $scope.goTop = function (id) {
      $ionicScrollDelegate.scrollTop();
    }


    $scope.switchCity = function (id, name) {
      window.localStorage['curr_city_id'] = id;
      window.localStorage['curr_city_name'] = name;
      var expirationTime = new Date();
      expirationTime.setMinutes(expirationTime.getMinutes() + 20, expirationTime.getSeconds(), 0);
      window.localStorage['expiration_time'] = expirationTime;
      $location.path($scope.oriPath);
      location.reload();
    }

    function loadCities() {
      var promise = CitiesService.getAllCities();
      promise.then(function (val) {
          $scope.cityLists = angular.fromJson(val);
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
        }
        , function () {
          $ionicLoading.hide();
          $scope.$broadcast('scroll.refreshComplete');
          alert("无法获取来源信息");
        });
    }
  })

  .filter('orderCinema', function () {
    return function (items, orderBy) {
      if (items) {
        for (var i = 0; i < items.length; i++) {
          for (var j = i; j < items.length; j++) {

            if (items[i][orderBy] > items[j][orderBy]
              && null != items[j][orderBy]) {
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


  .filter('filterDistrictCinema', function () {
    return function (items, districtId) {
      if (items && districtId) {
        var array = [];
        for (var i = 0; i < items.length; i++) {
          if (items[i].districtId == districtId) {
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

  .filter('orderCities', function () {
    return function (items, orderBy) {
      if (items) {
        var tmpItem = {};
        tmpItem['热门'] = items['热门'];
        angular.forEach(items, function (value, key) {
          if (!value.hasOwnProperty('热门')) {
            tmpItem[key] = value;
          }
        });
        return tmpItem;
      }
      return items;
    }
  })
