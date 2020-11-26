

## LeetCode 43字符串相乘
![image-20201118205245529](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201118205245529.png)



不允许我们使用BigInteger，肯定无法使用常规类型去解决问题了。对应也要使用其他方法去解决问题。

怎么处理我们需要分析这个乘法的底层逻辑到底是怎么样的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201023201823410.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


这样看这张图，其算法逻辑应该很清晰了，所以我们在具体处理的时候就用一个int数组用来表示对应位数乘积的值，最后从个位向上进位只保留各位就可以。

你可能会疑问，如果**两个数组的长度分别为a和b这个数组到底该开多大呢**？
- a+b大小就够了，怎么分析呢？其中一个a不变。另一个b变成最小b+1数字即十的倍数，那么这样在相乘的时候也不过是a+b长度，所以这里a+b长度就够了。

ac的代码为：

```java
public String multiply(String num1, String num2) {
      if("0".equals(num1)||"0".equals(num2))return "0";
      char a[]=num1.toCharArray();
		char b[]=num2.toCharArray();
		
		int value[]=new int[a.length+b.length];
		
		for(int i=a.length-1;i>=0;i--)
		{
			for(int j=b.length-1;j>=0;j--)
			{
				int index=a.length-1-i+b.length-1-j;
				value[index]+=(a[i]-'0')*(b[j]-'0');
			}
		}
	
		//System.out.println(Arrays.toString(a)+""+Arrays.toString(b)+" "+Arrays.toString(value));
		for(int i=0;i<value.length-1;i++)
		{
			value[i+1]+=value[i]/10;
			value[i]=value[i]%10;
		}
		int index=value.length-1;
		while(value[index]==0)
		{index--;}
		StringBuilder sBuilder=new StringBuilder();
		while (index>=0) {
			sBuilder.append(value[index--]);
		}
		return sBuilder.toString();
    }
```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201023195744740.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)