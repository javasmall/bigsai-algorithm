

### 前言

在**图论**中，在寻路最短路径中除了`Dijkstra`算法以外，还有`Floyd`算法也是非常经典，然而两种算法还是有区别的，`Floyd`主要**计算多源最短路径**。

**在单源正权值最短路径**，我们会用`Dijkstra`算法来求最短路径，并且算法的思想很简单—**贪心算法**:每次确定最短路径的一个点然后维护(更新)这个点周围点的距离加入预选队列，等待下一次的抛出确定。虽然思想很简单，实现起来是非常复杂的，我们需要邻接矩阵(表)储存长度，需要优先队列(或者每次都比较)维护一个预选点的集合。还要用一个boolean数组标记是否已经确定、还要……

总之，`Dijkstra`算法的**思想上是很容易接受**的，但是**实现上其实是非常麻烦**的。但是单源最短路径解算暂时还没有有效的办法，复杂度也为`O(n2)`。

而在n点图中想求多源最短路径,如果**从Dijkstra算法的角度上**，需要将`Dijkstra`执行n次才能获得所有点之间的最短路径，不过执行n次Dijkstra算法即可,复杂度为`O(n3)`。但是这样感觉很臃肿，代码量巨大，占用很多空间内存。有没有啥方法能够稍微变变口味呢？

答案是有的，今天就带大家一起了解一下牛逼轰轰的Floyed算法。

### 算法介绍

什么是Floyed算法？
>Floyd算法又称为插点法，是一种利用动态规划的思想寻找给定的加权图中多源点之间最短路径的算法，与Dijkstra算法类似。该算法名称以创始人之一、1978年图灵奖获得者、斯坦福大学计算机科学系教授罗伯特·弗洛伊德命名。

简单的来说，算法的主要思想是**动态规划(dp)**，而求最短路径需要不断松弛(熟悉spfa算法的可能熟悉松弛)。

而算法的具体思想为：

1 .邻接矩阵(二维数组)`dist`储存路径，数组中的值开始表示点点之间初始直接路径，最终是点点之间的最小路径，有两点需要注意的，第一是如果没有直接相连的两点那么默认为一个很大的值(不要因为计算溢出成负数)，第二是自己和自己的距离要为0。

2 .从第1个到第n个点依次加入松弛计算，每个点加入进行**试探**枚举是否有路径长度被更改(自己能否更新路径)。顺序加入(k枚举)松弛的点时候，需要**遍历图中每一个点对(i,j双重循环)**，判断每一个点对距离**是否因为加入的点而发生最小距离变化**，如果发生改变(变小)，那么两点(i,j)距离就更改。 

2 .重复上述**直到最后插点试探完成。**

其中**第2步的状态转移方程**为:

```
dp[i][j]=min(dp[i][j],dp[i][k]+dp[k][j])
```

其中`dp[a][b]`的意思可以理解为点a到点b的最短路径,所以`dp[i][k]`的意思可以理解为**i到k的最短路径**`dp[k][j]`的意思为**k到j的最短路径.**

咱们图解一个案例，初始情况每个点只知道和自己直接相连的点的距离，而其他间接相连的点还不知道距离，比如A-B=2,A-C=3但是B-C在不经过计算的情况是不知道长度的。

![image-20210825234854353](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20210825234854353.png)

加入第一个节点`A`进行更新计算,大家可以发现，由于A的加入，使得本来不连通的`B，C`点对和`B，D`点对变得联通，并且加入A后**距离为当前最小**,同时你可以发现加入`A`其中也使得`C-D`多一条联通路径（6+3），但是`C-D`联通的话距离为9远远大于本来的`(C,D)`联通路径2，所以这条不进行更新。

![image-20210826102018687](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20210826102018687.png)



咱们继续加入第二个节点`B`，这个点执行和前面`A`相同操作进行。对一些点进行更新。因为和B相连的点比较多，可以产生很多新的路径，这里给大家列举一下并做一个说明,这里新路径我统一用1表示，原来长度用0表示。

AF1=AB+BF=6+2=8 < AF0(∞)  进行更新

AE1=AB+BE=2+4=6 < AE0(∞) 进行更新

CE1=CB+BE=5+4=9 < CE0(∞) 进行更新

CF1=CB+BF=5+6=11<CF0(∞) 进行更新

EF1=EB+BF=4+6=10<EF0(∞) 进行更新

当然，也有一些新的路径大于已有路径不进行更新，例如：

AC1=AB+BC=2+5=7>AC0(3) **不更新** 

AD1=AB+BD=2+8=10>AD0(6) **不更新**

……

更多路径这里就不一一列举了。



![image-20210826115604710](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20210826115604710.png)



后序加入C、D、E、F都是进行相同的操作,最终全部加完没有路径可以更新就结束停止。实际上这个时候图中的连线就比较多了。这些连线都是代表**当前的最短路径。** 这也和我们的需求贴合，我们最终要的是所有节点的最短路径。**每个节点最终都应该有5条指向不同节点的边！** 矩阵对应边值就是点点之间最短路径。

至于算法的模拟两部核心已经告诉大家了，大家可以自行模拟剩下的。




### 程序实现

而对于程序而言，这个插入的过程相当简单。**核心代码只有四行！** 这个写法适合有向图和无向图，无向图的算法优化后面会说。
代码如下

```java
public class floyd {
	static int max = 66666;// 别Intege.max 两个相加越界为负
	public static void main(String[] args) {
		int dist[][] = {
				{ 0, 2, 3, 6, max, max }, 
				{ 2, 0, max, max,4, 6 }, 
				{ 3, max, 0, 2, max, max },
				{ 6, max, 2, 0, 1, 3 }, 
				{ max, 4, max, 1, 0, max }, 
				{ max, 6, max, 3, max, 0 } };// 地图
		// 6个
		for (int k = 0; k < 6; k++)// 加入第k个节点进行计算
		{
			for (int i = 0; i < 6; i++)// 每加入一个点都要枚举图看看有没有可以被更新的
			{
				for (int j = 0; j < 6; j++)
				{
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		// 输出
		for (int i = 0; i < 6; i++) {
			System.out.print("节点"+(i+1)+" 的最短路径");
			for (int j = 0; j < 6; j++) {
				System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}
}

```
执行结果为：
![image-20210826163628018](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20210826163628018.png)

可以自行计算，**图和上篇的Dijkstra用的图是一致的**，大家可以自行比对，结果一致，说明咱么的结果成功的。

当然，在你学习的过程中，可以在每加入一个节点插入完成后，**打印邻接矩阵的结果**，看看前两部和笔者的是否相同(有助于理解)，如果相同，则说明正确！

对于加入点更新你可能还是有点疑惑其中的过程，那咱么就用一个局部来演示一下帮助你进一步理解Floyd算法，看其中AB最短距离变化情况祝你理解：
![image-20210826164944787](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20210826164944787.png)



### 小试牛刀

自己会没会？刷一道题就可以知道了，刚好力扣1334是一道Floyd算法解决的问题。

**题目描述为**：

有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。

返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。

注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。

**示例1：**

![img](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/find_the_city_01.png)

>输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
>输出：3
>解释：城市分布图如上。
>每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
>城市 0 -> [城市 1, 城市 2] 
>城市 1 -> [城市 0, 城市 2, 城市 3] 
>城市 2 -> [城市 0, 城市 1, 城市 3] 
>城市 3 -> [城市 1, 城市 2] 
>城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。

**示例2：**

![img](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/find_the_city_02.png)



>输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
>输出：0
>解释：城市分布图如上。 
>每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
>城市 0 -> [城市 1] 
>城市 1 -> [城市 0, 城市 4] 
>城市 2 -> [城市 3, 城市 4] 
>城市 3 -> [城市 2, 城市 4]
>城市 4 -> [城市 1, 城市 2, 城市 3] 
>城市 0 在阈值距离 2 以内只有 1 个邻居城市。

**提示：**

> 2 <= n <= 100
> 1 <= edges.length <= n * (n - 1) / 2
> edges[i].length == 3
> 0 <= fromi < toi < n
> 1 <= weighti, distanceThreshold <= 10^4
> 所有 (fromi, toi) 都是不同的。



**思路分析：**

拿到一道题，首先就是要理解题意，而这道题的意思借助案例也是非常能够理解，其实就是判断在distanceThreshold范围内找到能够到达的最少点的编号，如果多个取最大即可。正常求到达最多情景比较多这里求的是最少的，但是思路都是一样的。

这道题如果使用搜索，那复杂度就太高了啊，很明显要使用多源最短路径Floyd算法，具体思路为;

1 .先使用Floyd算法求出点点之间的最短距离，时间复杂度O(n3)

2 . 统计每个点与其他点距离在distanceThreshold之内的点数量，统计的同时看看是不是小于等于已知最少个数的，如果是，那么保存更新。

3 .返回最终的结果。

实现代码：

```java
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dist[][]=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++){
              //保证数据比最大二倍大(两相加不能比它大)，并且不能溢出，不要Int最大 相加为负会出错
                dist[i][j]=1000000;
            }
            dist[i][i]=0;
        }
        for(int arr[]:edges){
            dist[arr[0]][arr[1]]=arr[2];
            dist[arr[1]][arr[0]]=arr[2];
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int min=Integer.MAX_VALUE;
        int minIndex=0;
        int pathNum[]=new int[n];//存储距离
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(dist[i][j]<=distanceThreshold){
                    pathNum[i]++;
                }
            }
            if(pathNum[i]<=min) {
                min = pathNum[i];
                minIndex=i;
            }
        }
        return  minIndex;

    }
}
```

那么想一下优化空间：Floyd算法还有优化空间嘛？

有的，这个是个**无向图**，也就是加入点的时候枚举其实会有一个重复的操作过程(例如枚举AC和CA是效果一致的)，所以我们在Floyd算法的实现过程中过滤掉重复的操作，具体代码为：

```java
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dist[][]=new int[n][n];//存储距离
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++){
                dist[i][j]=1000000;
            }
            dist[i][i]=0;
        }
        for(int arr[]:edges){
            dist[arr[0]][arr[1]]=arr[2];
            dist[arr[1]][arr[0]]=arr[2];
        }
         for(int k=0;k<n;k++){
            for(int i=0;i<n;i++) {
                for(int j=i+1;j<n;j++){//去掉重复的计算
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    dist[j][i]=dist[i][j];
                }
            }
        }
        int min=Integer.MAX_VALUE;
        int minIndex=0;
        int pathNum[]=new int[n];//
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(dist[i][j]<=distanceThreshold){
                    pathNum[i]++;
                }
            }
            if(pathNum[i]<=min) {
                min = pathNum[i];
                minIndex=i;
            }
        }
        return  minIndex;

    }
}
```

### 尾声

对于`Floyd`算法，如果初次接触不一定能够理解这个松弛的过程。

`Floyd`像什么呢，最终最短路径大部分都是通过计算得到而存储下来直接使用的，我觉得它和MySQL视图有点像的，视图是一个虚表在实表上计算获得的，但是计算之后各个数据就可以直接使用，`Floyd`是在原本的路径图中通过一个动态规划的策略计算出来点点之间的最短路径。

`Floyd`和`Dijkstra`是经典的最短路径算法，两者有相似也有不同。在复杂度上，`Dijkstra`算法时间复杂度是`O(n2)`,`Floyd`算法时间复杂度是`O(n3)`；在功能上，Dijkstra是求单源最短路径，并且路径权值不能为负，而`Floyd`是求多源最短路径，可以有负权值；算法实现上，Dijkstra 是一种贪心算法实现起来较复杂，`Floyd`基于动态规划实现简单；两个作者`Dijkstra`和`Floyd`都是牛逼轰轰的大人物，都是**图灵奖**的获得者。

除了`Floyd`算法，堆排序算法`heapSort`也是`Floyd`大佬发明的，属实佩服！

Floyd算法，俗称插点法，不就一个点一个点插进去更新用到被插点距离嘛！

好啦，Floyd算法就介绍到这里，如果对你有帮助，请动动小手点个赞吧！蟹蟹。
