package in.qwerto.qwerto.common;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import in.qwerto.qwerto.R;
import in.qwerto.qwerto.SingleSuggestion;

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
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
            case 4:
                view = inflater.inflate(R.layout.view_chat_location,parent,false);
                return new ChatViewHolder(view,4);
            case 5:
                view = inflater.inflate(R.layout.view_chat_suggestion, parent,false);
                return  new ChatViewHolder(view,5);
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
        }else if (single.getType()==4){
            ChatLocation cl = (ChatLocation) chatData.get(position);
            holder.locationName.setText(cl.getName());
            double lat = cl.getLatitude();
            double lon = cl.getLongitude();
            String url = "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lon+"&zoom=17&size=600x300&maptype=normal";
            new DownloadImageTask(holder.locationImage).execute(url);
        }else if(single.getType()==5){
            ChatSuggestions cs  = (ChatSuggestions) chatData.get(position);
            SuggestionsAdapter adapter = new SuggestionsAdapter(context,cs.getSuggestions());
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.suggestionsRC.setLayoutManager(layoutManager);
            holder.suggestionsRC.setAdapter(adapter);
//            ChatLocation chatLocation = new ChatLocation(1,4,"Location",12.985806, 80.249666);
//            SuggestionsPagerAdapter adapter = new SuggestionsPagerAdapter(context,s,chatLocation);
//            holder.pager.setAdapter(adapter);
//            int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20 * 2, context.getResources().getDisplayMetrics());
//            holder.pager.setPageMargin(-margin);
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
        ImageView image, locationImage;
        TextView contactName,contactNumber;
        TextView locationName;
        LinearLayout chat;
        RecyclerView suggestionsRC;

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
                case 4:
                    locationImage = (ImageView) itemView.findViewById(R.id.ivStaticMap);
                    locationName = (TextView) itemView.findViewById(R.id.tvStaticMapLocation);
                    break;
                case 5:
                    suggestionsRC = (RecyclerView) itemView.findViewById(R.id.rvSuggestions);
                    break;
            }

        }
    }
}