# 文件权限

```she
ls -l
drwxrwxr-x. 9 eddy eddy 220 May 26 19:31 apache-tomcat-8.5.55
```

d - 文档类型

rwx - 文件拥有者可具备的权限  

rwx - 加入此群组之账号的权限  

r-x - 非本人且没有加入本群组之其他账号的权限  

9 - 多少档名连结到此节点  

eddy - 拥有者账号  

eddy - 文件的所属群组

220 - 文件的容量大小，默认单位为 bytes  

May.. - 个文件的建档日期或者是最近的修改日期  

Apache... - 文件的档名 

# 用户切换

`su -`

`exit`



# 系统服务 -  daemon

```bash
#启动服务
systemctl start docker

#查看服务状态
systemctl status docker
	Loaded: loaded (/usr/lib/systemd/system/atd.service; enabled)#Loaded enable 说明开机启动
	
#开机启动
systemctl enable docker

#关闭服务
systemctl stop docker
```