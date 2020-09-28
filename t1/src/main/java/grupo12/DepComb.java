package grupo12;

class DepComb {

    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }
    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;
    public static final int MAX_COMBUSTIVEL = 13000;

    public SITUACAO situação;
    public TIPOPOSTO tipo;
    public int tAditivo;
    public int tGasolina;
    public int tAlcool1;
    public int tAlcool2;

    public DepComb(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        int totalAlcool = (tAlcool1 + tAlcool2) / 2;
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = totalAlcool;
        this.tAlcool2 = totalAlcool;
        this.defineSituacao();
    }

    public void defineSituacao(){
        int total = this.tAditivo + this.tGasolina + this.tAlcool1 + this.tAlcool2;
        if(this.MAX_COMBUSTIVEL / total < 0.5){
            if(this.MAX_COMBUSTIVEL / total < 0.25){
                this.situação = SITUACAO.EMERGENCIA;
            }else{
                this.situação = SITUACAO.SOBRAVISO;
            }
        }else{
            this.situação = SITUACAO.NORMAL;
        }
    }

    public SITUACAO getSituacao(){
        return this.situação;
    }

    public int gettGasolina(){
        return this.tGasolina;
    }

    public int gettAditivo(){
        return this.tAditivo;
    }

    public int gettAlcool1(){
        return this.tAlcool1;
    }

    public int gettAlcool2(){
        return this.tAlcool2;
    }

    public int recebeAditivo(int qtdade) { 
        if(qtdade < 0){
            return -1;
        }
        int livre  = this.MAX_ADITIVO - this.tAditivo;
        if(livre >= qtdade){
            this.tAditivo += qtdade;
            return qtdade;
        }else if(livre < 1){
            return 0;
        }else{
            this.tAditivo += livre;
            return livre;
        }
    }

    public int recebeGasolina(int qtdade) { 
        if(qtdade < 0){
            return -1;
        }
        int livre  = this.MAX_GASOLINA - this.tGasolina;
        if(livre >= qtdade){
            this.tGasolina += qtdade;
            return qtdade;
        }else if(livre < 1){
            return 0;
        }else{
            this.tGasolina += livre;
            return livre;
        }
    }

    public int recebeAlcool(int qtdade) {
        if(qtdade < 0){
            return -1;
        }
        int livre  = this.MAX_ADITIVO - this.tAlcool1 - this.tAlcool2;
        if(livre >= qtdade){
            this.tAlcool1 += qtdade/2;
            this.tAlcool2 += qtdade/2;
            return qtdade;
        }else if(livre < 1){
            return 0;
        }else{
            this.tAlcool1 += livre/2;
            this.tAlcool1 += livre/2;
            return livre;
        }
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        Double gasolina =  Double.valueOf(this.tGasolina);
        Double aditivo = Double.valueOf(this.tAditivo);
        Double alcool = Double.valueOf(this.tAlcool1 + this.tAlcool2);
        int [] ret = new int[4];

        if(qtdade < 0){
            ret[0] = -1;
            return ret;
        }
        if(this.situação == SITUACAO.SOBRAVISO){
            if(tipoPosto != TIPOPOSTO.ESTRATEGICO){
                qtdade = qtdade/2;
            }

        } else if(this.situação == SITUACAO.EMERGENCIA){
            if(tipoPosto == TIPOPOSTO.ESTRATEGICO){
                if(gasolina >= qtdade * 0.7 && alcool >= qtdade * 0.25){
                    ret[0] = 0;
                    ret[1] = (int) (qtdade * 0.7);
                    ret[2] = (int) (qtdade * 0.125);
                    ret[3] = (int) (qtdade * 0.125);
                    return ret;
                } else{
                    ret[0] = -3;
                    return ret;
                }
            }else{
                ret[0] = -2;
                return ret;
            }
        }
        if(gasolina >= qtdade * 0.7 && alcool >= qtdade * 0.25 && aditivo >= qtdade * 0.05){
            ret[0] = (int) (qtdade * 0.05);
            ret[1] = (int) (qtdade * 0.7);
            ret[2] = (int) (qtdade * 0.125);
            ret[3] = (int) (qtdade * 0.125);
        }else{
            ret[0] = -3;
            return ret;
        }

        return new int[]{1,2};
    }
}
