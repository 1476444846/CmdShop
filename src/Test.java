import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[]=new Product[3];
    static int count=0;
    static Scanner sc = new Scanner(System.in);
    /*
创建一个购物车的数组：存放商品
 */
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while(bo) {
            System.out.println("请输入用户名");
            String username = sc.next();
            //System.out.println("刚刚输入的用户名:" + username);

            System.out.println("请输入密码");
            String password = sc.next();
            //System.out.println("刚刚输入密码是:" + password);//用于检测输入的用户名是否正确

            /*
    开始读取文件
    */
            //File file = new File("D:\\CmdShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");//此方法能返回一个输入流，满足ReadExcel要求的输入流
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User users[] = readExcel.readExcel(in);

            System.out.println(users.length);
            for (int i=0;i<users.length;i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    //System.out.println("登录成功");
                    bo=false;
                    /*
                    显示商品
                     */
                    while(true){
                        System.out.println("查看购物车请按1");
                        System.out.println("购买商品请按2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");
                        int choose=sc.nextInt();
                        if(choose==1){
                            viewCarts();//查看购物车
                        }
                        else if(choose==2){
                            shopping(inProduct);
                        }
                        else if(choose==3){
                            /*
                            1、产生订单（必须有订单类）
                            2、用POI创建Order.xlsx文件
                            3、把购物车里的商品写入Order.xlsx
                             */
                            Order order = new Order();
                            order.setUser(users[i]);//订单关联用户
                            Product products[]=new Product[count];
                            int num=0;
                            /*
                            最多三个商品，实际买了两个商品，怎么样把carts中的两个Product对象放入products中
                             */
                            for(int j=0;j<carts.length;j++){
                                if(carts[j]!=null);{
                                    products[j]=carts[j];
                                }
                            }
                            order.setProducts(products);//订单关联商品:实际上应该处理一下，将数组为null的去除
                            CreateOrder.createOrder(order);//下订单（创建Excel）
                        }
                        else if(choose==4){
                            break;//退出while(true)
                            //System.exit(0);
                        }
                    }
                    break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }
    }
    public static void viewCarts(){
        System.out.println("当前购物车商品如下：");
        for (Product product1:carts) {
            if (product1 != null) {
                System.out.print(product1.getId());
                System.out.print("\t" + product1.getName());
                System.out.print("\t" + product1.getPrice());
                System.out.println("\t" + product1.getDesc());
            }
        }
    }
    public static void shopping(InputStream in) throws ClassNotFoundException {
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.getAllProduct(in);
        for(Product product:products){
            System.out.print(product.getId());
            System.out.print("\t"+product.getName());
            System.out.print("\t"+product.getPrice());
            System.out.println("\t"+product.getDesc());
        }
        System.out.println("请输入商品ID，把该商品加入购物车");
        String id=sc.next();
        int count=0;

                    /*
                    根据此ID中去Excel查找是否有该ID的商品信息，如果有则返回该商品信息即可
                     */
        in = null;
        in = Class.forName("Test").getResourceAsStream("/product.xlsx");
        Product product=readProductExcel.getProductById(id,in);
        System.out.println("要购买的商品价格：" + product.getPrice());
        if(product!=null){
            carts[count++]=product;
        }

    }
}