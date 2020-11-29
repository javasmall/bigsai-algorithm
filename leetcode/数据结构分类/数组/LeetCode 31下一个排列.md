## LeetCode 31下一个排列
![image-20201117212146696](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201117212146696.png)

**分析：**
是不是和全排列有点像，但是又不是全排列。**是需要找到下一个字典序。**
分析数列你会发现以下两个规律：

首先从右往左交换第一个正序：
- 例如1 2 **3** 5 **4** 交换3和4成为1 2 **4** 5 **3**.

其次根据**交换的区间内**，从右向左(双重循环)交换逆序对(仔细考虑边界哦)：
- 例如上述变成1 2 4 3 5；

具体实现的时候，注意位置编号等问题。具体代码为：

```java
 public void nextPermutation(int[] nums) {
     boolean jud=false;
	 int i,j=0;
	 for( i=nums.length-2;i>=0;i--)
	 {
		 for( j=nums.length-1;j>i;j--)
		 {
			 
			 if(!jud&&nums[i]<nums[j])
			 {
				 int team=nums[i];
				 nums[i]=nums[j];
				 nums[j]=team;
				 jud=true;
				 break;
			 }
		 }
		 if(jud)break;
	 }
            if(jud)
	 for(int k=nums.length-1;k>i;k--)
	 {
		 for(int m=k-1;m>i;m--)
		 {
			 if(nums[k]<nums[m])
			 {
				 int team=nums[k];
				 nums[k]=nums[m];
				 nums[m]=team;
			 }
		 }
	 }
	 int team;
	 if(!jud)
	 for( i=0;i<nums.length/2;i++)
	 {
		 team=nums[i];
		 nums[i]=nums[nums.length-1-i];
		 nums[nums.length-1-i]=team;
	 }
  }
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200921205622320.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70#pic_center)结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！