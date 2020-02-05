package kr.co.test.cli.dao;

import kr.co.test.cli.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class MemberDao { // DAO: Data Access Object

    private JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insert(String username, String password) throws SQLException {
        jdbcTemplate.update("insert into member (username, password) values (?, ?)", username, password);

    }

    public void print() throws SQLException {
        List<Member> list = jdbcTemplate.query("select id, username, password from member",
                (resultSet, i) -> new Member(resultSet));
        list.forEach(x -> log.info(">> Member: " + x.getUsername() + "/" + x.getPassword()));
    }

}
