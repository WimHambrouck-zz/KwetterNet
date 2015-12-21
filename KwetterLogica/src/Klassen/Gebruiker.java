package Klassen;

public class Gebruiker extends Verzendbaar{

    private int id;
    private String username;
    private String wachtwoord;
    private String email;
    private String nickname;
    private String extra;


    public Gebruiker() {}

    /**
     *
     * @param extra
     * @param nickname
     * @param email
     * @param wachtwoord
     * @param username
     * @param id
     */
    public Gebruiker(String extra, String nickname, String email, String wachtwoord, String username, int id) {

        setExtra(extra);
        setId(id);
        setUsername(username);
        setUsername(email);
        setWachtwoord(wachtwoord);
        setNickname(nickname);
    }

    /**
     *
     * @param id
     * @param username
     * @param email
     * @param wachtwoord
     * @param nickname
     */

    public Gebruiker(int id, String username, String email, String wachtwoord, String nickname) {

        setId(id);
        setUsername(username);
        setUsername(email);
        setWachtwoord(wachtwoord);
        setNickname(nickname);
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        if(!extra.isEmpty()) {
            this.extra = extra;
        }else{
            throw new IllegalArgumentException("extra is leeg");
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        if(!nickname.isEmpty()) {
            this.nickname = nickname;
        }else{
            throw new IllegalArgumentException("Nickname is leeg");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.isEmpty()) {
            this.email = email;
        }else{
            throw new IllegalArgumentException("Email is leeg");
        }
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        if(!wachtwoord.isEmpty()) {
            this.wachtwoord = wachtwoord;
        }else{
            throw new IllegalArgumentException("Wachtwoord is leeg");
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(!username.isEmpty()) {
            this.username = username;
        }else{
            throw new IllegalArgumentException("Username is leeg");
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id >= 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException("Id lager dan 0");
        }
    }
}
