package it.dinfo.stlab;

import it.dinfo.stlab.dao.UserAccountDao;
import it.dinfo.stlab.model.user.UserAccount;
import it.dinfo.stlab.model.user.UserRole;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.UUID;

@Startup
@Singleton
public class Initializer {

	@Inject
	private UserAccountDao userAccountDao;

	@PostConstruct
	public void init() {
		try {
			userAccountDao.findByEmail("superadmin@mail.com");
		} catch (NoResultException e) {
			UserAccount superAdmin = new UserAccount();
			superAdmin.setId(UUID.randomUUID().toString());
			superAdmin.setName("Super");
			superAdmin.setSurname("Admin");
			superAdmin.setEmail("superadmin@mail.com");
			superAdmin.assignPassword("password");
			superAdmin.setUserRole(UserRole.SUPER_ADMIN);
			userAccountDao.save(superAdmin);
		}

		try {
			userAccountDao.findByEmail("admin@mail.com");
		} catch (NoResultException e) {
			UserAccount admin = new UserAccount();
			admin.setId(UUID.randomUUID().toString());
			admin.setName("Simple");
			admin.setSurname("Admin");
			admin.setEmail("admin@mail.com");
			admin.assignPassword("password");
			admin.setUserRole(UserRole.ADMIN);
			userAccountDao.save(admin);
		}
	}
}
