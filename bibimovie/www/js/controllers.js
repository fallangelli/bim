angular.module('bibimovie.controllers', [])
  .controller('HomeCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, Geolocation) {
    $scope.currCityId = -1;
    $ionicLoading.show({template: '加载中...'})

    var promiseLL = Geolocation.getCurrentPosition();
    promiseLL.then(function () {  // 调用承诺API获取数据 .resolve
        $scope.currLat = Geolocation.currLat['lat'];
        $scope.currLng = Geolocation.currLng['lng'];
        alert('读取附近的影院')
      }
      , function () {  // 处理错误 .reject
        $scope.currCityId = 1;
        $scope.currCityName = '1';
        alert("无法获取坐标信息");
      });

    var promiseCity = Geolocation.getCurrentCity();
    promiseCity.then(function () {  // 调用承诺API获取数据 .resolve
        $scope.currCityId = Geolocation.currCityId;
        $scope.currCityName = Geolocation.currCityName;
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
      }, function () {  // 处理错误 .reject
        $scope.currCityId = 1;
        $scope.currCityName = '1';
        alert("无法定位当前城市");
        $ionicLoading.hide();
      }
    )
  })


  .controller('CinemaCtrl', function ($scope, $http, $ionicLoading, ApiEndpoint, CinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})

    $scope.currCityId = Geolocation.getCurrentCityId();
    $scope.currCityName = Geolocation.currCityName;
    var promise = CinemaService.getCinemas($scope.currCityId);
    promise.then(function (val) {
        $scope.cinemas = val;
        $ionicLoading.hide();
      }
      , function () {
        $ionicLoading.hide();
        alert("无法获取影院信息");
      });


    $scope.getItemHeight = function (item, index) {
      //使索引项平均都有10px高，例如
      return (index % 2) === 0 ? 80 : 90;
    };
  })

  .controller('MovieCinemaCtrl', function ($scope, $stateParams, $http, $ionicLoading, ApiEndpoint, MovieCinemaService, Geolocation) {
    $ionicLoading.show({template: '加载中...'})

    $scope.currCityId = Geolocation.getCurrentCityId();
    $scope.currCityName = Geolocation.currCityName;

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
  })
