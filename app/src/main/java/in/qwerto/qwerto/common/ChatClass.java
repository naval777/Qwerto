package in.qwerto.qwerto.common;

/**
 * Created by sandeep on 22/8/15.
 */
public class ChatClass {

    private int side;
    private String msg;

    public ChatClass(int side, String msg){
        this.side=side;
        this.msg=msg;
    }

    public int getSide() {
        return side;
    }

    public String getMsg() {
        return msg;
    }
}
