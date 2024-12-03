package Utility;

public class Friendship {
//    between
    private final User firstUser;
    private final User secondUser;

    public Friendship(User firstUser, User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                '}';
    }
}
