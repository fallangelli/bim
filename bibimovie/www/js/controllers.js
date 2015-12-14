angular.module('bibimovie.controllers', [])

  .controller('HomeCtrl', function ($scope, $http, ApiEndpoint, HomeService) {
    var url = ApiEndpoint.server_url + "home/hotMovies?cityId=1";
    $http.get(url)
      .success(function (data) {
        var movies = angular.fromJson(data)
        $scope.movieList = movies;
      })
  })

  .controller('CinemaCtrl', function ($scope, $http, ApiEndpoint, HomeService) {
    var url = ApiEndpoint.server_url + "cityCinemas/Cinemas?cityId=1";
    $http.get(url)
      .success(function (data) {
        var cinemas = angular.fromJson(data)
        $scope.cinemas = cinemas;
      })

    $scope.getItemHeight = function (item, index) {
      //使索引项平均都有10px高，例如
      return (index % 2) === 0 ? 80 : 90;
    };
  })

  .controller('MovieCinemaCtrl', function ($scope, $stateParams, $http, $ionicLoading, ApiEndpoint, HomeService) {
    $scope.showLoading = function () {
      $ionicLoading.show({
        template: 'Loading...'
      });
    };
    $scope.hideLoading = function () {
      $ionicLoading.hide();
    };

    $scope.showLoading();


    var dates_url = ApiEndpoint.server_url + "cityScreening/MovieCinemaShowDates?" +
      "cityId=1&movieId=" + $stateParams.movieId + "&cinemaId=933";

    $http.get(dates_url)
      .success(function (data) {
        var dates = angular.fromJson(data);

        $scope.dates = dates;
        $scope.currDate = dates[0];
        var cinema_url = ApiEndpoint.server_url + "cityCinemas/DateMovieCinemas?" +
          "cityId=1&movieId=" + $stateParams.movieId + "&showDate=" + dates[0];
        $http.get(cinema_url)
          .success(function (data) {
            var cinemas = angular.fromJson(data);
            $scope.cinemas = cinemas;
            $scope.hideLoading();
          })
          .error(function (data, status, headers, config) {
            $scope.hideLoading();
            console.log(status);
          });
      })
      .error(function (data, status, headers, config) {
        $scope.hideLoading();
        console.log(status);
      });


  })
