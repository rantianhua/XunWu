package rht.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Rth on 2015/3/9.
 */
public class Utils {

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId,int reqWidth, int reqHeight) {



        final BitmapFactory.Options options = new BitmapFactory.Options();
        //先不分配内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //计算缩小比列
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // 分配内存，加载缩略图
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    //计算缩放比例
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 图片本身的宽高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static void loadBitmapFromResource(Resources res,ImageView view,int resId,int reqWidth,int reqHeight,int colorId) {
        BitmapWorkerTask task = new BitmapWorkerTask(res,view,reqWidth,reqHeight,colorId);
        task.execute(resId);
    }

    //提取透明位图
    public static Bitmap getAlphaBitmap(Resources res,Bitmap bitmap,int color) {
        Bitmap alphaBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(alphaBitmap);
        Paint paint = new Paint();
        paint.setColor(res.getColor(color));
        //从原图中提取只包含alpha的位图
        Bitmap newAlpha = bitmap.extractAlpha();
        bitmap.recycle();
        canvas.drawBitmap(newAlpha, 0, 0, paint);
        return alphaBitmap;
    }

//    public static void recycleDrawable(Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//            bitmapDrawable.getBitmap().recycle();
//        }else {
//            drawable = null;
//        }
//    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

}
