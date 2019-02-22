package yalongz.component.dao;

import yalongz.entity.Leave;

public interface LeaveWorkflowDao extends BaseDao<Leave> {
	public int SaveLeave(Leave entity);

	public Leave getLeave(Long id);
}
