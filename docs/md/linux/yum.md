# Yum repository configuration

```shell
/etc/yum.reops.d
```

# Add Repository

```shell
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

go to `/etc/yum.repos.d` folder, you will found `docker-ce.repo` is added

# yum install

`yum install -y docker`

> -y automatically answer 'yes' for all questions

# yum remove

`sudo yum remove docker`

# Show list available in the repository

`yum list docker-ce --showduplicates | sort -r`

# install specific version

`sudo yum install docker-ce-<VERSION_STRING> docker-ce-cli-<VERSION_STRING> containerd.io`