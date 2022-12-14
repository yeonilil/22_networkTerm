package network_term;
public class usermodel {
    String name;
    String uid;
    String password;
    String on;
    String intro;
    public usermodel(String name, String uid, String password,String on, String intro){
        this.name = name;
        this.uid = uid;
        this.password = password;
        this.on = on;
        this.intro = intro;
    }
    public String getIntro() {
        return intro;
    }
    public String getName() {
        return name;
    }
    public String getOn() {
        return on;
    }
    public String getPassword() {
        return password;
    }
    public String getUid() {
        return uid;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
