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
-e MYSQL_ROOT_PASSWORD=wj1324  \
--name mysql mysql:8.0
```

### docker REDIS 挂载

```shell
docker run --name redis \
 --restart=always -p 6379:6379 \
 -d redis:6.0 --requirepass "wj1324" //启动Redis
```
