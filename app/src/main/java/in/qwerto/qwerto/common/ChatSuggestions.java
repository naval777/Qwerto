package in.qwerto.qwerto.common;

import java.util.ArrayList;

import in.qwerto.qwerto.SingleSuggestion;

/**
 * Created by sandeep on 5/9/15.
 */
public class ChatSuggestions extends ChatClass {

    ArrayList<SingleSuggestion> suggestions;


    public ChatSuggestions(int side,int type,ArrayList<SingleSuggestion> suggestions){
        super(side,type);
        this.suggestions = suggestions;
    }

    public ArrayList<SingleSuggestion> getSuggestions(){
        return suggestions;
    }
}
