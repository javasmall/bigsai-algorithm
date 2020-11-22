## LeetCode 61旋转链表
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
示例 1:
>输入: 1->2->3->4->5->NULL, k = 2
>输出: 4->5->1->2->3->NULL
>解释:
>向右旋转 1 步: 5->1->2->3->4->NULL
>向右旋转 2 步: 4->5->1->2->3->NULL

示例 2:
>输入: 0->1->2->NULL, k = 4
>输出: 2->0->1->NULL
>解释:
>向右旋转 1 步: 2->0->1->NULL
>向右旋转 2 步: 1->2->0->NULL
>向右旋转 3 步: 0->1->2->NULL
>向右旋转 4 步: 2->0->1->NULL


**分析**
本题的话就是题意比较清晰，就是旋转链表将链表进行右移动k个。

但是具体的处理上可能存在时间复杂度的差距，比如你可以第一次遍历到结尾，然后构成一个循环链表不停遍历找到合适的位置。或者先遍历一次到尾，然后再找到需要移动的位置去进行操作，但是这样都避免不了循环两次。

那本题采用什么方法呢？使用快慢指针，快指针先走k步，然后快慢指针一起走，**一直到快指针走到尾。** 执行以下操作即可：
- fast.next=head
- ListNode team=slow
- slow.next=null
- return team

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201115164620637.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020111516470510.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)



但是过程可能没那么顺畅，很可能k比整个链表的长度还大，所以可能k没跑完就遍历结束了，这种情况也不用担心，当fast到底的时候可以通过一次计算算出真实有效的移动位数。比如如果链表长10让你右移59，69，79这些和移动9次是一样的，**所以只需要slow再次移动到真实有效的地方即可。**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201115164810232.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)


实现的代码为：

```java
 public ListNode rotateRight(ListNode head, int k) {
      if(k==0||head==null)return head;
       ListNode fast=head;
       ListNode slow=head;
      
       for(int i=0;i<k;i++)
       {            
           if(fast.next==null)//说明超出范围了 但是此时知道最大长度了
           {           	
               k=(k)%(i+1);            
               if(k==0)return head;      
               for(int j=0;j<i-k;j++)
               {
              	 slow=slow.next;
               }
               break;
           }
           fast=fast.next;
       }
       while (fast.next !=null) {
      	 fast=fast.next;
      	 slow=slow.next;
       }        
       fast.next=head;
       ListNode team=slow.next;
       slow.next=null;
       return team;
  }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201115164510611.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)

## 结语

原创不易，bigsai请你帮两件事帮忙一下:

1. star支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/3cd335655373276f330fa2c16b0e20f6.png)