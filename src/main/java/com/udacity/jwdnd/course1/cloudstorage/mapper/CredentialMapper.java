package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, userid)" +
            "VALUES (#{url}, #{username}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredentialByCredentialId(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getCredentialByUserId(Integer userId);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAllCredentials();

    @Update("UPDATE CREDENTIALS SET url = #{url} AND username = #{username} AND key = #{key}" +
            "WHERE credentialid = #{credentialIdToUpdate}")
    void update(Integer credentialIdToUpdate, Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHEREE credentialid = #{credentialId}")
    void delete(Integer credentialId);
}
