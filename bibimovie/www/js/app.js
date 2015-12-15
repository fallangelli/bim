angular.module('bibimovie', ['ionic', 'bibimovie.services', 'bibimovie.controllers'])
  .constant('ApiEndpoint', {
    server_url: 'http://localhost:8100/server_url/bibimovie/'
    //server_url: 'http://localhost:8080/bibimovie/'
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

      .state('cinema', {
        url: '/cinema',
        templateUrl: 'templates/cinema.html',
        controller: 'CinemaCtrl'
      })

      .state('moivecinema', {
        url: '/moivecinema/:movieId',
        templateUrl: 'templates/moivecinema.html',
        controller: 'MovieCinemaCtrl'
      })
    $urlRouterProvider.otherwise('/');
  });


