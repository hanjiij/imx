//package com.mx.web.config;
//
//import static org.springframework.data.mongodb.core.query.Criteria.where;
//import static org.springframework.data.mongodb.core.query.Query.query;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.mx.web.bean.User;
//
//@Component
//public class UserSecurity implements UserDetailsService {
//
//	private static final Logger logger = LoggerFactory.getLogger(UserSecurity.class);
//
//	@Autowired
//	private MongoTemplate template;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = this.getByUsername(username);
//		return new org.springframework.security.core.userdetails.User(username,
//				user == null ? null : user.getPassword(), true, true, true, true, getAuthorities());
//	}
//
//	public User getByUsername(String username) {
//		logger.info("执行UserService.getByUsername()方法");
//		Query query = query(where("username").is(username));
//		return template.findOne(query, User.class);
//	}
//
//	private Collection<GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		return authList;
//	}
//}
