package grupo12;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



class DepComb {

    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    public DepComb(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {}

    public void defineSituacao(){}

    public SITUACAO getSituacao(){
        return SITUACAO.NORMAL;
    }

    public int gettGasolina(){
        return 0;
    }

    public int gettAditivo(){
        return 0;
    }

    public int gettAlcool1(){
        return 0;
    }

    public int gettAlcool2(){
        return 0;
    }

    public int recebeAditivo(int qtdade) { 
        return 0;
    }

    public int recebeGasolina(int qtdade) { 
        return 0;
    }

    public int recebeAlcool(int qtdade) {  
        return 0;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) { 
        return new int[]{1,2};
    }
}
