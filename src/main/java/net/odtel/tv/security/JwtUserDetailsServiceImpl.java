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

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Date date = new Date();
        date.setTime(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1));
        if (username.equals("acid0od")) {
            return new JwtUser(0L, "acid0od", "firstname", "lastname", "email@email", "password",
                    Collections.emptyList(), true, date);
        } else if (username.equals("serbin")) {
            return new JwtUser(0L, "serbin", "firstname", "lastname", "email@email", "password",
                    Collections.emptyList(), true, date);
        } else {
            throw new UsernameNotFoundException("UsernameNotFoundException userName=" + username);
        }
    }
}