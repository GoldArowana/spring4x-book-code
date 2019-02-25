package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final static String MATCH_COUNT_SQL = " SELECT count(*) FROM t_user WHERE user_name =? and password=? ";
    private final static String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET last_visit=?,last_ip=?,credits=?  WHERE user_id =?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        final String sqlStr = " SELECT user_id,user_name,credits FROM t_user WHERE user_name =? ";
        final User.UserBuilder userbuilder = User.builder();
        jdbcTemplate.query(sqlStr, new Object[]{userName}, rs -> {
            userbuilder.userId(rs.getInt("user_id"));
            userbuilder.userName(userName);
            userbuilder.credits(rs.getInt("credits"));
        });
        return userbuilder.build();
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
