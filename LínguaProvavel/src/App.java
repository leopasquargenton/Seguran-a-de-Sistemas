import java.io.BufferedReader;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {

        //for(int textos=1;textos<8;textos++){
            BufferedReader buffReader = new BufferedReader(new FileReader("C:\\Users\\lpasq\\PUCRS\\2022 - 2º Semestre\\Segurança de Sistemas\\Aula 1\\LínguaProvavel\\src\\TextoClaro.txt"));

            String texto = buffReader.readLine();
            double n = texto.length();
            double [] letras = new double [26];
            char letra;
            for(int aux=0;aux<texto.length();aux++){
                letra = texto.charAt(aux);
                if(letra=='a'){letras[0]++;}if(letra=='b'){letras[1]++;}if(letra=='c'){letras[2]++;}if(letra=='d'){letras[3]++;}if(letra=='e'){letras[4]++;}
                if(letra=='f'){letras[5]++;}if(letra=='g'){letras[6]++;}if(letra=='h'){letras[7]++;}if(letra=='i'){letras[8]++;}if(letra=='j'){letras[9]++;}
                if(letra=='k'){letras[10]++;}if(letra=='l'){letras[11]++;}if(letra=='m'){letras[12]++;}if(letra=='n'){letras[13]++;}if(letra=='o'){letras[14]++;}
                if(letra=='p'){letras[15]++;}if(letra=='q'){letras[16]++;}if(letra=='r'){letras[17]++;}if(letra=='s'){letras[18]++;}if(letra=='t'){letras[19]++;}
                if(letra=='u'){letras[20]++;}if(letra=='v'){letras[21]++;}if(letra=='w'){letras[22]++;}if(letra=='x'){letras[23]++;}if(letra=='y'){letras[24]++;}
                if(letra=='z'){letras[25]++;}
            }
            double frequencia;
            double somatorioFrequencia=0;
            
            for(int aux = 0;aux<letras.length;aux++){
                frequencia = letras[aux];
                somatorioFrequencia = somatorioFrequencia + (frequencia*(frequencia-1));
            }
            double indiceDeCoincidencia = somatorioFrequencia/(n*(n-1));  
            System.out.println("O índice de coincidencia do Texto é "+indiceDeCoincidencia);      
        //}
    }
}