package cn.zrong.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**\
 * 
 *<p>Titile:ImeUtil</p>
 *<p>Description: Judge the soft keyboard is turned on</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yz
 * @version 1.0
 * @date 2012-5-31
 */
public class ImeUtil
{
	 public static void hideIme(Activity context)
	 {
	        if(context==null)
	            return;
	        final View v = context.getWindow().peekDecorView();
	        if (v != null && v.getWindowToken() != null) {
	            InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	        }
	 }
	 
    public static boolean isImeShow(Activity context)
    {
    	 
//    	if(((Activity)context).getWindow().getAttributes().softInputMode ==WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
//    	{
//    		return true;
//    	}
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        final View v = context.getWindow().peekDecorView();
        return imm.isActive(v);
    }
}
