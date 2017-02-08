import java.util.Vector;

public class Push {

	public Push() {
		super();
	}

	public Push(String mobile, String alias, String token, Vector<String> tags) {
		super();
		this.mobile = mobile;
		this.alias = alias;
		this.token = token;
		this.tags = tags;
	}

	private String mobile;
	private String alias;
	private String token;
	private Vector<String> tags;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Vector<String> getTags() {
		return tags;
	}

	public void setTags(Vector<String> tags) {
		this.tags = tags;
	}

	public void addTag(String tag) {
		if (tag == null || tag.equals(""))
			return;
		if (isTagExist(tag))
			return;
		tags.add(tag);
	}

	public void removeTag(String tag) {
		if (tag == null || tag.equals(""))
			return;
		if (!isTagExist(tag))
			return;
		tags.remove(tag);
	}

	private boolean isTagExist(String tag) {
		for (String t : tags) {
			if (t.equals(tag))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "tags:" + tags + "-----alias:" + alias;
	}
}
