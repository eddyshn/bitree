# 安装软件包

```npm install```

> --save 添加到dependencies **默认值**
>
> --save-dev 添加到devDependencies 
>
> -g 全局安装

# 查看安装的软件包

```sh
npm list
npm list -g
#查看根包
npm list --depth=0
npm list <package-name>
#查看包在仓库的最新版本
npm view <package-name> version
```

# 更新软件包

```console
#合部更新
npm update
#更新特定包
npm update <package-name>
```

# 卸载 软件包

```npm uninstall <package-name>```

> -S / --save 从package.json的dependencies中删除
>
> -D / --save-dev 从package.json的devDependencies 中删除
>
> -g / --global 全局删除

# npm 配置

[doc](https://docs.npmjs.com/cli-commands/config.html)

## .npmrc

```bash
#快速编辑
npm config edit

#查看配置信息
npm config ls
npm config ls -l

userconfig = "C:\\Users\\eddys\\.npmrc"
globalconfig = "C:\\Users\\eddys\\AppData\\Roaming\\npm\\etc\\npmrc"
globalignorefile = "C:\\Users\\eddys\\AppData\\Roaming\\npm\\etc\\npmignore"
cache = "C:\\Users\\eddys\\AppData\\Roaming\\npm-cache"
prefix = "C:\\Users\\eddys\\AppData\\Roaming\\npm"
metrics-registry = "https://registry.npmjs.org/"

#npm的缓存目录在哪里？
npm config get cache

#npm的全局node包在哪里？
npm config get prefix

#清除缓存
npm cache clean --force
```

