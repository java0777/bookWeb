package com.demo.bookWeb.controller;

import com.demo.book.jdbc.model.entity.Book;
import com.demo.book.jdbc.model.service.IBookService;
import com.demo.model.entity.AppResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
@RequestMapping("/appApi")
public class AppAPI {
private final Logger logger =Logger.getLogger(AppAPI.class);
    @Resource
    private IBookService bookService;
    @RequestMapping(value = "/queryBook", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppResult queryBook(@RequestParam String bookName, String author) {
        AppResult result = new AppResult();
        logger.info("开始查询书籍");
        Book book = bookService.queryBook(bookName, author);
        if(book!=null){
            logger.info("数据库找到书籍");
            result.setSuccess(true);
            result.setData(book);
            return result;
        }
        Book catchBook = null;
        logger.info("开始尝试抓取书籍");
        try {
            catchBook = bookService.catchBook(bookName, author);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(bookName+"：抓取失败");
            logger.info("抓取异常");
            result.setMess("服务器出错了，请稍后再试");
            result.setSuccess(false);
            return result;
        }
        if (catchBook==null){
            logger.info("尝试抓取失败，暂无该书籍资源");
            result.setSuccess(false);
            result.setMess("书籍暂无资源");
            return result;
        }
        logger.info("尝试抓取成功，返回书籍信息");
        result.setSuccess(true);
        result.setData(catchBook);
        return result;
    }


    @RequestMapping(value = "/getTitleContent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppResult getTitleContent(@RequestParam Integer bookId,@RequestParam Integer titleId){
        AppResult result = new AppResult();
        try {
            String content = bookService.getTitleContent(bookId, titleId);
            if(content!=null&&content.length()>0){
                result.setSuccess(true);
                result.setData(content);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("书籍："+bookId+"，章节："+titleId+" 获取失败");
            result.setMess("服务器出错了，请稍后再试");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(false);
        result.setMess("章节暂无资源");
        return result;
    }
}
