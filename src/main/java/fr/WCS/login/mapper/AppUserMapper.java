package fr.WCS.login.mapper;

import fr.WCS.login.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {

    public static final String SELECT_SQL //
            = "SELECT u.User_Id, u.User_Name, u.Encryted_Password FROM App_User u ";

    public static final String INSERT_SQL //
            = "INSERT INTO APP_USER (USER_NAME, ENCRYTED_PASSWORD) ";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long userId = rs.getLong("User_Id");
        String userName = rs.getString("User_Name");
        String encrytedPassword = rs.getString("Encryted_Password");

        return new AppUser(userId, userName, encrytedPassword);
    }

}