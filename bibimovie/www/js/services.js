angular.module('bibimovie.services', [])

  .provider('HotMovie', function () {

    //三个一组，两组
    var movies = [
      [{title: '万万没想到', img: 'http://pic.spider.com.cn/pic/filmpic/jdt/1449130087843_mobile.jpg', url: '#'},
        {title: '极盗者', img: 'http://pic.spider.com.cn/pic/filmpic/jdt/1435113797007_mobile.jpg', url: '#'},
        {title: '师父', img: 'http://pic.spider.com.cn/pic/filmpic/jdt/1449474976308_mobile.jpg', url: '#'}],
      [{title: '火星救援', img: 'http://pic.spider.com.cn/pic/filmpic/jdt/1448003480928_mobile.jpg', url: '#'},
        {title: '极速风流', img: 'http://pic.spider.com.cn/pic/filmpic/jdt/1432794245402_mobile.jpg', url: '#'},
        {title: '怦然星动', img: 'http://pic.spider.com.cn/pic/filmpic/jdt/1441102325568_mobile.jpg', url: '#'}]
    ];
    this.$get = function () {
      return {movies: movies};
    };
  })

  .factory('HomeService', ['$http', 'ApiEndpoint', 'HotMovie', function ($http, ApiEndpoint, HotMovie) {


    return {
      findAll: function () {


        return HotMovie.movies;
      }
    }
  }])
