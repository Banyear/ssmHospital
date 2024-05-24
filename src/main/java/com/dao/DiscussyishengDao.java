package com.dao;

import com.entity.DiscussyishengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyishengVO;
import com.entity.view.DiscussyishengView;


/**
 * 医生评论表
 * 
 * @author 
 * @email 
 * @date 2024-04-22 16:28:32
 */
public interface DiscussyishengDao extends BaseMapper<DiscussyishengEntity> {
	
	List<DiscussyishengVO> selectListVO(@Param("ew") Wrapper<DiscussyishengEntity> wrapper);
	
	DiscussyishengVO selectVO(@Param("ew") Wrapper<DiscussyishengEntity> wrapper);
	
	List<DiscussyishengView> selectListView(@Param("ew") Wrapper<DiscussyishengEntity> wrapper);

	List<DiscussyishengView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyishengEntity> wrapper);

	
	DiscussyishengView selectView(@Param("ew") Wrapper<DiscussyishengEntity> wrapper);
	

}
