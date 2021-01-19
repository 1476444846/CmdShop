import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*System.out.println("请输入用户名");

        Scanner sc = new Scanner(System.in);
        String username = sc.next();

        System.out.println("请输入密码");
        String password = sc.next();
        //System.out.println("刚刚输入的用户名:" + username);//用于检测输入的用户名是否正确
        */

    /*
    开始读取文件
    */
    File file=new File("D:\\CmdShop\\src\\users.xlsx");
    ReadExcel readExcel=new ReadExcel();
    readExcel.readExcel(file);
        System.out.println(users.length);
        for(User user:users){
            System.out.println(user.getUsername()) && password.equals(user.getPassword());
            System.out.println("登录成功"+);
        }
    }
}
