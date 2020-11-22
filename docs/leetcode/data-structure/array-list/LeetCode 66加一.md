## LeetCode 66加一

![image-20201122205650504](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122205650504.png)

分析：简单题，只需要本本分分模拟即可，将数组最后一位加一，如果产生进位一直向前判断是否还需要进位。有一个需要注意的地方就是如果结束第0位也需要进位那么需要重新创建数组扩容赋值返回。

实现代码：

```java
class Solution {
    public int[] plusOne(int[] digits) {
         if(digits [digits.length-1]++==9)
            for(int i=digits.length-1;i>0;i--)
            {
                digits[i]=0;
                if(++digits[i-1]!=10)
                    break;
            }
        if(digits[0]==10)
        {
            int value[]=new int[digits.length+1];
            digits[0]=0;
            value[0]=1;
            System.arraycopy(digits,0,value,1,digits.length);
            return  value;
        }
        return digits;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201121164402655.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

