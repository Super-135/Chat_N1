package server;

import java.lang.annotation.Retention;
import java.sql.*;

public class SQLClientHandler {
    private static Connection connectionDB;
    private static PreparedStatement getNick;
    private static PreparedStatement registration;
    private static PreparedStatement changeUser;

    private Connection connection;
    public Statement stmt;

    public static boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionDB = DriverManager.getConnection("jdbc:sqlite:chat.db");
            prepareAllStatements();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void prepareAllStatements() throws SQLException {
        getNick = connectionDB.prepareStatement("SELECT nick FROM users WHERE login = ? AND password = ?;");
        registration = connectionDB.prepareStatement("INSERT INTO users (login, password, nick) VALUES (?, ?, ?);");
        changeUser  =connectionDB.prepareStatement("UPDATE users SET nick = ? WHERE nick = ?;");

    }
    public static String getNicknameByLoginAndPassword(String login, String password) {
        String nick = null;
        try {
            getNick.setString(1,login);
            getNick.setString(2,password);
            ResultSet rs = getNick.executeQuery();
            if (rs.next()){
                nick = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nick;
    }

    public static boolean registration(String login, String password, String nickname) {
        try {
            registration.setString(1,login);
            registration.setString(2,password);
            registration.setString(3,nickname);
            registration.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeUser(String oldUser, String newUser) {
        try {
            changeUser.setString(1,newUser);
            changeUser.setString(2,oldUser);
            changeUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void disconnect() {
        try {
            getNick.close();
            registration.close();
            changeUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connectionDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
