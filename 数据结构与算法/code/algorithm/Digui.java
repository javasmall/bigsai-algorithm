package algorithm;

import java.util.Arrays;

public class Digui {
    public static void main(String[] args) {
        int arr[]={6,8,9};//需要排列组合的数组
        int val[]={0,0,0};//临时储存的数组
        boolean jud[] = new boolean[arr.length];// 判断是否被用
        dfs(arr,val, jud,  0,"");//用一个字符串长度更直观看结果
    }

    private static void dfs(int[] arr, int val[],boolean[] jud, int index,String s) {
        System.out.println(s+Arrays.toString(val));
        if (index == arr.length){ }//停止递归条件
        else{
            for (int i = 0; i < arr.length; i++) {
                if (!jud[i]) {//当前不能用的
                    int team=val[index];
                    val[index] = arr[i];
                    jud[i] = true;// 下层不能在用
                    dfs(arr, val, jud, index + 1,s+"  _  ");
                    jud[i] = false;// 还原
                    val[index]=team;
                }
            }
        }
    }
}