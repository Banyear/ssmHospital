package com.entity.view;

import com.entity.DianzichufangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 电子处方
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@TableName("dianzichufang")
public class DianzichufangView  extends DianzichufangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DianzichufangView(){
	}
 
 	public DianzichufangView(DianzichufangEntity dianzichufangEntity){
 	try {
			BeanUtils.copyProperties(this, dianzichufangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
