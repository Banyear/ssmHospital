package com.entity.vo;

import com.entity.DianzichufangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 电子处方
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
public class DianzichufangVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 医生姓名
	 */
	
	private String yishengxingming;
		
	/**
	 * 病历编号
	 */
	
	private String binglibianhao;
		
	/**
	 * 患者账号
	 */
	
	private String huanzhezhanghao;
		
	/**
	 * 患者姓名
	 */
	
	private String huanzhexingming;
		
	/**
	 * 诊断
	 */
	
	private String zhenduan;
		
	/**
	 * 治疗方案
	 */
	
	private String zhiliaofangan;
		
	/**
	 * 医嘱
	 */
	
	private String yizhu;
		
	/**
	 * 处方图
	 */
	
	private String chufangtu;
				
	
	/**
	 * 设置：医生姓名
	 */
	 
	public void setYishengxingming(String yishengxingming) {
		this.yishengxingming = yishengxingming;
	}
	
	/**
	 * 获取：医生姓名
	 */
	public String getYishengxingming() {
		return yishengxingming;
	}
				
	
	/**
	 * 设置：病历编号
	 */
	 
	public void setBinglibianhao(String binglibianhao) {
		this.binglibianhao = binglibianhao;
	}
	
	/**
	 * 获取：病历编号
	 */
	public String getBinglibianhao() {
		return binglibianhao;
	}
				
	
	/**
	 * 设置：患者账号
	 */
	 
	public void setHuanzhezhanghao(String huanzhezhanghao) {
		this.huanzhezhanghao = huanzhezhanghao;
	}
	
	/**
	 * 获取：患者账号
	 */
	public String getHuanzhezhanghao() {
		return huanzhezhanghao;
	}
				
	
	/**
	 * 设置：患者姓名
	 */
	 
	public void setHuanzhexingming(String huanzhexingming) {
		this.huanzhexingming = huanzhexingming;
	}
	
	/**
	 * 获取：患者姓名
	 */
	public String getHuanzhexingming() {
		return huanzhexingming;
	}
				
	
	/**
	 * 设置：诊断
	 */
	 
	public void setZhenduan(String zhenduan) {
		this.zhenduan = zhenduan;
	}
	
	/**
	 * 获取：诊断
	 */
	public String getZhenduan() {
		return zhenduan;
	}
				
	
	/**
	 * 设置：治疗方案
	 */
	 
	public void setZhiliaofangan(String zhiliaofangan) {
		this.zhiliaofangan = zhiliaofangan;
	}
	
	/**
	 * 获取：治疗方案
	 */
	public String getZhiliaofangan() {
		return zhiliaofangan;
	}
				
	
	/**
	 * 设置：医嘱
	 */
	 
	public void setYizhu(String yizhu) {
		this.yizhu = yizhu;
	}
	
	/**
	 * 获取：医嘱
	 */
	public String getYizhu() {
		return yizhu;
	}
				
	
	/**
	 * 设置：处方图
	 */
	 
	public void setChufangtu(String chufangtu) {
		this.chufangtu = chufangtu;
	}
	
	/**
	 * 获取：处方图
	 */
	public String getChufangtu() {
		return chufangtu;
	}
			
}
