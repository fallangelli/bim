angular.module('bibimovie.controllers', [])
  .controller('HomeCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, Geolocation) {

    $ionicLoading.show({template: '加载中...'});

    if (Geolocation.curr_city_id.length > 0 && Geolocation.curr_city_name.length > 0 &&
      Geolocation.curr_lat.length > 0 && Geolocation.curr_lng.length > 0) {
      $scope.currCityId = Geolocation.curr_city_id;
      $scope.currCityName = Geolocation.curr_city_name;
      $scope.currLat = Geolocation.curr_lat;
      $scope.currLng = Geolocation.curr_lng;
      loadHotMovies();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = Geolocation.curr_city_id;
        $scope.currCityName = Geolocation.curr_city_name;
        $scope.currLat = Geolocation.curr_lat;
        $scope.currLng = Geolocation.curr_lng;
        loadHotMovies();
      }, function () {
        alert("无法得到当前城市信息");
        $ionicLoading.hide();
      })
    }

    //if (Geolocation.curr_lat.length > 0 && Geolocation.curr_lng.length > 0) {
    //  $scope.currLat = Geolocation.curr_lat;
    //  $scope.currLng = Geolocation.curr_lng;
    //} else {
    //  var promiseCity = Geolocation.initCurrentPosition();
    //  promiseCity.then(function () {
    //    $scope.currLat = Geolocation.curr_lat;
    //    $scope.currLng = Geolocation.curr_lng;
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

    if (Geolocation.curr_city_id.length > 0 && Geolocation.curr_city_name.length > 0) {
      $scope.currCityId = Geolocation.curr_city_id;
      $scope.currCityName = Geolocation.curr_city_name;
      loadCinemas();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = Geolocation.curr_city_id;
        $scope.currCityName = Geolocation.curr_city_name;
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

  .controller('MovieCinemaCtrl', function ($scope, $stateParams, $http, $ionicLoading, ApiEndpoint, MovieCinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})

    if (Geolocation.curr_city_id.length > 0 && Geolocation.curr_city_name.length > 0) {
      $scope.currCityId = Geolocation.curr_city_id;
      $scope.currCityName = Geolocation.curr_city_name;
      loadMovieCinemas();
    } else {
      var promiseCity = Geolocation.initCurrentCity();
      promiseCity.then(function () {
        $scope.currCityId = Geolocation.curr_city_id;
        $scope.currCityName = Geolocation.curr_city_name;
        loadMovieCinemas();
      }, function () {
        alert("无法得到当前位置");
      })
    }
    function loadMovieCinemas() {
      var promise = MovieCinemaService.getMovieCinemas($scope.currCityId, $stateParams.movieId);
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
