package in.qwerto.qwerto.common;

import android.graphics.Bitmap;

/**
 * Created by sandeep on 24/8/15.
 */
public class ChatImage extends ChatClass {

    Bitmap bitmap;

    public ChatImage(int side,int type) {
        super(side,type);
    }

    public ChatImage(int side,int type,Bitmap bitmap){
        super(side,type);
        this.bitmap=bitmap;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }



}
