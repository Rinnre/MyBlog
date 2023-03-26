/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 26/03/2023 21:08:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章主键',
    `user_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者id',
    `title`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
    `context`      mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '文章主体内容',
    `thumbnail`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章头图',
    `category_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '文章分类id',
    `password`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '文章密码',
    `source_link`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '文章转载链接',
    `status`       int(0) UNSIGNED                                              NOT NULL DEFAULT 1 COMMENT '文章状态（1：草稿，2：待发布，3：已发布）',
    `create_time`  timestamp(0)                                                 NOT NULL COMMENT '文章创建时间',
    `update_time`  timestamp(0)                                                 NOT NULL COMMENT '文章修改时间',
    `release_time` timestamp(0)                                                 NOT NULL COMMENT '文章发布时间',
    `is_delete`    tinyint(1)                                                   NOT NULL COMMENT '逻辑删除（0：未删除，1：已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article`
VALUES ('1', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2018-11-25 20:51:52', '2018-11-25 20:51:52',
        '2018-11-25 20:51:52', 0);
INSERT INTO `article`
VALUES ('2', '1', 'Cookie和Session学习记录',
        '\n\n## Cookie 和 Session\n\n### **会话（Session）:**\n\n​	用户打开一个浏览器，点击了许多超链接，访问了多个web资源，关闭浏览器这个过程可以称之为会话。\n\n### **有状态会话：**\n\n​	记录了上次的访问者。\n\n### **Cookie ：**\n\n​	客户端技术（响应、请求）。\n\n1. 请求中获取cookie信息\n2. 服务器响应给客户端\n\n### **记录上次访问时间的页面**\n\n```java\npublic class CookieTest extends HttpServlet {\n    @Override\n    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n        //从request中获取cookie\n        Cookie[] cookies = req.getCookies();\n\n        //设置响应文本类型和编码\n        resp.setContentType(\"text/html;charset=utf-8\");\n\n        //初始化数据\n        String name = \"lastTime\";\n        Date date = new Date();\n        SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n        String time  = sdf.format(date);\n\n        //以utf-8格式对时间字符串进行编码\n        time= URLEncoder.encode(time,\"utf-8\");\n\n        PrintWriter writer = resp.getWriter();\n        //如果cookies不为空，则从中取出名称为lastTime的值，并响应到网站页面\n        if(cookies!=null){\n            for(Cookie cookie : cookies){\n                if(cookie.getName().equals(name)) {\n                    //将时间字符串以utf-8格式解码，防止乱码\n                    writer.println(\"上次访问时间：\" + URLDecoder.decode(cookie.getValue(),\"utf-8\"));\n                }\n            }\n        }else{\n            //cookies为空表示第一次访问网站\n            writer.println(\"第一次访问网站！\");\n        }\n        //更新cookie中的访问时间\n        Cookie cookie = new Cookie(name,time);\n        //设置cookie最大存在时间(1天)\n        cookie.setMaxAge(24*60*60);\n        resp.addCookie(cookie);\n    }\n\n    @Override\n    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n        doGet(req, resp);\n    }\n}\n```\n\n\n\n### **Session：**\n\n​	服务器端技术，利用这个这个技术，可以保存用户的会话信息，我们可以把信息或者数据存储在session中。\n\n​	**常见场景：**网站登录以后，下次访问可以不需要再次登录，第二次访问就处于登录状态。一般这种数据将会保存在本地的用户目录下app/data/...下。\n\n#### 	什么是Session：\n\n​		服务器会给每一个用户（浏览器）创建一个session对象\n\n​		一个session独占一个浏览器，只要浏览器没有关闭，这个session就存在\n\n​		用户登录后整个网站都能访问，保存用户的信息，保存购物车的信息\n\n利用session存数据\n\n```java\n/**\n * @author wj\n * @date 2021/7/9 - 21:57\n */\npublic class SessionTest extends HttpServlet {\n    @Override\n    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n        doPost(req, resp);\n    }\n\n    @Override\n    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n        //解决乱码\n        req.setCharacterEncoding(\"utf-8\");\n        resp.setCharacterEncoding(\"utf-8\");\n        resp.setContentType(\"text/html\");\n\n        //获取session\n        HttpSession session = req.getSession();\n\n        //向session中存储数据\n        session.setAttribute(\"person\",new person(\"张三\",21));\n\n        //获取sessionId\n        String id = session.getId();\n\n        //判断session是否新创建的\n        if(session.isNew()){\n            resp.getWriter().write(\"session创建成功！id:\"+id);\n        }else{\n            resp.getWriter().write(\"session已经存在！id：\"+id);\n        }\n    }\n}\n```\n\n```java\n/**\n * @author wj\n * @date 2021/7/9 - 22:10\n */\npublic class SessionDemoTest extends HttpServlet {\n    @Override\n    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n        doGet(req, resp);\n    }\n\n    @Override\n    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n        //解决乱码问题\n        req.setCharacterEncoding(\"utf-8\");\n        resp.setCharacterEncoding(\"utf-8\");\n        resp.setContentType(\"text/html\");\n\n        //获取session\n        HttpSession session = req.getSession();\n\n        //读取session中的数据并输出\n        person person = (person) session.getAttribute(\"person\");\n        resp.getWriter().write(person.toString());\n    }\n}\n```\n\n取出数据：\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20210712164450229.png#pic_center)\n\n\n\n\n### Cookie和Session区别：\n\n1. cookie是将用户数据写到用户浏览器中，浏览器可以保存多个\n2. session是将用户数据写到用户独占的session中，服务器端保存（保存重要的信息，减少服务器资源的浪费）\n3. session对象是由服务器创建\n\n\n\n### 工作流程\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20210712164502255.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODkwMzY4,size_16,color_FFFFFF,t_70#pic_center)\n',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2018-11-23 20:51:52', '2018-11-25 20:51:52',
        '2018-11-23 20:51:52', 0);
INSERT INTO `article`
VALUES ('3', '1', 'Filter过滤器学习记录',
        '## **Filter过滤器**\n\n**Filter:过滤器，用于过滤网站数据和垃圾请求**\n\n- 处理中文乱码\n- 登录验证\n\n### **开发步骤**\n\n1. 在pom中引入serlvet依赖\n\n   ```xml\n   <dependencies>\n           <dependency>\n               <groupId>org.mortbay.jetty</groupId>\n               <artifactId>servlet-api-2.5</artifactId>\n               <version>6.1.7</version>\n           </dependency>\n       </dependencies>\n   ```\n\n   \n\n2. 编写过滤器\n\n   1. 在过滤器java代码中导入正确的包 javax.servlet\n   2. 实现filter接口，实现其所需要的方法（重点实现doFilter方法）\n\n   ```java\n   import javax.servlet.*;\n   import java.io.IOException;\n   \n   /**\n    * @author wj\n    * @date 2021/7/12 - 10:30\n    */\n   public class filterServlet implements Filter {\n   \n       //初始化\n       @Override\n       public void init(FilterConfig filterConfig) throws ServletException {\n           System.out.println(\"过滤器初始化..\");\n       }\n   \n       //过滤具体操作\n       @Override\n       public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {\n           //处理乱码问题\n           servletRequest.setCharacterEncoding(\"utf-8\");\n           servletResponse.setCharacterEncoding(\"utf-8\");\n           servletResponse.setContentType(\"text/html\");\n           System.out.println(\"问题处理中..\");\n           filterChain.doFilter(servletRequest,servletResponse);\n           System.out.println(\"问题处理完成..\");\n       }\n   \n       //销毁\n       @Override\n       public void destroy() {\n           System.out.println(\"过滤器销毁..\");\n       }\n   }\n   \n   ```\n\n3. 在web.xml中配置过滤器	\n\n```xml\n<filter>\n    <filter-name>filter</filter-name>\n    <filter-class>com.wj.filter.filterServlet</filter-class>\n</filter>\n<filter-mapping>\n    <filter-name>filter</filter-name>\n    <url-pattern>/servlet/*</url-pattern>\n</filter-mapping>\n```\n\n### **Filterchain（链）作用：**\n\n1. 过滤所有代码，在过滤特定请求时候都会执行\n2. 必须使过滤器继续通行\n\n```java\n filterChain.doFilter(servletRequest,servletResponse);\n```\n\n### Filter**工作流程**\n![Filter工作流程](https://img-blog.csdnimg.cn/20210712112550685.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODkwMzY4,size_16,color_FFFFFF,t_70#pic_center)\n\n',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2018-11-08 20:51:52', '2018-11-25 20:51:52',
        '2018-11-08 20:51:52', 0);
INSERT INTO `article`
VALUES ('4', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2018-11-15 20:51:52', '2018-11-25 20:51:52',
        '2018-11-15 20:51:52', 0);
INSERT INTO `article`
VALUES ('5', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2018-11-24 20:51:52', '2018-11-25 20:51:52',
        '2018-11-24 20:51:52', 0);
INSERT INTO `article`
VALUES ('6', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2018-11-21 20:51:52', '2018-11-25 20:51:52',
        '2018-11-21 20:51:52', 0);
INSERT INTO `article`
VALUES ('7', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2021-10-18 21:37:40', '2021-10-04 21:37:36',
        '2021-10-18 21:37:40', 0);
INSERT INTO `article`
VALUES ('8', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2021-09-16 21:37:44', '2021-09-30 21:37:54',
        '2021-09-16 21:37:44', 0);
INSERT INTO `article`
VALUES ('9', '1', 'HttpServletResponse和HttpServletRequest学习记录',
        '**HttpServletResponse 和 HttpServletRequest**\n\n##### 	HttpServletRespose\n\n1. 向浏览器发送数据\n\n   ```java\n   getOutputStream()\n   getWriter()\n   ```\n\n2. 向浏览器发送响应头\n\n   ```javascript\n   server  //使用的服务器名称，如Server:ApachApache/1.36(Unix)\n   Content-type  //用来指明发送给接受者的实体正文的媒体类型，如Content-type:text/html;charset=GBK\n   Content-language //描述了资源所用的自然语言，与Accept-Language对应\n   Content-length //指明实体正文的长度，用以字节方式存储的十进制数字来表示\n   Keep-Alive //保持连接的时间，如Kepp-Alive:timeout=5,max=120\n   ```\n\n3. 常见的状态码\n\n   ```javascript\n   200 	//客服端请求成功\n   302  	//临时跳转，跳转的地址通过指定（页面重定位）\n   400		//客户端请求语法有错误，不能被服务器识别\n   403		//服务器收到请求，但是拒接提供服务\n   404		//请求资源不存在\n   500		//服务器发生不可预期的错误（网关错误，语法错误）\n   ```\n\n   \n\n#####  应用\n\n1. 向浏览器输出信息\n\n2. 下载文件\n\n   ```java\n   public class ResponseDownload extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //获取路径\n   \n           String realPath =\"F:\\\\ideaProject\\\\javaweb-02-servlet\\\\responseTest\\\\target\\\\classes\\\\八重樱.png\";\n   \n           //获取下载文件名\n           String fileName = realPath.substring(realPath.lastIndexOf(\"\\\\\") + 1);\n   //        PrintWriter writer = resp.getWriter();\n   //        writer.println(URLEncoder.encode(fileName,\"UTF-8\"));\n           //设置浏览器支持下载\n           resp.setHeader(\"Content-Disposition\",\"attachment;filename=\"+ URLEncoder.encode(fileName,\"UTF-8\"));\n   \n           //获取文件输入流\n           FileInputStream in = new FileInputStream(realPath);\n           ServletOutputStream os = resp.getOutputStream();\n           int len=0;\n           byte[] buff = new byte[1024];\n           while((len = in.read(buff))!=-1){\n               os.write(buff,0,len);\n           }\n   \n           os.close();\n           in.close();\n   \n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n3. 验证码\n\n4. 实现重定向\n\n   ```java\n   public class ResponseServlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           resp.sendRedirect(\"/re/login.html\");\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n   \n\n##### HttpServletRequest\n\n1. 获取前端传递过来的参数\n\n   ```java\n   getParameter()   	//String str\n   getParameterValues() //String []str\n   ```\n\n2. 请求转发\n\n   ```java\n   public class RequestSerlet extends HttpServlet {\n       @Override\n       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           //从request中获取数据\n           String username = req.getParameter(\"username\");\n           String password = req.getParameter(\"password\");\n           String[] hobbies = req.getParameterValues(\"hobbies\");\n           System.out.println(username);\n           System.out.println(password);\n           //请求转发\n           req.getRequestDispatcher(\"/index.jsp\").forward(req,resp);\n       }\n   \n       @Override\n       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {\n           doGet(req, resp);\n       }\n   }\n   ```\n\n##### 重定向和转发的区别\n\n​	**相同点：**\n\n​		页面都会发生跳转\n\n​	**不同点：**\n\n​		请求转发：url地址栏不会发生变化\n\n​		重定向：url地址栏会发生改变\n\n​		',
        '../assets/img/article_default_img.png', '1', NULL, NULL, 1, '2021-09-30 21:37:48', '2021-10-04 21:37:51',
        '2021-09-30 21:37:48', 0);

-- ----------------------------
-- Table structure for article_tag_ref
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_ref`;
CREATE TABLE `article_tag_ref`
(
    `article_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章id',
    `category_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签id',
    PRIMARY KEY (`article_id`, `category_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章-标签'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag_ref
-- ----------------------------
INSERT INTO `article_tag_ref`
VALUES ('1', '2');
INSERT INTO `article_tag_ref`
VALUES ('1', '9');
INSERT INTO `article_tag_ref`
VALUES ('10', '2');
INSERT INTO `article_tag_ref`
VALUES ('10', '9');
INSERT INTO `article_tag_ref`
VALUES ('2', '2');
INSERT INTO `article_tag_ref`
VALUES ('2', '9');
INSERT INTO `article_tag_ref`
VALUES ('3', '2');
INSERT INTO `article_tag_ref`
VALUES ('3', '9');
INSERT INTO `article_tag_ref`
VALUES ('4', '2');
INSERT INTO `article_tag_ref`
VALUES ('4', '9');
INSERT INTO `article_tag_ref`
VALUES ('5', '2');
INSERT INTO `article_tag_ref`
VALUES ('5', '9');
INSERT INTO `article_tag_ref`
VALUES ('6', '2');
INSERT INTO `article_tag_ref`
VALUES ('6', '9');
INSERT INTO `article_tag_ref`
VALUES ('7', '2');
INSERT INTO `article_tag_ref`
VALUES ('7', '9');
INSERT INTO `article_tag_ref`
VALUES ('8', '2');
INSERT INTO `article_tag_ref`
VALUES ('8', '9');
INSERT INTO `article_tag_ref`
VALUES ('9', '2');
INSERT INTO `article_tag_ref`
VALUES ('9', '9');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '分类ID',
    `name`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '分类名称',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类描述',
    `type`        tinyint(0)                                                    NOT NULL COMMENT '类别（0：分类:1：标签）',
    `update_time` timestamp(0)                                                  NOT NULL COMMENT '更新时间',
    `create_time` timestamp(0)                                                  NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类、标签表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category`
VALUES ('1', 'java', NULL, 0, '2023-02-20 17:07:10', '2023-02-20 17:07:13');
INSERT INTO `category`
VALUES ('1630505679900942338', 'spring', NULL, 1, '2023-02-28 09:50:22', '2023-02-28 09:50:22');
INSERT INTO `category`
VALUES ('2', 'javaWeb', NULL, 1, '2023-02-20 17:06:09', '2023-02-20 17:06:11');
INSERT INTO `category`
VALUES ('9', 'servlet', NULL, 1, '2023-02-20 17:06:44', '2023-02-20 17:06:46');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '评论id',
    `pid`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL     DEFAULT NULL COMMENT '评论父类id',
    `replay_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL     DEFAULT NULL COMMENT '回复用户id',
    `source_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '评论所属id',
    `source_type`    tinyint(0)                                                    NOT NULL COMMENT '所属类型(0:留言，1：文章，2：动态)',
    `context`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
    `create_time`    datetime(0)                                                   NOT NULL COMMENT '评论发布时间',
    `user_id`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '评论发布人',
    `status`         tinyint(0)                                                    NOT NULL DEFAULT 0 COMMENT '评论状态',
    `is_delete`      tinyint(0)                                                    NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment`
VALUES ('1', NULL, NULL, '1', 0, 'test', '2021-09-12 13:19:32', '1', 0, 0);
INSERT INTO `comment`
VALUES ('10', NULL, NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('1630416864926609410', NULL, NULL, '1', 1, 'test', '2023-02-28 03:57:27', '1', 0, 0);
INSERT INTO `comment`
VALUES ('2', '1', NULL, '1', 0, 'test2', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('3', '1', NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('4', '10', NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('5', '10', NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('6', NULL, NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('7', '6', NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('8', '6', NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);
INSERT INTO `comment`
VALUES ('9', '6', NULL, '1', 0, 'lalala', '2021-10-04 22:32:20', '1', 0, 0);

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '动态id',
    `context`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
    `status`      tinyint(0)                                              NOT NULL COMMENT '状态',
    `user_id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '发布人',
    `create_time` timestamp(0)                                            NOT NULL COMMENT '创建时间',
    `update_time` timestamp(0)                                            NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '动态表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
INSERT INTO `dynamic`
VALUES ('1630384243941163009', 'test', 1, '1', '2023-02-28 01:47:49', '2023-02-28 01:47:49');
INSERT INTO `dynamic`
VALUES ('1630384692115070978', 'test', 1, '1', '2023-02-28 01:49:36', '2023-02-28 01:49:36');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '图片id',
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片地址',
    `source_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '图片所属',
    `source_type` tinyint(0)                                                    NOT NULL COMMENT '图片所属类型',
    `create_time` timestamp(0)                                                  NOT NULL COMMENT '图片上传时间',
    `update_time` timestamp(0)                                                  NOT NULL COMMENT '图片修改时间',
    `is_delete`   tinyint(1)                                                    NOT NULL COMMENT '逻辑删除（0：未删除，1：已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片地址存储'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image`
VALUES ('1630384244100546563', '/test', '1630384243941163009', 1, '2023-02-28 01:47:50', '2023-02-28 01:47:50', 0);
INSERT INTO `image`
VALUES ('1630384692240900098', '/test', '1630384692115070978', 1, '2023-02-28 01:49:36', '2023-02-28 01:49:36', 0);
INSERT INTO `image`
VALUES ('1630395290584408067', '/test', '1630395290584408065', 1, '2023-02-28 02:31:43', '2023-02-28 02:31:43', 0);

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '友链主键',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '博主头像',
    `nick_name`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '博主名称',
    `status`      tinyint(0)                                                    NOT NULL COMMENT '博客链接状态',
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '友链地址',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '博主简介',
    `user_id`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '申请用户id',
    `update_time` timestamp(0)                                                  NULL     DEFAULT NULL COMMENT '更新时间',
    `create_time` timestamp(0)                                                  NULL     DEFAULT NULL COMMENT '创建时间',
    `is_delete`   tinyint(1)                                                    NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：未删除，1：已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '友链'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link`
VALUES ('1', '', 'test1', 0, '#',
        '汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃汉堡真好吃',
        '0', NULL, NULL, 0);
INSERT INTO `link`
VALUES ('1631273979874484226', '/test', '阎军', 0, 'http://vlkdsipwv.iq/tmbmuxvayu', NULL, '88', '2023-03-02 12:43:19',
        '2023-03-02 12:43:19', 1);
INSERT INTO `link`
VALUES ('2', '', 'test2', 0, '#', '测试', '0', NULL, NULL, 0);
INSERT INTO `link`
VALUES ('3', '', 'test3', 0, '#', '测试', '0', NULL, NULL, 0);
INSERT INTO `link`
VALUES ('4', '', 'test4', 0, '#', '测试', '0', NULL, NULL, 0);
INSERT INTO `link`
VALUES ('5', '', 'test5', 0, '#', '测试', '0', NULL, NULL, 0);
INSERT INTO `link`
VALUES ('6', '', 'test6', 0, '#', '测试', '0', NULL, NULL, 0);

-- ----------------------------
-- Table structure for message_history
-- ----------------------------
DROP TABLE IF EXISTS `message_history`;
CREATE TABLE `message_history`
(
    `id`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '消息历史id',
    `creator`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '发送者',
    `title`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '消息标题',
    `type`      tinyint(0)                                              NOT NULL COMMENT '消息类型',
    `content`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
    `is_delete` tinyint(0)                                              NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '消息历史'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_history
-- ----------------------------

-- ----------------------------
-- Table structure for message_recipient
-- ----------------------------
DROP TABLE IF EXISTS `message_recipient`;
CREATE TABLE `message_recipient`
(
    `id`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息接收id',
    `message_id`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息id',
    `recipient_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收人',
    `contact_way`  tinyint(0)                                             NOT NULL COMMENT '发送方式',
    `read_time`    timestamp(0)                                           NOT NULL COMMENT '阅读时间',
    `is_delete`    tinyint(0)                                             NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '消息接收历史'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_recipient
-- ----------------------------

-- ----------------------------
-- Table structure for message_template
-- ----------------------------
DROP TABLE IF EXISTS `message_template`;
CREATE TABLE `message_template`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '模板id',
    `name`        varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '模板名称',
    `title`       varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '标题',
    `content`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
    `create_time` timestamp(0)                                            NOT NULL COMMENT '创建时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '消息模板'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_template
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `user_id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
    `type`        tinyint(0)                                             NOT NULL COMMENT '记录类型',
    `source_id`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属id',
    `create_time` datetime(0)                                            NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '点赞、收藏记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for statistics
-- ----------------------------
DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics`
(
    `id`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `like_count`    int(0)                                                 NOT NULL DEFAULT 0 COMMENT '点赞次数',
    `collect_count` int(0)                                                 NOT NULL DEFAULT 0 COMMENT '收藏次数',
    `comment_count` int(0)                                                 NOT NULL DEFAULT 0 COMMENT '评论次数',
    `view_count`    int(0)                                                 NOT NULL DEFAULT 0 COMMENT '浏览次数',
    `source_id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属id',
    `source_type`   tinyint(0)                                             NOT NULL COMMENT '所属类型(0:文章，1：动态)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '文章、动态数据统计'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statistics
-- ----------------------------
INSERT INTO `statistics`
VALUES ('1', 0, 0, 0, 0, '1', 0);
INSERT INTO `statistics`
VALUES ('1630384244100546562', 0, 0, 0, 0, '1630384243941163009', 1);
INSERT INTO `statistics`
VALUES ('1630384692173791234', 0, 0, 0, 0, '1630384692115070978', 1);
INSERT INTO `statistics`
VALUES ('1630395290584408066', 0, 0, 0, 0, '1630395290584408065', 1);
INSERT INTO `statistics`
VALUES ('2', 0, 0, 0, 0, '2', 0);
INSERT INTO `statistics`
VALUES ('3', 0, 0, 0, 0, '3', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '用户id',
    `name`        varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '昵称',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '手机号',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '邮箱',
    `password`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '密码',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('1', 'admin', '', '15570756116', NULL, '123', NULL);

-- ----------------------------
-- Table structure for user_login_record
-- ----------------------------
DROP TABLE IF EXISTS `user_login_record`;
CREATE TABLE `user_login_record`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录记录id',
    `user_id`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属用户id',
    `login_type`  tinyint(0)                                             NOT NULL COMMENT '登录类型',
    `ip_address`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ip地址',
    `ip_resource` tinyint(0)                                             NOT NULL COMMENT 'IP来源',
    `login_time`  timestamp(0)                                           NOT NULL COMMENT '登录时间',
    `is_delete`   tinyint(0)                                             NOT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户登录记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login_record
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
