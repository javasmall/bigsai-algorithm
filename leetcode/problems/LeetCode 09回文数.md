## LeetCode 09回文数

![image-20201115192906733](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201115192906733.png)

**分析：**
此题比较简单，需要考虑以下几点：
- 不能是负数，负数不满足回文数的要求
- 考虑奇数偶数长度数字会文性

提供两种方法：第一种将数字转成字符串，从中间向两侧拓展比较。
![在这里插入图片描述](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70.png)
实现代码为：

```java
//11%
	 public boolean isPalindrome(int x) {
		 if(x<0)return false;
		 String va=x+"";
		 int left=0,right=0;
		 if(va.length()%2==0)
		 {
			 left=va.length()/2-1;right=left+1;
		 }
		 else {
			left=va.length()/2;right=left;
		}
		 while (left>=0) {
			if(va.charAt(left)!=va.charAt(right))
				return false;
			left--;right++;
		}
		 return true;
	 }
```
但很遗憾这种方法效率比较低只能打败11%的人呢,大概18ms左右。

但是可以换一种思路，使用字符串比较效率较低。可以使用数字类型**计算一遍得到逆向数值**然后进行比较最终值是否相同：
![在这里插入图片描述](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70-20201115192755268.png)


```java
public boolean isPalindrome(int x) {
		 if(x<0)return false;
		 int team=x;
		 int va=0;
		 while (x>0) {
			va=va*10+x%10;
			x/=10;
		}
		 if(va==team)return true;
		 return false;
	 }

```
这样就大概9-10ms左右，9ms大概打败98%而10ms只40%多。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200816135901883.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70#pic_center)
## 结语

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)