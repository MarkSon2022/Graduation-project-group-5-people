package sse.edu.SPR2024.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoDetailService implements UserDetailsService {

//    @Autowired
//    private IAccountService iAccountService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Account account = iAccountService.GetAccountByEmail(email);
//        List<String> accountRoles = new ArrayList<>();
//        //accountRoles.add(account.GetRoleName());
//        List<String> hasRoles = accountRoles != null && accountRoles.size() > 0 ? accountRoles : null;
//        System.out.println("UserInfo: " + hasRoles == null);
        List<SimpleGrantedAuthority> roles = List.of();

//        UserDetails userDetails = User.builder().username(email)
//                .password(account.getPassword())
//                .roles(roles);
//                .build();
        UserDetails userDetails = new User("A", "A", roles);
        System.out.println(userDetails.getAuthorities());
        return userDetails;
    }
}
