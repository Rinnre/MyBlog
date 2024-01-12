## 环境搭建

### maven安装

1. [下载](https://maven.apache.org/download.cgi)（打不开就找国内镜像网址、或者直接用idea自带的maven也行如果你不进行开发的话）

2. 解压![image-20230227145752277](C:/Users/wj/AppData/Roaming/Typora/typora-user-images/image-20230227145752277.png)

3.

修改conf文件夹中的setting文件，配置阿里云镜像仓库<img src="C:/Users/wj/AppData/Roaming/Typora/typora-user-images/image-20230227150000629.png" alt="image-20230227150000629"  />

```xml
<mirrors> 
    <mirror>
      <id>maven-default-http-blocker</id>
      <mirrorOf>external:http:*</mirrorOf>
      <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
      <url>http://0.0.0.0/</url>
      <blocked>true</blocked>
    </mirror>
    
    <mirror>      
      <id>nexus-aliyun</id>    
      <name>nexus-aliyun</name>  
      <url>http://maven.aliyun.com/nexus/content/groups/public</url>    
      <mirrorOf>central</mirrorOf>      
    </mirror>
    
  </mirrors>
```

4. 新建一个本地maven存放仓库（文件夹名称随便起）

   ![image-20230227150241387](C:/Users/wj/AppData/Roaming/Typora/typora-user-images/image-20230227150241387.png)

5. idea配置maven设置

![image-20230227150340217](C:/Users/wj/AppData/Roaming/Typora/typora-user-images/image-20230227150340217.png)

6. 重写maven配置

   ![image-20230227150755238](C:/Users/wj/AppData/Roaming/Typora/typora-user-images/image-20230227150755238.png)

### docker MYSQL 挂载

```sh
docker run -d -p 32006:3306 \
--restart=always \
-v /auroras/mysql/log:/var/log/mysql \
-v /auroras/mysql/data:/var/lib/mysql \
-v /auroras/mysql/conf:/etc/mysql/conf.d  \
-e MYSQL_ROOT_PASSWORD=  \
--name mysql mysql:8.0
```

### docker REDIS 挂载

```shell
docker run --name redis \
 --restart=always -p 6379:6379 \
 -d redis:6.0 --requirepass "" //启动Redis
```

模块划分
- 文章
- 动态
- 友链
- 我的
- 评论
- 消息
- 分类

#### 实时推送方法选择
- 长轮询
- websocket
- SSE  
   单向连接，只支持UTF-8编码，存在最大连接数
- MQTT  
   双向连接，缺乏有效的安全验证，通讯时间长
- http2推送

#### 文章访问量、点赞量、收藏量
- 访问量根据ip进行过滤，一天内同一个ip只增加一次访问量
- 点赞、收藏需要做用户登录判断，请求头判断是否存在带有用户信息的token
- 上述操作均需通过SSE推送到前端
- redis缓存，需要考虑与MySQL同步，文章多，内存占用大

#### 回收站
1. 删除后先进行逻辑删除，并更新修改时间
2. 回收站只查询已经被删除的文章
3. 定时任务，每天定时清理超过30天的文章
4. 优化第三步，过期时间小于等于1天时，推送消息，并存入redis，通过监听过期回调删除文章
5. 上一步存在缺点，文章过多时redis的内存占用会过大

