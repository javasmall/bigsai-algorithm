## LeetCode 74搜素二维矩阵
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。


示例 1：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201129162448358.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
输出：true
```
示例 2：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201129162459767.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
输出：false
```
示例 3：
```
输入：matrix = [], target = 0
输出：false
```

提示：
```
m == matrix.length
n == matrix[i].length
0 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
```

**分析：**
这题可以根据数据进行两次二分，第一次二分对第一列找到对应的行，第二次对对应的行进行二分找到对应的位置。

具体代码为：

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)return false;
		 
		 //先对第一行进行二分定位到该列
		 if(matrix[0][0]>target) return false;
		 int l=0,r=matrix.length;
		 while (l<r) {
			int mid=(l+r)/2;
			if(matrix[mid][0]==target)
				return true;
			if(matrix[mid][0]<target)
			{
				l=mid+1;
			}
			else {
				r=mid;
			}
		}
		int index=l-1;
		l=0;r=matrix[0].length;
		while (l<r) {
			int mid=(l+r)/2;
			if(matrix[index][mid]==target)
				return true;
			if(matrix[index][mid]<target)
			{
				l=mid+1;
			}
			else {
				r=mid;
			}
		}		 
		 return false;		  
    }
}
```