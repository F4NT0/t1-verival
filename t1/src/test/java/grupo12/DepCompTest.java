/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author lucas
 */
public class DepCompTest {

    private DepComb dc = null;

    @BeforeEach
    void setUp() {

    }
    
    @ParameterizedTest
    @CsvSource({
        "255,5100,638,638",
        "250,5000,625,625",
        "245,4900,612,612",
        "125,2500,313,313",
        "120,2400,300,300",
        "50,1000,125,125"
    })
    public void getSituacaoTest(int aditivo,int gasolina,int alcool1,int alcool2) {
        DepComb.SITUACAO resultado;
        if(aditivo+gasolina+alcool1+alcool2 < DepComb.MAX_COMBUSTIVEL*0.25){
            resultado = DepComb.SITUACAO.EMERGENCIA;
        }
        else if(aditivo+gasolina+alcool1+alcool2 < DepComb.MAX_COMBUSTIVEL*0.5){
            resultado = DepComb.SITUACAO.SOBRAVISO;
        }
        else{
            resultado = DepComb.SITUACAO.NORMAL;
        }
        
        dc = new DepComb(aditivo, gasolina, alcool1, alcool2);
        int total = aditivo + gasolina + alcool1 + alcool2;
        DepComb.SITUACAO aux = dc.getSituacao();
        Assertions.assertEquals(resultado, aux);
    }
    
    
    @ParameterizedTest
    @CsvSource({
        "500, 1,0",
        "0,-1,-1",
        "100,200,200",
        "450,300,50"
    })
    public void recebeAditivoTest(int aditivoIni, int aditivoExtra, int retorno) {
        dc = new DepComb(aditivoIni, 10000, 1250, 1250);
        int aux = dc.recebeAditivo(aditivoExtra);
        Assertions.assertEquals(retorno, aux);
    }

    @ParameterizedTest
    @CsvSource({
        "10000, 1,0",
        "0,-1,-1",
        "3000,1000,1000",
        "9000,2000,1000"
    })
    public void recebeGasolinaTest(int gasolinaIni, int gasolinaExtra, int retorno) {
        dc = new DepComb(500, gasolinaIni, 2500, 2500);
        int aux = dc.recebeGasolina(gasolinaExtra);
        Assertions.assertEquals(retorno, aux);
    }

    @ParameterizedTest
    @CsvSource({
        "1250, 1,0",
        "0,-1,-1",
        "625,1000,1000",
        "1200,300,100"
    })
    public void recebeAlcoolTest(int alcoolIni, int alcoolExtra, int retorno) {
        dc = new DepComb(500, 10000, alcoolIni, alcoolIni);
        int aux = dc.recebeAlcool(alcoolExtra);
        Assertions.assertEquals(retorno, aux);
    }

    @ParameterizedTest
    @CsvSource({
        "255,5100,638,638,400,COMUM,235;4820;588;588",
        "255,5100,638,638,100,ESTRATEGICO,250;5030;625;625",
        "185,3700,463,463,1000,COMUM,160;3350;400;400",
        "185,3700,463,463,500,ESTRATEGICO,160;3350;400;400",
        "120,2400,300,300,100,COMUM,-2;0;0;0",
        "0,1000,125,125,200,ESTRATEGICO,0;860;100;100",
        "30,10,10,10,5000,ESTRATEGICO,-3;0;0;0",
        "255,5100,638,638,-7,COMUM,-1;0;0;0"
    })
    public void encomendaCombustivel(int aditivo,int gasolina,int alcool1,int alcool2,int qtdade, String tipoPosto,String resultado) { 
        dc = new DepComb(aditivo, gasolina, alcool1, alcool2);

        String[] split = resultado.split(";");
        int size = split.length;
        int [] rs = new int [4];
        for(int i=0; i<size; i++) {
            rs[i] = Integer.parseInt(split[i]);
        }
        DepComb.TIPOPOSTO tp;
        if(tipoPosto.equals("COMUM")){
            tp = DepComb.TIPOPOSTO.COMUM;
        }else{
            tp = DepComb.TIPOPOSTO.ESTRATEGICO;
        }
        int[] aux = dc.encomendaCombustivel(qtdade, tp);
        
        Assertions.assertArrayEquals(rs, aux);
    }
}






/*
        // Para Depurar o teste de encomendaCombustivel
        System.out.println("alcool1: " + dc.tAlcool1);
        System.out.println("alcool2: " + dc.tAlcool2);

        int[] aux = dc.encomendaCombustivel(qtdade, tp);

        System.out.print(aux.length + "  " + rs.length + "\n");

        
        for(int i = 0; i<aux.length; i++) {
            System.out.print(aux[i] + "  ");
        }
        System.out.println();
        for(int i = 0; i<rs.length; i++) {
            System.out.print(rs[i] + "  ");
        }
        System.out.println();
        System.out.println();
*/