define("../src/common/event", ["require"], function (require) {
  function t(t, e, i) {
    (t || "").split(a).forEach(function (t) {
      i(t, e)
    })
  }

  function e(t) {
    return new RegExp("(?:^| )" + t.replace(" ", " .* ?") + "(?: |$)")
  }

  function i(t) {
    var e = ("" + t).split(".");
    return {e: e[0], ns: e.slice(1).sort().join(" ")}
  }

  function n(t, n, r, o) {
    var s, a;
    return a = i(n), a.ns && (s = e(a.ns)), t.filter(function (t) {
      return t && (!a.e || t.e === a.e) && (!a.ns || s.test(t.ns)) && (!r || t.cb === r || t.cb._cb === r) && (!o || t.ctx === o)
    })
  }

  function r(t, e) {
    var i;
    for (i in e)if (e.hasOwnProperty(i))if ("object" == typeof e[i])t[i] = {}, t[i] = r(t[i], e[i]); else t[i] = e[i];
    return t
  }

  function o(t, e) {
    if (!(this instanceof o))return new o(t, e); else return e && r(this, e), this.type = t, this
  }

  var s = [].slice, a = /\s+/, l = function () {
    return !1
  }, c = function () {
    return !0
  };
  o.prototype = {
    isDefaultPrevented: l, isPropagationStopped: l, preventDefault: function () {
      this.isDefaultPrevented = c
    }, stopPropagation: function () {
      this.isPropagationStopped = c
    }
  };
  var h = {
    on: function (e, n, r) {
      var o, s = this;
      if (!n)return this; else return o = this._events || (this._events = []), t(e, n, function (t, e) {
        var n = i(t);
        n.cb = e, n.ctx = r, n.ctx2 = r || s, n.id = o.length, o.push(n)
      }), this
    }, one: function (e, i, n) {
      var r = this;
      if (!i)return this; else return t(e, i, function (t, e) {
        var i = function () {
          return r.off(t, i), e.apply(n || r, arguments)
        };
        i._cb = e, r.on(t, i, n)
      }), this
    }, off: function (e, i, r) {
      var o = this._events;
      if (!o)return this;
      if (!e && !i && !r)return this._events = [], this; else return t(e, i, function (t, e) {
        n(o, t, e, r).forEach(function (t) {
          delete o[t.id]
        })
      }), this
    }, trigger: function (t) {
      var e, i, r, a, l, c = -1;
      if (!this._events || !t)return this;
      if ("string" == typeof t && (t = new o(t)), e = s.call(arguments, 1), t.args = e, e.unshift(t), i = n(this._events, t.type))for (a = i.length; ++c < a;)if ((r = t.isPropagationStopped()) || !1 === (l = i[c]).cb.apply(l.ctx2, e)) {
        r || (t.stopPropagation(), t.preventDefault());
        break
      }
      return this
    }, Event: o
  };
  return h
}), define("slider", ["require", "../src/common/event"], function (require) {
  $.flexslider = function (t, e) {
    var i = $(t);
    i.vars = $.extend({}, $.flexslider.defaults, e);
    var n, r = i.vars.namespace, o = window.navigator && window.navigator.msPointerEnabled && window.MSGesture, s = ("ontouchstart" in window || o || window.DocumentTouch && document instanceof DocumentTouch) && i.vars.touch, a = "click touchend MSPointerUp", l = "", c = "vertical" === i.vars.direction, h = i.vars.reverse, u = i.vars.itemWidth > 0, d = "fade" === i.vars.animation, p = "" !== i.vars.asNavFor, f = {}, m = !0;
    $(t).data("flexslider", i), f = {
      init: function () {
        if (i.animating = !1, i.currentSlide = parseInt(i.vars.startAt ? i.vars.startAt : 0, 10), isNaN(i.currentSlide))i.currentSlide = 0;
        if (i.animatingTo = i.currentSlide, i.atEnd = 0 === i.currentSlide || i.currentSlide === i.last, i.containerSelector = i.vars.selector.substr(0, i.vars.selector.search(" ")), i.slides = $(i.vars.selector, i), i.container = $(i.containerSelector, i), i.count = i.slides.length, i.syncExists = $(i.vars.sync).length > 0, "slide" === i.vars.animation)i.vars.animation = "swing";
        if (i.prop = c ? "top" : "marginLeft", i.args = {}, i.manualPause = !1, i.stopped = !1, i.started = !1, i.startTimeout = null, i.transitions = !i.vars.video && !d && i.vars.useCSS && function () {
              var t = document.createElement("div"), e = ["perspectiveProperty", "WebkitPerspective", "MozPerspective", "OPerspective", "msPerspective"];
              for (var n in e)if (void 0 !== t.style[e[n]])return i.pfx = e[n].replace("Perspective", "").toLowerCase(), i.prop = "-" + i.pfx + "-transform", !0;
              return !1
            }(), "" !== i.vars.controlsContainer)i.controlsContainer = $(i.vars.controlsContainer).length > 0 && $(i.vars.controlsContainer);
        if ("" !== i.vars.manualControls)i.manualControls = $(i.vars.manualControls).length > 0 && $(i.vars.manualControls);
        if (i.vars.randomize)i.slides.sort(function () {
          return Math.round(Math.random()) - .5
        }), i.container.empty().append(i.slides);
        if (i.doMath(), i.setup("init"), i.vars.controlNav)f.controlNav.setup();
        if (i.vars.directionNav)f.directionNav.setup();
        if (i.vars.keyboard && (1 === $(i.containerSelector).length || i.vars.multipleKeyboard))$(document).bind("keyup", function (t) {
          var e = t.keyCode;
          if (!i.animating && (39 === e || 37 === e)) {
            var n = 39 === e ? i.getTarget("next") : 37 === e ? i.getTarget("prev") : !1;
            i.flexAnimate(n, i.vars.pauseOnAction)
          }
        });
        if (i.vars.mousewheel)i.bind("mousewheel", function (t, e, n, r) {
          t.preventDefault();
          var o = 0 > e ? i.getTarget("next") : i.getTarget("prev");
          i.flexAnimate(o, i.vars.pauseOnAction)
        });
        if (i.vars.pausePlay)f.pausePlay.setup();
        if (i.vars.slideshow && i.vars.pauseInvisible)f.pauseInvisible.init();
        if (i.vars.slideshow) {
          if (i.vars.pauseOnHover)i.hover(function () {
            if (!i.manualPlay && !i.manualPause)i.pause()
          }, function () {
            if (!i.manualPause && !i.manualPlay && !i.stopped)i.play()
          });
          if (!i.vars.pauseInvisible || !f.pauseInvisible.isHidden())i.vars.initDelay > 0 ? i.startTimeout = setTimeout(i.play, i.vars.initDelay) : i.play()
        }
        if (p)f.asNav.setup();
        if (s && i.vars.touch)f.touch();
        if (!d || d && i.vars.smoothHeight)$(window).bind("resize orientationchange focus", f.resize);
        i.find("img").attr("draggable", "false"), setTimeout(function () {
          i.vars.start(i)
        }, 200)
      }, asNav: {
        setup: function () {
          if (i.asNav = !0, i.animatingTo = Math.floor(i.currentSlide / i.move), i.currentItem = i.currentSlide, i.slides.removeClass(r + "active-slide").eq(i.currentItem).addClass(r + "active-slide"), !o)i.slides.on(a, function (t) {
            t.preventDefault();
            var e = $(this), n = e.index(), o = e.offset().left - $(i).scrollLeft();
            if (0 >= o && e.hasClass(r + "active-slide"))i.flexAnimate(i.getTarget("prev"), !0); else if (!$(i.vars.asNavFor).data("flexslider").animating && !e.hasClass(r + "active-slide"))i.direction = i.currentItem < n ? "next" : "prev", i.flexAnimate(n, i.vars.pauseOnAction, !1, !0, !0)
          }); else t._slider = i, i.slides.each(function () {
            var t = this;
            t._gesture = new MSGesture, t._gesture.target = t, t.addEventListener("MSPointerDown", function (t) {
              if (t.preventDefault(), t.currentTarget._gesture)t.currentTarget._gesture.addPointer(t.pointerId)
            }, !1), t.addEventListener("MSGestureTap", function (t) {
              t.preventDefault();
              var e = $(this), n = e.index();
              if (!$(i.vars.asNavFor).data("flexslider").animating && !e.hasClass("active"))i.direction = i.currentItem < n ? "next" : "prev", i.flexAnimate(n, i.vars.pauseOnAction, !1, !0, !0)
            })
          })
        }
      }, controlNav: {
        setup: function () {
          if (!i.manualControls)f.controlNav.setupPaging(); else f.controlNav.setupManual()
        }, setupPaging: function () {
          var t, e, n = "thumbnails" === i.vars.controlNav ? "control-thumbs" : "control-paging", o = 1;
          if (i.controlNavScaffold = $('<ol class="' + r + "control-nav " + r + n + '"></ol>'), i.pagingCount > 1)for (var s = 0; s < i.pagingCount; s++) {
            if (e = i.slides.eq(s), t = "thumbnails" === i.vars.controlNav ? '<img src="' + e.attr("data-thumb") + '"/>' : "<a>" + o + "</a>", "thumbnails" === i.vars.controlNav && !0 === i.vars.thumbCaptions) {
              var a = e.attr("data-thumbcaption");
              if ("" != a && void 0 != a)t += '<span class="' + r + 'caption">' + a + "</span>"
            }
            i.controlNavScaffold.append("<li>" + t + "</li>"), o++
          }
          i.controlsContainer ? $(i.controlsContainer).append(i.controlNavScaffold) : i.append(i.controlNavScaffold), f.controlNav.set(), f.controlNav.active()
        }, setupManual: function () {
          i.controlNav = i.manualControls, f.controlNav.active(), i.controlNav.bind(a, function (t) {
            if (t.preventDefault(), "" === l || l === t.type) {
              var e = $(this), n = i.controlNav.index(e);
              if (!e.hasClass(r + "active"))n > i.currentSlide ? i.direction = "next" : i.direction = "prev", i.flexAnimate(n, i.vars.pauseOnAction)
            }
            if ("" === l)l = t.type;
            f.setToClearWatchedEvent()
          })
        }, set: function () {
          var t = "thumbnails" === i.vars.controlNav ? "img" : "a";
          i.controlNav = $("." + r + "control-nav li " + t, i.controlsContainer ? i.controlsContainer : i)
        }, active: function () {
          i.controlNav.removeClass(r + "active").eq(i.animatingTo).addClass(r + "active")
        }, update: function (t, e) {
          if (i.pagingCount > 1 && "add" === t)i.controlNavScaffold.append($("<li><a>" + i.count + "</a></li>")); else if (1 === i.pagingCount)i.controlNavScaffold.find("li").remove(); else i.controlNav.eq(e).closest("li").remove();
          f.controlNav.set(), i.pagingCount > 1 && i.pagingCount !== i.controlNav.length ? i.update(e, t) : f.controlNav.active()
        }
      }, directionNav: {
        setup: function () {
          var t = $('<ul class="' + r + 'direction-nav"><li><a class="' + r + 'prev" href="#">' + i.vars.prevText + '</a></li><li><a class="' + r + 'next" href="#">' + i.vars.nextText + "</a></li></ul>");
          if (i.controlsContainer)$(i.controlsContainer).append(t), i.directionNav = $("." + r + "direction-nav li a", i.controlsContainer); else i.append(t), i.directionNav = $("." + r + "direction-nav li a", i);
          f.directionNav.update(), i.directionNav.bind(a, function (t) {
            t.preventDefault();
            var e;
            if ("" === l || l === t.type)e = $(this).hasClass(r + "next") ? i.getTarget("next") : i.getTarget("prev"), i.flexAnimate(e, i.vars.pauseOnAction);
            if ("" === l)l = t.type;
            f.setToClearWatchedEvent()
          })
        }, update: function () {
          var t = r + "disabled";
          if (1 === i.pagingCount)i.directionNav.addClass(t).attr("tabindex", "-1"); else if (!i.vars.animationLoop)if (0 === i.animatingTo)i.directionNav.removeClass(t).filter("." + r + "prev").addClass(t).attr("tabindex", "-1"); else if (i.animatingTo === i.last)i.directionNav.removeClass(t).filter("." + r + "next").addClass(t).attr("tabindex", "-1"); else i.directionNav.removeClass(t).removeAttr("tabindex"); else i.directionNav.removeClass(t).removeAttr("tabindex")
        }
      }, pausePlay: {
        setup: function () {
          var t = $('<div class="' + r + 'pauseplay"><a></a></div>');
          if (i.controlsContainer)i.controlsContainer.append(t), i.pausePlay = $("." + r + "pauseplay a", i.controlsContainer); else i.append(t), i.pausePlay = $("." + r + "pauseplay a", i);
          f.pausePlay.update(i.vars.slideshow ? r + "pause" : r + "play"), i.pausePlay.bind(a, function (t) {
            if (t.preventDefault(), "" === l || l === t.type)if ($(this).hasClass(r + "pause"))i.manualPause = !0, i.manualPlay = !1, i.pause(); else i.manualPause = !1, i.manualPlay = !0, i.play();
            if ("" === l)l = t.type;
            f.setToClearWatchedEvent()
          })
        }, update: function (t) {
          "play" === t ? i.pausePlay.removeClass(r + "pause").addClass(r + "play").html(i.vars.playText) : i.pausePlay.removeClass(r + "play").addClass(r + "pause").html(i.vars.pauseText)
        }
      }, touch: function () {
        function e(e) {
          if (i.animating)e.preventDefault(); else if (window.navigator.msPointerEnabled || 1 === e.touches.length)i.pause(), g = c ? i.h : i.w, y = Number(new Date), w = e.touches[0].pageX, b = e.touches[0].pageY, m = u && h && i.animatingTo === i.last ? 0 : u && h ? i.limit - (i.itemW + i.vars.itemMargin) * i.move * i.animatingTo : u && i.currentSlide === i.last ? i.limit : u ? (i.itemW + i.vars.itemMargin) * i.move * i.currentSlide : h ? (i.last - i.currentSlide + i.cloneOffset) * g : (i.currentSlide + i.cloneOffset) * g, p = c ? b : w, f = c ? w : b, t.addEventListener("touchmove", n, !1), t.addEventListener("touchend", r, !1)
        }

        function n(t) {
          w = t.touches[0].pageX, b = t.touches[0].pageY, v = c ? p - b : p - w, x = c ? Math.abs(v) < Math.abs(w - f) : Math.abs(v) < Math.abs(b - f);
          var e = 500;
          if (!x || Number(new Date) - y > e)if (t.preventDefault(), !d && i.transitions) {
            if (!i.vars.animationLoop)v /= 0 === i.currentSlide && 0 > v || i.currentSlide === i.last && v > 0 ? Math.abs(v) / g + 2 : 1;
            i.setProps(m + v, "setTouch")
          }
        }

        function r(e) {
          if (t.removeEventListener("touchmove", n, !1), i.animatingTo === i.currentSlide && !x && null !== v) {
            var o = h ? -v : v, s = o > 0 ? i.getTarget("next") : i.getTarget("prev");
            if (i.canAdvance(s) && (Number(new Date) - y < 550 && Math.abs(o) > 50 || Math.abs(o) > g / 2))i.flexAnimate(s, i.vars.pauseOnAction); else if (!d)i.flexAnimate(i.currentSlide, i.vars.pauseOnAction, !0)
          }
          t.removeEventListener("touchend", r, !1), p = null, f = null, v = null, m = null
        }

        function s(e) {
          if (e.stopPropagation(), i.animating)e.preventDefault(); else i.pause(), t._gesture.addPointer(e.pointerId), _ = 0, g = c ? i.h : i.w, y = Number(new Date), m = u && h && i.animatingTo === i.last ? 0 : u && h ? i.limit - (i.itemW + i.vars.itemMargin) * i.move * i.animatingTo : u && i.currentSlide === i.last ? i.limit : u ? (i.itemW + i.vars.itemMargin) * i.move * i.currentSlide : h ? (i.last - i.currentSlide + i.cloneOffset) * g : (i.currentSlide + i.cloneOffset) * g
        }

        function a(e) {
          e.stopPropagation();
          var i = e.target._slider;
          if (i) {
            var n = -e.translationX, r = -e.translationY;
            if (_ += c ? r : n, v = _, x = c ? Math.abs(_) < Math.abs(-n) : Math.abs(_) < Math.abs(-r), e.detail === e.MSGESTURE_FLAG_INERTIA)return void setImmediate(function () {
              t._gesture.stop()
            });
            if (!x || Number(new Date) - y > 500)if (e.preventDefault(), !d && i.transitions) {
              if (!i.vars.animationLoop)v = _ / (0 === i.currentSlide && 0 > _ || i.currentSlide === i.last && _ > 0 ? Math.abs(_) / g + 2 : 1);
              i.setProps(m + v, "setTouch")
            }
          }
        }

        function l(t) {
          t.stopPropagation();
          var e = t.target._slider;
          if (e) {
            if (e.animatingTo === e.currentSlide && !x && null !== v) {
              var i = h ? -v : v, n = i > 0 ? e.getTarget("next") : e.getTarget("prev");
              if (e.canAdvance(n) && (Number(new Date) - y < 550 && Math.abs(i) > 50 || Math.abs(i) > g / 2))e.flexAnimate(n, e.vars.pauseOnAction); else if (!d)e.flexAnimate(e.currentSlide, e.vars.pauseOnAction, !0)
            }
            p = null, f = null, v = null, m = null, _ = 0
          }
        }

        var p, f, m, g, v, y, x = !1, w = 0, b = 0, _ = 0;
        if (!o)t.addEventListener("touchstart", e, !1); else t.style.msTouchAction = "none", t._gesture = new MSGesture, t._gesture.target = t, t.addEventListener("MSPointerDown", s, !1), t._slider = i, t.addEventListener("MSGestureChange", a, !1), t.addEventListener("MSGestureEnd", l, !1)
      }, resize: function () {
        if (!i.animating && i.is(":visible")) {
          if (!u)i.doMath();
          if (d)f.smoothHeight(); else if (u)i.slides.width(i.computedW), i.update(i.pagingCount), i.setProps(); else if (c)i.viewport.height(i.h), i.setProps(i.h, "setTotal"); else {
            if (i.vars.smoothHeight)f.smoothHeight();
            i.newSlides.width(i.computedW), i.setProps(i.computedW, "setTotal")
          }
        }
      }, smoothHeight: function (t) {
        if (!c || d) {
          var e = d ? i : i.viewport;
          t ? e.animate({height: i.slides.eq(i.animatingTo).height()}, t) : e.height(i.slides.eq(i.animatingTo).height())
        }
      }, sync: function (t) {
        var e = $(i.vars.sync).data("flexslider"), n = i.animatingTo;
        switch (t) {
          case"animate":
            e.flexAnimate(n, i.vars.pauseOnAction, !1, !0);
            break;
          case"play":
            if (!e.playing && !e.asNav)e.play();
            break;
          case"pause":
            e.pause()
        }
      }, uniqueID: function (t) {
        return t.find("[id]").each(function () {
          var t = $(this);
          t.attr("id", t.attr("id") + "_clone")
        }), t
      }, pauseInvisible: {
        visProp: null, init: function () {
          var t = ["webkit", "moz", "ms", "o"];
          if ("hidden" in document)return "hidden";
          for (var e = 0; e < t.length; e++)if (t[e] + "Hidden" in document)f.pauseInvisible.visProp = t[e] + "Hidden";
          if (f.pauseInvisible.visProp) {
            var n = f.pauseInvisible.visProp.replace(/[H|h]idden/, "") + "visibilitychange";
            document.addEventListener(n, function () {
              if (f.pauseInvisible.isHidden())if (i.startTimeout)clearTimeout(i.startTimeout); else i.pause(); else if (i.started)i.play(); else i.vars.initDelay > 0 ? setTimeout(i.play, i.vars.initDelay) : i.play()
            })
          }
        }, isHidden: function () {
          return document[f.pauseInvisible.visProp] || !1
        }
      }, setToClearWatchedEvent: function () {
        clearTimeout(n), n = setTimeout(function () {
          l = ""
        }, 3e3)
      }
    }, i.flexAnimate = function (t, e, n, o, a) {
      function l(t) {
        if (t && !t.find("img").attr("src")) {
          var e = t.find("img");
          e.attr("src", e.data("src"))
        }
      }

      if (!i.vars.animationLoop && t !== i.currentSlide)i.direction = t > i.currentSlide ? "next" : "prev";
      if (p && 1 === i.pagingCount)i.direction = i.currentItem < t ? "next" : "prev";
      if (!i.animating && (i.canAdvance(t, a) || n) && i.is(":visible")) {
        if (p && o) {
          var m = $(i.vars.asNavFor).data("flexslider");
          if (i.atEnd = 0 === t || t === i.count - 1, m.flexAnimate(t, !0, !1, !0, a), i.direction = i.currentItem < t ? "next" : "prev", m.direction = i.direction, Math.ceil((t + 1) / i.visible) - 1 !== i.currentSlide && 0 !== t)i.currentItem = t, i.slides.removeClass(r + "active-slide").eq(t).addClass(r + "active-slide"), t = Math.floor(t / i.visible); else return i.currentItem = t, i.slides.removeClass(r + "active-slide").eq(t).addClass(r + "active-slide"), !1
        }
        if (i.animating = !0, i.animatingTo = t, e)i.pause();
        if (i.vars.before(i), i.syncExists && !a)f.sync("animate");
        if (i.vars.controlNav)f.controlNav.active();
        if (!u)i.slides.removeClass(r + "active-slide").eq(t).addClass(r + "active-slide");
        if (i.atEnd = 0 === t || t === i.last, i.vars.directionNav)f.directionNav.update();
        if (t === i.last)if (i.vars.end(i), !i.vars.animationLoop)i.pause();
        if (!d) {
          var g, v, y, x = c ? i.slides.filter(":first").height() : i.computedW;
          if (u)g = i.vars.itemMargin, y = (i.itemW + g) * i.move * i.animatingTo, v = y > i.limit && 1 !== i.visible ? i.limit : y; else if (0 === i.currentSlide && t === i.count - 1 && i.vars.animationLoop && "next" !== i.direction)v = h ? (i.count + i.cloneOffset) * x : 0; else if (i.currentSlide === i.last && 0 === t && i.vars.animationLoop && "prev" !== i.direction)v = h ? 0 : (i.count + 1) * x; else v = h ? (i.count - 1 - t + i.cloneOffset) * x : (t + i.cloneOffset) * x;
          if (i.setProps(v, "", i.vars.animationSpeed), i.transitions) {
            if (!i.vars.animationLoop || !i.atEnd)i.animating = !1, i.currentSlide = i.animatingTo;
            i.container.unbind("webkitTransitionEnd transitionend"), i.container.bind("webkitTransitionEnd transitionend", function () {
              i.wrapup(x)
            })
          } else i.container.animate(i.args, i.vars.animationSpeed, i.vars.easing, function () {
            i.wrapup(x)
          })
        } else if (!s)i.slides.eq(i.currentSlide).css({zIndex: 1}).animate({opacity: 0}, i.vars.animationSpeed, i.vars.easing), i.slides.eq(t).css({zIndex: 2}).animate({opacity: 1}, i.vars.animationSpeed, i.vars.easing, i.wrapup); else i.slides.eq(i.currentSlide).css({
          opacity: 0,
          zIndex: 1
        }), i.slides.eq(t).css({opacity: 1, zIndex: 2}), i.wrapup(x);
        if (i.vars.smoothHeight)f.smoothHeight(i.vars.animationSpeed);
        var w = $("." + r + "active-slide");
        l(w.next()), l(w.prev())
      }
    }, i.wrapup = function (t) {
      if (!d && !u)if (0 === i.currentSlide && i.animatingTo === i.last && i.vars.animationLoop)i.setProps(t, "jumpEnd"); else if (i.currentSlide === i.last && 0 === i.animatingTo && i.vars.animationLoop)i.setProps(t, "jumpStart");
      i.animating = !1, i.currentSlide = i.animatingTo, i.vars.after(i)
    }, i.animateSlides = function () {
      if (!i.animating && m)i.flexAnimate(i.getTarget("next"))
    }, i.pause = function () {
      if (clearInterval(i.animatedSlides), i.animatedSlides = null, i.playing = !1, i.vars.pausePlay)f.pausePlay.update("play");
      if (i.syncExists)f.sync("pause")
    }, i.play = function () {
      if (i.playing)clearInterval(i.animatedSlides);
      if (i.animatedSlides = i.animatedSlides || setInterval(i.animateSlides, i.vars.slideshowSpeed), i.started = i.playing = !0, i.vars.pausePlay)f.pausePlay.update("pause");
      if (i.syncExists)f.sync("play")
    }, i.stop = function () {
      i.pause(), i.stopped = !0
    }, i.canAdvance = function (t, e) {
      var n = p ? i.pagingCount - 1 : i.last;
      return e ? !0 : p && i.currentItem === i.count - 1 && 0 === t && "prev" === i.direction ? !0 : p && 0 === i.currentItem && t === i.pagingCount - 1 && "next" !== i.direction ? !1 : t === i.currentSlide && !p ? !1 : i.vars.animationLoop ? !0 : i.atEnd && 0 === i.currentSlide && t === n && "next" !== i.direction ? !1 : i.atEnd && i.currentSlide === n && 0 === t && "next" === i.direction ? !1 : !0
    }, i.getTarget = function (t) {
      if (i.direction = t, "next" === t)return i.currentSlide === i.last ? 0 : i.currentSlide + 1; else return 0 === i.currentSlide ? i.last : i.currentSlide - 1
    }, i.setProps = function (t, e, n) {
      var r = function () {
        var n = t ? t : (i.itemW + i.vars.itemMargin) * i.move * i.animatingTo, r = function () {
          if (u)return "setTouch" === e ? t : h && i.animatingTo === i.last ? 0 : h ? i.limit - (i.itemW + i.vars.itemMargin) * i.move * i.animatingTo : i.animatingTo === i.last ? i.limit : n; else switch (e) {
            case"setTotal":
              return h ? (i.count - 1 - i.currentSlide + i.cloneOffset) * t : (i.currentSlide + i.cloneOffset) * t;
            case"setTouch":
              return h ? t : t;
            case"jumpEnd":
              return h ? t : i.count * t;
            case"jumpStart":
              return h ? i.count * t : t;
            default:
              return t
          }
        }();
        return -1 * r + "px"
      }();
      if (i.transitions)r = c ? "translate3d(0," + r + ",0)" : "translate3d(" + r + ",0,0)", n = void 0 !== n ? n / 1e3 + "s" : "0s", i.container.css("-" + i.pfx + "-transition-duration", n), i.container.css("transition-duration", n);
      if (i.args[i.prop] = r, i.transitions || void 0 === n)i.container.css(i.args);
      i.container.css("transform", r)
    }, i.setup = function (t) {
      if (!d) {
        var e, n;
        if ("init" === t)if (i.viewport = $('<div class="' + r + 'viewport"></div>').css({
            overflow: "hidden",
            position: "relative"
          }).appendTo(i).append(i.container), i.cloneCount = 0, i.cloneOffset = 0, h)n = $.makeArray(i.slides).reverse(), i.slides = $(n), i.container.empty().append(i.slides);
        if (i.vars.animationLoop && !u) {
          if (i.cloneCount = 2, i.cloneOffset = 1, "init" !== t)i.container.find(".clone").remove();
          f.uniqueID(i.slides.first().clone().addClass("clone")).appendTo(i.container), f.uniqueID(i.slides.last().clone().addClass("clone")).prependTo(i.container)
        }
        if (i.newSlides = $(i.vars.selector, i), e = h ? i.count - 1 - i.currentSlide + i.cloneOffset : i.currentSlide + i.cloneOffset, c && !u)i.container.height(200 * (i.count + i.cloneCount) + "%").css("position", "absolute").width("100%"), setTimeout(function () {
          i.newSlides.css({display: "block"}), i.doMath(), i.viewport.height(i.h), i.setProps(e * i.h, "init")
        }, "init" === t ? 100 : 0); else i.container.width(200 * (i.count + i.cloneCount) + "%"), i.setProps(e * i.computedW, "init"), setTimeout(function () {
          if (i.doMath(), i.newSlides.css({
              width: i.computedW,
              "float": "left",
              display: "block"
            }), i.vars.smoothHeight)f.smoothHeight()
        }, "init" === t ? 100 : 0)
      } else {
        if (i.slides.css({
            width: "100%",
            "float": "left",
            marginRight: "-100%",
            position: "relative"
          }), "init" === t)if (!s)i.slides.css({
          opacity: 0,
          display: "block",
          zIndex: 1
        }).eq(i.currentSlide).css({zIndex: 2}).animate({opacity: 1}, i.vars.animationSpeed, i.vars.easing); else i.slides.css({
          opacity: 0,
          display: "block",
          webkitTransition: "opacity " + i.vars.animationSpeed / 1e3 + "s ease",
          zIndex: 1
        }).eq(i.currentSlide).css({opacity: 1, zIndex: 2});
        if (i.vars.smoothHeight)f.smoothHeight()
      }
      if (!u)i.slides.removeClass(r + "active-slide").eq(i.currentSlide).addClass(r + "active-slide");
      i.vars.init(i)
    }, i.doMath = function () {
      var t = i.slides.first(), e = i.vars.itemMargin, n = i.vars.minItems, r = i.vars.maxItems;
      if (i.w = void 0 === i.viewport ? i.width() : i.viewport.width(), i.h = t.height(), i.boxPadding = t[0].offsetWidth - t.width(), u)i.itemT = i.vars.itemWidth + e, i.minW = n ? n * i.itemT : i.w, i.maxW = r ? r * i.itemT - e : i.w, i.itemW = i.minW > i.w ? (i.w - e * (n - 1)) / n : i.maxW < i.w ? (i.w - e * (r - 1)) / r : i.vars.itemWidth > i.w ? i.w : i.vars.itemWidth, i.visible = Math.floor(i.w / i.itemW), i.move = i.vars.move > 0 && i.vars.move < i.visible ? i.vars.move : i.visible, i.pagingCount = Math.ceil((i.count - i.visible) / i.move + 1), i.last = i.pagingCount - 1, i.limit = 1 === i.pagingCount ? 0 : i.vars.itemWidth > i.w ? i.itemW * (i.count - 1) + e * (i.count - 1) : (i.itemW + e) * i.count - i.w - e; else i.itemW = i.w, i.pagingCount = i.count, i.last = i.count - 1;
      i.computedW = i.itemW - i.boxPadding
    }, i.update = function (t, e) {
      if (i.doMath(), !u) {
        if (t < i.currentSlide)i.currentSlide += 1; else if (t <= i.currentSlide && 0 !== t)i.currentSlide -= 1;
        i.animatingTo = i.currentSlide
      }
      if (i.vars.controlNav && !i.manualControls)if ("add" === e && !u || i.pagingCount > i.controlNav.length)f.controlNav.update("add"); else if ("remove" === e && !u || i.pagingCount < i.controlNav.length) {
        if (u && i.currentSlide > i.last)i.currentSlide -= 1, i.animatingTo -= 1;
        f.controlNav.update("remove", i.last)
      }
      if (i.vars.directionNav)f.directionNav.update()
    }, i.addSlide = function (t, e) {
      var n = $(t);
      if (i.count += 1, i.last = i.count - 1, c && h)void 0 !== e ? i.slides.eq(i.count - e).after(n) : i.container.prepend(n); else void 0 !== e ? i.slides.eq(e).before(n) : i.container.append(n);
      i.update(e, "add"), i.slides = $(i.vars.selector + ":not(.clone)", i), i.setup(), i.vars.added(i)
    }, i.removeSlide = function (t) {
      var e = isNaN(t) ? i.slides.index($(t)) : t;
      if (i.count -= 1, i.last = i.count - 1, isNaN(t))$(t, i.slides).remove(); else c && h ? i.slides.eq(i.last).remove() : i.slides.eq(t).remove();
      i.doMath(), i.update(e, "remove"), i.slides = $(i.vars.selector + ":not(.clone)", i), i.setup(), i.vars.removed(i)
    }, f.init()
  }, $(window).blur(function (t) {
    focused = !1
  }).focus(function (t) {
    focused = !0
  }), $.flexslider.defaults = {
    namespace: "flex-",
    selector: ".slides > li",
    animation: "fade",
    easing: "swing",
    direction: "horizontal",
    reverse: !1,
    animationLoop: !0,
    smoothHeight: !1,
    startAt: 0,
    slideshow: !0,
    slideshowSpeed: 7e3,
    animationSpeed: 600,
    initDelay: 0,
    randomize: !1,
    thumbCaptions: !1,
    pauseOnAction: !0,
    pauseOnHover: !1,
    pauseInvisible: !0,
    useCSS: !0,
    touch: !0,
    video: !1,
    controlNav: !0,
    directionNav: !0,
    prevText: "Previous",
    nextText: "Next",
    keyboard: !0,
    multipleKeyboard: !1,
    mousewheel: !1,
    pausePlay: !1,
    pauseText: "Pause",
    playText: "Play",
    controlsContainer: "",
    manualControls: "",
    sync: "",
    asNavFor: "",
    itemWidth: 0,
    itemMargin: 0,
    minItems: 1,
    maxItems: 0,
    move: 0,
    allowOneSlide: !0,
    start: function (t) {
      t.controlNavScaffold && t.controlNavScaffold.css({display: "block"}), require("../src/common/event").trigger("sliderInit")
    },
    before: function () {
    },
    after: function (t) {
      t.pause(), t.play()
    },
    end: function () {
    },
    added: function () {
    },
    removed: function () {
    },
    init: function () {
    }
  }, $.fn.flexslider = function (t) {
    if (void 0 === t)t = {};
    if ("object" == typeof t)return this.each(function () {
      var e = $(this), i = t.selector ? t.selector : ".slides > li", n = e.find(i);
      if (1 === n.length && t.allowOneSlide === !0 || 0 === n.length) {
        if (n.fadeIn(400), t.start)t.start(e)
      } else if (void 0 === e.data("flexslider"))new $.flexslider(this, t)
    }); else {
      var e = $(this).data("flexslider");
      switch (t) {
        case"play":
          e.play();
          break;
        case"pause":
          e.pause();
          break;
        case"stop":
          e.stop();
          break;
        case"next":
          e.flexAnimate(e.getTarget("next"), !0);
          break;
        case"prev":
        case"previous":
          e.flexAnimate(e.getTarget("prev"), !0);
          break;
        default:
          if ("number" == typeof t)e.flexAnimate(t, !0)
      }
    }
  }, $(".flexslider").flexslider({animation: "slide", directionNav: !1, allowOneSlide: !0})
}), define("echarts/slider", ["slider"], function (t) {
  return t
}), define("activity/common/isNA", ["require"], function (require) {
  var t = function (t) {
    if (t && "function" == typeof t)if (window.BNJS && "object" == typeof window.BNJS && BNJS._isAllReady)t(); else document.addEventListener("BNJSReady", function () {
      t()
    }, !1)
  };
  t(function () {
    $(".bnjs").addClass("isNA")
  })
}), define("util", [], function () {
  var exports = {};
  exports.qs = {}, exports.qs.stringify = function (t) {
    if (!t)return "";
    var e = [];
    for (var i in t)if (t.hasOwnProperty(i) && null !== t[i]) {
      if ("object" == typeof t[i])t[i] = JSON.stringify(t[i]);
      e.push(encodeURIComponent(i) + "=" + encodeURIComponent(t[i]))
    }
    return e.join("&")
  }, exports.qs.parse = function (t) {
    if (!t)return {};
    var e = {};
    t = t.split("&");
    for (var i = 0; i < t.length; i++) {
      var n = t[i].split("=", 2);
      if (1 === n.length)e[n[0]] = ""; else e[n[0]] = decodeURIComponent(n[1].replace(/\+/g, " "))
    }
    return e
  }, exports.isAndroid = function () {
    return /android/i.test(navigator.userAgent)
  }, exports.isIOS = function () {
    return /iphone|ipad|ipod/i.test(navigator.userAgent)
  }, exports.dateFormat = function (t, e) {
    var i = {
      "M+": t.getMonth() + 1,
      "d+": t.getDate(),
      "h+": t.getHours(),
      "m+": t.getMinutes(),
      "s+": t.getSeconds(),
      "q+": Math.floor((t.getMonth() + 3) / 3),
      S: t.getMilliseconds()
    };
    if (/(y+)/.test(e))e = e.replace(RegExp.$1, (t.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var n in i)if (new RegExp("(" + n + ")").test(e))e = e.replace(RegExp.$1, 1 === RegExp.$1.length ? i[n] : ("00" + i[n]).substr(("" + i[n]).length));
    return e
  }, exports.formatDateText = function (t, e) {
    var i = new Date(t), n = new Date(e);
    i = new Date(i.getFullYear(), i.getMonth(), i.getDate()), n = new Date(n.getFullYear(), n.getMonth(), n.getDate());
    var r = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], o = i.getMonth() + 1 + "月" + i.getDate() + "日", s = i.getDay(), a = n.getDay(), l = 6048e5, c = i - n;
    if (0 === c)return "今天" + o; else if (864e5 === c)return "明天" + o; else if (1728e5 === c)return "后天" + o; else if (l >= c)if (s > a && 0 !== a || c !== l && 0 === s)return r[s] + o; else return "下" + r[s] + o;
    return o
  }, exports.encodeHTML = function (t) {
    return String(t).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;")
  }, exports.decodeHTML = function (t) {
    var e = String(t).replace(/&quot;/g, '"').replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&");
    return e.replace(/&#([\d]+);/g, function (t, e) {
      return String.fromCharCode(parseInt(e, 10))
    })
  }, exports.load = function (t, e) {
    if ("undefined" == typeof t)return void(e && e());
    if ("string" == typeof t)t = [t];
    var i = t.length;
    if (0 === i)return void(e && e()); else return void t.forEach(function (t, n) {
      var r = new Image;
      r.src = t, r.addEventListener("load", function () {
        r = null, --i <= 0 && e && e()
      })
    })
  }, exports.getUrlParams = function () {
    var t = window.location.search || "";
    return t = t.replace("?", ""), exports.qs.parse(t)
  }, exports.handleTextOverflow = function (t, e, i) {
    for (var n = document.getElementsByClassName(t), r = document.getElementsByClassName(i), o = document.getElementsByClassName(e), s = n.length, a = 0; s > a; a++) {
      var l = n[a].offsetWidth, c = r[a] ? r[a].offsetWidth : 0;
      o[a].style.maxWidth = l - c - 10 + "px"
    }
  }, exports.strformatter = function (t, e) {
    if (!t)return "";
    if (null == e)return t;
    var i = [].slice.call(arguments, 1);
    return t.replace(/\$\{(.+?)\}/g, function (t, n) {
      var r = Number(n);
      if (r >= 0)return i[r];
      var o = e[n];
      if ("function" == typeof o)o = o(n);
      return null == o ? "" : o
    })
  }, exports.getTimeValue = function (t) {
    var e = Math.floor(t / 60), i = Math.floor(t % 60);
    if (10 > e)e = "0" + e;
    if (10 > i)i = "0" + i;
    return {m: e, s: i}
  }, exports.getFormatScore = function (t, e) {
    t = 10 * t;
    var i = Math.floor(t / 10), n = t - 10 * i;
    if (0 === n)t = i - 1; else t = i;
    if (t = (32 + 42 * t) / 4, t = t > 100 ? 100 : t, e)return Math.round(.01 * t * e); else return t
  }, exports.stringStartsWith = function (t, e) {
    if (!String.prototype.startsWith) {
      var i = 0;
      return t.indexOf(e, i) === i
    }
    return t.startsWith(e)
  }, exports.stringFormat = function () {
    var t = [].slice.apply(arguments), e = new RegExp("\\{(\\d+)\\}", "g");
    return t[0].replace(e, function () {
      return t[parseInt(RegExp.$1, 10) + 1]
    })
  }, exports.guid = function () {
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (t) {
      var e = 16 * Math.random() | 0, i = "x" === t ? e : 3 & e | 8;
      return i.toString(16)
    })
  }, exports.inherits = function (t, e) {
    var i = function () {
    };
    i.prototype = e.prototype;
    var n = t.prototype, r = t.prototype = new i;
    for (var o in n)if (n.hasOwnProperty(o))r[o] = n[o];
    return t.prototype.constructor = t, t.superClass = e.prototype, t
  }, exports.featureTest = function (t, e) {
    var i = t + ":", n = document.createElement("div").style;
    return n.cssText = i + ["-webkit-", "-moz-", "-ms-", "-o-", ""].join(e + ";" + i) + e + ";", n[t]
  };
  var t = parseFloat(document.documentElement.style.fontSize);
  return exports.rem2px = function (e) {
    return t / 16 * e
  }, exports.scrollTo = function (t, e) {
    function i() {
      if (1 === n)return void window.scrollTo(0, t); else return n--, window.scrollTo(0, window.scrollY + r), void o(i)
    }

    if (!e || 16 > e)return void window.scrollTo(0, t);
    var n = Math.floor(e / 16), r = (t - window.scrollY) / n, o = window.requestAnimationFrame || window.webkitRequestAnimationFrame || function (t, e) {
        window.setTimeout(t, 1e3 / 60)
      };
    i()
  }, exports
}), define("common/logger", ["require", "../util"], function (require) {
  function t(t) {
    var e = l + "&t=" + Date.now() + "&" + t;
    if (window._MOVIE.debug) {
      var i = s.qs.parse(t);
      console.info("LOGGER da_act: %s da_src: %s", i.da_act, i.da_src)
    } else {
      var n = new Image, r = "img" + Date.now() + Math.ceil(100 * Math.random(100));
      window[r] = n, n.onload = n.onerror = function () {
        delete window[r]
      }, n.src = e
    }
  }

  function e(e, i) {
    var n, r = window._MOVIE.statParam;
    if (window._MOVIE.autocomplete && !h.test(e.da_src) && r.da_src && e.da_src)if (-1 === e.da_src.indexOf(r.da_src))e.da_src = r.da_src + "." + e.da_src;
    if (e = $.extend({}, u, e || {}), e.da_src) {
      n = s.qs.stringify(e), t(n);
      var o;
      if ("lightapp_discover" === window._MOVIE.sfrom || "lightapp_map_discover" === window._MOVIE.sfrom)try {
        var l = JSON.parse(window._MOVIE.subChannel);
        o = l.sub_channel
      } catch (d) {
        o = window._MOVIE.subChannel
      } else o = window._MOVIE.subChannel;
      if ("cloud_shenghuo_focus_wap_cjxql" === window._MOVIE.subChannel || "cloud_floating_wap_cjxql" === window._MOVIE.subChannel || "cloud_wise_wap_cjxql" === window._MOVIE.subChannel || "cloud_shenghuo_51huodong_wap_crazy51" === window._MOVIE.subChannel || "cloud_floating_51huodong_wap_crazy51" === window._MOVIE.subChannel || "cloud_wise_51huodong_wap_crazy51" === window._MOVIE.subChannel)o = window._MOVIE.subChannel;
      if (o)if ("lightapp_discover" === window._MOVIE.sfrom || "lightapp_map_discover" === window._MOVIE.sfrom || "ready" === e.da_act)e = $.extend(e || {}, {
        from: a,
        resid: 31,
        da_ver: "2.1.0",
        da_trd: "movie",
        da_act: "attached",
        mobile: c ? "android" : "phone",
        da_thirdpar: window._MOVIE.sfrom + "_" + o
      }), n = s.qs.stringify(e), t(n)
    }
  }

  function i(t) {
    e({da_src: t && t.da_src ? t.da_src : window._MOVIE.statParam.da_src, da_act: "ready"})
  }

  function n(t) {
    $.extend(window._MOVIE.statParam, t || {})
  }

  function r(t) {
    window._MOVIE.statParam.da_src = t
  }

  function o() {
    window._MOVIE.autocomplete = !0
  }

  var s = require("../util"), a = _MOVIE.from || "webapp", l = "http://map.baidu.com/mobile/img/transparent.gif?newmap=1", c = $.os && $.os.android, h = /movieGeo/, u = {
    from: a,
    resid: 31,
    da_ver: "2.1.0",
    da_trd: "movie",
    mobile: c ? "android" : "phone",
    da_thirdpar: _MOVIE.statParam.da_thirdpar,
    da_abtest: _MOVIE.statParam.da_abtest,
    da_subchannel: _MOVIE.subChannel,
    da_module: _MOVIE.module,
    da_channel: _MOVIE.channel,
    da_client: _MOVIE.client,
    da_ua: navigator.userAgent
  };
  e._reqQueue = [];
  var exports = {
    imgPath: l,
    addStat: e,
    addPvStat: i,
    addDefaultParams: n,
    setDefaultPageName: r,
    autocomplete: o,
    init: function (t) {
      var n = window._MOVIE.statParam;
      $.extend(n, t || {}), $(document.body).on("click", "[data-log]", function (t) {
        var i = $(this), n = i.data("log"), r = {};
        if ("string" == typeof n)try {
          n = s.decodeHTML(n), n = n.replace(/\'/g, '"'), n = JSON.parse(n)
        } catch (t) {
          n = {}
        }
        if ("object" == typeof n) {
          var o;
          for (var a in n)if (n.hasOwnProperty(a)) {
            if (o = n[a], "object" == typeof o)o = JSON.stringify(o);
            if (o)r[a] = o.toString()
          }
          if (!r.da_act)r.da_act = "click";
          e(r)
        }
      }), i(window._MOVIE.statParam)
    }
  };
  return exports
}), define("common/cookie", ["require"], function (require) {
  var t = {};
  return t._isValidKey = function (t) {
    return new RegExp('^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\"\\[\\]\\?=\\{\\}\\/\\u0080-\\uffff]+$').test(t)
  }, t.getRaw = function (e) {
    if (t._isValidKey(e)) {
      var i = new RegExp("(^| )" + e + "=([^;]*)(;|$)"), n = i.exec(document.cookie);
      if (n)return n[2] || null
    }
    return null
  }, t.get = function (e) {
    var i = t.getRaw(e);
    if ("string" == typeof i)return i = decodeURIComponent(i); else return null
  }, t.setRaw = function (e, i, n) {
    if (t._isValidKey(e)) {
      n = n || {};
      var r = n.expires;
      if ("number" == typeof n.expires)r = new Date, r.setTime(r.getTime() + n.expires);
      document.cookie = e + "=" + i + (n.path ? "; path=" + n.path : "") + (r ? "; expires=" + r.toGMTString() : "") + (n.domain ? "; domain=" + n.domain : "") + (n.secure ? "; secure" : "")
    }
  }, t.remove = function (e, i) {
