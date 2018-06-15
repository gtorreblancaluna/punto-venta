package mx.com.proyect.puntoventa.web.util.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//import org.acegisecurity.GrantedAuthority;
//import org.acegisecurity.GrantedAuthorityImpl;
//import org.acegisecurity.userdetails.User;
//import org.acegisecurity.userdetails.UserDetails;
//import org.acegisecurity.userdetails.UsernameNotFoundException;
//import org.acegisecurity.userdetails.jdbc.JdbcDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class VirtualAgencyJdbcDaoImpl extends JdbcDaoImpl {

	private Log log = LogFactory.getLog(VirtualAgencyJdbcDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}	
	public String query;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		// String query ="SELECT ds_login, ds_password, 1 FROM c_usuario WHERE
		// fg_activo='1' and ds_login=?";

		List<UserDetails> users = getJdbcTemplate().query(query, new String[] { username },
				new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);

						
						List<GrantedAuthority> authts = new ArrayList<GrantedAuthority>();
						authts.add(new SimpleGrantedAuthority("USER"));
						return new User(username, password, enabled, true, true, true, authts);
						// return new User(username, password, enabled, true,
						// true, true, AuthorityUtils.NO_AUTHORITIES);
					}

				});

		if (users.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		} else {
			return users.get(0);
		}
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
