package yalongz.component.service.impl;

import yalongz.component.dao.BaseDao;
import yalongz.component.service.BaseService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

public class BaseServiceImpl<T, I extends BaseDao<T>> extends ServiceImpl implements BaseService {

	public  I	baseDao;

	public I getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(I baseDao) {
		this.baseDao = baseDao;
	}

	
	

}
