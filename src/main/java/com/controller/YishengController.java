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

import com.entity.YishengEntity;
import com.entity.view.YishengView;

import com.service.YishengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 医生 
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@RestController
@RequestMapping("/yisheng")
public class YishengController {
    @Autowired
    private YishengService yishengService;


    @Autowired
    private StoreupService storeupService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		YishengEntity u = yishengService.selectOne(new EntityWrapper<YishengEntity>().eq("yishenggonghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(u.getId(), username,"yisheng",  "医生" );
		return R.ok().put("token", token);
	}

	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YishengEntity yisheng){
    	//ValidatorUtils.validateEntity(yisheng);
    	YishengEntity u = yishengService.selectOne(new EntityWrapper<YishengEntity>().eq("yishenggonghao", yisheng.getYishenggonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        if(yishengService.selectCount(new EntityWrapper<YishengEntity>().eq("yishenggonghao", yisheng.getYishenggonghao()))>0) {
            return R.error("医生工号已存在");
        }
		Long uId = new Date().getTime();
		yisheng.setId(uId);
        yishengService.insert(yisheng);
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
        YishengEntity u = yishengService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	YishengEntity u = yishengService.selectOne(new EntityWrapper<YishengEntity>().eq("yishenggonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
    	u.setMima("123456");
        yishengService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YishengEntity yisheng, 
		HttpServletRequest request){

        EntityWrapper<YishengEntity> ew = new EntityWrapper<YishengEntity>();


		PageUtils page = yishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yisheng), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YishengEntity yisheng, 
		HttpServletRequest request){
        EntityWrapper<YishengEntity> ew = new EntityWrapper<YishengEntity>();

		PageUtils page = yishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yisheng), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YishengEntity yisheng){
       	EntityWrapper<YishengEntity> ew = new EntityWrapper<YishengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yisheng, "yisheng")); 
        return R.ok().put("data", yishengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YishengEntity yisheng){
        EntityWrapper< YishengEntity> ew = new EntityWrapper< YishengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yisheng, "yisheng")); 
		YishengView yishengView =  yishengService.selectView(ew);
		return R.ok("查询医生成功").put("data", yishengView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YishengEntity yisheng = yishengService.selectById(id);
		yisheng.setClicktime(new Date());
		yishengService.updateById(yisheng);
        yisheng = yishengService.selectView(new EntityWrapper<YishengEntity>().eq("id", id));
        return R.ok().put("data", yisheng);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YishengEntity yisheng = yishengService.selectById(id);
		yisheng.setClicktime(new Date());
		yishengService.updateById(yisheng);
        yisheng = yishengService.selectView(new EntityWrapper<YishengEntity>().eq("id", id));
        return R.ok().put("data", yisheng);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YishengEntity yisheng, HttpServletRequest request){
        if(yishengService.selectCount(new EntityWrapper<YishengEntity>().eq("yishenggonghao", yisheng.getYishenggonghao()))>0) {
            return R.error("医生工号已存在");
        }
    	yisheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yisheng);
    	YishengEntity u = yishengService.selectOne(new EntityWrapper<YishengEntity>().eq("yishenggonghao", yisheng.getYishenggonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}

		yisheng.setId(new Date().getTime());
        yishengService.insert(yisheng);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YishengEntity yisheng, HttpServletRequest request){
        if(yishengService.selectCount(new EntityWrapper<YishengEntity>().eq("yishenggonghao", yisheng.getYishenggonghao()))>0) {
            return R.error("医生工号已存在");
        }
    	yisheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yisheng);
    	YishengEntity u = yishengService.selectOne(new EntityWrapper<YishengEntity>().eq("yishenggonghao", yisheng.getYishenggonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}

		yisheng.setId(new Date().getTime());
        yishengService.insert(yisheng);
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YishengEntity yisheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yisheng);
        if(yishengService.selectCount(new EntityWrapper<YishengEntity>().ne("id", yisheng.getId()).eq("yishenggonghao", yisheng.getYishenggonghao()))>0) {
            return R.error("医生工号已存在");
        }
        yishengService.updateById(yisheng);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yishengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YishengEntity yisheng, HttpServletRequest request,String pre){
        EntityWrapper<YishengEntity> ew = new EntityWrapper<YishengEntity>();
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
		PageUtils page = yishengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yisheng), params), params));
        return R.ok().put("data", page);
    }










}
