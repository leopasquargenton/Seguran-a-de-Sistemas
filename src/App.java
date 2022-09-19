import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //Le texto em Português
        String texto = leArquivo("Seguran-a-de-Sistemas/LínguaProvavel/src/20201-teste2.txt");
        int tamanhoChave = 1;
        //Calcula o índice de coincidência para chaves entre 1 e 20
        for(;tamanhoChave<20;tamanhoChave++){
            double indice = indiceDeCoincidencia(texto, tamanhoChave);
            

        /*Para se encontrar um índice entre 0,070 e 0,0755, pois < 0,070 pode ser Inglês e >0,0755 pode ser  Francês ou alemão */
            if(indice >=0.070 && indice < 0.0755) break;
            
        }
        System.out.println("A chave tem "+tamanhoChave );
    }

    public static String leArquivo(String path) throws IOException{
        BufferedReader buffReader = new BufferedReader(new FileReader(path));
        String texto = buffReader.readLine();
        buffReader.close();
        return texto;
    }
    public static double indiceDeCoincidencia(String texto, int tamanhoChave){
        double indiceParcial =0;
        double tamanho = texto.length();
        double [] letras = new double [26];
        char letra;
        int quant=0;
        for(int j=0;j<tamanhoChave;j++){
        for(int aux=0;aux<tamanho;aux=aux+tamanhoChave){
             letra = texto.charAt(aux);
             if(letra=='a'){letras[0]++;}if(letra=='b'){letras[1]++;}if(letra=='c'){letras[2]++;}if(letra=='d'){letras[3]++;}if(letra=='e'){letras[4]++;}if(letra=='f'){letras[5]++;}if(letra=='g'){letras[6]++;}if(letra=='h'){letras[7]++;}if(letra=='i'){letras[8]++;}if(letra=='j'){letras[9]++;}if(letra=='k'){letras[10]++;}if(letra=='l'){letras[11]++;}if(letra=='m'){letras[12]++;}if(letra=='n'){letras[13]++;}if(letra=='o'){letras[14]++;}if(letra=='p'){letras[15]++;}if(letra=='q'){letras[16]++;}if(letra=='r'){letras[17]++;}if(letra=='s'){letras[18]++;}if(letra=='t'){letras[19]++;}if(letra=='u'){letras[20]++;}if(letra=='v'){letras[21]++;}if(letra=='w') {letras[22]++;}if(letra=='x'){letras[23]++;}if(letra=='y'){letras[24]++;}if(letra=='z'){letras[25]++;}
                quant++;
            } 
           double frequencia=0;
           double somatorioFrequencia=0;
           
            for(int aux2 = 0;aux2<26;aux2++){
                frequencia = letras[aux2];
                somatorioFrequencia = somatorioFrequencia + (frequencia*(frequencia-1));
            }
            
        indiceParcial = indiceParcial+ (somatorioFrequencia/((quant*1)*(quant-1)));
          
        System.out.println(indiceParcial);
        }
        return indiceParcial/(tamanhoChave*1.0);
    }
}