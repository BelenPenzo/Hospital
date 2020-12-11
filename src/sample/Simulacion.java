package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Simulacion {
    public int cantSimulaciones;
    public int contador;
    public int desde;    // MostrarDesde
    public int hasta;    // MostrarHasta
    private Fila fila;
    private ArrayList<Fila> filasMostrar;
    private Fila ultimaFila;
    private String html;
    private float mediaConTurno;
    private float mediaSinTurno;
    private float desdeSecretaria;
    private float hastaSecretaria;
    private int cantTurnos;
    private float mediaMedico;
    private float desviacionMedico;
    private float cierreConsultorio;

    public Simulacion(int cantSimulaciones, int contador, int desde, int cantFilas, float mediaConTurno, float mediaSinTurno, float desdeSecretaria, float hastaSecretaria, float mediaMedico, float desvMedico, int cantTurnos, float cierreCons) throws IOException {
        this.cantSimulaciones = cantSimulaciones;
        this.contador = contador;
        this.desde = desde;
        this.hasta = cantFilas;
        this.filasMostrar = new ArrayList<Fila>();
        this.html = "";
        this.mediaConTurno = mediaConTurno;
        this.mediaSinTurno = mediaSinTurno;
        this.desdeSecretaria = desdeSecretaria;
        this.hastaSecretaria = hastaSecretaria;
        this.cantTurnos = cantTurnos;
        this.mediaMedico = mediaMedico;
        this.desviacionMedico = desvMedico;
        this.cierreConsultorio = cierreCons;
        generarSimulacion();
    }

    public void generarSimulacion() throws IOException {

        for (int i = 0; i < cantSimulaciones; i++) {
            Fila filaAnterior;
            if (i == 0) {
                filaAnterior = new Fila();
                this.fila = new Fila(filaAnterior, mediaConTurno, mediaSinTurno, desdeSecretaria, hastaSecretaria, cantTurnos, mediaMedico, desviacionMedico,cierreConsultorio);

            } else {
                filaAnterior = fila;
                this.fila = new Fila(filaAnterior, mediaConTurno, mediaSinTurno, desdeSecretaria, hastaSecretaria, cantTurnos, mediaMedico, desviacionMedico,cierreConsultorio);

            }
            if (i >= desde && i <= hasta) {
                filasMostrar.add(fila);
                this.html = html + fila.toString2();
            }

            if (i == cantSimulaciones - 1) {
                this.ultimaFila = fila;
            }
        }
        this.html = html + ultimaFila.toString3();
        crearHTML();
    }

    public void crearHTML() throws IOException {
        Writer wr2 = new FileWriter("src/sample/prueba.html");
        String encabezados_pacientes = "";
        for (int i = 0; i < ultimaFila.getPacientes().size(); i++) {
            encabezados_pacientes = encabezados_pacientes + htmlPacientes();
        }
        wr2.write(html1(encabezados_pacientes));
        wr2.write(html);
        wr2.flush();
        wr2.close();
    }


    public String html1(String encabezados_pacientes){
        String cadena = "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Establecimiento de salud</title>\n" +
                "\t\t <link href=\"Style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"\">\n" +
                "\t\t <link rel=\"shortcut icon\" href=\"hospital.png\">" +
                "\t</head>\n" +
                "\t\n" +
                "\t<body>\n" +
                "\n" +
                "\t\t<table class=\"tabla71\">\n" +
                "\t\t\t<thead>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<th>Nro Fila</th>\n" +
                "\t\t\t\t\t<th>Evento</th>\n" +
                "\t\t\t\t\t<th>Reloj</th>\n" +
                "\t\t\t\t\t<th>RND llegada Paciente sin Turno</th>\n" +
                "\t\t\t\t\t<th>Tiempo entre llegadas sin Turno</th>\n" +
                "\t\t\t\t\t<th class='color'>Pr&oacute;xima llegada Paciente sin Turno</th>\n" +
                "\t\t\t\t\t<th>RND llegada Paciente con Turno</th>\n" +
                "\t\t\t\t\t<th>Tiempo entre llegadas con Turno</th>\n" +
                "\t\t\t\t\t<th class='color'>Pr&oacute;xima llegada Paciente con Turno</th>\n" +
                "\t\t\t\t\t<th>RND tiempo atencion</th>\n" +
                "\t\t\t\t\t<th>Tiempo atencion</th>\n" +
                "\t\t\t\t\t<th class='color'>Fin atencion</th>\n" +
                "\t\t\t\t\t<th>Tiempo estudio</th>\n" +
                "\t\t\t\t\t<th class='color'>Fin estudio</th>\n" +
                "\t\t\t\t\t<th>Cierre consultorio</th>\n" +
                "\t\t\t\t\t<th>Secretaria</th>\n" +
                "\t\t\t\t\t<th>Turnos entregados</th>\n" +
                "\t\t\t\t\t<th>Cola Turnos</th>\n" +
                "\t\t\t\t\t<th>Tecnico</th>\n" +
                "\t\t\t\t\t<th>Cola estudio</th>\n" +
                "\t\t\t\t\t<th>Cont pacientes rechazados ST</th>\n" +
                "\t\t\t\t\t<th>Cont pacientes atendidos CT</th>\n" +
                "\t\t\t\t\t<th>Tiempo de permanencia</th>\n" +
                "\t\t\t\t\t<th>AC permanencia</th>\n" +
                "\t\t\t\t\t<th>Prom. permanencia</th>\n" +
                "\t\t\t\t\t<th>Cont Pacientes que fueron atendidos</th>\n" +
                "\t\t\t\t\t<th>Tiempo espera atencion</th>\n" +
                "\t\t\t\t\t<th>AC espera atencion</th>\n" +
                "\t\t\t\t\t<th>Prom. espera atencion</th>\n" +
                "\t\t\t\t\t<th>Acumulador reloj</th>\n" +
                "\t\t\t\t\t<th>AC ocupación secretaria</th>\n" +
                "\t\t\t\t\t<th>% Ocupación secretaria</th>\n" +
                encabezados_pacientes +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</thead>\n" +
                "\t\t\t\n" +
                "\t\t\t<tbody>\n";
        return cadena;
    }

    public String htmlPacientes(){
        String cadena = "\t\t\t\t\t<th>Id Paciente</th>\n" +
                "\t\t\t\t\t<th>Llegada Paciente</th>\n" +
                "\t\t\t\t\t<th>Estado</th>\n" +
                "\t\t\t\t\t<th>Inicio Espera</th>\n" +
                "\t\t\t\t\t<th>Tipo Paciente</th>\n";
        return cadena;
    }
}