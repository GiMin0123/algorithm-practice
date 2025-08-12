import java.util.*;
class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0; 
        while(true){
            
            //만약 지갑에 들어가는 경우라면 반복문을 빠져나온다.
            if(Math.max(wallet[0],wallet[1])>=Math.max(bill[0],bill[1]) && Math.min(wallet[0],wallet[1])>=Math.min(bill[0],bill[1])){
                break;
            }
            
            if(bill[0] > bill[1]){
                bill[0]=bill[0]/2;
            } else{
                bill[1]=bill[1]/2;
            }
            answer++;
        }
        
        return answer;
    }
}