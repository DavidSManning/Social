package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * Tests for {@link MessageService}.
 */
public class TestMessageService {

  private Session session;
  private SessionFactory factory;

  /**
   * Sets up the mocked dependencies for the service.
   */
  @Before
  public void setUpMocks() {
    factory = mock(SessionFactory.class);
    session = mock(Session.class);
    when(factory.getCurrentSession()).thenReturn(session);
  }


  /**
   * Tests making a post.
   */
  @Test
  public void makePost() {
    MessageService service = new MessageService(factory);
    service.post("Alice", "Test posting");

    ArgumentCaptor<Post> argumentCaptor = ArgumentCaptor.forClass(Post.class);
    verify(session).save(argumentCaptor.capture());
    assertEquals("Post user name", "Alice", argumentCaptor.getValue().getUser());
    assertTrue("Post time", new Date().getTime() -  argumentCaptor.getValue().getTime().getTime() < 1000);
    assertEquals("Post message", "Test posting", argumentCaptor.getValue().getMessage());
  }
}
