package in.qwerto.qwerto.common;

/**
 * Created by sandeep on 22/8/15.
 */
public class ChatClass {

    int side;
    int type;
    //type =1 for text
    //type =2 for image
    //type =3 for contact
    //type =4 for Location

    public ChatClass(){

    }

    public ChatClass(int side,int type)
    {
        this.side=side;
        this.type=type;
    }

    public int getSide() {
        return side;
    }

    public int getType(){
        return type;
    }
}
