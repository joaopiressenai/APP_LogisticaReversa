package com.palazzo.logisticareversa;

public enum EnumStatus {
    ENTREGUE("Entregue"),
    TRANSITO("Em trânsito"),
    ESPERA("Em espera"),
    COLETADO("Coletado"),
    ATRASADO("Em trânsito atrasado");
    
    private String status;

    private EnumStatus(String status2) {
        this.status = status2;
    }

    public String getStatus() {
        return this.status;
    }
}
