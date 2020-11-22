## LeetCode 08字符串转整数
![image-20201115191511298](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115191511298.png)
![image-20201115191559452](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115191559452.png)
**分析：**

本题主要是字符串的处理。需要注意以下几点：
- 去除字符前的空字符
- 第一个有效字符必须是符号或者数字
- 只能有一个符号
- 注意数值越界

所以在具体处理的时候，你可以截取一个字符然后直接转成一个数字类型(考虑越界)但是并不推荐。这里个人使用遍历字符串数字字符时候将其与`'0'`字符差转换成数字进行计算，当超出int范围直接停止。

通过代码为：

```java
public static int myAtoi(String str) {	
		int zheng = 1;
		int index=0;
		long value=0;
		while (index<str.length()&&str.charAt(index)==' ') {//防止"" 和 "   "等
			index++;
		}
		if(index>str.length()-1)return 0;
		if(str.charAt(index)=='+') {index++;}
		else if (str.charAt(index)=='-') {
			zheng=-1;index++;
		}
	
		for(int j=index;j<str.length();j++)
		{	
			if(str.charAt(j)>='0'&&str.charAt(j)<='9')
			{
				value=value*10+str.charAt(j)-'0';
				if(value*zheng>Integer.MAX_VALUE)return Integer.MAX_VALUE;
				if(value*zheng<Integer.MIN_VALUE)return Integer.MIN_VALUE;
			}
			else {
				break;
			}
		}
		value=zheng*value;	
		return (int)value;
	}
```

## 结语

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)

