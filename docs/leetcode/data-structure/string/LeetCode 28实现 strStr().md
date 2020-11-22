## LeetCode 28实现 strStr()
**题目描述：**

>给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:
>输入: haystack = "hello", needle = "ll"
>输出: 2

示例 2:
>输入: haystack = "aaaaa", needle = "bba"
>输出: -1

说明:
>当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
>对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符

**分析**
由于数据量的问题，本题使用KMP算法效率并不是很好(KMP需要预处理)，相反使用普通方法效率就很高.使用sunday算法效果也比较普通，就很神奇。

**普通的匹配**又称双指针啥的其实就是一个暴力匹配，但是同为暴力匹配官方给的方法速度要快很多，个人写法为：


```java
public int strStr(String haystack, String needle) {
	      if(needle==null||"".equals(needle))
		     return 0;
	      char a[]=haystack.toCharArray();
	      char b[]=needle.toCharArray();
		  for(int i=0;i<a.length-b.length+1;i++)
		  {
              int j=-1;
			  while(j++<b.length)
			  {
				  if(a[i+j]!=b[j])
				  {
					  break;
				  }
				  if(j==b.length-1)
					  return i; 
			 }
			  
		  }
		  return -1;
 }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919195307177.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
官方给的简洁写法：

```java
public int strStr(String haystack, String needle) {
    int L = needle.length(), n = haystack.length();
    for (int start = 0; start < n - L + 1; ++start) {
      if (haystack.substring(start, start + L).equals(needle)) {
        return start;
      }
    }
    return -1;
  }
```
kmp方法笔者也实现了，但是由于数据问题效果一般般，当然kmp算法这里就不作详细介绍：

```java
 public  int[] getNext(String needle) {
		    char[] p = needle.toCharArray();
		    int[] next = new int[p.length];
		    next[0] = -1;
		    int j = 0;
		    int k = -1;
		    while (j < p.length - 1) {
		       if (k == -1 || p[j] == p[k]) {
		           next[++j] = ++k;
		       } else {
		           k = next[k];
		       }
		    }
		    return next;
		}
	  public  int KMP(String haystack, String needle) {
		     char[] t = haystack.toCharArray();
		    char[] p = needle.toCharArray();
		    int i = 0; // 主串的位置
		    int j = 0; // 模式串的位置
		    int[] next = getNext(needle);
		    while (i < t.length && j < p.length) {
		       if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
		           i++;
		           j++;
		       } else {
		           // i不需要回溯了
		           // i = i - j + 1;
		           j = next[j]; // j回到指定位置
		       }
		       if(j==p.length) {return i-j;}
		    }
		    return -1;
		}
	  public int strStr(String haystack, String needle) {
		  if(needle==null||"".equals(needle))
		     return 0;
		 
		  return KMP(haystack, needle);
	 }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919195924766.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)
同样使用**sunday算法** (后面会专门讲解)效果也是一般般

```java
 public  int strStr(String haystack, String needle) {
		  if(needle==null||"".equals(needle))
			     return 0;
		  int lastchar[]=new int [200];
		  char a[]=haystack.toCharArray();
		  char p[]=needle.toCharArray();
		  for(int i=0;i<p.length;i++)
		  {
			  lastchar[p[i]]=i+1;
		  }
		  int i=0;
		  int j=0;
		 int len=a.length-p.length+1;
		  while (i<a.length) {
			 int index=i-(lastchar[a[i]]-1);//a[i] 字符
			if(lastchar[a[i]]!=0&&index>=0&&index<len)//可以匹配
			{
				while (j<p.length) {//尝试匹配
					if(p[j]!=a[index+j])
						break;
					j++;
				}
				if(j==p.length)
				{
					return index;
				}
				else {
					i=index+p.length;
					j=0;
				}
			}
			else {
				i++;
			}
		}
		  
		return -1;
	}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200919200200338.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)



后面会优化的！