package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public int addCredential(CredentialsForm credentialsForm) {
        return credentialsMapper.insert(new Credentials(null, credentialsForm.getUrl(), credentialsForm.getUserName(), "2", credentialsForm.getPassword(), 1));
    }

    public List<Credentials> getCredentials() {
        return this.credentialsMapper.getCredentials();
    }

}