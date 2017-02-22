package fjnu.demo;

public class MatchingTest {

	public static String matchString(String str1,String str2){
		//空值判断
		if(str1.length() == 0 || str2.length() == 0){
			return "";
		}
		int arr[][] = null;
		//判断两个字符串长度,以短的为第一维，长的为第二维
		if(str1.length() > str2.length()){
			//初始化数组，所有元素为0
			arr = new int[str2.length()][str1.length()];
			setValueToArr(arr, str1.toCharArray(), str2.toCharArray());
			return getSameStr(arr, str1.toCharArray(), str2.toCharArray());
		}else{
			//初始化数组，所有元素为0
			arr = new int[str1.length()][str2.length()];
			setValueToArr(arr, str2.toCharArray(), str1.toCharArray());
			return getSameStr(arr, str2.toCharArray(),  str1.toCharArray());
		}
	}
	
	public static String getSameStr(int arr[][],char longArr[],char shortArr[]){
		
		int count = 0,temp = 0;			//计数器 count用来标记每一行的匹配，temp用来记录所有的最大的匹配
		int longNum = 0,shortNum = 0;	//字符数组的标志位
		int beginNum = 0;
		int tempCount = 0;				//用来记录每一行的最大匹配
		while(true){
			if(shortNum == arr.length){
				break;
			}
			int tempShort = shortNum;
			//向下拓展，看是否有连续相同
			while(true){
				//如果已经到了第二维的数组尾部，则break，第一维+1
				if(tempShort == shortArr.length || longNum == (arr[0].length)){
					//取最大的一段
					if(count > tempCount){
						tempCount = count;
					}
					break;
				}
				if(arr[tempShort][longNum] == 1){
					count ++;
					tempShort ++;
					longNum ++;
				}else{
					//取最大的一段
					if(count > tempCount){
						tempCount = count;
					}
					//清空标志位重新开始计数
					tempShort = shortNum;	//之前所加的行应该回退
					count = 0;				//count应该重新计数
					longNum ++;				//列往下推移
					continue;
				}
			}
			//判断每次的连续相同的个数
			if(tempCount > temp){
				beginNum = shortNum;
				temp = tempCount;
			}
			shortNum ++;
			count = 0;
			longNum = 0;
		}
		String str = new String(shortArr);
		return str.substring(beginNum,beginNum+temp);
	}
	
	public static void setValueToArr(int arr[][],char longArr[],char shortArr[]){
		for(int i = 0;i < arr.length;i ++){
			for(int j = 0;j < arr[0].length;j ++){
				//如果匹配上了，就把该标志位赋值为1
				if(shortArr[i] == longArr[j]){
					arr[i][j] = 1;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(matchString("GCGCCAGTGDE","GCCCTAGCCAGDE"));
	}
	
}
