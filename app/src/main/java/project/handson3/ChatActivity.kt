package project.handson3
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import project.handson3.databinding.ActivityChatBinding

class ChatActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var vm: ChatViewModel
    private lateinit var adapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
        vm=ViewModelProvider(this)[ChatViewModel::class.java]
        adapter=ChatAdapter()
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
        binding.sendButton.setOnClickListener { send() }
        vm.messages.observe(this) { adapter.submitList(it.toList()) }
        if(vm.messages.value.isNullOrEmpty()) vm.addBot("Hello! Ask me anything.")
    }
    private fun send() {
        val s=binding.inputEditText.text.toString().trim()
        if(s.isEmpty()) return
        binding.inputEditText.text?.clear()
        vm.addUser(s)
        vm.send(s)
    }
}
