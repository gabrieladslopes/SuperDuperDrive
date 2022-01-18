package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int addCredential(Credential credential) {
        return credentialMapper.insert(credential);
    }

    public List<Credential> getCredentialsByUser(Integer userId) {
        return credentialMapper.getCredentialByUserId(userId);
    }

    public void updateCredential(Credential credential) {
        credentialMapper.update(credential);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}