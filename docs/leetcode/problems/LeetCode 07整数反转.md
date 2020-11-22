## LeetCode 07整数反转
![image-20201115191303035](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115191303035.png)

这题的话注意以下数组越界问题，可以用long类型处理最终再用整形处理。

主要有两种处理方法，一个就是转成字符串处理，第二个就是用数值处理。**但是一般尽量不要用字符串处理比较慢。**

ac代码为：

```java
public  int reverse(int x) {
		 if(x==0)return 0;
		 String value=x+"";
		 if(value.charAt(0)=='-')
			 value=value.substring(1);
		 StringBuilder sb=new StringBuilder();
		 for(int i=value.length()-1;i>=0;i--)
		 {
			 sb.append(value.charAt(i));
		 }
		 long num=Long.parseLong(sb.toString());
		 if(x<0)num=-num;
		 if(num<Integer.MIN_VALUE||num>Integer.MAX_VALUE)
		 {
			 return 0;
		 }
		 return (int)num;
	 }
```

数值类型处理方式为：

```java
 public int reverse(int x) {
		 if(x==0)return 0;
		 long num=0;
		 while (x%10==0) {
			x/=10;
		}
		 int t;
		 while (x!=0) {
		    t=x%10;//各位
			num=num*10+t;
			x/=10;
			if(num>Integer.MAX_VALUE||num<Integer.MIN_VALUE)
				return 0;
		}
		 return (int)num;	 
	 }
```
时间还行：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200814194720924.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201114211553660.png)

