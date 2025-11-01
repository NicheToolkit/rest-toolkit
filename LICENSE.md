### Keytool生成公私钥证书库
> 
> 公钥库密码为：5rjbahNkhcCKpGtpT2Xs
> 
> 私钥库密码为：sCEuUecTNet3ZnUJaTxu
> 

> * 生成私匙库
> 
> validity：私钥的有效期多少天
> 
> alias：私钥别称
> 
> keystore: 指定私钥库文件的名称(生成在当前目录)
> 
> storepass：指定私钥库的密码(获取keystore信息所需的密码)
> 
> keypass：指定别名条目的密码(私钥的密码)
> 
> ```bash
> keytool -genkeypair -keysize 1024 -validity 3650 -alias "privateKey" -keystore "privateKeys.keystore" -storepass "5rjbahNkhcCKpGtpT2Xs" -keypass "sCEuUecTNet3ZnUJaTxu" -dname "CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN"
> ```

> * 私匙库内的公匙导出到一个文件当中
>
> alias：私钥别称
> 
> keystore：指定私钥库的名称(在当前目录查找)
> 
> storepass: 指定私钥库的密码
> 
> file：证书名称
> 
> ```bash
> keytool -exportcert -alias "privateKey" -keystore "privateKeys.keystore" -storepass "5rjbahNkhcCKpGtpT2Xs" -file "certfile.cer"
> ```

> * 再把这个证书文件导入到公匙库
>
> alias：公钥别称
> 
> file：证书名称
> 
> keystore：公钥文件名称
> 
> storepass：指定私钥库的密码
>
> ```bash
> keytool -import -alias "publicCert" -file "certfile.cer" -keystore "publicCerts.keystore" -storepass "5rjbahNkhcCKpGtpT2Xs"
> ```

