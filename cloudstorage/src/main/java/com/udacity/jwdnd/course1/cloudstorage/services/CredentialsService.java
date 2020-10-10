package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService, UserService userService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public int addCredential(CredentialsForm credentialsForm, String userName) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return credentialsMapper.insert(new Credentials(null, credentialsForm.getUrl(), credentialsForm.getUserName(),
                encodedKey, encryptionService.encryptValue(credentialsForm.getPassword(), encodedKey),
                userService.getUser(userName).getUserId()));
    }

    public List<Credentials> getCredentials(String userName) {
        return this.credentialsMapper.getCredentials(userService.getUser(userName).getUserId());
    }

    public int deleteCredential(Integer credentialId)
    {
        return this.credentialsMapper.delete(credentialId);
    }

}