package in.qwerto.qwerto.common;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import in.qwerto.qwerto.R;
/**
 * Created by sandeep on 22/8/15.
 */
public class ChatAdapterTemp extends RecyclerView.Adapter<ChatAdapterTemp.ChatViewHolder> {
    private LayoutInflater inflater;
    Context context;
    ArrayList<ChatClass> chatData;
    public ChatAdapterTemp(Context context, ArrayList<ChatClass> chatData){
        this.context =context;
        inflater=LayoutInflater.from(context);
        this.chatData = chatData;
    }
    @Override
    public ChatAdapterTemp.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 1:
                view = inflater.inflate(R.layout.view_chat_text,parent,false);
                return new ChatViewHolder(view,1);
            case 2:
                view=inflater.inflate(R.layout.view_chat_image, parent, false);
                return new ChatViewHolder(view,2);
            case 3:
                view= inflater.inflate(R.layout.view_chat_contact,parent,false);
                return new ChatViewHolder(view,3);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatClass single = chatData.get(position);
        if(single.getType()==1){
            ChatText ct = (ChatText) chatData.get(position);
            holder.msg.setText(ct.getMsg());
        }else if(single.getType()==2){
            ChatImage ci = (ChatImage) chatData.get(position);
            holder.image.setImageBitmap(ci.getBitmap());
        }else if(single.getType()==3){
            ChatContact cc = (ChatContact) chatData.get(position);
            holder.contactName.setText(cc.getName());
            holder.contactNumber.setText(cc.getNumber());
        }
        if(single.getSide()==1){
            holder.chat.setGravity(Gravity.RIGHT);
        }else if (single.getSide()==2){
            holder.chat.setGravity(Gravity.LEFT);
        }

    }

    @Override
    public int getItemCount() {
        return chatData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatData.get(position).getType();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView msg;
        ImageView image;
        TextView contactName,contactNumber;
        LinearLayout chat;

        public ChatViewHolder(View itemView,int type) {
            super(itemView);
            chat = (LinearLayout) itemView.findViewById(R.id.llChat);
            switch (type){
                case 1:
                    msg = (TextView) itemView.findViewById(R.id.tvChatMsg);
                    break;
                case 2:
                    image = (ImageView) itemView.findViewById(R.id.ivChatImage);
                    break;
                case 3:
                    contactName= (TextView) itemView.findViewById(R.id.tvChatContactName);
                    contactNumber= (TextView) itemView.findViewById(R.id.tvChatContactNumber);
                    break;
            }

        }
    }
}