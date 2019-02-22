package yalongz.controller;

import java.util.Arrays;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InitData implements InitializingBean {
	@Autowired
	protected RepositoryService	repositoryService;
	@Autowired
	private IdentityService		identityService;

	@Override
	public void afterPropertiesSet() throws Exception {
		initDemoGroups();
		initDemoUsers();
		initProcessDefinitions();

	}

	protected void initProcessDefinitions() {

		String deploymentName = "leave";
		List<Deployment> deploymentList = repositoryService.createDeploymentQuery().deploymentName(deploymentName).list();

		if (deploymentList == null || deploymentList.isEmpty()) {
			repositoryService.createDeployment()
					.name(deploymentName)
					.addClasspathResource("diagrams/leave/leave.bpmn")
					.addClasspathResource("diagrams/leave/leave.png")
					.deploy();
		}

	}

	protected void initDemoUsers() {
		createUser("admin", "Henry", "Yan", "000000", "henry.yan@kafeitu.me",
				"", Arrays.asList("user", "admin"), null);

		createUser("hruser", "Lili", "Zhang", "000000", "lili.zhang@kafeitu.me",
				"", Arrays.asList("hr", "user"), null);

		createUser("leaderuser", "Jhon", "Li", "000000", "jhon.li@kafeitu.me",
				"", Arrays.asList("deptLeader", "user"), null);

		createUser("kafeitu", "Coffee", "Rabbit", "000000", "zhaoyalongyong@163.com",
				"", Arrays.asList("user", "admin"), null);
	}

	protected void createUser(String userId, String firstName, String lastName, String password,
			String email, String imageResource, List<String> groups, List<String> userInfo) {

		if (identityService.createUserQuery().userId(userId).count() == 0) {

			// Following data can already be set by demo setup script
			User user = identityService.newUser(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			identityService.saveUser(user);

			if (groups != null) {
				for (String group : groups) {
					identityService.createMembership(userId, group);
				}
			}
		}

		// Following data is not set by demo setup script

		// image
		if (imageResource != null) {
			byte[] pictureBytes = IoUtil.readInputStream(this.getClass().getClassLoader().getResourceAsStream(imageResource), null);
			Picture picture = new Picture(pictureBytes, "image/jpeg");
			identityService.setUserPicture(userId, picture);
		}

		// user info
		if (userInfo != null) {
			for (int i = 0; i < userInfo.size(); i += 2) {
				identityService.setUserInfo(userId, userInfo.get(i), userInfo.get(i + 1));
			}
		}
	}

	protected void initDemoGroups() {
		String[] assignmentGroups = new String[] { "deptLeader", "hr" };
		for (String groupId : assignmentGroups) {
			createGroup(groupId, "assignment");
		}

		String[] securityGroups = new String[] { "user", "admin" };
		for (String groupId : securityGroups) {
			createGroup(groupId, "security-role");
		}
	}

	protected void createGroup(String groupId, String type) {
		if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
			Group newGroup = identityService.newGroup(groupId);
			newGroup.setName(groupId.substring(0, 1).toUpperCase() + groupId.substring(1));
			newGroup.setType(type);
			identityService.saveGroup(newGroup);
		}
	}
}
