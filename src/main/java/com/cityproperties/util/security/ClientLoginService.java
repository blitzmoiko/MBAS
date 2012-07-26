package com.cityproperties.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.util.Constants;

/**
 * The Class ClientLoginService
 */
public class ClientLoginService
        implements UserDetailsService {

    private ClientDAO clientDao;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    public UserDetails loadUserByUsername(String username) {
        if (username != null && !username.equals("")) {
            Client client = clientDao.findByUsername(username);
            if (client == null) {
                return null;
            }

            GrantedAuthority grantedAuth = null;
            if (client.getZuper()) {
                grantedAuth = new ClientGrantedAuthority(Constants.ROLE_ADMIN);
            } else {
                grantedAuth = new ClientGrantedAuthority(Constants.ROLE_USER);
            }

            CustomClient cu = new CustomClient(client.getClientId(),
                    client.getUsername(), client.getPassword(),
                    new GrantedAuthority[] { grantedAuth });
            return cu;
        } else {
            return null;
        }
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

}