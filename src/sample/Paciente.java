package sample;

public class Paciente {
    private static int cont;
    private int idPaciente;
    private String estado;
    private float llegadaSistema;
    private float inicioEsperaEstudio;
    private String tipoPaciente;
    private int mostrado = 0;
    private int rechazado = 1;

    public Paciente() {
        cont++;
        this.idPaciente = cont;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getLlegadaSistema() {
        return llegadaSistema;
    }

    public void setLlegadaSistema(float llegadaSistema) {
        this.llegadaSistema = llegadaSistema;
    }

    public float getInicioEsperaEstudio() {
        return inicioEsperaEstudio;
    }

    public void setInicioEsperaEstudio(float inicioEsperaEstudio) {
        this.inicioEsperaEstudio = inicioEsperaEstudio;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public int getMostrado() {
        return mostrado;
    }

    public void setMostrado(int mostrado) {
        this.mostrado = mostrado;
    }

    public static int getCont() {
        return cont;
    }

    public static void setCont(int cont) {
        Paciente.cont = cont;
    }

    public int getRechazado() {
        return rechazado;
    }

    public void setRechazado(int rechazado) {
        this.rechazado = rechazado;
    }

    public void reset(){
        this.cont = 0;
    }

    @Override
    public String toString() {

        if(this.mostrado > 1 ){
            String cadena =
                    "\t\t\t\t\t<td>" + " " +"</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n" +
                            "\t\t\t\t\t<td>" + " " +"</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n";
            return cadena;
        } else {
            String cadena =
                    "\t\t\t\t\t<td>" + idPaciente +"</td>\n" +
                            "\t\t\t\t\t<td>" + llegadaSistema + "</td>\n" +
                            "\t\t\t\t\t<td>" + estado +"</td>\n" +
                            "\t\t\t\t\t<td>" + inicioEsperaEstudio + "</td>\n" +
                            "\t\t\t\t\t<td>" + tipoPaciente + "</td>\n";
            return cadena;
        }
    }

}
