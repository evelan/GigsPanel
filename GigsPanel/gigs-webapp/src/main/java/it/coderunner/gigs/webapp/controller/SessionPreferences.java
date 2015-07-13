package it.coderunner.gigs.webapp.controller;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.users.IUserService;
import it.coderunner.gigs.webapp.utils.SecurityUtils;

import java.io.Serializable;

import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionPreferences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;
	@Setter
	private String displayName;

	public String getDisplayName() {
		if (StringUtils.isBlank(displayName)) {
			if (SecurityUtils.isUserLogged()) {
				User user = userService.uniqueObject(Users.findAll()
						.withUsername(SecurityUtils.getLoggedUsername()));
				displayName = user.getUsername();
			} else {
				displayName = "Niezalogowany";
			}
		}
		return displayName;
	}

}
