

import com.google.gson.Gson;

public class JsonMaker {
	// 将bean转换给json数据
	public static String requestObjectBean(Object bean) {
		if (bean == null)
			return null;
		Gson gson = new Gson();
		String json = gson.toJson(bean);
		return json;
	}
}
