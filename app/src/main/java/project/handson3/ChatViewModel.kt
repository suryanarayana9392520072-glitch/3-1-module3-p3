package project.handson3
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import project.handson3.models.ChatMessage
import project.handson3.network.RetrofitClient

class ChatViewModel: ViewModel() {
    private val _messages=MutableLiveData(mutableListOf<ChatMessage>())
    val messages:LiveData<MutableList<ChatMessage>> = _messages
    private val _error=MutableLiveData<String?>()
    val error:LiveData<String?> = _error
    fun addUser(s:String)=add(ChatMessage(s,true))
    fun addBot(s:String)=add(ChatMessage(s,false))
    private fun add(m:ChatMessage) {
        val x=(_messages.value ?: mutableListOf()).toMutableList()
        x.add(m); _messages.value=x
    }
    fun send(s:String) {
        viewModelScope.launch {
            try {
                val r=RetrofitClient.api.sendMessage(s,"android-session")
                addBot(r.response.ifBlank{"No response returned."})
            } catch(e:Exception) {
                _error.value="Backend connection failed: ${e.message}"
                addBot("Start the Python backend on port 5000 and try again.")
            }
        }
    }
}
