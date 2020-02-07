package kr.co.test.web.service;

import kr.co.test.web.dao.MemberDao;
import kr.co.test.web.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class MemberService {
    private MemberDao memberDao;

    @Transactional
    public void insert(String username, String password) {
        memberDao.insert(username, password);
    }

    public List<Member> list() {
        return memberDao.list();
    }
}
