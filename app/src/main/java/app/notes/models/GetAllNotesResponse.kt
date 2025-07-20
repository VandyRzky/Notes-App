package app.notes.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllNotesResponse(

	@field:SerializedName("data")
	val data: List<Note?>? = null,

	@field:SerializedName("error")
	val error: String? = null
) : Parcelable

@Parcelize
data class StoreNoteResponse(

	@field:SerializedName("data")
	val data: Note? = null,

	@field:SerializedName("error")
	val error: String? = null
) : Parcelable


@Parcelize
data class DeleteNoteResponse(

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("error")
	val error: String? = null
) : Parcelable


@Parcelize
data class Note(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable
