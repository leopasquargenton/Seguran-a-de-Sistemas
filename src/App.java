import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {


        System.out.println("DESCRIPTOGRAFANDO TEXTO DE VIGENERE!!");
        System.out.println("");
        System.out.println("...");

        // Le o arquivo com o idioma passad por parâmetro

        String texto = leArquivo("C:/Users/lpasq/PUCRS/2022 - 2º Semestre/Segurança de Sistemas/Trabalhos/T1/src/20201-teste2.txt", "pt");

        
        System.out.println("...Lido o texto criptografado: ");
        System.out.println("Tamanho do texto: " +texto.length());
        

        // Verifica se o tamanho do texto é grande o suficiente para ser decifrado usando Vigenere

        if(texto.length()<1000){System.out.println("Texto muito pequeno. Impossível analisar com Vigenere");}else{
        
        //Calcula o tamanho da chave    

        int tamanhoChave = tamanhoChave(texto);
        System.out.println("Tamanho da chave " +tamanhoChave);
        

        // Quebra o texto em "n" textos, de acordo com o tamanho da chave
        
        for(int i=0;i<tamanhoChave;i++){
            String textoQuebrado = quebraTexto(texto,tamanhoChave, i);
            int letraMaisFrequente = letraMaisFrequente(textoQuebrado);
            System.out.println("Letra mais frequente para o "+(i+1)+"º Bloco foi "+ letraMaisFrequente);
            }
        }
    }
    
    public static String leArquivo(String path, String lingua) throws IOException{
        BufferedReader buffReader = new BufferedReader(new FileReader(path));
        String texto = buffReader.readLine();
        buffReader.close();
        return texto;
    }


    public static int tamanhoChave(String texto){
        // Calcula o índice de coincidência para chaves entre 1 e 20 utilizando a média de suas variações (recuos) e para no momento em que chega em um índice entre 0,06 e 0,082 (intervalo das línguas mais comuns)
        int tamanhoChave = 1;
        double indiceParcial = 0; 
        double indiceTotal = 0;
        for(int recuo =0;tamanhoChave<22;tamanhoChave++){
            for(int i=0;i<tamanhoChave;i++){
                double indice = indiceDeCoincidencia(texto, tamanhoChave, recuo);
                indiceParcial = indiceParcial+indice;
                recuo++;
            }
                indiceTotal = indiceParcial/tamanhoChave;
                if(indiceTotal>=0.06 && indiceTotal <=0.082) break;
                indiceParcial=0;
        }
        return tamanhoChave;
    }
        
    
    public static double indiceDeCoincidencia(String texto, int tamanhoChave, int recuo){
         
        int [] letras = frequenciaLetras(texto, tamanhoChave, recuo);
        double quant=texto.length()/tamanhoChave;
        double frequencia=0;
        double somatorioFrequencia=0;
           
        for(int aux2 = 0;aux2<26;aux2++){
            frequencia = letras[aux2];
            somatorioFrequencia = somatorioFrequencia + (frequencia*(frequencia-1));
        }    
        return somatorioFrequencia/((quant)*(quant-1)); 
    }

    
    private static String quebraTexto(String texto, int tamanhoChave, int recuo){
        String textoQuebrado = "";
        int tamanhoTexto = texto.length();
        
        for(int i=recuo;i<tamanhoTexto;i=i+tamanhoChave){
            textoQuebrado=textoQuebrado+texto.charAt(i);
        }
        return textoQuebrado;
    }


    private static int letraMaisFrequente(String texto) {
        
        int [] letras = frequenciaLetras(texto, 1, 0);
        int maior = 0;
        for(int i=0;i<letras.length;i++){
            
        }
        
        for(int i=0;i<letras.length;i++){
            if(letras[i]>letras[maior]){
                maior=i;
            }
        }
        for(int i=0;i<26;i++){
            System.out.println(i+ "saiu "+letras[i]);
            
        }
        System.out.println("A letra que mais saiu teve uma porcentagem de "+(letras[maior]*1.0)/texto.length());
        return maior;
    }
    private static int[] frequenciaLetras(String texto, int tamanhoChave, int recuo){
        double tamanho = texto.length();
        char letra;
        int [] letras = new int [26];
        for(int aux=recuo;aux<tamanho;aux=aux+tamanhoChave){
             letra = texto.charAt(aux);
             if(letra=='a'){letras[0]++;}if(letra=='b'){letras[1]++;}if(letra=='c'){letras[2]++;}if(letra=='d'){letras[3]++;}if(letra=='e'){letras[4]++;}if(letra=='f'){letras[5]++;}if(letra=='g'){letras[6]++;}if(letra=='h'){letras[7]++;}if(letra=='i'){letras[8]++;}if(letra=='j'){letras[9]++;}if(letra=='k'){letras[10]++;}if(letra=='l'){letras[11]++;}if(letra=='m'){letras[12]++;}if(letra=='n'){letras[13]++;}if(letra=='o'){letras[14]++;}if(letra=='p'){letras[15]++;}if(letra=='q'){letras[16]++;}if(letra=='r'){letras[17]++;}if(letra=='s'){letras[18]++;}if(letra=='t'){letras[19]++;}if(letra=='u'){letras[20]++;}if(letra=='v'){letras[21]++;}if(letra=='w') {letras[22]++;}if(letra=='x'){letras[23]++;}if(letra=='y'){letras[24]++;}if(letra=='z'){letras[25]++;}
        } 
        return letras;

    }
}