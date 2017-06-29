package com.demo.bookWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
@Controller
public class TestController {
    @RequestMapping("/books")
    @ResponseBody
    public List foo() {
      List list = new ArrayList<>();
      list.add("亵渎");
      list.add("罪恶之城");
      list.add("狩魔手记");
      list.add("仙缘");
      list.add("永夜君王");
      return list;
    }

    @RequestMapping("/content")
    @ResponseBody
    public String foo(@RequestBody Map<String,Object> data) {
        Object bookName=data.get("bookName");
        if(bookName==null){
            return null;
        }
        if(bookName.equals("亵渎")){
            return "一个胖子的传说";
        }
        if(bookName.equals("罪恶之城")){
            return "无尽的位面战争";
        }
        return "内容暂无";
    }
}
