package org.jeecg;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;


// SpringRunner 继承了SpringJUnit4ClassRunner
//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoTest {
    //准备测试的环境
    @Before
//    @BeforeClass
    public  void beforeRead(){
        System.out.println("准备测试环境成功...");
    }
    //读取文件数据，把把文件数据都
    @Test
    public void readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\a.txt");
        int content  = fileInputStream.read();
        FileDescriptor fd = fileInputStream.getFD();
        FileChannel channel = fileInputStream.getChannel();
        String string = fileInputStream.toString();
        System.out.println(string);
        System.out.println(fd);
        System.out.println("内容："+content);
        System.out.println(channel + "555555");
        System.out.println(
                fileInputStream
        );
    }
//    @Test
//    public void sort(){
//        System.out.println("读取文件数据排序..");
//    }

    // 插入排序
    @Test
    public void insert(){
        int[] num = {2,5,3,6,4,1,9,8,4,4,4,3,4};
        int pre = 0;
        int temp = 0;
        for(int i = 1 ;i < num.length ; i++){
            pre = num[i];
            temp = i;
            for(int j = i;j>=1;j--){
                if(pre<num[j-1]){
                    temp = j-1;
                    num[j] = num[j-1];
                }else{
                    break;
                }
            }
            num[temp] = pre;
        }
        System.out.println(Arrays.toString(num));
    }
    // 选择排序
    @Test
    public void select(){
        int[] num = {2,5,3,6,4,1,9,8,4,4,4,3,4};
        int temp;
        int min;
        for(int i=0; i<num.length;i++){
            min = num[i];
            for(int j=i+1; j<num.length;j++){
                if(min>num[j]){
                    temp = num[j];
                    num[j] = min;
                    min = temp;
                }
            }
            num[i] = min;
        }
        System.out.println(Arrays.toString(num));
    }
    // 希尔排序
    @Test
    public void updateInsert(){
        int[] num = {2,5,3,6,4,1,9,8,4,4,4,3,4};
        sort3(num);
        System.out.println(Arrays.toString(num));
    }
    public void sort3(int[] num){
        int length = num.length;
        for(int i=length/2;i>0;i=i/2){
            udateSort3(i,num);
        }
    }
    public  void udateSort3(int path,int[] num){
        int m = path;
        int pre = 0;
        int temp = 0;
        for(int i =path;i<num.length;i++){
            pre = num[i];
            temp = i;
            for(int j = i;j>=m;j=j-m){
                if(pre<num[j-m]){
                    temp = j-m;
                    num[j]=num[j-m];
                }else{
                    break;
                }
            }
            num[temp] = pre;
        }
    }
    // 归并排序
    @Test
    public void m(){
        int[] num = {2,5,3,6,4,1,9,8};
        sort(num);
        System.out.println(Arrays.toString(num));
    }
    public void sort(int[] num){
        int[] arr = new int[num.length];
        merge(arr,0,num.length-1,num);
    }
    public void merge(int[] arr,int start,int end,int[] num){
        if(start<end){
            int mid = (start+end)/2;
            merge(arr,start,mid,num);
            merge(arr,mid+1,end,num);
            mergeSort(arr,start,mid,end,num);
        }
    }
    public void mergeSort(int[] arr,int start,int mid,int end,int[] num){
        int left = start;
        int right = mid+1;
        int local = 0;
        while(left<=mid && right<=end){
            if(num[left]<num[right]){
                arr[local++]=num[left++];
            }else{
                arr[local++]=num[right++];
            }
        }
        while(left<=mid){
            arr[local++]=num[left++];
        }
        while(right<=end){
            arr[local++]=num[right++];
        }
        local=0;
        //将temp中的元素全部拷贝到原数组中
        while(start <= end){
            num[start++] = arr[local++];
        }
    }

// 归并排序
@Test
    public void mm(){
        int arr[] = {6,1,7,2,4,3,8,5};
        sort1(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort1(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

    //清理测试环境的方法
	@After
//    @AfterClass
    public  void afterRead(){
        System.out.println("清理测试环境..");
    }

}
