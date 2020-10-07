package grupo12;

class DepComb {

    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }
    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;
    public static final int MAX_COMBUSTIVEL = 13000;

    public SITUACAO situacao;
    public TIPOPOSTO tipo;
    public int tAditivo;
    public int tGasolina;
    public int tAlcool1;
    public int tAlcool2;

    public DepComb(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        int totalAlcool = (tAlcool1 + tAlcool2);
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
        this.defineSituacao();
    }

    public void defineSituacao(){
        int total = this.tAditivo + this.tGasolina + this.tAlcool1 + this.tAlcool2;
        if((double)total / this.MAX_COMBUSTIVEL < 0.5) {
            if((double)total/this.MAX_COMBUSTIVEL < 0.25) {
                this.situacao = SITUACAO.EMERGENCIA;
            }else{
                this.situacao = SITUACAO.SOBRAVISO;
            }
        }else{
            this.situacao = SITUACAO.NORMAL;
        }
    }

    public SITUACAO getSituacao(){
        return this.situacao;
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
        int livre = this.MAX_ALCOOL - this.tAlcool1 - this.tAlcool2;
        if(livre >= qtdade){
            this.tAlcool1 += qtdade/2.0;
            this.tAlcool2 += qtdade/2.0;
            return qtdade;
        }else if(livre < 1){
            return 0;
        }else{
            this.tAlcool1 += livre/2.0;
            this.tAlcool1 += livre/2.0;
            return livre;
        }
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        Double gasolina =  Double.valueOf(this.tGasolina);
        Double aditivo = Double.valueOf(this.tAditivo);
        Double alcool = Double.valueOf(this.tAlcool1 + this.tAlcool2);
        int [] ret = new int[] {0,0,0,0};

        if(qtdade < 0){
            // valores invalidos
            ret[0] = -1;
            return ret;
        }

        if(this.situacao == SITUACAO.SOBRAVISO){
            if(tipoPosto == TIPOPOSTO.COMUM){
                // so entrega a metade
                qtdade = (int) (qtdade/2.0);
            }
        } else if(this.situacao == SITUACAO.EMERGENCIA){
            if(tipoPosto == TIPOPOSTO.ESTRATEGICO){
                if(gasolina >= qtdade * 0.7 && alcool >= qtdade * 0.25) {
                    // entrega mesmo sem aditivo
                    aditivo = aditivo - qtdade * 0.05;
                    if(aditivo >= 0) {
                        this.tAditivo = aditivo.intValue();
                    } else {
                        this.tAditivo = 0;
                    }
                    this.tGasolina = (int) (gasolina - qtdade * 0.7);
                    this.tAlcool1 = (int) (this.tAlcool1 - (qtdade * 0.25 / 2.0));
                    this.tAlcool2 = this.tAlcool1;
                    ret[0] = this.tAditivo;
                    ret[1] = this.tGasolina;
                    ret[2] = this.tAlcool1;
                    ret[3] = this.tAlcool2;
                    return ret;
                } else{
                    // nao ha combustivel suficiente
                    ret[0] = -3;
                    return ret;
                }
            }else{
                // nao conseguiu atender a demanda
                ret[0] = -2;
                return ret;
            }
        }
        
        if(gasolina >= qtdade * 0.7 && alcool >= qtdade * 0.25 && aditivo >= qtdade * 0.05) {
            this.tAditivo = (int) (aditivo - qtdade * 0.05);
            this.tGasolina = (int) (gasolina - qtdade * 0.7);
            this.tAlcool1 = (int) (this.tAlcool1 - (qtdade * 0.25 / 2.0));
            this.tAlcool2 = this.tAlcool1;
            ret[0] = this.tAditivo;
            ret[1] = this.tGasolina;
            ret[2] = this.tAlcool1;
            ret[3] = this.tAlcool2;
        } else {
            ret[0] = -3;
            return ret;
        }
        return ret;
    }
}
