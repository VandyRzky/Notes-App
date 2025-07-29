package app.notes.appinf.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.notes.adapter.NoteViewModel
import app.notes.appinf.component.ErrorScreen
import app.notes.appinf.component.LoadingScreen
import app.notes.appinf.component.NoteForm
import app.notes.appinf.component.TopBarDetailScreen

@Composable
fun NoteDetailScreen(viewModel: NoteViewModel, noteId: String?, isEditMode: Boolean = false, onDone: ()-> Unit){
    // memiliki 3 mode
    // create
    // update
    // read (tidak akan digunakan akan digabungkan dengan update)
    // mode ditentukan oleh args noteId, isEditMode
    // digunakan untuk membuat note, mengedit note, melihat note, menghapus note


    val note = viewModel.note.collectAsState()
    val loading = viewModel.loading.collectAsState()
    val error = viewModel.error.collectAsState()

    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }
    LaunchedEffect(noteId) {
        if (noteId != null){
            viewModel.fetchNoteById(noteId)
        }
    }

    LaunchedEffect(note.value) {
        note.let {
            title = note.value?.title!!
            content = note.value?.content!!
        }
    }

    Scaffold(
        topBar = {
            TopBarDetailScreen(
                noteId = noteId,
                isEditMode = isEditMode,
                onBack = onDone,
                onDelete = {
                    viewModel.deleteNote(noteId!!)
                    onDone()
                },
                onCreateOrUpdate = {
                    if (noteId == null) { // mode create
                        viewModel.createNote(title, content)
                    } else { // mode update
                        viewModel.editNote(noteId, title, content)
                    }
                    onDone()
                }
            )
        }
    ) {
        padding ->
        if (loading.value) {
            LoadingScreen()
        }
        else{
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                if (error.value != null){
                    ErrorScreen(error = error.value)
                }
                NoteForm(title = title,
                    onTitleChange = { if (noteId == null || isEditMode) title = it },
                    content = content,
                    onContentChange = { if (noteId == null || isEditMode) content = it })
            }
        }
    }
}

@Preview
@Composable
fun PreviewScren(){
    NoteDetailScreen(viewModel = NoteViewModel(), noteId = null) {

    }
}