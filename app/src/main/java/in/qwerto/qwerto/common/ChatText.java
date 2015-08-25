package in.qwerto.qwerto.common;

/**
 * Created by sandeep on 24/8/15.
 */
public class ChatText extends ChatClass {

    private String msg;

    public ChatText(int side, int type) {
        super(side,type);
    }

    public ChatText(int side, int type, String msg){
        super(side,type);
        this.msg=msg;

    }

    public int getSide() {
        return side;
    }

    public String getMsg() {
        return msg;
    }
}
