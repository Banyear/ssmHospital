package com.entity.view;

import com.entity.HuanzheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 患者
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
@TableName("huanzhe")
public class HuanzheView  extends HuanzheEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public HuanzheView(){
	}
 
 	public HuanzheView(HuanzheEntity huanzheEntity){
 	try {
			BeanUtils.copyProperties(this, huanzheEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
