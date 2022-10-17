<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
          rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
        rel="stylesheet">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
          href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
          rel="stylesheet">
  <link href="${path}/resources/static/css/home.css" rel="stylesheet">
  <title>Coding Study Friends</title>
  <script
          src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/template/header.jsp"></jsp:include>
<sectio class="nav">
  <h1>CODING FRIENDS</h1>
  <h3 class="span loader"><span class="m">B</span><span class="m">E</span><span class="m">N</span><span class="m">E</span><span class="m">F</span><span class="m">I</span><span class="m">T</span><span class="m">S</span><span class="m">&nbsp;</span><span class="m">o</span><span class="m">f</span><span class="m">&nbsp;</span><span class="m">T</span><span class="m">E</span><span class="m">C</span><span class="m">H</span><span class="m">N</span><span class="m">O</span><span class="m">L</span><span class="m">O</span><span class="m">G</span><span class="m">I</span><span class="m">E</span><span class="m">S</span></h3>
</sectio>
<canvas class="background"></canvas>
<div style="background: aliceblue"></div>


<script>
  /* Credit and Thanks:
Matrix - Particles.js;
SliderJS - Ettrics;
Design - Sara Mazal Web;
Fonts - Google Fonts
*/

  window.onload = function () {
    Particles.init({
      selector: ".background"
    });
  };
  const particles = Particles.init({
    selector: ".background",
    color: ["#03dac6", "#ff0266", "#000000"],
    connectParticles: true,
    responsive: [
      {
        breakpoint: 768,
        options: {
          color: ["#faebd7", "#03dac6", "#ff0266"],
          maxParticles: 43,
          connectParticles: false
        }
      }
    ]
  });

  class NavigationPage {
    constructor() {
      this.currentId = null;
      this.currentTab = null;
      this.tabContainerHeight = 70;
      this.lastScroll = 0;
      let self = this;
      $(".nav-tab").click(function () {
        self.onTabClick(event, $(this));
      });
      $(window).scroll(() => {
        this.onScroll();
      });
      $(window).resize(() => {
        this.onResize();
      });
    }

    onTabClick(event, element) {
      event.preventDefault();
      let scrollTop =
              $(element.attr("href")).offset().top - this.tabContainerHeight + 1;
      $("html, body").animate({ scrollTop: scrollTop }, 600);
    }

    onScroll() {
      this.checkHeaderPosition();
      this.findCurrentTabSelector();
      this.lastScroll = $(window).scrollTop();
    }

    onResize() {
      if (this.currentId) {
        this.setSliderCss();
      }
    }

    checkHeaderPosition() {
      const headerHeight = 75;
      if ($(window).scrollTop() > headerHeight) {
        $(".nav-container").addClass("nav-container--scrolled");
      } else {
        $(".nav-container").removeClass("nav-container--scrolled");
      }
      let offset =
              $(".nav").offset().top +
              $(".nav").height() -
              this.tabContainerHeight -
              headerHeight;
      if (
              $(window).scrollTop() > this.lastScroll &&
              $(window).scrollTop() > offset
      ) {
        $(".nav-container").addClass("nav-container--move-up");
        $(".nav-container").removeClass("nav-container--top-first");
        $(".nav-container").addClass("nav-container--top-second");
      } else if (
              $(window).scrollTop() < this.lastScroll &&
              $(window).scrollTop() > offset
      ) {
        $(".nav-container").removeClass("nav-container--move-up");
        $(".nav-container").removeClass("nav-container--top-second");
        $(".nav-container-container").addClass("nav-container--top-first");
      } else {
        $(".nav-container").removeClass("nav-container--move-up");
        $(".nav-container").removeClass("nav-container--top-first");
        $(".nav-container").removeClass("nav-container--top-second");
      }
    }

    findCurrentTabSelector(element) {
      let newCurrentId;
      let newCurrentTab;
      let self = this;
      $(".nav-tab").each(function () {
        let id = $(this).attr("href");
        let offsetTop = $(id).offset().top - self.tabContainerHeight;
        let offsetBottom =
                $(id).offset().top + $(id).height() - self.tabContainerHeight;
        if (
                $(window).scrollTop() > offsetTop &&
                $(window).scrollTop() < offsetBottom
        ) {
          newCurrentId = id;
          newCurrentTab = $(this);
        }
      });
      if (this.currentId != newCurrentId || this.currentId === null) {
        this.currentId = newCurrentId;
        this.currentTab = newCurrentTab;
        this.setSliderCss();
      }
    }

    setSliderCss() {
      let width = 0;
      let left = 0;
      if (this.currentTab) {
        width = this.currentTab.css("width");
        left = this.currentTab.offset().left;
      }
      $(".nav-tab-slider").css("width", width);
      $(".nav-tab-slider").css("left", left);
    }
  }

  new NavigationPage();
  /* Credit and Thanks:
  Matrix - Particles.js;
  SliderJS - Ettrics;
  Design - Sara Mazal Web;
  Fonts - Google Fonts
  */

</script>
<jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>


