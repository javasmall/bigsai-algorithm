## LeetCode02两数之加

**题目描述**：
>给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
>如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
>您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例**:

>输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
>输出：7 -> 0 -> 8
>原因：342 + 465 = 807

**分析**：
本题其实就是用一个链表存储一个数字(逆序存储)，你需要给它计算出结果后在 逆序 存储到一个链表中返回。
所谓加法的运算规则：**从两个数的最低位进行计算，进行到下一位的时候需要考虑进位问题。一直到最后**，而本题所给的链表刚好可以用来直接计算，因为链表头都是数字最低位可以直接相加，然后一直遍历到结束。可以用一个常数表示进位。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200805170458557.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_1,color_FFFFFF,t_70)
在具体实现(链表)的时候：
- 创建新的链表，每次将计算的数值插入到链表尾部即可。
- 需要准确表示进位，并且最后要考虑以下进位
- 妥善返回正确节点，可以用一个头节点用来使得所有节点都正常操作，而不需要特殊判断。
通过代码第一次比较啰嗦的写法：

当然，如果你遍历链表把各个数字取出来，使用字符串、数字转换然后相加得到一个数字，最后在转成字符串、链表的理论可以，可以自行实现。
第一次比较臃肿但易理解代码为：
```java
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode node=new ListNode(0);//用一个头节点(不存真实的值)
		 ListNode team=node;
		 int jin=0;//进位
		 while(l1!=null&&l2!=null)//当可以正常相加时候
		 {
			 int num=l1.val+l2.val+jin;//该位理论的数字
			 jin=num/10;//进位0 或 1
			 num%=10;//实际能表示的数字
			 team.next=new ListNode(num);//将数字放到下一个节点中
			 team=team.next;//往下进行
			 l1=l1.next;l2=l2.next;
		 }
		 //其中一个为null或全为null时候
		 while (l1!=null) {
			 int num=l1.val+jin;
			 jin=num/10;
			 num%=10;
			 team.next=new ListNode(num);
			 team=team.next;
			 l1=l1.next;
		}
		 while (l2!=null) {
			 int num=l2.val+jin;
			 jin=num/10;
			 num%=10;
			 team.next=new ListNode(num);
			 team=team.next;
			 l2=l2.next;
		}
		 if(jin!=0)team.next=new ListNode(jin);
		return node.next;

	    }
```
优化后的代码为：

```java
 //更简洁的写法
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode node=new ListNode(0);
		 ListNode team=node;
		 int jin=0;//进位
		 while(l1!=null||l2!=null)
		 {
			 int num=jin;
			 if(l1!=null)
			 {
				 num+=l1.val;l1=l1.next;
			 }
			 if(l2!=null)
			 {
				 num+=l2.val;l2=l2.next;
			 }
			 jin=num/10;
			 num%=10;
			 team.next=new ListNode(num);
			 team=team.next;
		 
		}
		 if(jin!=0)team.next=new ListNode(jin);
		return node.next;
	    }
```

当然，如果遇到评论区或者其他好的方法也可以，如果我错误还请指正。

## 结语

原创不易，最后我请你帮两件事帮忙一下:

1. star、follow支持一下， 您的肯定是我在平台创作的源源动力。

2. 微信搜索「**bigsai**」，关注我的公众号，不仅免费送你电子书，我还会第一时间在公众号分享知识技术。加我还可拉你进力扣打卡群一起打卡LeetCode。

记得关注、咱们下次再见！

![image-20201114211553660](https://bigsai.oss-cn-shanghai.aliyuncs.com/img/image-20201122215000846.png)

