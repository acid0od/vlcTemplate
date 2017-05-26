/* ===========================================================================
 * Copyright (c) 2015 Comcast Corp. All rights reserved.
 * ===========================================================================
 *
 * Author: Alexander Ievstratiev
 * Created: 05/26/2017  3:10 PM
 */
package net.odtel.tv.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }*/
        
        Date date = new Date();
        date.setTime(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1));
        return new JwtUser(0L, "acid0od", "firstname", "lastname", "email@email", "password",
                Collections.emptyList(), true, date);
    }
}