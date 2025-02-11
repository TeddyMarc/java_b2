import java.util.Arrays;

public class tab_entier {
    public static void main(String[] args) {
        int[] data = {3,5,7,2,9,13,12,4,6};

        //calcul de la moyenne 
        double sum = 0;
        for(int value : data){
            sum += value;
        }
        double mean = sum / data.length;
        System.out.println("Moyenne " + mean);
        

        //calcul de la mediane 
        Arrays.sort(data);
        double median;
        if (data.length % 2 == 0){
            median = (data[data.length / 2 - 1] + data[data.length / 2]) /2.0;
        }else{
            median = data[data.length / 2];
        }
        System.out.println("Médiane " + median);
           
          
        

    }
}

