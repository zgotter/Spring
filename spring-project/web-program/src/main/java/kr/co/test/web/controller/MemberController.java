package kr.co.test.web.controller;

import kr.co.test.web.entity.Member;
import kr.co.test.web.model.MemberDto;
import kr.co.test.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        List<Member> list = memberService.list();
        mv.addObject("members", list);
        return mv;
    }

    @RequestMapping("/create")
    public String create(MemberDto dto) {
        memberService.insert(dto.getUsername(), dto.getPassword());
        return "redirect:index";
    }

    //
    //
}
