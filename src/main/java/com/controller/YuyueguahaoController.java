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

import com.entity.YuyueguahaoEntity;
import com.entity.view.YuyueguahaoView;

import com.service.YuyueguahaoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 预约挂号 
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@RestController
@RequestMapping("/yuyueguahao")
public class YuyueguahaoController {
    @Autowired
    private YuyueguahaoService yuyueguahaoService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyueguahaoEntity yuyueguahao, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yisheng")) {
			yuyueguahao.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("huanzhe")) {
			yuyueguahao.setHuanzhezhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();


		PageUtils page = yuyueguahaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyueguahao), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyueguahaoEntity yuyueguahao, 
		HttpServletRequest request){
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();

		PageUtils page = yuyueguahaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyueguahao), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyueguahaoEntity yuyueguahao){
       	EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyueguahao, "yuyueguahao")); 
        return R.ok().put("data", yuyueguahaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyueguahaoEntity yuyueguahao){
        EntityWrapper< YuyueguahaoEntity> ew = new EntityWrapper< YuyueguahaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyueguahao, "yuyueguahao")); 
		YuyueguahaoView yuyueguahaoView =  yuyueguahaoService.selectView(ew);
		return R.ok("查询预约挂号成功").put("data", yuyueguahaoView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyueguahaoEntity yuyueguahao = yuyueguahaoService.selectById(id);
        yuyueguahao = yuyueguahaoService.selectView(new EntityWrapper<YuyueguahaoEntity>().eq("id", id));
        return R.ok().put("data", yuyueguahao);
    }

    /**
     * 前台详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyueguahaoEntity yuyueguahao = yuyueguahaoService.selectById(id);
        yuyueguahao = yuyueguahaoService.selectView(new EntityWrapper<YuyueguahaoEntity>().eq("id", id));
        return R.ok().put("data", yuyueguahao);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YuyueguahaoEntity yuyueguahao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyueguahao);

        yuyueguahaoService.insert(yuyueguahao);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YuyueguahaoEntity yuyueguahao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyueguahao);

        yuyueguahaoService.insert(yuyueguahao);
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YuyueguahaoEntity yuyueguahao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyueguahao);
        yuyueguahaoService.updateById(yuyueguahao);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyueguahaoEntity> list = new ArrayList<YuyueguahaoEntity>();
        for(Long id : ids) {
            YuyueguahaoEntity yuyueguahao = yuyueguahaoService.selectById(id);
            yuyueguahao.setSfsh(sfsh);
            yuyueguahao.setShhf(shhf);
            list.add(yuyueguahao);
        }
        yuyueguahaoService.updateBatchById(list);
        return R.ok();
    }
    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yuyueguahaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	







    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yisheng")) {
            ew.eq("yishenggonghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("huanzhe")) {
            ew.eq("huanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = yuyueguahaoService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yisheng")) {
            ew.eq("yishenggonghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("huanzhe")) {
            ew.eq("huanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = yuyueguahaoService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yisheng")) {
            ew.eq("yishenggonghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("huanzhe")) {
            ew.eq("huanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = yuyueguahaoService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yisheng")) {
            ew.eq("yishenggonghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("huanzhe")) {
            ew.eq("huanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = yuyueguahaoService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yisheng")) {
            ew.eq("yishenggonghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("huanzhe")) {
            ew.eq("huanzhezhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = yuyueguahaoService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,YuyueguahaoEntity yuyueguahao, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yisheng")) {
            yuyueguahao.setYishenggonghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("huanzhe")) {
            yuyueguahao.setHuanzhezhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<YuyueguahaoEntity> ew = new EntityWrapper<YuyueguahaoEntity>();
        int count = yuyueguahaoService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyueguahao), params), params));
        return R.ok().put("data", count);
    }


}
