angular.module('bibimovie', ['ionic', 'bibimovie.services', 'bibimovie.controllers'])
  .constant('ApiEndpoint', {
    server_url: 'http://192.168.2.126:8100/server_url/bibimovie/'
    //server_url: 'http://192.168.2.111:18888/bibimovie/'
    //server_url: 'http://fallangelli.imwork.net:18888/bibimovie/'
  })

  .run(function ($ionicPlatform) {
    $ionicPlatform.ready(function () {
      if (window.cordova && window.cordova.plugins.Keyboard) {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs)
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

        // Don't remove this line unless you know what you are doing. It stops the viewport
        // from snapping when text inputs are focused. Ionic handles this internally for
        // a much nicer keyboard experience.
        cordova.plugins.Keyboard.disableScroll(true);
      }
      if (window.StatusBar) {
        StatusBar.styleDefault();
      }
    });
  })

  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'templates/home.html',
        controller: 'HomeCtrl'
      })

      .state('movie', {
        url: '/movie/:movieId',
        templateUrl: 'templates/movie.html',
        controller: 'MovieCtrl'
      })

      .state('movielist', {
        url: '/movielist',
        templateUrl: 'templates/movielist.html',
        controller: 'MovieListCtrl'
      })

      .state('cinema', {
        url: '/cinema/:cinemaId',
        templateUrl: 'templates/cinema.html',
        controller: 'CinemaCtrl'
      })

      .state('cinemalist', {
        url: '/cinemalist',
        templateUrl: 'templates/cinemalist.html',
        controller: 'CinemaListCtrl'
      })

      .state('moivecinema', {
        url: '/moivecinema/:movieId',
        templateUrl: 'templates/moivecinema.html',
        controller: 'MovieCinemaCtrl'
      })

      .state('screening', {
        url: '/screening/:cinemaId/:movieId/:showDate',
        templateUrl: 'templates/screening.html',
        controller: 'ScreeningCtrl'
      })

      .state('source', {
        url: '/source/:cinemaId/:movieId/:showDate/:startTime',
        templateUrl: 'templates/source.html',
        controller: 'SourceCtrl'
      })

      .state('cities', {
        url: '/cities/:oriPath',
        templateUrl: 'templates/cities.html',
        controller: 'CitiesCtrl'
      })
    $urlRouterProvider.otherwise('/');
  });


