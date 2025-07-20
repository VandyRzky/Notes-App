package app.notes.api

import app.notes.models.DeleteNoteResponse
import app.notes.models.GetAllNotesResponse
import app.notes.models.StoreNoteResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("notes")
    suspend fun getAllNotes(): GetAllNotesResponse

    @GET("notes/{id}")
    suspend fun getNote(@Path("id") id:String): StoreNoteResponse

    @POST("notes")
    suspend fun createNote(@Body request: RequestBody): StoreNoteResponse

    @PATCH("notes/{id}")
    suspend fun editNote(@Path("id") id:String, @Body request: RequestBody): StoreNoteResponse

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id:String): DeleteNoteResponse
}