package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MenzhenbingliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MenzhenbingliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MenzhenbingliView;


/**
 * 门诊病历
 *
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
public interface MenzhenbingliService extends IService<MenzhenbingliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MenzhenbingliVO> selectListVO(Wrapper<MenzhenbingliEntity> wrapper);
   	
   	MenzhenbingliVO selectVO(@Param("ew") Wrapper<MenzhenbingliEntity> wrapper);
   	
   	List<MenzhenbingliView> selectListView(Wrapper<MenzhenbingliEntity> wrapper);
   	
   	MenzhenbingliView selectView(@Param("ew") Wrapper<MenzhenbingliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MenzhenbingliEntity> wrapper);

   	

}

