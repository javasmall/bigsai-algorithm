## LeetCode 76最小覆盖子串
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。



示例 1：
```
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
```
示例 2：
```
输入：s = "a", t = "a"
输出："a"
```

提示：
```
1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
```
进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？


问题分析：

题意很清晰，就是要求再s串中找到所有包含t串字符的短串。不考虑存储我们分析一下该怎么做呢？

常规思路：暴力枚举，一个字符串有左到右，我只需要从左向右遍历left，每个left找到最短的right覆盖t串即可。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020120518512847.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

但是这样是一个O(n2)的操作，题目要求用O(n)来完成。该如何做呢？ **双指针**
我们可以动态的去覆盖这个s串，在遍历的时候遵从一个策略：
- 选定left的时候，让right到达最短的那个能够覆盖的地方。
- 下一步将left右移的时候判断是否能够满足覆盖t串的条件，如果满足比较下是否需要更新结果，如果不满足那么将right向右一直找到能够覆盖为止。
- 重复上述一直到结束

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201205185235208.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

但是另一个需要你考虑得问题是，**如何统计是否覆盖**呢？ 
肯定是要通过某种容器，这里哈希肯定是最方便的了，但是我这里不用HashMap 使用2个`int []`数组分别统计s串和t串中字符出现的个数。

统计t串的可以开始就统计，统计s串的需要统计left和right范围内的字符。而可以借助一个int类型参数num来统计覆盖次数：也就是right向右遍历s串该字符如果比t串中次数少那么加一。如果left向右记录s串的字符减少如果小于t串中的字符数那么num减少。

具体实现的时候，将字符串转成字符数组，用左右区间标记替代每次字符串结果来优化时间，可以跑到3ms.
具体的代码为：

```java
public String minWindow(String s, String t) {
    int countS[]=new int[150];//用来储存s中的字符
	int CountT[]=new int[150];//用来储存t中的字符
	
	char strs[]=s.toCharArray();//转成数组更快
	char strt[]=t.toCharArray();
	
	for(int i=0;i<strt.length;i++)
	{
		CountT[strt[i]]++;//计数
	}
	String value="";
	int num=0;//s中拥有t字符 的个数
	int valueLeft=0,valueRight=strs.length+1;//最终的左右区间范围
	int right=0;//right 临时指针
	for(int i=0;i<strs.length;i++)
	{
		
		if(right==strs.length&&num<strt.length)//已经不可能了 推出
		{
			break;
		}
		else if (num<strt.length) {//需要往右叠加
			while (right<strs.length&&num<strt.length) {
				if(countS[strs[right]]++<CountT[strs[right]])//s这个字符数量小于t中这个字符的数量
				{
					num++;
				}
				right++;
			}
		}
		//System.out.println("66666 "+right+" "+num);
		if(num<strt.length&&right==strs.length)//不满足条件
			break;
		if(num==strt.length&&(valueRight-valueLeft>right-i))
		{
			valueLeft=i;
			valueRight=right;
			
		}
		
		if(countS[s.charAt(i)]--<=CountT[s.charAt(i)])//左侧的left右移
		{
			num--;
		}
				
	}
	if(valueRight!=strs.length+1)//如果有满足的字符串
	value=s.substring(valueLeft,valueRight);
	//System.out.println(value);
	return value;
   }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201205181006133.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)