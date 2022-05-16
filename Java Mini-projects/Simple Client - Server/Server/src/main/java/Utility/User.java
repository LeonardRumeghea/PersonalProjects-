package Utility;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final Integer id;
    private final List<User> friends;

    public User(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.friends = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void addFriends(User friend) {
        this.friends.add(friend);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", friends=" + friends +
                '}';
    }
}
