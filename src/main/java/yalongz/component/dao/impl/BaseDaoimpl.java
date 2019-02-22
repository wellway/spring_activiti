package yalongz.component.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import yalongz.component.dao.BaseDao;

public class BaseDaoimpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

}
