## LeetCode 73矩阵置零
题目描述：

给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:
```
输入: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```
示例 2:
```
输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```
进阶:

一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？

**分析：**

这道题可谓是一波三折，对于O(m*n)的方法不谈了，没啥好讲了。

从O(m+n)的方法说起，一维空间，只要出现0，那么这一行或者这一列就全部变成0，那么就可以用两个boolean数组解决，一个标记行，一个标记列，遍历的时候遇到0就把该行和该列标记为true。最终根据两个boolean数组进行赋值即可。

实现代码为：

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean hang[]=new boolean[matrix.length];
        boolean lie[]=new boolean[matrix[0].length];
        for (int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                if(matrix[i][j]==0) {
                    hang[i] = true;
                    lie[j] = true;
                }
            }
        }
        for(int i=0;i<hang.length;i++)
        {
            if(hang[i])
            {
                for(int j=0;j<lie.length;j++)
                {
                    matrix[i][j]=0;
                }
            }
        }
        for(int j=0;j<lie.length;j++)
        {
            if(lie[j])
            {
                for(int i=0;i<hang.length;i++)
                {
                    matrix[i][j]=0;
                }
            }
        }
    }
}
```

题目有说到要使用常数级别的额外空间，而这种情况就要**考虑空间的复用**！这个就想了一下谈谈我的想法：

这题的核心问题是在根据行或者列遍历的时候不能第一次遇到就把改行和列置零，说说我的错误想法：
- 第一趟按行遍历，**遇到0那么将这一行所有数据特殊的标记起来。** 我首先想到的是除0以外标记成负数。
- 第二趟按列遍历，遇到0直接将该列置为0(行已经遍历过)。遇到负数的时候置为0.

如果所有数据是正数，那么这是可行的，但是很可疑测试数据有负数，over。。

后来又想逃巧将所有数据换成int的最大值或者最小值，但是不幸的是测试有这两组数据，把我卡的死死的。

后来只能先用枚举的方法找到一个数组中不存在的数，因为二维数组是有大小范围`int [m][n]`的，所以在[1，m*n+1] 范围内一定有一个不存在的数可以作为临时标记。

第二趟按照列遍历，遇到0直接将该列置为0(行已经遍历过)。遇到标记的那个数将那个位置数字为0.
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201129184113437.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

实现代码为：

```java
class Solution {
    public void setZeroes(int[][] matrix) {
         boolean jud=false;//
        int num=1;
        //找到一个合适的数字用于临时替换
        for(int q=1;q<matrix.length*matrix[0].length+2;q++)
        {
            jud=false;
            outer:
            for (int i=0;i<matrix.length;i++)
            {
                for(int j=0;j<matrix[i].length;j++)
                {
                    if(matrix[i][j]==q) {
                        jud=true;
                        break outer;
                    }
                }
            }
           if(!jud)
           {
               num=q;break;
           }
        }
        //遍历行
        for (int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                if(matrix[i][j]==0)
                {
                    for(int q=0;q<matrix[0].length;q++)
                    {
                        if(matrix[i][q]!=0)
                        matrix[i][q]=num;
                    }
                    break;
                }
            }
        }
        for(int j=0;j<matrix[0].length;j++)//列
        {
            for (int i=0;i<matrix.length;i++)
            {
                if(matrix[i][j]==0)
                {
                    for(int q=0;q<matrix.length;q++)
                    {
                        matrix[q][j]=0;
                    }
                    break;
                }
                if(matrix[i][j]==num)
                    matrix[i][j]=0;
            }
        }
    }
}
```

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)