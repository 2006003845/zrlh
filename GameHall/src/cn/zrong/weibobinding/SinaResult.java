package cn.zrong.weibobinding;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SinaResult {
	public String request;
	public String error_code;
	public String error;

	public SinaResult(JSONObject jsonObj) throws JSONException {
		Log.i("Loh", jsonObj + "");
		if (!jsonObj.isNull(RESULT_RET))
			request = jsonObj.getString(RESULT_RET);
		if (!jsonObj.isNull(RESULT_ERROR))
			error = jsonObj.getString(RESULT_ERROR);
		if (!jsonObj.isNull(RESULT_ERROR_CODE))
			error_code = jsonObj.getString(RESULT_ERROR_CODE);

	}

	public static final String RESULT_RET = "request";
	public static final String RESULT_ERROR = "error";
	public static final String RESULT_ERROR_CODE = "error_code";

}
