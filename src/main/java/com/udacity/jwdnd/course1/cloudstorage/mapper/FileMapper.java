package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            "VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFileByFilename(String filename);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getFileByUserId(Integer userId);

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Update("UPDATE FILES SET filename = #{filename} AND contentType = #{contentType} AND filesize = #{fileSize} AND userid = @{userId} AND filedata = #{fileData}" +
            "WHERE filename = #{filenameToUpdate}")
    void update(String filenameToUpdate, File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void delete(String fileId);
}
