package com.family.webserver.entity;

import java.util.List;

public class CityMovie {

  Movieshowing movie;

  List<Comment> comments;

  List<Image> images;

  public Movieshowing getMovie() {
    return movie;
  }

  public void setMovie(Movieshowing movie) {
    this.movie = movie;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}
