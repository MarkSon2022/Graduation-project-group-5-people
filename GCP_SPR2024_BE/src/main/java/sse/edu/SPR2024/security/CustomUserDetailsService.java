//package sse.edu.SPR2024.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import sse.edu.SPR2024.entity.Account;
//import sse.edu.SPR2024.service.IAccountService;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private IAccountService accountService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account user = accountService.GetAccountByEmail(username);
//        List<String> roles = new ArrayList<>();
//        try{
//            roles.add(user.GetRoleName());
//        }catch (Exception e){
//        }
//        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(roles));
//    }
//
//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
//    }
//}