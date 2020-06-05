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