# jasypt 配置加密组件

> * 最顶级 `pom.xml` 文件已经为所有子项目引用了 `jasypt-spring-boot-starter` 组件依赖，子项目无需另行配置依赖。
> * `venus-example-jasypt` 模块引用了 `jasypt-maven-plugin` 插件依赖，可在此项目中获取加密后的密文。

## 加密

**1. 加密单个参数值**

```shell
mvn jasypt:encrypt-value -Djasypt.encryptor.password="encrypt_password" -Djasypt.plugin.value="theValueYouWantToEncrypt"
```

* -Djasypt.encryptor.password: 加密密钥
* -Djasypt.plugin.value: 你需要加密的参数明文

加密后的结果为：`ENC(AU2g4Klbbo3sbLt4SF84LIky50NjLlepnX8WoXpbzrgXYlW+3NE5dMGG+r2obJmLiMirRTX3nYB7ZTUwo2x55g==)`

**2. 加密文件**

可以将多个参数写在文件中，然后指定文件，将文件中需要加密的参数一次性全部加密。需要加密的参数要使用 `DEC()` 包裹起来。如下：

```yaml
username: DEC(admin)
password: DEC(123456)
```
使用命令进行加密：

```shell
mvn jasypt:encrypt -Djasypt.encryptor.password="encrypt_password" -Djasypt.plugin.path="file:src/main/resources/application.yaml"
```
加密结果如下：

```yaml
username: ENC(NOeEuyKJqu1CdmxG0F4Z8CRiIhunPoIsRWrjvPUaKlwJrtAmK7/PPX5HhTgrfeGk)
password: ENC(jyccgE6SfA5Zey4tCawfdQt8BMUXsrcWD02d9gSFO4IDlhP0fRpFhvUfT9pp8EnN)
```

## 解密

**1. 解密单个参数**

```shell
mvn jasypt:decrypt-value -Djasypt.encryptor.password="encrypt_password" -Djasypt.plugin.value="DbG1GppXOsFa2G69PnmADvQFI3esceEhJYbaEIKCcEO5C85JEqGAhfcjFMGnoRFf"
```

**2. 解密文件**

```shell
mvn jasypt:encrypt -Djasypt.plugin.path="file:src/main/resources/application.yaml" -Djasypt.encryptor.password="encrypt_password"
```

## 非对称加解密

公钥加密：

```shell
mvn jasypt:encrypt-value -Dspring.config.location="file:src/main/resources/application.yaml" -Djasypt.encryptor.public-key-format="PEM" -Djasypt.encryptor.public-key-location="file:src/main/resources/publickey.pem" -Djasypt.plugin.value="shiduai"
```

解密测试：

```shell
mvn jasypt:decrypt-value -Djasypt.encryptor.password="encrypt_password" -Djasypt.plugin.value="DbG1GppXOsFa2G69PnmADvQFI3esceEhJYbaEIKCcEO5C85JEqGAhfcjFMGnoRFf"
```

```shell
mvn jasypt:decrypt-value -Dspring.config.location="file:src/main/resources/application.yaml" -Djasypt.encryptor.private-key-format="PEM" -Djasypt.encryptor.private-key-location="file:src/main/resources/privatekey.pem" -Djasypt.plugin.value="TV2eB83Fz2My+T18BSFK9v0xCY94jufjspR1j11xn7ZR0XEueCWt3eQCBuLNCn34c1zcbP5ewjS+xjQ+ij/4OtRYgpJ5svNRt1y2xVxxePmi4hf+U6J1ZlMZXvYd9cMRpV7BxTCQjdrmgHmbedcSZv317QWaM0WhW+hl/1g3Ealu2ZfwyskUQkTLGr/toPE/Hgg41pUq9GjM2EnA9IKx041fWMT4UBZH2cZ+zIAVFU48EaiWDQu7mmjSEePVZyq8aEEaVONjgI2EfEecgIOXUXh7kHoCAqg5yNT1ySl25U/4sHPUiuDcCYFQ5CAg42r0hmS9sXAebPCJ3Izw5hwEeA=="
```

私钥解密：

```shell
jasypt:
  encryptor:
    privateKeyFormat: PEM
    privateKeyLocation: classpath:privatekey.pem
```