## LeetCode 03无重复字符的最长子串(滑动窗口)
**题目描述：**
>给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

**示例 1:**
>输入: "abcabcbb"
>输出: 3 
>解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

**示例 2:**
>输入: "bbbbb"
>输出: 1
>解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

**示例 3:**
>输入: "pwwkew"
>输出: 3
>解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
>请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

## 分析
此题就是给一个字符串让你**找出最长没有重复的一个字串。** 要搞清子串和子序列的区别：
- 子串：**是连续的**，可以看成原串的一部分截取。
- 子序列：不一定是连续的，但是要保证各个元素之间相对位置不变。


处理思路？
- 暴力查找，暴力查找当然是可以的，但是复杂度过高这里就不进行讲解了。

本题选择的思路是**滑动窗口**，滑动窗口，就是用一个区间从左往右，右侧先进行试探，找到区间无重复最大值，当有重复时左侧再往右侧移动一直到没重复，然后重复进行。在整个过程中找到最大的那个空间返回即可。

但是在Java编程语言中如何操作呢？
- 定义一个`left`和`right`，表示滑动的区间。初始均为0.定义一个`max`表示最长初始为0.
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807173224764.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

- `right`往右移动，同时记录移动经过元素的个数。当遇到重复即存在该元素的时候暂停。比较这个长度(right-left+1)与max的大小，max是否需要更新。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807173341355.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

- 接着`left`往右移动，同时移动途中将出现字母的词数减一。直到移动到right位置相同字母的右侧说明当前窗口没有重复序列了，继续循环执行到结束。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020080717343376.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


当然，最长的情况也在其中，因为我们只要不重复right就会右移，不能移的时候前一个即**可能是**最大长度：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200807173645343.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

你可能会问，用什么存储这个词数呢？
- 哈希当然可以啦，你可以用HashMap存储记录这个值进行维护，就是可能偶尔稍微麻烦一点。

因为咱们知道字符`char`它底层是一个ASCII,是一个数值，我们可以创建一个int数组直接把ASCII值作为数组对应下表进行处理，这样虽然占了点内存但是使用起来方便很多。

## ac代码
附上ac 代码：

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        	 int a[]=new int[500];
		 
		 int max=0;
		 int l=0;
		 for(int i=0;i<s.length();i++)
		 {
			 a[s.charAt(i)]++;
			 while (a[s.charAt(i)]>1) {
				a[s.charAt(l++)]--;
			}
			 if(i-l+1>max)
				 max=i-l+1;
		 }
		 return max;
    }
}
```

## 最后

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)

