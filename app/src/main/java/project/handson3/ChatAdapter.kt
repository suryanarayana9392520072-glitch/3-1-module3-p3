package project.handson3
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.handson3.models.ChatMessage

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.Holder>() {
    private var items=listOf<ChatMessage>()
    fun submitList(x:List<ChatMessage>){items=x;notifyDataSetChanged()}
    override fun onCreateViewHolder(p:ViewGroup,v:Int):Holder {
        val t=TextView(p.context); t.setPadding(24,18,24,18)
        return Holder(t)
    }
    override fun onBindViewHolder(h:Holder,i:Int){
        h.t.text=if(items[i].isUser) "You: ${items[i].text}" else "AI: ${items[i].text}"
    }
    override fun getItemCount()=items.size
    class Holder(val t:TextView):RecyclerView.ViewHolder(t)
}
