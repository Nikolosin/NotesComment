package ru.netology

fun main() {

}


data class Note(
    val id: Int,
    var title: String,
    var content: String,
    var isDeleted: Boolean = false
)

data class Comment(
    val id: Int,
    var noteId: Int,
    var content: String,
    var isDeleted: Boolean = false
)

class NoteService {
    private var lastIdNote = 0
    private var lastIdComment = 0
    val notes = mutableListOf<Note>()
    val comments = mutableListOf<Comment>()

    fun add(title: String, content: String): Int {
        val note = Note(id = ++lastIdNote, title = title, content = content)
        notes.add(note)
        return note.id
    }

    fun createComment(noteId: Int, content: String): Int {
        val comment = Comment(id= ++lastIdComment, noteId = noteId, content = content)
        comments.add(comment)
        return comment.id
    }

    fun delete(noteId: Int) : Boolean {
        var result = false
        val note = notes.find { it.id == noteId }
        if (note != null) {
            note.isDeleted = true
            result = true
        }
        return result
    }

    fun deleteComment(commentId: Int) : Boolean {
        var result = false
        val comment = comments.find { it.id == commentId }
        if (comment != null) {
            comment.isDeleted = true
            result = true
        }
        return result
    }

    fun edit(noteId: Int, title: String, content: String) : Boolean {
        var result = false
        val note = notes.find { it.id == noteId }
        if (note != null) {
            note.title = title
            note.content = content
            result = true
        }
        return result
    }

    fun editComment(commentId: Int, content: String) : Boolean {
        var result = false
        val comment = comments.find { it.id == commentId }
        if (comment != null) {
            comment.content = content
            result = true
        }
        return result
    }

    fun get(): List<Note> {
        return notes.filter { !it.isDeleted }
    }

    fun getById(noteId: Int): Note? {
        return notes.find { it.id == noteId && !it.isDeleted }
    }

    fun getComments(noteId: Int): List<Comment> {
        return comments.filter { it.noteId == noteId && !it.isDeleted }
    }

    fun restoreComment(commentId: Int) : Boolean {
        var result = false
        val comment = comments.find { it.id == commentId }
        if (comment != null) {
            comment.isDeleted = false
            result = true
        }
        return result
    }
}