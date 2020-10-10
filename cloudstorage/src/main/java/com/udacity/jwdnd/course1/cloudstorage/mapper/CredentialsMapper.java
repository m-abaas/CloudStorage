package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CredentialsMapper {
    //@Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    //Credentials getCredentials(String username);
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credentials> getCredentials(Integer userId);


    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialID = #{credentialID}")
    int delete(Integer credentialID);

}
