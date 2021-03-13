//package utils;
//
//import org.mindrot.jbcrypt.BCrypt;
//
//public class BCryptHelper {
//    public static boolean check(String text, String hashed) {
//        return BCrypt.checkpw(text, hashed);
//    }
//
//    public static String encode(String text) {
//        return BCrypt.hashpw(text, BCrypt.gensalt(9));
//    }
//}
// con nếu cái này bị lỗi: chuột phải pom.xml -> maven -> reload project
// tạm che đi, chưa cần dùng đâu