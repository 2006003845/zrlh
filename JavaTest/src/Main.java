import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String getAmount(String amount) {

        if (amount.contains(".")) {
            amount = amount.split("\\.")[0];
        }
        long totalTrade1 = Long.parseLong(amount);
        if (totalTrade1 >= 100) {
            String a = new DecimalFormat("##,###,###,#####").format(totalTrade1);
            int len = a.length();
            String xx = a.substring(0, len - 2) + "." + a.substring(len - 2);
            return xx;
        } else if (totalTrade1 >= 10 && totalTrade1 < 100) {
            return "0." + totalTrade1;
        } else if (totalTrade1 >= 0 && totalTrade1 < 10) {
            return "0.0" + totalTrade1;
        } else {
            String a = new DecimalFormat("##,###,###,#####").format(totalTrade1);
            int len = a.length();
            String xx = a.substring(0, len - 2) + "." + a.substring(len - 2);
            return xx;
        }
    }

    static String digitReg = "^\\d{0,}$";

    public static boolean isDate(String date) {
        String digitReg = "^\\d{0,}$";
        Pattern p = Pattern.compile(digitReg);
        Matcher matcher = p.matcher(date);
        return matcher.matches();
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return "0x" + str;// 0x表示十六进制
    }

    public static String toStringHex(String s) {
        if ("0x".equals(s.substring(0, 2))) {
            s = s.substring(2);
        }
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static void jjjj() {
        List<String> list = new ArrayList<String>();
        list.add("asss");
        // list.add("asss2");
        // list.add("asss3");
        // list.add("asss4");

        StringBuilder b = new StringBuilder();
        for (String lottery : list) {
            b.append(lottery);
            b.append("\n");
        }
        if (b.length() >= 2) {
            b = b.replace(b.length() - 2, b.length(), "");
        }
        if (b.length() > 0)
            System.out.println(b.toString());
    }

    private String getStars(int starLength) {
        StringBuilder starSB = new StringBuilder();
        int i = 0;
        while (i++ < starLength) {
            starSB.append("*");
        }
        return starSB.toString();
    }

    public static boolean isContainChinese(String str) {
        String reg = "(\\d{3}-\\d{3}-\\d{4})|1[34578]{1}\\d{9}";//^[0-9]{3}-[0-9]{3}$
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(isContainChinese("7-001-8888"));
        System.out.println(isContainChinese("天天 86-001-8888"));
        System.out.println(isContainChinese("18600159319 天天 0570-3015508-19"));
        System.out.println(isContainChinese("天天18600159319天天"));
        jjjj();
        String ss = "{\"vender_id\":\"100\"}";

        String jsonDefault = DESEncrypt.encryption(ss);
        System.out.println(jsonDefault);
        String j = toHexString(jsonDefault);
        System.out.println(j);
        String re = toStringHex(j);
        System.out.println(re);

        System.out.println(DESEncrypt.decryption(re));
        // System.out.println(isDate("122"));
        // System.out.println(isDate("0"));
        // System.out.println(isDate("1,2"));
        // System.out.println(isDate("1.2"));
        // System.out.println(isDate("哈哈"));
        //
        // ArrayList<String> list = new ArrayList<String>();
        // list.add("www.baidu.com");
        // list.add("adaiuh.jpg");
        // list.add("andiauhdcab");
        // System.out.println(list.toString());
        //
        // String amount = "123232222.22";
        // System.out.println("amount:" + amount + "---" +
        // Pattern.compile(digitReg).matcher(amount).matches());
        // amount = amount.split("\\.")[0];
        // System.out.println("amount:" + amount + "---" +
        // Pattern.compile(digitReg).matcher(amount).matches());
        // System.out.println(Pattern.compile(digitReg).matcher("123234").matches());
        // System.out.println(Pattern.compile(digitReg).matcher("中的十大").matches());
        // System.out.println(Pattern.compile(digitReg).matcher("andaidua").matches());
        // BigDecimal qrBD = new BigDecimal((double) 10000000);
        // BigDecimal swipeBD = new BigDecimal((double) 1000000);
        // BigDecimal countBD = qrBD.add(swipeBD);
        // String countAmount = countBD.toString();
        //
        // System.out.println(countAmount);
        //
        // // System.out.println(getAmount("1"));
        // String con = "山大哦哈达(查查查)";
        //
        // String key = "查";
        // int indexStart = con.indexOf(key);
        // while (indexStart >= 0) {
        // System.out.println("indexStart:" + indexStart);
        // System.out.println(con.substring(indexStart, indexStart +
        // key.length()));
        // indexStart = con.indexOf(key, indexStart + key.length());
        // }
        // // Time time = new Time();
        // Date d = new Date();
        //
        // System.out.println((d.getDay()) + "");
        //
        // System.out.println(isValid("2@3"));
        //
        // System.out.println(isValid("牛"));
        // System.out.println(isValid("2@3"));
        // System.out.println(isValid("asd"));
        // Vector<String> actionPool = new Vector<String>();
        // actionPool.add("welcome");
        //
        // System.out.println(actionPool.contains("welcome"));
        // actionPool.remove("welcome");
        // System.out.println(actionPool.contains("welcome"));
        // actionPool.remove("aaa");
        //
        // System.out.println(isCarNo("北京123"));
        // System.out.println(isCarNo("京8618600159319"));
        // System.out.println(isCarNo("京B13503"));
        // System.out.println(isPhoneNumberValid("013505700531"));
        // System.out.println(isPhoneNumberValid("x18600159319"));
        // System.out.println(isPhoneNumberValid("18600159319"));
        // Vector<String> v = new Vector<String>();
        // v.add("parents");
        // v.add("friends");
        // v.add("schoolmates");
        // Push p = new Push("18600159319", "18600159319", "SDANACNIACNAV", v);
        // String json = JsonMaker.requestObjectBean(p);
        // System.out.println(json);
        // Push pp = (Push) JsonParser.parserJsonStringToObject(json,
        // Push.class);
        // System.out.println(pp);
        //
        // String jj =
        // "{\"mobile\":\"18600159319\",\"alias\":\"18600159319\",\"token\":\"SDANACNIACNAV\"}";
        // Push pp2 = (Push) JsonParser.parserJsonStringToObject(jj,
        // Push.class);
        // System.out.println(pp2);
        // execute(new Object[] { new Bird(), new Tree(), new Flower() });
        // dodo(Bird.class);
        // dodo(Tree.class);

    }

    protected static boolean isValid(String text) {
        String matchReg = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{0,}$";
        if (Pattern.compile(matchReg).matcher(text).matches()) {
            return true;
        }
        return false;
    }

    public static boolean isCarNo(String carno) {
        String vehicleNoStyle = "^[\u4e00-\u9fa5|WJ]{1}[A-Z0-9]{6}$";
        Pattern pattern = Pattern.compile(vehicleNoStyle);
        Matcher matcher = pattern.matcher(carno);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;

        String expression = "^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$";

        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static void execute(Object... params) {
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i].getClass().getSimpleName());
            System.out.println(JsonMaker.requestObjectBean(params[i]));
        }
    }

    public static <T> void dodo(Class<T> ccc) {

        beanClass = ccc;
        System.out.println(beanClass.getSimpleName());
    }

    @SuppressWarnings("rawtypes")
    public static Class beanClass;

    static class Bird {
        int id;
        String name = "bitdc";
    }

    static class Tree {
        int id;
        String name = "tree";
        String high = "50m";
    }

    static class Flower {
        int id;
        String name = "flower";
        String type = "34";
    }

}
