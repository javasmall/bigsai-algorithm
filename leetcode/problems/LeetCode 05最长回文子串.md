## LeetCode 05最长回文子串
描述：
>给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
>输入: "babad"
>输出: "bab"
>注意: "aba" 也是一个有效答案。

示例 2：
>输入: "cbbd"
>输出: "bb"



## 普通暴力
**分析：**
- 求最长的回文串。而回文串又有奇数串和偶数串两种形式，我们只需要对有所情况从左到右进行枚举，然后返回最长的串即可。
- 在编写代码的同时注意边界的问题不能越界。返回合理编号字符串。
- 不要用String类型进行拼凑，因为String是不可变类每个拼凑都会生成一个新的字符串，多个拼凑会导致效率非常低下。

通过代码：

```java
public String longestPalindrome(String s) {
		int max = 0;
		String va = "";
		if (s.length() > 0)
			va = s.charAt(0) + "";
		for (int i = 0; i < s.length() - 1; i++) {
			int l = i, r = i;//奇数个回文串
			while (l >= 0 && r <= s.length() - 1) {
				if (s.charAt(l) == s.charAt(r)) {
					l--;
					r++;
				} else {
					break;
				}
			}
			if (r - l + 1 > max) {
				max = r - l + 1;
				va = s.substring(l+1, r );
			}
			l = i;r = i + 1;//偶数个回文串
			if (s.charAt(i) == s.charAt(i + 1)) {
				while (l >= 0 && r <= s.length() - 1) {
					if (s.charAt(l) == s.charAt(r)) {
						l--;
						r++;
					} else {
						break;
					}
				}
			}
			if (r - l + 1 > max) {
				max = r - l + 1;
				va = s.substring(l+1, r );
			}

		}
		return va;
	}
```

## 中心扩散
求**最长回文串**，能不能有什么优化的方案呢？

首先，最长可能出现在哪里呢？
- 当然最长会出现在中间位置：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200812172316918.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70#pic_center)
如果第一次就找到这个最大的长度了，那么还需要查找**其他不可能比它长的回文串**了嘛？
- 当然不需要。

使用什么方法能够确定不需要再查找更短的回文串了呢？
- 从中间向两边查找，边查找边标记最大的回文串长度为max。
- 如果向两边扩散时候该点到其中一个边界距离的二倍明显已经小于最长回文串的max长度，那就没必要计算了。可以直接停止。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200812174724505.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
不过在具体的代码实现方面，要注意一些界限、特殊情况。ac代码为：

```java
// 法二，中间扩散
	public static String getmaxhuiwen(int l,int r,String s) {
		if(l>r)return"";
		while (l >= 0 && r <= s.length() - 1) {
			if (s.charAt(l) == s.charAt(r)) {
				l--;
				r++;
			} else {
				break;
			}
		}
		return s.substring(l+1, r);
		
	}
	public  static String longestPalindrome(String s) {
		int max = 0;
		String va = "";
		if(s.length()<2)return s;//""和"a"
		int mid = (s.length()-1) / 2;//中间(偶数左侧，奇数中间)
		for (int i = 0; i < mid+1; i++) {
			
			int l = mid - i, r = l;//左奇数个
			String s1=getmaxhuiwen(l, r, s);
			va=va.length()>s1.length()?va:s1;
			l=mid-i;r=l+1;//左偶数个
			s1=getmaxhuiwen(l, r, s);
			va=va.length()>s1.length()?va:s1;
			l=mid+i;r=l;//右奇数个
			s1=getmaxhuiwen(l, r, s);
			va=va.length()>s1.length()?va:s1;
			l=mid+i;r=l+1;//右偶数个
			s1=getmaxhuiwen(l, r, s);
			va=va.length()>s1.length()?va:s1;
			max=va.length();//最大回文长度
			if(max>(mid-i+1)*2)//找不到更长直接返回
			{
				break;
			}
			
		}
		return va;
	}
```
这种情况效率已经不错了：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200812180907943.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)

## 结语
至于题解区有人提出动态规划的方法，但是动态规划在这题并没有太好的效率提高。这里就不作介绍了。

还有求回文串的**马拉车算法**，后面会专门写一篇记录学习和理解，敬请关注！

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)

