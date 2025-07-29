package app.notes.appinf.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.notes.adapter.NoteViewModel
import app.notes.appinf.component.ErrorScreen
import app.notes.appinf.component.LoadingScreen
import app.notes.appinf.component.NoteBox


@Composable
fun SeeAllNotes(noteViewModel: NoteViewModel= remember {
    NoteViewModel()
}) {
    val notes by noteViewModel.notes.collectAsState()
    val isLoading by noteViewModel.loading.collectAsState()
    val error by noteViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        noteViewModel.fetchAllNotes()
    }

    when {
        isLoading -> {
            LoadingScreen()
        }
        error != null -> {
            ErrorScreen(error = error)
        }
        notes.isEmpty() ->{
            Text("Belum ada data")
        }
        else -> {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notes){note ->
                    NoteBox(title = note.title!!, content = note.content!!)
                }
            }
        }
    }
}





@Preview
@Composable
fun SeeAllPrev(){
    SeeAllNotes()
}