package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DianzichufangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DianzichufangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DianzichufangView;


/**
 * 电子处方
 *
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
public interface DianzichufangService extends IService<DianzichufangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DianzichufangVO> selectListVO(Wrapper<DianzichufangEntity> wrapper);
   	
   	DianzichufangVO selectVO(@Param("ew") Wrapper<DianzichufangEntity> wrapper);
   	
   	List<DianzichufangView> selectListView(Wrapper<DianzichufangEntity> wrapper);
   	
   	DianzichufangView selectView(@Param("ew") Wrapper<DianzichufangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DianzichufangEntity> wrapper);

   	

}

