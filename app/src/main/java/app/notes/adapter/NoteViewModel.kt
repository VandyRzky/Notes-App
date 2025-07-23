package app.notes.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.notes.api.ApiConfig
import app.notes.models.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class NoteViewModel: ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _note = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> = _note

    private val _operationResult = MutableStateFlow<Note?>(null)
    val operationResult: StateFlow<Note?> = _operationResult

    private val _deleteResult = MutableStateFlow<String?>(null)
    val deleteResult: StateFlow<String?> = _deleteResult

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error:StateFlow<String?> = _error

    private val api = ApiConfig.getApiService()

    fun fetchAllNotes() = viewModelScope.launch {
        _loading.value = true
        try {
            val response = api.getAllNotes()
            if (response.error == null) {
                _notes.value = response.data.orEmpty().filterNotNull()
            } else {
                _error.value = response.error
            }
        } catch (e: Exception) {
            _error.value = e.message
        } finally {
            _loading.value = false
        }
    }

    fun fetchNoteById(id: String) = viewModelScope.launch {
        _loading.value = true
        try {
            val response = api.getNote(id)
            if (response.error == null) {
                _note.value = response.data
            } else {
                _error.value = response.error
            }
        } catch (e: Exception) {
            _error.value = e.message
        } finally {
            _loading.value = false
        }
    }

    fun createNote(title:String, content:String){
        val json = JSONObject().apply {
            put("title", title)
            put("content", content)
        }
        val requestBody = json.toString().toRequestBody("application/json".toMediaType())
        postOrPatchNote(null, requestBody)
    }

    fun editNote(id: String, title: String, content: String) {
        val json = JSONObject().apply {
            put("title", title)
            put("content", content)
        }
        val requestBody = json.toString().toRequestBody("application/json".toMediaType())
        postOrPatchNote(id, requestBody)
    }

    private fun postOrPatchNote(id: String?, body: RequestBody) = viewModelScope.launch {
        _loading.value = true
        try {
            val response = if (id == null)
                api.createNote(body)
            else
                api.editNote(id, body)

            if (response.error == null) {
                _operationResult.value = response.data
            } else {
                _error.value = response.error
            }
        } catch (e: Exception) {
            _error.value = e.message
        } finally {
            _loading.value = false
        }
    }

    fun deleteNote(id: String) = viewModelScope.launch {
        _loading.value = true
        try {
            val response = api.deleteNote(id)
            if (response.error == null) {
                _deleteResult.value = response.data
            } else {
                _error.value = response.error
            }
        } catch (e: Exception) {
            _error.value = e.message
        } finally {
            _loading.value = false
        }
    }

    fun resetOperationState() {
        _operationResult.value = null
        _deleteResult.value = null
        _error.value = null
    }

}