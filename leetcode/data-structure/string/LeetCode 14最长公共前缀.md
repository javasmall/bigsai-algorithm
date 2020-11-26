## LeetCode 14最长公共前缀
![image-20201115204145914](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115204145914.png)

这题的话思路很简单，但是需要一定优化，尽量减少判断，所以这里使用最短的那个串作为预备遍历的串(公共前缀极限这么大)。然后只需要每次遍历比较找到第一个不同的即停止。否则一直进行。

ac代码为：

```java
public String longestCommonPrefix(String[] strs) {
      StringBuilder sBuilder=new StringBuilder();
		if(strs==null||strs.length==0)return "";
		String team=strs[0];
		for(int i=0;i<strs.length;i++)
		{
			if(strs[i].length()<team.length())
			{
				team=strs[i];
			}
		}
		int i=0;
		for(;i<team.length();i++)
		{
			for(int j=0;j<strs.length;j++)
			{
				//System.out.println(strs[j].charAt(i)+" "+team.charAt(i));
				if(strs[j].charAt(i)!=team.charAt(i))
				{
					 return sBuilder.toString();
					
				}
			}
			sBuilder.append(team.charAt(i));
		}
		return sBuilder.toString();		
    }
```
不知道0ms是什么神仙啊
![image-20201115204245702](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115204245702.png)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://img-blog.csdnimg.cn/img_convert/3cd335655373276f330fa2c16b0e20f6.png)