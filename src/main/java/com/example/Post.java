package com.example;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A post made on the social timeline, encapsulating the user, time and message.
 */
@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected long identifier;

  @Column
  protected Date time;

  @Column(length = 140)
  protected String message;

  @Column(length = 50)
  protected String user;

  /**
   * @return the time the post was made.
   */
  public Date getTime() {
    return time;
  }

  /**
   * @return the message in the post.
   */
  public String getMessage() {
    return message;
  }

  /**
   * @return the user who made the post.
   */
  public String getUser() {
    return user;
  }

  /**
   * @param time the time to set
   */
  public void setTime(Date time) {
    this.time = time;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @param user the user to set
   */
  public void setUser(String user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return String.format("User: %s,  message: %s", user, message);
  }
}
