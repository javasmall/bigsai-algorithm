## LeetCode 65有效数字
验证给定的字符串是否可以解释为十进制数字。

例如:
```
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。
这里给出一份可能存在于有效十进制数字中的字符列表：

数字 0-9
指数 - "e"
正/负号 - "+"/"-"
小数点 - "."
当然，在输入中，这些字符的上下文也很重要。
```
更新于 2015-02-10:
C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。

**分析**
这题其实挺麻烦的，我也是根据样例不停的测试然后才最终得到正确的结果。这些数字和符号**有一定的规律和要求。**  我的分析就是将数字分成三块 e前，e，e后。而每个数字可能是符号数字小数点等组成需要满足一定规律。符号可以没有，**但是数字必须有！** 具体可以参考这张图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201121172236402.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)


实现代码为：

```java
public boolean isNumber(String s) {
          s=s.trim();
          char str[]=s.toCharArray();
      
	    boolean smallpoint=false;//小数点
        boolean ise=false;//是否遇到e
        int localindex=0;//当前数字指标
        // 整体思路            左侧部分              e     右侧部分
        for(int i=0;i<str.length;i++)
        {
        	if(localindex==0&&(str[i]=='+'||str[i]=='-'))// + -号必须出现在前面 也就是localindex=0
        	{
        		if(smallpoint)return false; //小数点后不能有+- 例如0.+3
        	    continue;//否则继续
        	}
        	else if (str[i]=='.') {
				if(smallpoint||ise)//当有小数点或者在e后面 不能3.12.5 也不能1e2.3
					return false;
				else {
					smallpoint=true;//否则将标记出现过小数点
				}
			}
        	else if ((str[i]=='e'||str[i]=='E')&&localindex>0) {//遇到e
				if(ise)return false;//如果已经有e 那么返回false 不能 4e5e
				else {
					smallpoint=false;//否则说明正常，开始统计e右侧部分。e右侧部分开始重新统计
					ise=true;//标记e已经出现
					localindex=0;//数字编号
				}
			}
        	else if (str[i]>='0'&&str[i]<='9') {
				localindex++;
			}
        	else {
				return false;
			}
        }
        if (localindex>0) {
			return true;
		}
        else	        
        	return false;
   }
```





还有这种方法就是**有限状态机（DFA）解决**，具体解释看题解吧：


[图片来源力扣题解](https://leetcode-cn.com/problems/valid-number/solution/biao-qu-dong-fa-by-user8973/)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201121154641623.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

```java
class Solution {
    public int make(char c) {
        switch(c) {
            case ' ': return 0;
            case '+':
            case '-': return 1;
            case '.': return 3;
            case 'e': return 4;
            default:
                if(c >= 48 && c <= 57) return 2;
        }
        return -1;
    }
    
    public boolean isNumber(String s) {
        int state = 0;
        int finals = 0b101101000;
        int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
                                       {-1,-1, 6, 2,-1},
                                       {-1,-1, 3,-1,-1},
                                       { 8,-1, 3,-1, 4},
                                       {-1, 7, 5,-1,-1},
                                       { 8,-1, 5,-1,-1},
                                       { 8,-1, 6, 3, 4},
                                       {-1,-1, 5,-1,-1},
                                       { 8,-1,-1,-1,-1}};
        char[] ss = s.toCharArray();
        for(int i=0; i < ss.length; ++i) {
            int id = make(ss[i]);
            if (id < 0) return false;
            state = transfer[state][id];
            if (state < 0) return false;
        }
        return (finals & (1 << state)) > 0;
    }
}

作者：user8973
链接：https://leetcode-cn.com/problems/valid-number/solution/biao-qu-dong-fa-by-user8973/
来源：力扣（LeetCode）
```

原创不易，bigsai请你帮两件事帮忙一下:

1. 点赞、在看、分享支持一下， 您的肯定是我创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](/Users/a1/Documents/图床/LeetCode 65有效数字/3cd335655373276f330fa2c16b0e20f6.png)

