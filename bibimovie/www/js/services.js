angular.module('bibimovie.services', [])
  .provider('Geolocation', function () {

    this.$get = function ($http, $ionicLoading, ApiEndpoint) {
      var service = {
        GeoData: {},
        getCurrentPosition: function () {
          var pos = window.localStorage['curr_pos'];
          var geolocation = new BMap.Geolocation();
          geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
              var mk = new BMap.Marker(r.point);
              service.GeoData['lat'] = r.point.lat;
              service.GeoData['lng'] = r.point.lng;
            }
            else {
              alert('failed' + this.getStatus());
            }
          }, {enableHighAccuracy: false})
        },
        getCurrentCity: function () {
          var myCity = new BMap.LocalCity();
          myCity.get(myFun);

          function myFun(result) {
            var cityName = result.name;
            service.GeoData['cityName'] = cityName;
            var url = ApiEndpoint.server_url + "home/getCityIdFromName?cityName=" + cityName;
            $http.get(url)
              .success(function (data) {
                service.GeoData['cityId'] = data;
                alert(service.GeoData['cityName']);
              })
          }

        },
        showLoading: function () {
          $ionicLoading.show({
            template: 'Loading...'
          })
        },
        hideLoading: function () {
          $ionicLoading.hide();
        }

      };
      return service;


      //var self = this;
      //var service = {
      //  user: {},
      //  setName: function(newName) {
      //    service.user['name'] = newName;
      //  },
      //  setEmail: function(newEmail) {
      //    service.user['email'] = newEmail;
      //  },
      //  save: function() {
      //    return $http.post(self.backendUrl + '/users', {
      //      user: service.user
      //    })
      //  }
      //};
      //return service;
    }
  });


//.provider('Geolocation', ['$http', 'ApiEndpoint', function ($http, ApiEndpoint) {
//

//
//  this.$get = function () {
//    return {movies: pos};
//  };
//}])

//.factory('HomeService', ['Geolocation', function () {
//return {
//      getCurrentPosition: function () {
//        var geolocation = new BMap.Geolocation();
//        geolocation.getCurrentPosition(function (r) {
//          if (this.getStatus() == BMAP_STATUS_SUCCESS) {
//            var mk = new BMap.Marker(r.point);
//            alert('您的位置：');
//          }
//          else {
//            alert('failed' + this.getStatus());
//          }
//        }, {enableHighAccuracy: true})
//      },
//
//      getCurrentCity: function () {
//
//        function myFun(result) {
//
//
//          var cityName = result.name;
//          var url = ApiEndpoint.server_url + "home/getCityIdFromName?cityName=" + cityName;
//          $http.get(url)
//            .success(function (data) {
//              //window.localStorage['curr_pos']
//              alert(data);
//            })
//
//        }
//
//        var myCity = new BMap.LocalCity();
//        myCity.get(myFun);
//      }
//    }
//  }])
