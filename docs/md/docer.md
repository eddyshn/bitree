# 开机启动

```bash
 sudo docker run --restart=always -d -p 8388:8388 <image> -shadowsocks -s 0.0.0.0 -p 8388 -k $SSPASSWORD -m aes-256-cfb

```

