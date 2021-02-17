import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while(bo) {
            System.out.println("请输入用户名");

            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("刚刚输入的用户名:" + username);

            System.out.println("请输入密码");
            String password = sc.next();
            System.out.println("刚刚输入密码是:" + password);//用于检测输入的用户名是否正确

            /*
    开始读取文件
    */
            //File file = new File("D:\\CmdShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("users.xlsx");//此方法能返回一个输入流，满足ReadExcel要求的输入流
            ReadUserExcel readExcel = new ReadUserExcel();
            User users[] = readExcel.readExcel(in);

            System.out.println(users.length);
            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登录成功");
                    bo=false;
                    break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }
    }
}
