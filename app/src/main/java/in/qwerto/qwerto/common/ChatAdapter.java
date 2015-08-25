package in.qwerto.qwerto.common;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import in.qwerto.qwerto.R;
import in.qwerto.qwerto.request.RequestClass;
/**
 * Created by sandeep on 22/8/15.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private LayoutInflater inflater;
    Context context;
    ArrayList<ChatClass> chatData;
    public ChatAdapter(Context context, ArrayList<ChatClass> chatData){
        this.context =context;
        inflater=LayoutInflater.from(context);
        this.chatData = chatData;
    }
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 1:
                view=inflater.inflate(R.layout.view_chat_left, parent, false);
                break;
            case 2:
                view=inflater.inflate(R.layout.view_chat_right, parent, false);
                break;
        }
        ChatViewHolder cvh = new ChatViewHolder(view);
        return cvh;
    }
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        final ChatClass singleMsg = chatData.get(position);
        holder.msg.setText(singleMsg.getSide());
    }
    @Override
    public int getItemCount() {
        return chatData.size();
    }
    @Override
    public int getItemViewType(int position) {
        return chatData.get(position).getSide();
    }
    class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView msg;
        public ChatViewHolder(View itemView) {
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.tvChatMsg);
        }
    }
}