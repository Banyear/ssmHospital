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

import com.entity.MenzhenbingliEntity;
import com.entity.view.MenzhenbingliView;

import com.service.MenzhenbingliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 门诊病历 
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@RestController
@RequestMapping("/menzhenbingli")
public class MenzhenbingliController {
    @Autowired
    private MenzhenbingliService menzhenbingliService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MenzhenbingliEntity menzhenbingli, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huanzhe")) {
			menzhenbingli.setHuanzhezhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			menzhenbingli.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<MenzhenbingliEntity> ew = new EntityWrapper<MenzhenbingliEntity>();


		PageUtils page = menzhenbingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, menzhenbingli), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MenzhenbingliEntity menzhenbingli, 
		HttpServletRequest request){
        EntityWrapper<MenzhenbingliEntity> ew = new EntityWrapper<MenzhenbingliEntity>();

		PageUtils page = menzhenbingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, menzhenbingli), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MenzhenbingliEntity menzhenbingli){
       	EntityWrapper<MenzhenbingliEntity> ew = new EntityWrapper<MenzhenbingliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( menzhenbingli, "menzhenbingli")); 
        return R.ok().put("data", menzhenbingliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MenzhenbingliEntity menzhenbingli){
        EntityWrapper< MenzhenbingliEntity> ew = new EntityWrapper< MenzhenbingliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( menzhenbingli, "menzhenbingli")); 
		MenzhenbingliView menzhenbingliView =  menzhenbingliService.selectView(ew);
		return R.ok("查询门诊病历成功").put("data", menzhenbingliView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MenzhenbingliEntity menzhenbingli = menzhenbingliService.selectById(id);
        menzhenbingli = menzhenbingliService.selectView(new EntityWrapper<MenzhenbingliEntity>().eq("id", id));
        return R.ok().put("data", menzhenbingli);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MenzhenbingliEntity menzhenbingli = menzhenbingliService.selectById(id);
        menzhenbingli = menzhenbingliService.selectView(new EntityWrapper<MenzhenbingliEntity>().eq("id", id));
        return R.ok().put("data", menzhenbingli);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MenzhenbingliEntity menzhenbingli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(menzhenbingli);

        menzhenbingliService.insert(menzhenbingli);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MenzhenbingliEntity menzhenbingli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(menzhenbingli);

        menzhenbingliService.insert(menzhenbingli);
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MenzhenbingliEntity menzhenbingli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(menzhenbingli);
        menzhenbingliService.updateById(menzhenbingli);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        menzhenbingliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
