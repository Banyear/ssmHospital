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

import com.entity.DianzichufangEntity;
import com.entity.view.DianzichufangView;

import com.service.DianzichufangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 电子处方 
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@RestController
@RequestMapping("/dianzichufang")
public class DianzichufangController {
    @Autowired
    private DianzichufangService dianzichufangService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DianzichufangEntity dianzichufang, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yisheng")) {
			dianzichufang.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("huanzhe")) {
			dianzichufang.setHuanzhezhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<DianzichufangEntity> ew = new EntityWrapper<DianzichufangEntity>();


		PageUtils page = dianzichufangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dianzichufang), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DianzichufangEntity dianzichufang, 
		HttpServletRequest request){
        EntityWrapper<DianzichufangEntity> ew = new EntityWrapper<DianzichufangEntity>();

		PageUtils page = dianzichufangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dianzichufang), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DianzichufangEntity dianzichufang){
       	EntityWrapper<DianzichufangEntity> ew = new EntityWrapper<DianzichufangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dianzichufang, "dianzichufang")); 
        return R.ok().put("data", dianzichufangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DianzichufangEntity dianzichufang){
        EntityWrapper< DianzichufangEntity> ew = new EntityWrapper< DianzichufangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dianzichufang, "dianzichufang")); 
		DianzichufangView dianzichufangView =  dianzichufangService.selectView(ew);
		return R.ok("查询电子处方成功").put("data", dianzichufangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DianzichufangEntity dianzichufang = dianzichufangService.selectById(id);
        dianzichufang = dianzichufangService.selectView(new EntityWrapper<DianzichufangEntity>().eq("id", id));
        return R.ok().put("data", dianzichufang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DianzichufangEntity dianzichufang = dianzichufangService.selectById(id);
        dianzichufang = dianzichufangService.selectView(new EntityWrapper<DianzichufangEntity>().eq("id", id));
        return R.ok().put("data", dianzichufang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DianzichufangEntity dianzichufang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(dianzichufang);

        dianzichufangService.insert(dianzichufang);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DianzichufangEntity dianzichufang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(dianzichufang);

        dianzichufangService.insert(dianzichufang);
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DianzichufangEntity dianzichufang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dianzichufang);
        dianzichufangService.updateById(dianzichufang);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dianzichufangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
