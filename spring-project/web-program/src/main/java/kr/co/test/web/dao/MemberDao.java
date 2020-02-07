package kr.co.test.web.dao;

import kr.co.test.web.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class MemberDao { // DAO: Data Access Object

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    void init() {
        jdbcTemplate.update("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");
        jdbcTemplate.update("insert into member(username, password) values('shkim', '1234')");
    }

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insert(String username, String password) {
        jdbcTemplate.update("insert into member (username, password) values (?, ?)", username, password);

    }

    public List<Member> list() {
        return jdbcTemplate.query("select id, username, password from member",
                (resultSet, i) -> new Member(resultSet));
    }

}
