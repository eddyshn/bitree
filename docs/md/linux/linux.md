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