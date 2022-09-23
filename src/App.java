import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    static String lingua;
    public static void main(String[] args) throws Exception {
        System.out.println(" Cifra de Vigenere!!!");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
   

        // Le o arquivo (Substitua o path para o seu diretório local)
        String texto = leArquivo("C:/Users/lpasq/PUCRS/2022 - 2º Semestre/Segurança de Sistemas/Trabalhos/T1/src/20201-teste2.txt");

        //Calcula o tamanho da chave    
        int tamanhoChave = tamanhoChave(texto);
        
        // Identifica a chave
        String chave = getChave(tamanhoChave, texto);
        
        //Descriptografa com a chave
        descript(texto, chave);

        //Mensagens
        System.out.println("O texto está em:  "+lingua);
        System.out.println("A chave tem tamanho: "+tamanhoChave);
        System.out.println("A chave é: "+chave);
    }
    
    private static void descript(String texto, String chave) {

        
        char letraCript;
        int posCript;
        char letraChave;
        int posChave;
        int posDescript;
        int i=0;
        while(i<texto.length()){
            for(int j=0;j<chave.length();j++){
                letraCript = texto.charAt(i);
                letraChave = chave.charAt(j);
                posCript = posicaoCorrespondente(letraCript);
                posChave = posicaoCorrespondente(letraChave);

                if(posCript-posChave>=0){
                    posDescript=posCript-posChave;
                }else{
                    posDescript=26+posCript-posChave;
                }
                
                System.out.print(letraCorrespondente(posDescript));
               
                i++;
               if(i>=texto.length())break;
            }
        }
        
    }

    public static String leArquivo(String path) throws IOException{
        BufferedReader buffReader = new BufferedReader(new FileReader(path));
        String texto = buffReader.readLine();
        buffReader.close();
        return texto;
    }

    public static int tamanhoChave(String texto){
        
        int tamanhoChave = 1;
        double indiceParcial = 0; 
        double indiceTotal = 0;

        //Testa para chaves com tamanho até 22
        for(int recuo =0;tamanhoChave<22;tamanhoChave++){
            for(int i=0;i<tamanhoChave;i++){
                double indice = indiceDeCoincidencia(texto, tamanhoChave, recuo);
                indiceParcial = indiceParcial+indice;
                recuo++;
            }
                indiceTotal = indiceParcial/tamanhoChave;
                if(indiceTotal>=0.06 && indiceTotal <=0.08) break;
                indiceParcial=0;
        }
        //Identifica a língua pelo índice de coincidencia
        if(indiceTotal<=0.07){
            lingua = "Ingles";
        }else{
            lingua = "Portugues";
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


    private static String getChave(int tamanhoChave, String texto){
       
        String[] textoQuebrado = quebraTexto(texto,tamanhoChave);
        String chave = "";
        for(int i=0;i<tamanhoChave;i++){
            chave = chave+ caractereChave(textoQuebrado[i]);
        }
        return chave;
    }
    private static char caractereChave(String texto){
        int posChar=0;
        int [] maiores = letrasMaisFrequente(texto);
       
        if(lingua =="Portugues"){
            if(maiores[1]-maiores[0] == 4 || maiores[0]-maiores[1] == 22){
                posChar = maiores[0];
            }
            if(maiores[0]-maiores[1]==4 || maiores[1]-maiores[0] == 22){
                posChar = maiores[1];
            }
        }
        if(lingua=="Ingles"){
            if(maiores[0]> 1 && maiores[0] < 5){
                posChar = maiores[0]+ 22;
            }else{
                posChar = maiores[0] -4;
            }
        }
    
        return letraCorrespondente(posChar);

        

    }

    private static char letraCorrespondente(int posChar) {
        char k= 'a';
        if(posChar==0)k= 'a';if(posChar==1)k= 'b';if(posChar==2)k= 'c';if(posChar==3)k= 'd';if(posChar==4)k= 'e';if(posChar==5)k= 'f';if(posChar==6)k= 'g';if(posChar==7)k= 'h';if(posChar==8)k= 'i';if(posChar==9)k= 'j';if(posChar==10)k= 'k';if(posChar==11)k= 'l';if(posChar==12)k= 'm';if(posChar==13)k= 'n';if(posChar==14)k= 'o';if(posChar==15)k= 'p';if(posChar==16)k= 'q';if(posChar==17)k= 'r';if(posChar==18)k= 's';if(posChar==19)k= 't';if(posChar==20)k= 'u';if(posChar==21)k= 'v';if(posChar==22)k= 'w';if(posChar==23)k= 'x';if(posChar==24)k= 'y';if(posChar==25)k= 'z';
        return k;
    }
    private static int posicaoCorrespondente(char c){
        int pos =0;
        if(c=='a')pos= 0;if(c=='b')pos= 1;if(c=='c')pos= 2;if(c=='d')pos= 3;if(c=='e')pos= 4;if(c=='f')pos= 5;if(c=='g')pos= 6;if(c=='h')pos= 7;if(c=='i')pos= 8;if(c=='j')pos= 9;if(c=='k')pos= 10;if(c=='l')pos= 11;if(c=='m')pos= 12;if(c=='n')pos= 13;if(c=='o')pos= 14;if(c=='p')pos= 15;if(c=='q')pos= 16;if(c=='r')pos= 17;if(c=='s')pos= 18;if(c=='t')pos= 19;if(c=='u')pos= 20;if(c=='v')pos= 21;if(c=='w')pos= 22;if(c=='x')pos= 23;if(c=='y')pos= 24;if(c=='z')pos= 25;
        return pos;        

    }
    

    private static int[] letrasMaisFrequente(String texto) {

        
        int [] letras = frequenciaLetras(texto, 1, 0);
        int[] maiores = new int[2];
        int maior = 0;
        int segundaMaior =0;
       
        
        for(int i=0;i<letras.length;i++){
            if(letras[i]>letras[segundaMaior] ){
                segundaMaior=i;
           }
            if(letras[i]>letras[maior]){
                segundaMaior = maior;
                maior=i;
            }
           
        }
        maiores[0]=maior;
        maiores[1]=segundaMaior;
        return maiores;
    }
    

    private static String[] quebraTexto(String texto, int tamanhoChave){
        
        String [] textoQuebrado = new String [tamanhoChave];
        int tamanhoTexto = texto.length();
        char c = texto.charAt(0);
        int aux = 0;
        int tamanhoTextoQuebrado;

        if(tamanhoTexto>15000){
            tamanhoTextoQuebrado = 15000;
        }else{
            tamanhoTextoQuebrado = tamanhoTexto;
        }
        while(aux<(tamanhoTextoQuebrado-tamanhoChave)){
            for(int j=0;j<tamanhoChave;j++){
                textoQuebrado[j] = textoQuebrado[j]+c;
                aux++;
                c= texto.charAt(aux);
            }
        }
        
        return textoQuebrado;
    }
    
}