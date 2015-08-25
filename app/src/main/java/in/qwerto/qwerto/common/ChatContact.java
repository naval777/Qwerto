package in.qwerto.qwerto.common;


/**
 * Created by sandeep on 24/8/15.
 */
public class ChatContact extends ChatClass{

    String name;
    String number;

    public ChatContact(int side, int type) {
        super(side,type);
    }

    public ChatContact(int side,int type,String name, String number){
        super(side,type);
        this.name=name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
