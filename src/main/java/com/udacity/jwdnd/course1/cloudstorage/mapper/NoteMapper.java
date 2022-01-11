package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Select("SELECT * FROM NOTES")
    List<Note> getNotes();

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note getNoteByTitle(String noteTitle);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle} AND notedescription = #{noteDescription} AND userid = #{userId}" +
            "WHERE noteid = #{noteIdToUpdate}")
    void updateNote(Integer noteIdToUpdate, Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);
}
