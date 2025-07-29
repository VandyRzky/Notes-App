package app.notes.appinf.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun NoteForm (
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
){


    Column (
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        OutlinedTextField(value = title, onValueChange = {onTitleChange},
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(text = "Judul")},
            singleLine = true,
            maxLines = 1,
//            colors = TextFieldColors()
        )
        OutlinedTextField(value = content, onValueChange = {onContentChange},
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = Int.MAX_VALUE,
            placeholder = { Text(text = "Isi notes")},
            singleLine = false
            )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun prev(){
//    NoteForm()
//}