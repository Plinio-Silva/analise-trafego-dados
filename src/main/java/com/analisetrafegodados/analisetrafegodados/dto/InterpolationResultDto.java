package com.analisetrafegodados.analisetrafegodados.dto;

public class InterpolationResultDto {
    private String horarioConsultado;
    private double horaDecimal;
    private double valorInterpolado;
    private double pontoInicialHora;
    private double pontoInicialValor;
    private double pontoFinalHora;
    private double pontoFinalValor;
    private String formula;
    private String resultadoFinal;

    public InterpolationResultDto() {
    }

    public InterpolationResultDto(String horarioConsultado, double horaDecimal, double valorInterpolado,
                                 double pontoInicialHora, double pontoInicialValor,
                                 double pontoFinalHora, double pontoFinalValor,
                                 String formula, String resultadoFinal) {
        this.horarioConsultado = horarioConsultado;
        this.horaDecimal = horaDecimal;
        this.valorInterpolado = valorInterpolado;
        this.pontoInicialHora = pontoInicialHora;
        this.pontoInicialValor = pontoInicialValor;
        this.pontoFinalHora = pontoFinalHora;
        this.pontoFinalValor = pontoFinalValor;
        this.formula = formula;
        this.resultadoFinal = resultadoFinal;
    }

    public String getHorarioConsultado() {
        return horarioConsultado;
    }

    public void setHorarioConsultado(String horarioConsultado) {
        this.horarioConsultado = horarioConsultado;
    }

    public double getHoraDecimal() {
        return horaDecimal;
    }

    public void setHoraDecimal(double horaDecimal) {
        this.horaDecimal = horaDecimal;
    }

    public double getValorInterpolado() {
        return valorInterpolado;
    }

    public void setValorInterpolado(double valorInterpolado) {
        this.valorInterpolado = valorInterpolado;
    }

    public double getPontoInicialHora() {
        return pontoInicialHora;
    }

    public void setPontoInicialHora(double pontoInicialHora) {
        this.pontoInicialHora = pontoInicialHora;
    }

    public double getPontoInicialValor() {
        return pontoInicialValor;
    }

    public void setPontoInicialValor(double pontoInicialValor) {
        this.pontoInicialValor = pontoInicialValor;
    }

    public double getPontoFinalHora() {
        return pontoFinalHora;
    }

    public void setPontoFinalHora(double pontoFinalHora) {
        this.pontoFinalHora = pontoFinalHora;
    }

    public double getPontoFinalValor() {
        return pontoFinalValor;
    }

    public void setPontoFinalValor(double pontoFinalValor) {
        this.pontoFinalValor = pontoFinalValor;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }
}
