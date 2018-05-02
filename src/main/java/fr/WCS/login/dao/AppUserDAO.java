package fr.WCS.login.dao;

import fr.WCS.login.mapper.AppUserMapper;
import fr.WCS.login.model.AppUser;
import fr.WCS.login.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
//@RepositoryRestResource
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public AppUser findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = AppUserMapper.SELECT_SQL + " WHERE u.User_Name = ? ";

        Object[] params = new Object[]{userName};
        AppUserMapper mapper = new AppUserMapper();
        try {
            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public int CreateUserAccount(AppUser appUser) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = AppUserMapper.INSERT_SQL + " VALUE (? , ?)";
        String name = appUser.getUserName();
        String encryptedPassword = EncrytedPasswordUtils.encrytePassword(appUser.getEncrytedPassword());

        int questcequecest = getJdbcTemplate().update(sql, name, encryptedPassword);

        return questcequecest;
    }

}