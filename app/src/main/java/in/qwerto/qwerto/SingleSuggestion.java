package in.qwerto.qwerto;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import in.qwerto.qwerto.common.ChatLocation;

/**
 * Created by sandeep on 5/9/15.
 */
public class SingleSuggestion implements Serializable{

    String name;
    ChatLocation chatLocation;

    public SingleSuggestion(String name, ChatLocation chatLocation){
        this.name=name;
        this.chatLocation = chatLocation;
    }

    public String getName() {
        return name;
    }

    public ChatLocation getChatLocation(){
        return chatLocation;
    }


}
