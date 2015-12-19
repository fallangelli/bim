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
    } else {
      alert('asdf');
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        $scope.currLat = window.localStorage['curr_lat'];
        $scope.currLng = window.localStorage['curr_lng'];
        loadHotMovies();
      }, function () {
        alert("无法得到当前城市信息");
        $ionicLoading.hide();
      })
    }

    //if (window.localStorage['curr_lat'].length > 0 && window.localStorage['curr_lng'].length > 0) {
    //  $scope.currLat = window.localStorage['curr_lat'];
    //  $scope.currLng = window.localStorage['curr_lng'];
    //} else {
    //  var promiseCity = Geolocation.initCurrentPosition();
    //  promiseCity.then(function () {
    //    $scope.currLat = window.localStorage['curr_lat'];
    //    $scope.currLng = window.localStorage['curr_lng'];
    //  }, function () {
    //    alert("无法得到当前城市信息");
    //    $ionicLoading.hide();
    //  })
    //}

    function loadHotMovies() {
      var url = ApiEndpoint.server_url + "home/hotMovies?cityId=" + $scope.currCityId;
      $http.get(url)
        .success(function (data) {
          var movies = angular.fromJson(data)
          $scope.movieList = movies;
          $ionicLoading.hide();
        })
        .error(function (data, header, config, status) {
          alert("读取电影信息错误");
          $ionicLoading.hide();
        });
    }
  })


  .controller('CinemaCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, CinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadCinemas();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadCinemas();
      }, function () {
        alert("无法得到当前城市信息");
      })
    }

    function loadCinemas() {
      var promise = CinemaService.getCinemas($scope.currCityId);
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
  .controller('MovieCinemaCtrl', function ($scope, $timeout, $ionicSlideBoxDelegate, $stateParams, $http, $ionicLoading, ApiEndpoint, MovieCinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})
    var currTime = new Date();
    $timeout(function () {
      $ionicSlideBoxDelegate.slide(0);
      $ionicSlideBoxDelegate.update();
    });

    if (window.localStorage['curr_city_id'].length > 0 && window.localStorage['curr_city_name'].length > 0 &&
      window.localStorage['expiration_time'] && new Date(window.localStorage['expiration_time']) > currTime) {
      $scope.currCityId = window.localStorage['curr_city_id'];
      $scope.currCityName = window.localStorage['curr_city_name'];
      loadMovieCinemaDates();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = window.localStorage['curr_city_id'];
        $scope.currCityName = window.localStorage['curr_city_name'];
        loadMovieCinemaDates();
      }, function () {
        alert("无法得到当前位置");
      })
    }

    $scope.slideHasChanged = function (index) {
      $ionicLoading.show({template: '加载中...'})
      loadMovieCinemasByDate($scope.showDates[index]);
    }

    $scope.repeatDone = function () {
      $ionicLoading.show({template: '加载中...'})
      $ionicSlideBoxDelegate.update();
      loadMovieCinemasByDate($scope.showDates[$ionicSlideBoxDelegate.currentIndex()]);
    };

    $scope.lastSlide = function () {
      $ionicSlideBoxDelegate.previous();
    };
    $scope.nextSlide = function () {
      $ionicSlideBoxDelegate.next();
    };


    function loadMovieCinemaDates() {
      $ionicLoading.show({template: '加载中...'})
      var promise = MovieCinemaService.getMovieCinemaDates($scope.currCityId, $stateParams.movieId);
      promise.then(function (data) {
          var dates = angular.fromJson(data);
          $scope.showDates = dates;
          $ionicLoading.hide();
        }
        , function () {
          alert("无法获取影片影院信息");
          $ionicLoading.hide();
        })
    }

    function loadMovieCinemasByDate(date) {
      var promise = MovieCinemaService.getMovieCinemasByDate($scope.currCityId, $stateParams.movieId, date);
      promise.then(function (data) {
          var cinemas = angular.fromJson(data);
          $scope.cinemas = cinemas;
          $ionicLoading.hide();
        }
        , function () {
          alert("无法获取影片影院信息");
          $ionicLoading.hide();
        })
    }
  })
