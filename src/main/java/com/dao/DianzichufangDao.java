package com.dao;

import com.entity.DianzichufangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DianzichufangVO;
import com.entity.view.DianzichufangView;


/**
 * 电子处方
 * 
 * @author 
 * @email 
 * @date 2024-04-22 16:28:31
 */
public interface DianzichufangDao extends BaseMapper<DianzichufangEntity> {
	
	List<DianzichufangVO> selectListVO(@Param("ew") Wrapper<DianzichufangEntity> wrapper);
	
	DianzichufangVO selectVO(@Param("ew") Wrapper<DianzichufangEntity> wrapper);
	
	List<DianzichufangView> selectListView(@Param("ew") Wrapper<DianzichufangEntity> wrapper);

	List<DianzichufangView> selectListView(Pagination page,@Param("ew") Wrapper<DianzichufangEntity> wrapper);

	
	DianzichufangView selectView(@Param("ew") Wrapper<DianzichufangEntity> wrapper);
	

}
