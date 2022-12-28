package org.example;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TestingClass {

    FriendsCollection friends = mock(FriendsCollection.class);
    FriendshipsMongo friendshipsMongo = mock(FriendshipsMongo.class);
    List<String> friendsList = new ArrayList<>();

    @BeforeEach
    void set_up() {
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person("Mark");
        friendsList.add("person1");
        friendsList.add("person2");
        friendsList.add("person3");

    }

    @Test
    public void givenWhenTypeIsInterface_thenReturnTrue() {
        Assert.assertTrue(friends instanceof FriendsCollection);
    }


    @Test
    public void mockingWorksAsExpected() {
        Person person2 = new Person();
        when(friends.findByName("person2")).thenReturn(person2);
        assertThat(friends.findByName("person2")).isEqualTo(person2);


    }


    @Test
    public void savePerson() throws UnknownHostException {
        Person person3 = new Person("John");
        friends.save(person3);
        when(friends.findByName("John")).thenReturn(person3);
        assertThat(friends.findByName("John")).isEqualTo(person3);
    }

    @Test
    public void addFriends() throws UnknownHostException {
        Person person3 = new Person("John");
        Person person4 = new Person("Zbig");
        friends.save(person3);
        friends.save(person4);
        friendshipsMongo.makeFriends("John", "Zbig");
        when(friendshipsMongo.areFriends("John", "Zbig")).thenReturn(true);
        assertThat(friendshipsMongo.areFriends("John", "Zbig")).isEqualTo(true);
    }

    @Test
    public void getFriends() {
        Person person3 = new Person("John");
        Person person4 = new Person("Zbig");
        friends.save(person3);
        friends.save(person4);
        friendshipsMongo.makeFriends("John", "Zbig");
        List<String> myFriends = new ArrayList<>();
        myFriends.add("Zbig");
        when(friendshipsMongo.getFriendsList("John")).thenReturn(myFriends);
        assertTrue(friendshipsMongo.getFriendsList("John").contains("Zbig"));

    }
}
