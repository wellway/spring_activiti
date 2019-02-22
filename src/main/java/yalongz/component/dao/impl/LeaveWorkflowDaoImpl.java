package yalongz.component.dao.impl;

import yalongz.component.dao.LeaveWorkflowDao;
import yalongz.entity.Leave;

public class LeaveWorkflowDaoImpl extends BaseDaoimpl<Leave> implements LeaveWorkflowDao {
	public int SaveLeave(Leave entity) {
		return this.getSqlSession().insert("insertLeave", entity);
	}

	public Leave getLeave(Long id) {
		return this.getSqlSession().selectOne("getLeave", id);
	}
}
