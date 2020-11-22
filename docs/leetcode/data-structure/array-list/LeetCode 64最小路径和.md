## LeetCode 64最小路径和
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020112220400240.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

简单的动态规划，只能向右或者向下，所以可以使用动态规划动态的找到最小路径和，先对第一行和第一列特殊处理，然后顺序遍历数组的时候状态转移方程为：
```
dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
```
其中dp[i][j]意为第i行第j列的最小路径和。
具体代码为：
```java
public  int minPathSum(int[][] grid) {
       int m=grid.length;
        int n=grid[0].length;
        int dp[][]=new int[m][n];
        dp[0][0]=grid[0][0];
        //先初始化第0行和第0列
        for(int i=1;i<n;i++)
        {
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        for(int j=1;j<m;j++)
        {
            dp[j][0]=dp[j-1][0]+grid[j][0];
        }
        //System.out.println(Arrays.deepToString(dp));
        for(int i=1;i<m;i++)
        {
            for(int j=1;j<n;j++)
            {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];

    }
```