#  软件工程Airdrop实现流程
## 一. 实现手机端复制电脑端粘贴
 &emsp;
## 1. 思路
1） 该项目可以在手机上运行

2）手机复制文字以后将文字粘贴到对话框

3）设置传输按钮，点击以后即可将复制内容发送发送给PC端
 
 &emsp;
## 2. 使用工具
查找资料的时候，了解到编写app的主流工具是Android Studio，为避免重新下载软件以及配置，我本次使用的是IntelliJ软件，其中有内置项目可以进行Android编程。新建Android项目进行配置，并设置模拟机或者连接真机。配置过程中遇到了很多问题，包括版本的不匹配、无法使用真机进行测试等，通过查找资料进行一一解决。

 &emsp;
## 3.代码中所用的主要方法
我使用的方法是通过 **套接字 (Socket)** 进行通信传输，Socket可以看成在两个程序进行通讯连接中的一个端点，一个程序将一段信息写入Socket中，该Socket将这段信息发送给另外一个Socket中，使这段信息能传送到其他程序中。要通过互联网进行通信，至少需要一对套接字，一个运行于客户机端，称之为ClientSocket，另一个运行于服务器端，称之为serverSocket。在本项目中服务器是在PC端运行，客户端是在手机上运行，二者进行通信。

 &emsp;
## 4.功能以及使用方法介绍
1）PC端运行代码，点击开启服务器即可等待连接。

2）安卓端首先输入正确IP地址和端口号。

3）连接成功会给出提示，否则会有连接超时、没有连接服务器等提示。

4）成功连接服务器也会进行提示。

5）连接后安卓端可以进行消息发送，可以将手机端复制的文字进行粘贴，然后发送到PC端。

**注：**
1. 端口号必须是空闲的才可以否则会显示连接超时，可在cmd中用命令**netstat -ano|findstr 端口号**查看端口是否空闲。

2. 使用正确的IP，否则会显示连接超时，IP有很多个，需要找到可以ping通的ip,我下载了一个名称是人文PING的app验证手机和电脑是否能PING通。

 &emsp;
 ## 5.关键代码
 1）服务器端：

 ```java
 //创建套接字
 try {
    serverSocket = new ServerSocket(30452);//新建一个套接字，指定端口号
                    textArea.append("服务器已启动，正在等待连接"+"\n\n");
} catch (IOException e) {
    e.printStackTrace();
}
```
```java
//等待客户端连接
while(true) {
    try {
        socket = serverSocket.accept();  //调用ServerSocket对象的accept方法等待客户端连接，返回值是Socket对象
    if(socket != null) {
     new ServerThread(socket).start();  //不为空则为每个client启动一个新线程
        }
    } catch (IOException e) {
        e.printStackTrace();
    }}
```
**ServerThread类负责处理客户端的请求**
```java
public ServerThread(Socket socket) { 
    this.socket = socket;
    try {
            this.dataInputStream = new DataInputStream(socket.getInputStream()); //连接Socket对象 获取输入流（字节输入流）
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());//获取输出流（字节输出流）
    } catch (IOException e) {
            e.printStackTrace();
    }
}
```
```java
// 循环不断地从Socket中读取客户端发送过来的数据
while(true) {
        try {
            String listen = null;
            listen  = dataInputStream.readUTF().toString();
        }catch (Exception e) {

    }
 }
```
2）客户端

**点击连接按钮进行触发**
```java
this.button2.setOnClickListener(new View.OnClickListener() { //连接按钮
    @Override
    public void onClick(View v) { //对IP和端口是否为空进行判断
        if(!TextUtils.isEmpty(editText2.getText()) && !TextUtils.isEmpty(editText3.getText())){
            connectServer();  //都不为空进行连接
        }else{
            Toast.makeText(SocketActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
        }
    }
});
```
**connectServer()**
```java
try {
    socket = new Socket();      //新建套接字
    //获取输入的ip和端口号进行连接
    socket.connect(new InetSocketAddress(editText2.getText().toString(),Integer.parseInt(editText3.getText().toString())),2000);
    dataInputStream = new DataInputStream(socket.getInputStream());
    dataOutputStream = new DataOutputStream(socket.getOutputStream());      //获取输入输出流
    Looper.prepare();  //调用Looper.prepare()来给线程创建一个消息循环
    Toast.makeText(SocketActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
    Looper.loop();  //调用Looper.loop()来使消息循环起作用，从消息队列里取消息，处理消息。
} catch (IOException e) {
    Looper.prepare();
    Toast.makeText(SocketActivity.this, "连接超时", Toast.LENGTH_SHORT).show();
    Looper.loop();
}

```
**点击发送按钮进行触发**
```java
//判断是否连接成功
try {
    if(!socket.isConnected()){  //未连接服务器则提示
        Toast.makeText(SocketActivity.this,"没有连接服务器",Toast.LENGTH_SHORT).show();
        return;
        }
    }
```
```java
public void run() {
    try {
        dataOutputStream.writeUTF(editText1.getText().toString()); //将给定的字符串值写入基本数据输出流。
        textView.append("发送成功" +"\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

 &emsp;
 ## 6.效果展示

 &emsp;
 ## 二.实现传输文件的功能
&emsp;
## 1. 思路
1） 写一个简单的jsp页面，可以上传图片

2）在手机可以打开jsp页面

3）设置上传按钮，点击上传以后即可将手机的图片上传到电脑指定的文件夹内

 &emsp;
## 2. 使用工具
使用的IDE是**NetBeans**，使用的时候出现Tomcat无法启动以及缺失一些jar包、所需要的库等，通过查找资料解决。

 &emsp;
## 3.代码中所用的主要方法
方法主要是Jsp+Servlet, 获取文件传到servlet处理。

1）在Jsp中，表单传值要加 **enctype="multipart/form-data"**

因为默认情况下，enctype的值是application/x-www-form-urlencoded，不能用于文件上传，只有使用了multipart/form-data，才能完整的传递文件数据。

2）Servlet中需要构造文件项工厂对象；然后创建一个上传工具并解析上传的数据，得到表单项FileItem。

 &emsp;
 ## 4.关键代码
 
 1）jsp
 ```java
 //表单跳转
 <form action="UploadServlet" method="post" enctype="multipart/form-data">
 ```
 2）servlet关键代码
 ```java
 if (ServletFileUpload.isMultipartContent(req)) {
            //1.先判断上传的数据是否是多段，只有是多段的，才是文件上传。           
            FileItemFactory fileItemFactory = new DiskFileItemFactory();//创建FileItemFactory工厂实现类           
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);//用于解析上传数据的工具类ServletFileUpload类
            resp.setCharacterEncoding("utf-8");//解决乱码问题
 }
 ```
 ```java
             try {
                List<FileItem> list = servletFileUpload.parseRequest(req);//解析上传的数据，得到一个表单项FileItem
                for (FileItem fileItem : list) { //获得字段名和字段值
                    if (fileItem.isFormField()) { //判断是普通表单域还是文件上传表单域。
                        System.out.println("表单项的name属性：" + fileItem.getFieldName());
                        System.out.println("表单项的value属性值：" + fileItem.getString("UTF-8"));
                    } else {
                        System.out.println("表单项的name属性：" + fileItem.getFieldName());
                        System.out.println("上传的文件名" + fileItem.getName());
                        fileItem.write(new File("D:\\img\\" + fileItem.getName()));//上传路径
                    }
                }
            }
 ```
 
 &emsp;
 ## 5.效果展示

 &emsp;


 ## 三、两个项目的缺点：

 1）没有做到方便用户的使用，为了实现套接字连接，需要用户手动输入IP和端口号。

 2）复制粘贴需要用户点击发送才能实现。
 
 3）自己的能力有限，用了两种方法分开实现，没有统一到一起。



