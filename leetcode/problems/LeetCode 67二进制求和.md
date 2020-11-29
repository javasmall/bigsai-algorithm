## LeetCode 67二进制求和
给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

 

示例 1:
>输入: a = "11", b = "1"
>输出: "100"

示例 2:
>输入: a = "1010", b = "1011"
>输出: "10101"


提示：
>每个字符串仅由字符 '0' 或 '1' 组成。
>1 <= a.length, b.length <= 10^4
>字符串如果不是 "0" ，就都不含前导零。

**分析：**
思路也很简单，如果不等长找到短的，从右往左遍历叠加进位。然后再处理常得尾遍历地方，具体实现上。先通过交换是的ab字符串其中一个较小，可以用StringBuilder去实现数字叠加。

实现代码：

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder stringBuilder=new StringBuilder();
        if(a.length()<b.length())
        {
            String team=a;
            a=b;
            b=team;
        }
        char ach[]=a.toCharArray();
        char bch[]=b.toCharArray();
        int alen=a.length(),blen=b.length();
        int minLen=b.length();
        int jinwei=0;//进位
        for(int i=0;i<minLen;i++)
        {
            char ch1=ach[alen-1-i];
            char ch2=bch[blen-1-i];
            int va=(ch1-'0')+(ch2-'0')+jinwei;
            jinwei=va/2;
            va=va%2;
            stringBuilder.insert(0,va);
        }
        
        for(int i=0;i<alen-minLen;i++)
        {
            char ch1=ach[alen-1-minLen-i];
            int va=ch1-'0'+jinwei;
            jinwei=va/2;
            va=va%2;
            stringBuilder.insert(0,va);
        }
        if(jinwei==1)
            stringBuilder.insert(0,1);
        return stringBuilder.toString();
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201121170313625.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)