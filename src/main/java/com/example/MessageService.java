package com.example;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Core messaging service responsible for reading a writing posts, and listing
 * aggregated lists of posts.
 */
public class MessageService {


  private final SessionFactory sessionFactory;


  MessageService(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /**
   * Posts a new message.
   *
   * @param user The user making the post.
   * @param message The message being posted.
   */
  public void post(String user, String message) {
    Post post = new Post();
    post.setMessage(message);
    post.setTime(new Date());
    post.setUser(user);
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.save(post);
    transaction.commit();
    session.close();
  }


  /**
   * Retrieves the posts from a specified user and orders newest first.
   *
   * @param forUser The owner of the posts.
   * @return The posts, newest first.
   */
  public List<Post> viewTimeLine(String forUser) {
    return null;
  }


  /**
   * Creates a 'follower/followee' link between two users to facilitate aggregated
   * wall viewing.
   *
   * @param follower The user being followed.
   * @param followee The user following.
   */
  public void addFollower(String follower, String followee) {

  }


  /**
   * Returns the wall for a given user, including posts by themselves and
   * any users being followed, in reverse chronological order.
   *
   * @param user The whose wall to view.
   * @return the wall.
   */
  public List<Post> viewWall(String user) {
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    List list = session.createQuery("from Post where user = :user").setString("user", "Dave").list();
    for (Object l : list) System.out.println(l);
    transaction.commit();
    return null;
  }
}
