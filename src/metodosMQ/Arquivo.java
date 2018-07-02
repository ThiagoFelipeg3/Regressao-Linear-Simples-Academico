package metodosMQ;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.pow;

/******************************************
 * @author Thiago Felipe Pinto de Oliveira
 * @author Fernando Lucas Rodrigues Simões
 * @author Hugo Vínivius Freita Losqui
 * @author Leonardo do Carmo Almeida
 * @author José Lúcio Barbosa Júnior
 * @author Guilherme Passos
 *****************************************/
public class Arquivo {
    
    /**************************
     * Metodo para as formulas 
     * B1 e B0
     * @param x
     * @param y
     * @param n
     * @param mult
     * @param xElevado
     * @return 
     ************************/
    public static double B_1(int x, int y, int n, int mult, int xElevado){
        double b_1 = 0;
        
        b_1 = (x*y-n*mult)/(pow(x,2)-n*xElevado);
        return b_1;
    }
    public static double B_0(int x, int y, double b_1, int n){
        double b_0 = 0;
        
        b_0 = (y-b_1*x)/n;
        
        return b_0;
    }
    public static void main(String[] args) throws FileNotFoundException{
        
        int numeroBaseDados = 0;
        int sunTempodeEstudo = 0;
        
        int sunNotaProva =0;
        int sunNotaProva2 =0;
        int sunNotaProva3 =0;
        int sunTodasProvas =0;
        
        int tempoEstudoElevado = 0;
        
        int multXY = 0;
        int multXY2 =0;
        int multXY3 =0;
        int multXYtodasProvas =0;
       
        /******************************************************************
         * Foi calculadotodas as variáveis em uma só leitura do documento.
         * Por isso tem muitas variáveis, não se assuste. kkkkk
         * A muitas formas de se fazer isso. Esta aqui uma.
         * ****************************************************************/
       try{
           FileReader arq = new FileReader("AnaliseEstudo.csv");
           BufferedReader lerArq = new BufferedReader(arq);
           String lPL = "";
           try{
            lPL = lerArq.readLine();
         
                while(lPL != null){

                    String[] conteudo = lPL.split(";");

                    sunTempodeEstudo += Integer.parseInt(conteudo[1]); 
                    tempoEstudoElevado += pow(Integer.parseInt(conteudo[1]),2);

                    sunNotaProva += Integer.parseInt(conteudo[3]);
                    sunNotaProva2 += Integer.parseInt(conteudo[4]);
                    sunNotaProva3 += Integer.parseInt(conteudo[5]);
                    sunTodasProvas += Integer.parseInt(conteudo[3])+Integer.parseInt(conteudo[4])+Integer.parseInt(conteudo[5]);

                    multXY += Integer.parseInt(conteudo[1])*Integer.parseInt(conteudo[3]); 
                    multXY2 += Integer.parseInt(conteudo[1])*Integer.parseInt(conteudo[4]); 
                    multXY3 += Integer.parseInt(conteudo[1])*Integer.parseInt(conteudo[5]); 
                    multXYtodasProvas += Integer.parseInt(conteudo[1])*(Integer.parseInt(conteudo[3])+Integer.parseInt(conteudo[4])+Integer.parseInt(conteudo[5])); 

                    numeroBaseDados++;
                    

                   lPL = lerArq.readLine();
                }
                 arq.close();
           }catch (IOException ex){
               String msg = "Erro: não foi possivel ler o arquivo!";
           }    
        }catch(FileNotFoundException ex){
            String msg = "Erro: arquivo não escontrado!";
        }
       
       /**************************************************************
        * Calculo das formulas B1 e B2, para as provas 1,2,3 e todas
        * ************************************************************/
        double b_1ParaProva1 = B_1(sunTempodeEstudo, sunNotaProva, numeroBaseDados, multXY, tempoEstudoElevado);
       double b_0ParaProva1 = B_0(sunTempodeEstudo, sunNotaProva, b_1ParaProva1, numeroBaseDados);
       
        double b_1ParaProva2 = B_1(sunTempodeEstudo, sunNotaProva2, numeroBaseDados, multXY2, tempoEstudoElevado);
       double b_0ParaProva2 = B_0(sunTempodeEstudo, sunNotaProva2, b_1ParaProva2, numeroBaseDados);
       
        double b_1ParaProva3 = B_1(sunTempodeEstudo, sunNotaProva3, numeroBaseDados, multXY3, tempoEstudoElevado);
        double b_0ParaProva3 = B_0(sunTempodeEstudo, sunNotaProva3, b_1ParaProva3, numeroBaseDados);
        
        double b_1total = B_1(sunTempodeEstudo, sunTodasProvas, numeroBaseDados, multXYtodasProvas, tempoEstudoElevado);
       double b_0total = B_0(sunTempodeEstudo, sunTodasProvas, b_1total, numeroBaseDados);
    
        
        double prova1 = 0;
        double prova2 = 0;
        double prova3 = 0;
        double todasProvas = 0;
       
       int var[] = {0,1,2,3,4};
       
       /**********************************************
        * Modelo matemátivo para o calculo das provas
        * em relaçao ao tempo de estudo que vai de 
        * 0 a 4.
        * *******************************************/
       for(int i=0;i<=4; i++){
            prova1 = b_0ParaProva1 + b_1ParaProva1 * var[i]; 
            prova2 = b_0ParaProva2 + b_1ParaProva2 * var[i];
            prova3 = b_0ParaProva3 + b_1ParaProva3 * var[i];
            todasProvas = b_0total + b_1total * var[i];

            System.out.printf("Tempo de estudo: %d \tprova 1: %.2f \tprova 2: %.2f \tprova 3: %.2f \tTodas as provas: %.2f",var[i],prova1,prova2,prova3,todasProvas);
            System.out.println();
           
       }     
    }
    
}
   
