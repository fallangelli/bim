package com.family.webserver.entity;

import java.util.List;

public class RetCinema {

  Cinema cinema;

  List<Movieshowing> showingMovies;

  public Cinema getCinema() {
    return cinema;
  }

  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  public List<Movieshowing> getShowingMovies() {
    return showingMovies;
  }

  public void setShowingMovies(List<Movieshowing> showingMovies) {
    this.showingMovies = showingMovies;
  }
}
