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



}