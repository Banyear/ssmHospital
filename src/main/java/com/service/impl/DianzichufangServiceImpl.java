package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DianzichufangDao;
import com.entity.DianzichufangEntity;
import com.service.DianzichufangService;
import com.entity.vo.DianzichufangVO;
import com.entity.view.DianzichufangView;

@Service("dianzichufangService")
public class DianzichufangServiceImpl extends ServiceImpl<DianzichufangDao, DianzichufangEntity> implements DianzichufangService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DianzichufangEntity> page = this.selectPage(
                new Query<DianzichufangEntity>(params).getPage(),
                new EntityWrapper<DianzichufangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DianzichufangEntity> wrapper) {
		  Page<DianzichufangView> page =new Query<DianzichufangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DianzichufangVO> selectListVO(Wrapper<DianzichufangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DianzichufangVO selectVO(Wrapper<DianzichufangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DianzichufangView> selectListView(Wrapper<DianzichufangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DianzichufangView selectView(Wrapper<DianzichufangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
