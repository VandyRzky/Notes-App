package app.notes.appinf.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteBox(title:String, content:String){
    Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp), modifier = Modifier
        .wrapContentSize()
        .border(0.5.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
        colors = ButtonColors(containerColor = Color.Gray, contentColor = Color.White, disabledContainerColor = Color.LightGray, disabledContentColor = Color.White),
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
//            modifier = Modifier.padding(5.dp)
        ){
            Text(modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.SemiBold,
                text = title, fontSize = 18.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)//title
            Spacer(modifier = Modifier.size(5.dp))
            Text(modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Normal, lineHeight = 15.sp, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                text = content)
        }
    }
}