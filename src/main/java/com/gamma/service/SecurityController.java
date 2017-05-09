package com.gamma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;

/**
 * Created by rick on 5/9/17.
 */
@RestController(value = "/user")
public class SecurityController {
	@Autowired
	@Qualifier("ldapTemplate")
	LdapTemplate ldapTemplate;

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public SuccessObject createUserEndpoint(@RequestParam("username") String username,
											@RequestParam("firstname") String firstname,
											@RequestParam("lastname") String lastname,
											@RequestParam("password") String password) {
		try {
			createUser(username, firstname, lastname, password);
			return new SuccessObject(true, null);
		} catch (Exception e) {
			return new SuccessObject(false, e.toString());
		}
	}

	@RequestMapping(value = "/changepassword")
	public SuccessObject updatePasswordEndpoint(@RequestParam("newpassword") String newPassword,
												@RequestParam("newpasswordrepeat") String newPasswordRepeat,
												Principal principal) {
		if (!newPassword.equals(newPasswordRepeat))
			return new SuccessObject(false, "newPassword and newPasswordRepeat are not identical");
		try {
			changePassword(principal.getName(), newPassword);
			return new SuccessObject(true, null);
		} catch (Exception e) {
			return new SuccessObject(false, e.toString());
		}
	}

	/**
	 * Create a new user on the LDAP server, raising an exception when that fails.
	 * @param username The username
	 * @param firstname The first name
	 * @param lastname The last name
	 * @param password The password
	 */
	private void createUser(String username, String firstname, String lastname, String password) {
		//create a new ldap object, with the required attributes
		Attributes attributes = new BasicAttributes();
		BasicAttribute classAttribute = new BasicAttribute("objectClass");
		classAttribute.add("inetOrgPerson");
		classAttribute.add("organizationalPerson");
		classAttribute.add("person");
		classAttribute.add("simpleSecurityObject");
		attributes.put(classAttribute);

		attributes.put("cn", firstname);
		attributes.put("sn", lastname);
		attributes.put("userPassword", generatePassword(password));

		//Send the object to the ldap server
		//TODO ldap escape
		ldapTemplate.bind("uid=" + username + ",ou=users", null, attributes);

	}

	/**
	 * Change the password of a user.
	 * @param username The username
	 * @param newPassword The password
	 * @exception com.unboundid.ldap.sdk.LDAPException when the LDAP query fails
	 */
	private void changePassword(String username, String newPassword) {
		//Create a modification item, replacing the password
		ModificationItem modItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				new BasicAttribute("userPassword", generatePassword(newPassword)));

		//Send the modification object
		ldapTemplate.modifyAttributes("uid=" + username + ",ou=users", new ModificationItem[]{modItem});
	}

	/**
	 * Generate a hash object for the LDAP server using SHA(1)
	 * @param password The password to hash
	 * @return A String representing the original password
	 */
	private static String generatePassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			String result = Base64.getEncoder().encodeToString(digest);
			return "{SHA}" + result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * A class with a boolean success value, and a reason string.
	 * It is meant for serialisation to notify a client of the response.
	 */
	private class SuccessObject {
		private boolean success;
		private String reason;

		public SuccessObject(boolean success, String reason) {
			this.success = success;
			this.reason = reason;
		}

		public SuccessObject() {
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
	}
}
