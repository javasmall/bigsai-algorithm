## LeetCode 13罗马数字转整数
上一题是整数转罗马数字，这题是罗马数字转整数。虽然是简单题，但我感觉其实有点烦。


![image-20201115202812819](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115202812819.png)

上一次是数字转字符，这次是字符转数字，总的来说大体思想还是差不多的。
首先整个字符串可能是这样构造的：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020082421452356.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

然后你根据每个字符依次处理(这里从右往左)I类型，V类型，X类型。每种类型只需要考虑一下特殊情况的数值加上去就ok。记得移动字符串光标位置就可。

实现代码为：

```java
public static int romanToInt(String s) {
	 int numvalue []={1,5,10,50,100,500,1000};
	 char charvalue []= {'I', 'V', 'X', 'L','C','D','M'};
	 StringBuilder sBuilder=new StringBuilder();
	 int index=s.length()-1;
	 int value=0;
	 for(int i=0;i<charvalue.length;i++)//i代表字符
	 {
		 if(index<0)break;
		 if(s.charAt(index)==charvalue[i])
		 {
			 if(i%2==0&&i-2>=0&&index>=1&&s.charAt(index-1)==charvalue[i-2])
			 {
				 value+=numvalue[i];
				 value-=numvalue[i-2];
				 index-=2;
			 }
			 else if(i%2==1&&i-1>=0&&index>=1&&s.charAt(index-1)==charvalue[i-1])
			 {
				 value+=numvalue[i];
				 value-=numvalue[i-1];
				 index-=2;
			 }
			 while(index>=0&&s.charAt(index)==charvalue[i])//正常数字
			 {
				 value+=numvalue[i];
				 index--;
			 }
		 }	
	 }
	 return value;
	  
  }
```

## 结语

原创不易，bigsai我请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)