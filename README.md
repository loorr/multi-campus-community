# 校园社区项目需求分析

1. 用户
> **分权限，游客、普通用户、管理员，官方账号**
> 用户 - 角色 - 权限
> [http://www.woshipm.com/pd/1150093.html](http://www.woshipm.com/pd/1150093.html)

1. 游客登陆/账号密码登录
    1. **邮箱，密码登录 (QQ，微信)**
    1. **登出**
2. 官方账号，实现发布重要通知等功能
2. 收藏夹（收藏、取消收藏、收藏夹分类）
2. 消息推送（接口，前端调用接口，获取所有消息）
2. 管理员
    1. 额外想一下
2. 好友关系
    1. **单向关注**
    1. **互为好友**
    1. 聊天（私信：文件、语音、文字、视频、表情包）
    1. 设置分组
3. 动态
    1. **实名个人动态**
    1. **匿名动态**
    1. **热门动态（public）【24h 游览量，点赞数】**
    1. **转发、评论(回复)**
    1. **收藏**
    1. **动态分类（学术/生活；老师/学生；）**
    1. **动态管理（删除、创建、屏蔽(屏蔽单条动态，包括之后动态的回复)、拉黑（拉黑动态）等）**
    1. **动态可以设置为部分人可见、部分人不可见；**
    1. **短动态、博客**
4. **群组**
    1. **创建、退出群聊**
5. 创建组织（学院或班级为单位）
5. 资源库（用于共享资源）
5. 动态推荐
5. **搜索，**检索出来的动态按一定算法排序显示
5. 公告栏
    1. 学校通知
    1. 招聘
    1. 竞赛信息
10. 举报、监督、反馈
1. 点赞、拉踩并且对于公开动态的展示有一个根据点赞拉踩数量的排序；


## 开发记录

### 引入 protobuf
1. win10 安装 protobuf
   
2. maven 引入[版本号需要和本地的版本号一致]

```xml
   <dependency>
       <groupId>com.google.protobuf</groupId>
       <artifactId>protobuf-java</artifactId>
       <version>xxx</version>
   </dependency>
```

3. idea安装插件
   
4. 配置maven插件

```xml
<plugin>
 <groupId>org.xolstice.maven.plugins</groupId>
 <artifactId>protobuf-maven-plugin</artifactId>
 <version>0.6.1</version>
 <configuration>
     <protocExecutable>
         C:\software\Java\protoc-3.18.0-rc-1-win64\bin\protoc.exe  <!-- 刚刚环境变量配置的位置 -->
     </protocExecutable>
     <pluginId>protoc-java</pluginId>
     <!-- proto文件放置的目录 -->
     <protoSourceRoot>${project.basedir}/src/main/java/com/example/model/protobuf</protoSourceRoot>
     <!-- 生成文件的目录 -->
     <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
     <!-- 生成文件前是否把目标目录清空，这个最好设置为false，以免误删项目文件 -->
     <clearOutputDirectory>false</clearOutputDirectory>
 </configuration>
 <executions>
     <execution>
         <goals>
             <goal>compile</goal>
         </goals>
     </execution>
 </executions>
</plugin>
```