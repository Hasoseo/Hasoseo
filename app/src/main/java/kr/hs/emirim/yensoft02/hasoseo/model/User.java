package kr.hs.emirim.yensoft02.hasoseo.model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String nickname;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
