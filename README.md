# У԰������Ŀ�������

1. �û�
> **��Ȩ�ޣ��ο͡���ͨ�û�������Ա���ٷ��˺�**
> �û� - ��ɫ - Ȩ��
> [http://www.woshipm.com/pd/1150093.html](http://www.woshipm.com/pd/1150093.html)

1. �ο͵�½/�˺������¼
    1. **���䣬�����¼ (QQ��΢��)**
    1. **�ǳ�**
2. �ٷ��˺ţ�ʵ�ַ�����Ҫ֪ͨ�ȹ���
2. �ղؼУ��ղء�ȡ���ղء��ղؼз��ࣩ
2. ��Ϣ���ͣ��ӿڣ�ǰ�˵��ýӿڣ���ȡ������Ϣ��
2. ����Ա
    1. ������һ��
2. ���ѹ�ϵ
    1. **�����ע**
    1. **��Ϊ����**
    1. ���죨˽�ţ��ļ������������֡���Ƶ���������
    1. ���÷���
3. ��̬
    1. **ʵ�����˶�̬**
    1. **������̬**
    1. **���Ŷ�̬��public����24h ����������������**
    1. **ת��������(�ظ�)**
    1. **�ղ�**
    1. **��̬���ࣨѧ��/�����ʦ/ѧ������**
    1. **��̬����ɾ��������������(���ε�����̬������֮��̬�Ļظ�)�����ڣ����ڶ�̬���ȣ�**
    1. **��̬��������Ϊ�����˿ɼ��������˲��ɼ���**
    1. **�̶�̬������**
4. **Ⱥ��**
    1. **�������˳�Ⱥ��**
5. ������֯��ѧԺ��༶Ϊ��λ��
5. ��Դ�⣨���ڹ�����Դ��
5. ��̬�Ƽ�
5. **������**���������Ķ�̬��һ���㷨������ʾ
5. ������
    1. ѧУ֪ͨ
    1. ��Ƹ
    1. ������Ϣ
10. �ٱ����ල������
1. ���ޡ����Ȳ��Ҷ��ڹ�����̬��չʾ��һ�����ݵ�����������������


## ������¼

### ���� protobuf
1. win10 ��װ protobuf
   
2. maven ����[�汾����Ҫ�ͱ��صİ汾��һ��]

```xml
   <dependency>
       <groupId>com.google.protobuf</groupId>
       <artifactId>protobuf-java</artifactId>
       <version>xxx</version>
   </dependency>
```

3. idea��װ���
   
4. ����maven���

```xml
<plugin>
 <groupId>org.xolstice.maven.plugins</groupId>
 <artifactId>protobuf-maven-plugin</artifactId>
 <version>0.6.1</version>
 <configuration>
     <protocExecutable>
         C:\software\Java\protoc-3.18.0-rc-1-win64\bin\protoc.exe  <!-- �ոջ����������õ�λ�� -->
     </protocExecutable>
     <pluginId>protoc-java</pluginId>
     <!-- proto�ļ����õ�Ŀ¼ -->
     <protoSourceRoot>${project.basedir}/src/main/java/com/example/model/protobuf</protoSourceRoot>
     <!-- �����ļ���Ŀ¼ -->
     <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
     <!-- �����ļ�ǰ�Ƿ��Ŀ��Ŀ¼��գ�����������Ϊfalse��������ɾ��Ŀ�ļ� -->
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