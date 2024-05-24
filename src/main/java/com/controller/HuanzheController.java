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

import com.entity.HuanzheEntity;
import com.entity.view.HuanzheView;

import com.service.HuanzheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 患者 
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@RestController
@RequestMapping("/huanzhe")
public class HuanzheController {
    @Autowired
    private HuanzheService huanzheService;





    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(u.getId(), username,"huanzhe",  "患者" );
		return R.ok().put("token", token);
	}

	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody HuanzheEntity huanzhe){
    	//ValidatorUtils.validateEntity(huanzhe);
    	HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()))>0) {
            return R.error("患者账号已存在");
        }
		Long uId = new Date().getTime();
		huanzhe.setId(uId);
        huanzheService.insert(huanzhe);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        HuanzheEntity u = huanzheService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
    	u.setMima("123456");
        huanzheService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuanzheEntity huanzhe, 
		HttpServletRequest request){

        EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();


		PageUtils page = huanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huanzhe), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,HuanzheEntity huanzhe, 
		HttpServletRequest request){
        EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();

		PageUtils page = huanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huanzhe), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuanzheEntity huanzhe){
       	EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huanzhe, "huanzhe")); 
        return R.ok().put("data", huanzheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuanzheEntity huanzhe){
        EntityWrapper< HuanzheEntity> ew = new EntityWrapper< HuanzheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huanzhe, "huanzhe")); 
		HuanzheView huanzheView =  huanzheService.selectView(ew);
		return R.ok("查询患者成功").put("data", huanzheView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuanzheEntity huanzhe = huanzheService.selectById(id);
        huanzhe = huanzheService.selectView(new EntityWrapper<HuanzheEntity>().eq("id", id));
        return R.ok().put("data", huanzhe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuanzheEntity huanzhe = huanzheService.selectById(id);
        huanzhe = huanzheService.selectView(new EntityWrapper<HuanzheEntity>().eq("id", id));
        return R.ok().put("data", huanzhe);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()))>0) {
            return R.error("患者账号已存在");
        }
    	huanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(huanzhe);
    	HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}

		huanzhe.setId(new Date().getTime());
        huanzheService.insert(huanzhe);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()))>0) {
            return R.error("患者账号已存在");
        }
    	huanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(huanzhe);
    	HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}

		huanzhe.setId(new Date().getTime());
        huanzheService.insert(huanzhe);
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huanzhe);
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().ne("id", huanzhe.getId()).eq("huanzhezhanghao", huanzhe.getHuanzhezhanghao()))>0) {
            return R.error("患者账号已存在");
        }
        huanzheService.updateById(huanzhe);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        huanzheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
