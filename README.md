## 注意事项

### 本地服务
#### redis
启动redis：
```
# 修改配置文件
vi /etc/redis.conf
开启密码认证
requirepass mktime_123

# 启动服务
redis-server -c /etc/redis.conf
```

#### mysql(8.0.x版本,使用5.7版本会报错)

创建数据库
```
CREATE DATABASE develop
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
```

 导入数据
```
mysql -hlocalhost -uroot -p develop < db.sql
```

#### minio 
安装minio

```
brew install minio/stable/minio
```

启动
```
minio server /Users/demo/miniodata
```


### 启动后端
代码clone下来后：
```
# 编译
mvn clean package

# 解压
cd porn-web/target
unzip -d captain Captain-3.3.0.zip

# 启动,默认启用conf/application-prod.yml
cd captain/bin
sh start.sh

```

后端服务默认监听端口是8888。前端执行的时候改为对应的端口。

### 启动前端
(前端代码不包含在本仓库中)
解压前端代码mng-pc后：
```
cd mng-pc

# 安装依赖
rm package-lock.json
npm install

# 修改后端服务端口
vi .umirc.js

proxy:
UMI_ENV === 'prod'
? {}
: {
'/api': {
target: 'http://localhost:8888',
changeOrigin: true,
pathRewrite: { '^/api': '' },
},
},

```

