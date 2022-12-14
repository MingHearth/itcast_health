package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.List;

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    //新增检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.pageQuery(queryPageBean);
    }

    //根据ID查询检查组
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);//新增成功
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);//查询失败
        }
    }

    //    @RequestMapping("/findCheckItemIdsByCheckGroupId")
//    //根据检查组ID查询检查组包含的多个检查项ID
//    public Result findCheckItemIdsByCheckGroupId(Integer id) {
//        try {
//            List<Integer> checkitemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);
//            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitemIds);//查询成功
//        } catch (Exception e) {
//            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);//查询失败
//        }
//    }
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            List<Integer> checkitemIds =
                    checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    //新增检查组
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);//新增失败
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);//新增成功
    }


    //新增检查组
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckGroup> list = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);//查询成功
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);//查询失败
        }
    }
}
