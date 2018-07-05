package exchange.platform.authentication.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exchange.platform.authentication.domain.Employee;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午10:56:59</p>
 * @author Tony
 * @version 1.0
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = new Employee();
        employee.setId("a");
        employee.setPassword("1111");
        employee.setUsername("1111");
        return new User(employee.getUsername(), employee.getPassword(), emptyList());
    }
}
