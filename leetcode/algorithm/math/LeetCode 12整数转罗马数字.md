## LeetCode 12整数转罗马数字

![image-20201115201045832](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115201045832.png)

### 分析
对于这题，其实就是一个数字和字符串处理的问题。**规则和进制准换很相似** ，思路也大体一致——不断整除和求余，只不过要考虑5和10的两种特殊情况:
- 对于5种类来说除数的结果一定是4并且当前满足为偶数位(0,2,4等位)（比如处理4时候4/1=4，40/10=4）
- 对于10种类来说除数的结果为1，且加上比它小一位的数和为比它大一位的数(比如9/5=1，9+1=10)，且都在奇数位上。

有了上述方法找到对应位置的数就可以进行操作了，记住叠加罗马数字一定别用**String**而要用**StringBuilder**。

实现代码为：

```java
 public static String intToRoman(int num) {
		 int numvalue []={1,5,10,50,100,500,1000};
		 char charvalue []= {'I', 'V', 'X', 'L','C','D','M'};
		 StringBuilder sBuilder=new StringBuilder();
		 int team=0;
		 for(int i=numvalue.length-1;i>=0;i--)
		 {
			 team=num/numvalue[i];
			 if(team==4&&i%2==0) {//向上进一为
				 sBuilder.append(charvalue[i]);	
				 sBuilder.append(charvalue[i+1]);	
				 num=num%numvalue[i];
				 
			 }
			 else if (team==1&&i%2==1&&(num+numvalue[i-1])/numvalue[i+1]==1) {
				sBuilder.append(charvalue[i-1]);
				sBuilder.append(charvalue[i+1]);
				num%=numvalue[i-1];
			}
			 else {
				for(int j=0;j<team;j++)
				{
					sBuilder.append(charvalue[i]);
				}
				 num=num%numvalue[i];
			}
			
		 }
		 return sBuilder.toString();	 
	 }
```
效果还不错！
![image-20201115201234360](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115201234360.png)

## 结语

原创不易，bigsai我请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)