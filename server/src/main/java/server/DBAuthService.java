package server;

public class DBAuthService implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return SQLClientHandler.getNicknameByLoginAndPassword(login,password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return SQLClientHandler.registration(login, password, nickname);
    }

    @Override
    public boolean changeUser(String oldUser, String newUser) {
        return SQLClientHandler.changeUser(oldUser, newUser);
    }
}
