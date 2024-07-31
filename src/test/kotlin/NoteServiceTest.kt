import org.junit.Test

import org.junit.Assert.*
import ru.netology.Note
import ru.netology.NoteService

class NoteServiceTest {

    @Test
    fun add() {
        val noteService = NoteService()
        val size = noteService.notes.size
        noteService.add("first note", "test")
        assertEquals(size+1, noteService.notes.size)
    }

    @Test
    fun createComment() {
        val noteService = NoteService()
        val size = noteService.comments.size
        noteService.add("first note", "test")
        noteService.createComment(1,"test")
        assertEquals(size+1, noteService.comments.size)
    }

    @Test
    fun delete() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        noteService.delete(1)
        val note = noteService.notes.find { it.id == 1 }
        val result = note?.isDeleted
        assertTrue(result ?: throw IllegalArgumentException("No note with entered id"))
    }

    @Test
    fun deleteComment() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        noteService.createComment(1,"test")
        val comment = noteService.comments.find { it.id == 1 }
        noteService.deleteComment(1)
        assertTrue(comment?.isDeleted ?: throw IllegalArgumentException("No comment with entered id"))
    }

    @Test
    fun edit() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        val newTitle = "edit first note"
        val newContent = "new content"
        noteService.edit(1, newTitle, newContent)
        val note = noteService.notes.find { it.id == 1 }
        assertTrue((note?.title == newTitle) && (note?.content == newContent))
    }

    @Test
    fun editComment() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        noteService.createComment(1, "comment")
        val newComment = "new comment"
        noteService.editComment(1, newComment)
        val comment = noteService.comments.find { it.id == 1 }
        assertTrue(comment?.content == newComment)
    }

    @Test
    fun get() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        noteService.add("second note", "test")
        noteService.add("three note", "test")
        val list = noteService.get()
        assertTrue(list != emptyList<Note>())
    }

    @Test
    fun getById() {
        val noteService = NoteService()
        val note = Note(2, "second note", "test")
        noteService.add("first note", "test")
        noteService.add("second note", "test")
        assertTrue(noteService.getById(2) == note)

    }

    @Test
    fun getComments() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        noteService.createComment(1, "contetn")
        noteService.createComment(1, "contetn2")
        val list = noteService.getComments(1)
        assertTrue(list != emptyList<Note>())
    }

    @Test
    fun restoreComment() {
        val noteService = NoteService()
        noteService.add("first note", "test")
        noteService.createComment(1, "Content")
        noteService.deleteComment(1)
        noteService.restoreComment(1)
        val comment = noteService.comments.find { it.id == 1 }
        val result = comment?.isDeleted
        assertFalse(result ?: true)
    }
}