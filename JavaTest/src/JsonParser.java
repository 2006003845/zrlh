
import com.google.gson.Gson;

public class JsonParser {
	/**
	 * 将json结构字符串封装为T对象
	 * 
	 * @param json
	 *            JSONObject
	 * @param bean
	 *            Class<T>
	 * @return
	 */
	public static <T> Object parserJsonStringToObject(String jsonObject,
			Class<T> bean) {
		if (jsonObject == null || "".equals(jsonObject))
			return null;
		Gson gson = new Gson();
		Object responseBean = gson.fromJson(jsonObject, bean);
		return responseBean;
	}

	// /**
	// * 将json对象封装为T对象
	// *
	// * @param json
	// * JSONObject
	// * @param bean
	// * Class<T>
	// * @return
	// */
	// public static <T> Object parserJsonStringToObject(JSONObject json,
	// Class<T> bean) {
	// if (json == null)
	// return null;
	// Gson gson = new Gson();
	// String jsonObject = json.toString();
	// Object responseBean = gson.fromJson(jsonObject, bean);
	// return responseBean;
	// }
}
