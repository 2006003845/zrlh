package com.example.corporate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zzl.zl_app.connection.Protocol;

public class Jobs {
	public static class JobNew implements Serializable {
		String jobid; // 信息id（用户查询详情）
		String salary_min;// 最小薪资
		String salary_max;// 最大薪资
		String jobname; // 招工名称
		String peonumber; // 人数
		String address; // 地址
		String jobtime; // 发布时间
		String cityString;

		public String getCityString() {
			return cityString;
		}

		public void setCityString(String cityString) {
			this.cityString = cityString;
		}

		public String getJobid() {
			return jobid;
		}

		public void setJobid(String jobid) {
			this.jobid = jobid;
		}

		public String getSalary_min() {
			return salary_min;
		}

		public void setSalary_min(String salary_min) {
			this.salary_min = salary_min;
		}

		public String getSalary_max() {
			return salary_max;
		}

		public void setSalary_max(String salary_max) {
			this.salary_max = salary_max;
		}

		public String getJobname() {
			return jobname;
		}

		public void setJobname(String jobname) {
			this.jobname = jobname;
		}

		public String getPeonumber() {
			return peonumber;
		}

		public void setPeonumber(String peonumber) {
			this.peonumber = peonumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getJobtime() {
			return jobtime;
		}

		public void setJobtime(String jobtime) {
			this.jobtime = jobtime;
		}

	}

	public static class NearJob implements Serializable {
		String id;// 岗位id
		String salary_min;// 最小金额
		String salary_max;// 最大金额
		String job_name;// 招工名称
		String address;// 公司地址
		String jobtime;// 发布时间
		String lat;// 经度
		String lng;// 纬度
		String distance;// 距离
		String city;// 城市

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSalary_min() {
			return salary_min;
		}

		public void setSalary_min(String salary_min) {
			this.salary_min = salary_min;
		}

		public String getSalary_max() {
			return salary_max;
		}

		public void setSalary_max(String salary_max) {
			this.salary_max = salary_max;
		}

		public String getJob_name() {
			return job_name;
		}

		public void setJob_name(String job_name) {
			this.job_name = job_name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getJobtime() {
			return jobtime;
		}

		public void setJobtime(String jobtime) {
			this.jobtime = jobtime;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

		public String getLng() {
			return lng;
		}

		public void setLng(String lng) {
			this.lng = lng;
		}

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

	}

	public static class JobFair implements Serializable {
		String id;
		String name;// 招聘会名称
		String contacts;// 联系人
		String tel;// 联系电话
		String venues;// 场馆
		String address;// 地点
		String date;// 时间
		String content;
		String lat;
		String lng;

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

		public String getLng() {
			return lng;
		}

		public void setLng(String lng) {
			this.lng = lng;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContacts() {
			return contacts;
		}

		public void setContacts(String contacts) {
			this.contacts = contacts;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getVenues() {
			return venues;
		}

		public void setVenues(String venues) {
			this.venues = venues;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

	}

	public static class JobDetail implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4495003165275816089L;

		public JobDetail() {

		}

		public JobDetail(JSONObject json) throws JSONException {
			if (json == null) {
				return;
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_rid)) {
				rid = json.getString(Protocol.ProtocolKey.KEY_rid);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_name)) {
				name = json.getString(Protocol.ProtocolKey.KEY_name);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_people)) {
				peonumber = json.getString(Protocol.ProtocolKey.KEY_people);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_salary)) {
				String salary = json.getString(Protocol.ProtocolKey.KEY_salary);
				String[] salarys = salary.split("~");
				salary_min = salarys.length > 0 ? salarys[0] : "0";
				salary_max = salarys.length > 1 ? salarys[1] : "2000";
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_contacts_name)) {
				contacts_name = json
						.getString(Protocol.ProtocolKey.KEY_contacts_name);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_tel)) {
				tel = json.getString(Protocol.ProtocolKey.KEY_tel);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_class_ot)) {
				classify = json.getString(Protocol.ProtocolKey.KEY_class_ot);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_jobdescription)) {
				description = json
						.getString(Protocol.ProtocolKey.KEY_jobdescription);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_description)) {
				demand = json.getString(Protocol.ProtocolKey.KEY_description);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_outtime)) {
				deadline = json.getString(Protocol.ProtocolKey.KEY_outtime);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_tag)) {
				tags = json.getString(Protocol.ProtocolKey.KEY_tag);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_address)) {
				address = json.getString(Protocol.ProtocolKey.KEY_address);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_online)) {
				online = json.getString(Protocol.ProtocolKey.KEY_online);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_fabu_date)) {
				fabu_date = json.getString(Protocol.ProtocolKey.KEY_fabu_date);
			}
			if (!json.isNull(Protocol.ProtocolKey.KEY_lastOpTime)) {
				lastOpTime = json
						.getString(Protocol.ProtocolKey.KEY_lastOpTime);
			}

		}

		public static List<JobDetail> getList(JSONArray array)
				throws JSONException {
			if (array == null) {
				return null;
			}
			List<JobDetail> list = new ArrayList<JobDetail>();
			for (int i = 0, len = array.length(); i < len; i++) {
				JSONObject obj = array.getJSONObject(i);
				if (obj != null) {
					JobDetail p = new JobDetail(obj);
					list.add(p);
				}
			}
			return list;
		}
		
		String rid;

		/**
		 * 岗位名称
		 */
		String name;
		/**
		 * 招工人数
		 */
		String peonumber;
		String id;
		/**
		 * 工资最小值
		 */
		String salary_min;
		/**
		 * 工资最大值
		 */
		String salary_max;
		/**
		 * 单位名称
		 */
		String corporate_name;
		/**
		 * 联系人
		 */
		String contacts_name;
		/**
		 * 联系电话
		 */
		String tel;
		/**
		 * 工作要求
		 */
		String demand;
		/**
		 * 工作描述
		 */
		String description;
		/**
		 * 工作地址
		 */
		String address;
		/**
		 * 工作单位简介
		 */
		String company;
		/**
		 * 经度
		 */
		String lat;
		/**
		 * 维度
		 */
		String lng;
		/**
		 * 发布时间
		 */
		String fabu_date;
		/**
		 * 截止日期
		 */
		String deadline;
		/**
		 * 工作分类
		 */
		String classify;
		/**
		 * 工作所在城市
		 */
		String area;
		/**
		 * 岗位标签(可以以","隔开)
		 */
		String tags;

		/**
		 * 状态(1有效 2过期)
		 */
		String online;

		String lastOpTime;

		public String getClassify() {
			return classify;
		}

		public void setClassify(String classify) {
			this.classify = classify;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getTags() {
			return tags;
		}

		public void setTags(String tags) {
			this.tags = tags;
		}

		public String getDemand() {
			return demand;
		}

		public void setDemand(String demand) {
			this.demand = demand;
		}

		public String getDeadline() {
			return deadline;
		}

		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}

		public String getFabu_date() {
			return fabu_date;
		}

		public void setFabu_date(String fabu_date) {
			this.fabu_date = fabu_date;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPeonumber() {
			return peonumber;
		}

		public void setPeonumber(String peonumber) {
			this.peonumber = peonumber;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSalary_min() {
			return salary_min;
		}

		public void setSalary_min(String salary_min) {
			this.salary_min = salary_min;
		}

		public String getSalary_max() {
			return salary_max;
		}

		public void setSalary_max(String salary_max) {
			this.salary_max = salary_max;
		}

		public String getCorporate_name() {
			return corporate_name;
		}

		public void setCorporate_name(String corporate_name) {
			this.corporate_name = corporate_name;
		}

		public String getContacts_name() {
			return contacts_name;
		}

		public void setContacts_name(String contacts_name) {
			this.contacts_name = contacts_name;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

		public String getLng() {
			return lng;
		}

		public void setLng(String lng) {
			this.lng = lng;
		}

	}

	public static class NewSituation implements Serializable {
		String name1;
		String img1;
		String url1;
		String name2;
		String img2;
		String url2;
		String name3;
		String img3;
		String url3;
		String name4;
		String img4;
		String url4;
		String page;
		String next;
		String last;
		String dateString;

		public String getDateString() {
			return dateString;
		}

		public void setDateString(String dateString) {
			this.dateString = dateString;
		}

		public String getName1() {
			return name1;
		}

		public void setName1(String name1) {
			this.name1 = name1;
		}

		public String getImg1() {
			return img1;
		}

		public void setImg1(String img1) {
			this.img1 = img1;
		}

		public String getUrl1() {
			return url1;
		}

		public void setUrl1(String url1) {
			this.url1 = url1;
		}

		public String getName2() {
			return name2;
		}

		public void setName2(String name2) {
			this.name2 = name2;
		}

		public String getImg2() {
			return img2;
		}

		public void setImg2(String img2) {
			this.img2 = img2;
		}

		public String getUrl2() {
			return url2;
		}

		public void setUrl2(String url2) {
			this.url2 = url2;
		}

		public String getName3() {
			return name3;
		}

		public void setName3(String name3) {
			this.name3 = name3;
		}

		public String getImg3() {
			return img3;
		}

		public void setImg3(String img3) {
			this.img3 = img3;
		}

		public String getUrl3() {
			return url3;
		}

		public void setUrl3(String url3) {
			this.url3 = url3;
		}

		public String getName4() {
			return name4;
		}

		public void setName4(String name4) {
			this.name4 = name4;
		}

		public String getImg4() {
			return img4;
		}

		public void setImg4(String img4) {
			this.img4 = img4;
		}

		public String getUrl4() {
			return url4;
		}

		public void setUrl4(String url4) {
			this.url4 = url4;
		}

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public String getNext() {
			return next;
		}

		public void setNext(String next) {
			this.next = next;
		}

		public String getLast() {
			return last;
		}

		public void setLast(String last) {
			this.last = last;
		}

	}

	public static class CollJob implements Serializable {
		String name;
		String corporate_name;
		String reid;
		String peonumber;
		String date;
		String city;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCorporate_name() {
			return corporate_name;
		}

		public void setCorporate_name(String corporate_name) {
			this.corporate_name = corporate_name;
		}

		public String getReid() {
			return reid;
		}

		public void setReid(String reid) {
			this.reid = reid;
		}

		public String getPeonumber() {
			return peonumber;
		}

		public void setPeonumber(String peonumber) {
			this.peonumber = peonumber;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

	}

}
