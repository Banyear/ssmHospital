package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussyishengEntity;
import com.entity.view.DiscussyishengView;

import com.service.DiscussyishengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 医生评论表 
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-22 16:28:32
 */
@RestController
@RequestMapping("/discussyisheng")
public class DiscussyishengController {
    @Autowired
    private DiscussyishengService discussyishengService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyishengEntity discussyisheng, 
		HttpServletRequest request){

        EntityWrapper<DiscussyishengEntity> ew = new EntityWrapper<DiscussyishengEntity>();


		PageUtils page = discussyishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyisheng), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussyishengEntity discussyisheng, 
		HttpServletRequest request){
        EntityWrapper<DiscussyishengEntity> ew = new EntityWrapper<DiscussyishengEntity>();

		PageUtils page = discussyishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyisheng), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyishengEntity discussyisheng){
       	EntityWrapper<DiscussyishengEntity> ew = new EntityWrapper<DiscussyishengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyisheng, "discussyisheng")); 
        return R.ok().put("data", discussyishengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyishengEntity discussyisheng){
        EntityWrapper< DiscussyishengEntity> ew = new EntityWrapper< DiscussyishengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyisheng, "discussyisheng")); 
		DiscussyishengView discussyishengView =  discussyishengService.selectView(ew);
		return R.ok("查询医生评论表成功").put("data", discussyishengView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyishengEntity discussyisheng = discussyishengService.selectById(id);
        discussyisheng = discussyishengService.selectView(new EntityWrapper<DiscussyishengEntity>().eq("id", id));
        return R.ok().put("data", discussyisheng);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyishengEntity discussyisheng = discussyishengService.selectById(id);
        discussyisheng = discussyishengService.selectView(new EntityWrapper<DiscussyishengEntity>().eq("id", id));
        return R.ok().put("data", discussyisheng);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussyishengEntity discussyisheng, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussyisheng);

        discussyishengService.insert(discussyisheng);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyishengEntity discussyisheng, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussyisheng);

        discussyishengService.insert(discussyisheng);
        return R.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyishengEntity discussyisheng = discussyishengService.selectOne(new EntityWrapper<DiscussyishengEntity>().eq("", username));
        return R.ok().put("data", discussyisheng);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyishengEntity discussyisheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyisheng);
        discussyishengService.updateById(discussyisheng);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussyishengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyishengEntity discussyisheng, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyishengEntity> ew = new EntityWrapper<DiscussyishengEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        
        params.put("order", "desc");
		PageUtils page = discussyishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyisheng), params), params));
        return R.ok().put("data", page);
    }










}
