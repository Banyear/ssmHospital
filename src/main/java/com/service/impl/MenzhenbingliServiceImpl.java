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


import com.dao.MenzhenbingliDao;
import com.entity.MenzhenbingliEntity;
import com.service.MenzhenbingliService;
import com.entity.vo.MenzhenbingliVO;
import com.entity.view.MenzhenbingliView;

@Service("menzhenbingliService")
public class MenzhenbingliServiceImpl extends ServiceImpl<MenzhenbingliDao, MenzhenbingliEntity> implements MenzhenbingliService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MenzhenbingliEntity> page = this.selectPage(
                new Query<MenzhenbingliEntity>(params).getPage(),
                new EntityWrapper<MenzhenbingliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MenzhenbingliEntity> wrapper) {
		  Page<MenzhenbingliView> page =new Query<MenzhenbingliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<MenzhenbingliVO> selectListVO(Wrapper<MenzhenbingliEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MenzhenbingliVO selectVO(Wrapper<MenzhenbingliEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MenzhenbingliView> selectListView(Wrapper<MenzhenbingliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MenzhenbingliView selectView(Wrapper<MenzhenbingliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
