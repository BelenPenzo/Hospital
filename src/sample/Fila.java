package sample;

import java.util.ArrayList;

public class Fila {
    private Fila filaAnterior;
    private int nroFila;

    private String evento;
    private float reloj = 0;
    private float acumuladorReloj = 0;
    private String proximoEvento;
    private float proximoReloj;
    private float randomLlegadaSinTurno;
    private float tiempoEntreLlegadasSinTurno;
    private float proximaLlegadaPacienteSinTurno;
    private float randomLlegadaConTurno;
    private float tiempoEntreLlegadasConTurno;
    private float proximaLlegadaPacienteConTurno;
    private float proximaLlegadaPaciente;
    private int  contPacientesRechazados;
    private float randomFinAtencion;
    private float tiempoAtencion;
    private float tiempoFinAtencion;
    private float randomFinEstudio;
    private float tiempoEstudio;
    private float tiempoFinEstudio;
    //private float cierre_consultorio = 300;
    private String secretaria = "Libre";
    private int turnosDisponibles = 0;
    private String medico = "Libre";
    private int colaTurnos = 0;
    private int colaEstudio = 0;
    private boolean sinLlegadas = false;

    private Paciente pacienteActual;
    private Paciente pacienteAnterior;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Paciente> colaPacienteEsperandoAtencion;
    private ArrayList<Paciente> colaPacientesEstudio;

    //Estadisticas
    private float tiempoEsperaPaciente;
    private float tiempoPermaneciaPaciente;
    private int contPacientesAtendidos;
    private float acumuladorTiempoPermanencia;
    private float promedioTiempoPermanencia;
    private int contPacienteEsperandoEstudio;
    private float acumuladorTiempoEspera;
    private float promedioTiempoEspera;
    private float acumOcupacionSecretaria;
    private float porcentajeOcupacionSecretaria;

    // Parametros
    private float mediaConTurno;
    private float mediaSinTurno;
    private float desdeSecretaria;
    private float hastaSecretaria;
    private int cantTurnos;
    private float mediaMedico;
    private float desviacionMedico;
    private float cierreConsultorio;

    public Fila(){
        this.proximaLlegadaPaciente = 0;

        this.proximoEvento = "";
        this.proximoReloj = 0;
        this.proximaLlegadaPacienteConTurno = 0;
        this.proximaLlegadaPacienteSinTurno = 0;
        this.nroFila = -1;
        this.tiempoFinAtencion = 0;
        this.tiempoFinEstudio = 0;
        this.pacientes = new ArrayList<>();
        this.colaPacienteEsperandoAtencion = new ArrayList<>();
        this.colaPacientesEstudio = new ArrayList<>();

    }

    public Fila(Fila filaAnterior, float mediaConTurno, float mediaSinTurno, float desdeSecretaria, float hastaSecretaria, int cantTurnos, float mediaMedico, float desvMedico,float cierreCo) {
        this.filaAnterior = filaAnterior;
        this.nroFila = nroFila + filaAnterior.getNroFila() + 1;
        this.evento = filaAnterior.getProximoEvento();
        this.acumuladorReloj = filaAnterior.getAcumuladorReloj();
        this.reloj = filaAnterior.getProximoReloj();
        this.proximoEvento = filaAnterior.getProximoEvento();
        this.proximoReloj = filaAnterior.getProximoReloj();
        this.proximaLlegadaPacienteSinTurno = filaAnterior.getProximaLlegadaPacienteSinTurno();
        this.proximaLlegadaPacienteConTurno = filaAnterior.getProximaLlegadaPacienteConTurno();
        //this.cierre_consultorio = filaAnterior.getCierre_consultorio();
        this.secretaria = filaAnterior.getSecretaria();
        this.turnosDisponibles = filaAnterior.getTurnosDisponibles();
        this.medico = filaAnterior.getMedico();
        this.contPacientesAtendidos = filaAnterior.getContPacientesAtendidos();
        this.acumuladorTiempoPermanencia = filaAnterior.getAcumuladorTiempoPermanencia();
        this.promedioTiempoPermanencia = filaAnterior.getPromedioTiempoPermanencia();
        this.contPacienteEsperandoEstudio = filaAnterior.getContPacienteEsperandoEstudio();
        this.acumuladorTiempoEspera = filaAnterior.getAcumuladorTiempoEspera();
        this.promedioTiempoEspera = filaAnterior.getPromedioTiempoEspera();
        this.acumOcupacionSecretaria = filaAnterior.getacumOcupacionSecretaria();
        this.porcentajeOcupacionSecretaria = filaAnterior.getPorcentajeOcupacionSecretaria();
        this.pacientes = filaAnterior.pacientes;
        this.colaPacientesEstudio = filaAnterior.colaPacientesEstudio;
        this.colaPacienteEsperandoAtencion = filaAnterior.colaPacienteEsperandoAtencion;
        this.tiempoFinEstudio = filaAnterior.getTiempoFinEstudio();
        this.tiempoFinAtencion = filaAnterior.getTiempoFinAtencion();
        this.proximaLlegadaPaciente = filaAnterior.getProximaLlegadaPaciente();
        this.colaTurnos = filaAnterior.getColaTurnos();
        this.colaEstudio = filaAnterior.getColaEstudio();
        this.contPacientesRechazados = filaAnterior.getContPacientesRechazados();
        this.sinLlegadas = filaAnterior.isSinLlegadas();
        // Parametros
        this.mediaConTurno = mediaConTurno;
        this.mediaSinTurno = mediaSinTurno;
        this.desdeSecretaria = desdeSecretaria;
        this.hastaSecretaria = hastaSecretaria;
        this.cantTurnos = cantTurnos;
        this.mediaMedico = mediaMedico;
        this.desviacionMedico = desvMedico;
        this.cierreConsultorio = cierreCo;




        if (nroFila == 0) {
            this.evento = "Inicialización";

            generarFila(evento);
        } else {
            generarFila(filaAnterior.proximoEvento);
        }
    }
    private void generarFila(String eventoFila) {

        switch (eventoFila) {


            case "Inicialización":
                generarProximaLlegadaPacienteConTurno();
                generarProximaLlegadaPacienteSinTurno();
                calcularProximaLlegadaPaciente();
                break;

            case "Llegada Paciente sin turno":
                generarProximaLlegadaPacienteSinTurno();
                crearPacienteSinTurno();
                CalcularproximoEvento();
                ActualizarOcupacionSecretaria();
                CalcularEstadisticas();
                break;

            case "Llegada Paciente con turno":
                generarProximaLlegadaPacienteConTurno();
                crearPacienteConTurno();
                CalcularproximoEvento();
                ActualizarOcupacionSecretaria();
                CalcularEstadisticas();
                break;

            case "Fin Atencion":
                actualizarPaciente();
                revisarColaSecretaria();
                CalcularproximoEvento();
                ActualizarOcupacionSecretaria();
                CalcularEstadisticas();
                break;

            case "Fin Estudio":
                destruirConTurno();
                revisarColaMedico();
                CalcularproximoEvento();
                ActualizarOcupacionSecretaria();
                CalcularEstadisticas();
                break;
            case "Cierre consultorio":
                finLlegadas();
                CalcularproximoEvento();
                CalcularEstadisticas();
                break;
            case "Inicio Consultorio":
                inicializarValores();
                generarProximaLlegadaPacienteConTurno();
                generarProximaLlegadaPacienteSinTurno();
                calcularProximaLlegadaPaciente();
                CalcularEstadisticas();
                break;

        }
        noMostrarDestruidos();
    }

    private void inicializarValores() {
        Paciente paciente = new Paciente();
        paciente.reset();
        this.proximaLlegadaPacienteSinTurno = 0;
        this.proximaLlegadaPacienteConTurno = 0;
        this.tiempoFinAtencion = 0;
        this.tiempoFinEstudio = 0;
        this.turnosDisponibles = 0;
        this.sinLlegadas = false;
    }

    private void finLlegadas() {
        this.sinLlegadas = true;
        this.proximaLlegadaPacienteConTurno = 0;
        this.proximaLlegadaPacienteSinTurno = 0;

    }


    private void revisarColaSecretaria(){
        if(colaPacienteEsperandoAtencion.size() > 0){
            colaPacienteEsperandoAtencion.remove(0);
            this.colaTurnos--;
            for (Paciente paciente: pacientes) {
                if(paciente.getEstado().equals("Esperando Atencion Secretaria")){

                    paciente.setEstado("Siendo Atendido Secretaria");
                    generarFinAtencion();
                    if(paciente.getTipoPaciente().equals("ST")){
                        this.secretaria = "Atendiendo Paciente(ST)";
                    }
                    else{
                        this.secretaria = "Atendiendo Paciente(CT)";
                    }
                    return;
                }

            }

            this.colaTurnos--;

        }
        else{
            this.secretaria = "Libre";
        }
    }


   private void actualizarPaciente()
   {
       for (Paciente paciente:pacientes) {
           if(paciente.getEstado().equals("Siendo Atendido Secretaria")){
               if(paciente.getTipoPaciente().equals("ST")){
                   paciente.setEstado("Destruido");
               }
               if(paciente.getTipoPaciente().equals("CT")){
                if(this.filaAnterior.getMedico().equals("Libre")){
                    this.medico = "Ocupado";
                    generarFinEstudio();
                    paciente.setEstado("Siendo Atendido Medico");
                    this.contPacienteEsperandoEstudio++;

                }
                else{
                    this.colaEstudio++;
                    paciente.setEstado("Esperando Atencion Medico");
                    this.colaPacientesEstudio.add(paciente);

                    paciente.setInicioEsperaEstudio(reloj);
                }
               }
               return;
           }

       }

   }


    private void CalcularEstadisticas() {
        if(reloj != 0) {
            this.acumuladorReloj = reloj - filaAnterior.getReloj() + acumuladorReloj;
        }
        this.porcentajeOcupacionSecretaria = (acumOcupacionSecretaria / acumuladorReloj * 100);
        if(evento.equals("Fin Estudio")){
            this.promedioTiempoPermanencia = acumuladorTiempoPermanencia / contPacientesAtendidos;
            if(contPacienteEsperandoEstudio != 0){
                this.promedioTiempoEspera = acumuladorTiempoEspera / contPacienteEsperandoEstudio;
            }
        }


    }

    private void revisarColaMedico() {

        if(colaEstudio > 0){
            colaPacientesEstudio.remove(0);
            for (Paciente paciente: pacientes) {
                if(paciente.getEstado().equals("Esperando Atencion Medico")){

                    paciente.setEstado("Siendo Atendido Medico");
                    generarFinEstudio();
                    this.acumuladorTiempoEspera = reloj - paciente.getInicioEsperaEstudio() + acumuladorTiempoEspera;
                    this.tiempoEsperaPaciente = reloj - paciente.getInicioEsperaEstudio();
                    this.colaEstudio--;
                    this.contPacienteEsperandoEstudio++;
                    return;
                }

            }

        }
        else{
            this.medico = "Libre";
        }

    }

    private void destruirConTurno() {
        for (Paciente paciente: pacientes) {
            if(paciente.getEstado().equals("Siendo Atendido Medico")) {
            paciente.setEstado("Destruido");
            this.contPacientesAtendidos++;
            this.acumuladorTiempoPermanencia = reloj - paciente.getLlegadaSistema() + acumuladorTiempoPermanencia;
            this.tiempoPermaneciaPaciente = reloj - paciente.getLlegadaSistema();
            }
        }
    }


    private void generarFinEstudio() {
        this.tiempoEstudio = convolucion();
        this.tiempoFinEstudio = tiempoEstudio + reloj;

    }

    private float convolucion(){
        float suma = 0;
        for (int i = 0; i < 12 ; i++) {
            this.randomFinEstudio =(float) Math.random();
            suma = suma + randomFinEstudio;

        }
        float resta = suma-6;
        float multiplicacion = resta * desviacionMedico;
        float resultado = multiplicacion + mediaMedico;;
        if(resultado < 0)
        {
            resultado = convolucion();
        }

        return resultado;

    }


    private void crearPacienteConTurno() {
        Paciente paciente = new Paciente();
            if(secretaria.equals("Libre"))
            {
                secretaria = "Atendiendo Paciente(CT)";
                paciente.setLlegadaSistema(reloj);
                paciente.setEstado("Siendo Atendido Secretaria");
                paciente.setTipoPaciente("CT");
                pacientes.add(paciente);
                generarFinAtencion();
            }
            else{
                paciente.setLlegadaSistema(reloj);
                paciente.setEstado("Esperando Atencion Secretaria");
                paciente.setTipoPaciente("CT");
                pacientes.add(paciente);
                colaPacienteEsperandoAtencion.add(paciente);
                this.colaTurnos++;
            }
        }

    private void ActualizarOcupacionSecretaria() {
        if (this.filaAnterior.getSecretaria().equals("Atendiendo Paciente(CT)") ||this.filaAnterior.getSecretaria().equals("Atendiendo Paciente(ST)")  ){

                this.acumOcupacionSecretaria = acumOcupacionSecretaria + reloj - filaAnterior.reloj;
        }
    }

    private void CalcularproximoEvento() {
        double min = 999999999;
        String tipo = " ";
        if (tiempoFinAtencion != 0 && tiempoFinAtencion > reloj )
        {
            min = tiempoFinAtencion;
            tipo = "Fin Atencion";
        }
        if (tiempoFinEstudio != 0 && tiempoFinEstudio > reloj){
            if(tiempoFinEstudio < min  ){
                min = tiempoFinEstudio;
                tipo = "Fin Estudio";
            }
        }
        if(proximaLlegadaPacienteSinTurno != 0 && proximaLlegadaPacienteSinTurno > reloj  ){
            if(proximaLlegadaPacienteSinTurno < min && !sinLlegadas)  {
                min = proximaLlegadaPacienteSinTurno;
                tipo = "Llegada Paciente sin turno";
            }
        }
        if(proximaLlegadaPacienteConTurno != 0 && proximaLlegadaPacienteConTurno > reloj  ){
            if(proximaLlegadaPacienteConTurno < min && !sinLlegadas){
                min = proximaLlegadaPacienteConTurno;
                tipo = "Llegada Paciente con turno";
            }
        }
        if(cierreConsultorio < min && cierreConsultorio > reloj){
            min = cierreConsultorio;
            tipo = "Cierre consultorio";
        }
        if(this.medico.equals("Libre") && colaEstudio== 0 && sinLlegadas ) {
            tipo = "Inicio Consultorio";
            min = 0;
        }

        this.proximoEvento = tipo;
        this.proximoReloj = (float) min;



    }

    private void crearPacienteSinTurno() {
        Paciente paciente = new Paciente();
        if(turnosDisponibles >= cantTurnos)
        {

            //paciente.setEstado("Destruido");
            //paciente.setTipoPaciente("ST");
            //pacientes.add(paciente);
            this.contPacientesRechazados++;

        }
        else{

            if(secretaria.equals("Libre"))
            {
                secretaria = "Atendiendo Paciente(ST)";
                paciente.setEstado("Siendo Atendido Secretaria");
                paciente.setTipoPaciente("ST");
                pacientes.add(paciente);
                generarFinAtencion();
                this.turnosDisponibles++;
            }
            else{
                paciente.setEstado("Esperando Atencion Secretaria");
                paciente.setTipoPaciente("ST");
                pacientes.add(paciente);
                colaPacienteEsperandoAtencion.add(paciente);
                this.colaTurnos++;
                this.turnosDisponibles++;
            }

        }
    }

    private void generarFinAtencion() {
        this.randomFinAtencion =  (float) Math.random();
        this.tiempoAtencion = desdeSecretaria + randomFinAtencion*(hastaSecretaria - desdeSecretaria);
        this.tiempoFinAtencion = tiempoAtencion + reloj;
    }


    private void calcularProximaLlegadaPaciente() {
        proximoReloj = proximaLlegadaPacienteConTurno;
        proximoEvento = "Llegada Paciente con turno";
        if(proximaLlegadaPacienteSinTurno < proximaLlegadaPacienteConTurno){
            proximoReloj = proximaLlegadaPacienteSinTurno;
            proximoEvento = "Llegada Paciente sin turno";
        }

    }

    private void generarProximaLlegadaPacienteSinTurno() {
        this.randomLlegadaSinTurno = (float) Math.random();
        this.tiempoEntreLlegadasSinTurno = (float) ((-mediaSinTurno) * Math.log(1 - randomLlegadaSinTurno));
        this.proximaLlegadaPacienteSinTurno = tiempoEntreLlegadasSinTurno + reloj;
    }

    private void generarProximaLlegadaPacienteConTurno() {
        this.randomLlegadaConTurno = (float) Math.random();
        this.tiempoEntreLlegadasConTurno = (float) ((-mediaConTurno) * Math.log(1 - randomLlegadaConTurno));
        this.proximaLlegadaPacienteConTurno = tiempoEntreLlegadasConTurno + reloj;
        
    }

    public Fila getFilaAnterior() {
        return filaAnterior;
    }

    public void setFilaAnterior(Fila filaAnterior) {
        this.filaAnterior = filaAnterior;
    }

    public int getNroFila() {
        return nroFila;
    }

    public void setNroFila(int nroFila) {
        this.nroFila = nroFila;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public float getReloj() {
        return reloj;
    }

    public void setReloj(float reloj) {
        this.reloj = reloj;
    }

    public String getProximoEvento() {
        return proximoEvento;
    }

    public void setProximoEvento(String proximoEvento) {
        this.proximoEvento = proximoEvento;
    }

    public float getProximoReloj() {
        return proximoReloj;
    }

    public void setProximoReloj(float proximoReloj) {
        this.proximoReloj = proximoReloj;
    }

    public float getRandomLlegadaSinTurno() {
        return randomLlegadaSinTurno;
    }

    public void setRandomLlegadaSinTurno(float randomLlegadaSinTurno) {
        this.randomLlegadaSinTurno = randomLlegadaSinTurno;
    }

    public float getTiempoEntreLlegadasSinTurno() {
        return tiempoEntreLlegadasSinTurno;
    }

    public void setTiempoEntreLlegadasSinTurno(float tiempoEntreLlegadasSinTurno) {
        this.tiempoEntreLlegadasSinTurno = tiempoEntreLlegadasSinTurno;
    }

    public float getProximaLlegadaPacienteSinTurno() {
        return proximaLlegadaPacienteSinTurno;
    }

    public void setProximaLlegadaPacienteSinTurno(float proximaLlegadaPacienteSinTurno) {
        this.proximaLlegadaPacienteSinTurno = proximaLlegadaPacienteSinTurno;
    }

    public float getRandomLlegadaConTurno() {
        return randomLlegadaConTurno;
    }

    public void setRandomLlegadaConTurno(float randomLlegadaConTurno) {
        this.randomLlegadaConTurno = randomLlegadaConTurno;
    }

    public float getTiempoEntreLlegadasConTurno() {
        return tiempoEntreLlegadasConTurno;
    }

    public void setTiempoEntreLlegadasConTurno(float tiempoEntreLlegadasConTurno) {
        this.tiempoEntreLlegadasConTurno = tiempoEntreLlegadasConTurno;
    }

    public float getProximaLlegadaPacienteConTurno() {
        return proximaLlegadaPacienteConTurno;
    }

    public void setProximaLlegadaPacienteConTurno(float proximaLlegadaPacienteConTurno) {
        this.proximaLlegadaPacienteConTurno = proximaLlegadaPacienteConTurno;
    }

    //public float getCierre_consultorio() {
    //    return cierre_consultorio;
    //}

    //public void setCierre_consultorio(float cierre_consultorio) {
        //this.//   }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public int getTurnosDisponibles() {
        return turnosDisponibles;
    }

    public void setTurnosDisponibles(int turnosDisponibles) {
        this.turnosDisponibles = turnosDisponibles;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public int getColaTurnos() {
        return colaTurnos;
    }

    public void setColaTurnos(int colaTurnos) {
        this.colaTurnos = colaTurnos;
    }

    public int getColaEstudio() {
        return colaEstudio;
    }

    public void setColaEstudio(int colaEstudio) {
        this.colaEstudio = colaEstudio;
    }


    public int getContPacientesAtendidos() {
        return contPacientesAtendidos;
    }

    public void setContPacientesAtendidos(int contPacientesAtendidos) {
        this.contPacientesAtendidos = contPacientesAtendidos;
    }

    public float getAcumuladorTiempoPermanencia() {
        return acumuladorTiempoPermanencia;
    }

    public void setAcumuladorTiempoPermanencia(float acumuladorTiempoPermanencia) {
        this.acumuladorTiempoPermanencia = acumuladorTiempoPermanencia;
    }

    public float getPromedioTiempoPermanencia() {
        return promedioTiempoPermanencia;
    }

    public void setPromedioTiempoPermanencia(float promedioTiempoPermanencia) {
        this.promedioTiempoPermanencia = promedioTiempoPermanencia;
    }

    public int getContPacienteEsperandoEstudio() {
        return contPacienteEsperandoEstudio;
    }

    public void setContPacienteEsperandoEstudio(int contPacienteEsperandoEstudio) {
        this.contPacienteEsperandoEstudio = contPacienteEsperandoEstudio;
    }

    public float getAcumuladorTiempoEspera() {
        return acumuladorTiempoEspera;
    }

    public void setAcumuladorTiempoEspera(float acumuladorTiempoEspera) {
        this.acumuladorTiempoEspera = acumuladorTiempoEspera;
    }

    public float getPromedioTiempoEspera() {
        return promedioTiempoEspera;
    }

    public void setPromedioTiempoEspera(float promedioTiempoEspera) {
        this.promedioTiempoEspera = promedioTiempoEspera;
    }

    public float getacumOcupacionSecretaria() {
        return acumOcupacionSecretaria;
    }

    public void setacumOcupacionSecretaria(float acumOcupacionSecretaria) {
        this.acumOcupacionSecretaria = acumOcupacionSecretaria;
    }

    public float getPorcentajeOcupacionSecretaria() {
        return porcentajeOcupacionSecretaria;
    }

    public void setPorcentajeOcupacionSecretaria(float porcentajeOcupacionSecretaria) {
        this.porcentajeOcupacionSecretaria = porcentajeOcupacionSecretaria;
    }

    public float getRandomFinAtencion() {
        return randomFinAtencion;
    }

    public void setRandomFinAtencion(float randomFinAtencion) {
        this.randomFinAtencion = randomFinAtencion;
    }

    public float getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(float tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public float getTiempoFinAtencion() {
        return tiempoFinAtencion;
    }

    public void setTiempoFinAtencion(float tiempoFinAtencion) {
        this.tiempoFinAtencion = tiempoFinAtencion;
    }


    public float getTiempoFinEstudio() {
        return tiempoFinEstudio;
    }

    public void setTiempoFinEstudio(float tiempoFinEstudio) {
        this.tiempoFinEstudio = tiempoFinEstudio;
    }

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(Paciente pacienteActual) {
        this.pacienteActual = pacienteActual;
    }

    public Paciente getPacienteAnterior() {
        return pacienteAnterior;
    }

    public void setPacienteAnterior(Paciente pacienteAnterior) {
        this.pacienteAnterior = pacienteAnterior;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Paciente> getColaPacienteEsperandoAtencion() {
        return colaPacienteEsperandoAtencion;
    }

    public void setColaPacienteEsperandoAtencion(ArrayList<Paciente> colaPacienteEsperandoAtencion) {
        this.colaPacienteEsperandoAtencion = colaPacienteEsperandoAtencion;
    }

    public ArrayList<Paciente> getColaPacientesEstudio() {
        return colaPacientesEstudio;
    }

    public void setColaPacientesEstudio(ArrayList<Paciente> colaPacientesEstudio) {
        this.colaPacientesEstudio = colaPacientesEstudio;
    }


    public float getProximaLlegadaPaciente() {
        return proximaLlegadaPaciente;
    }

    public void setProximaLlegadaPaciente(float proximaLlegadaPaciente) {
        this.proximaLlegadaPaciente = proximaLlegadaPaciente;
    }

    public int getContPacientesRechazados() {
        return contPacientesRechazados;
    }

    public void setContPacientesRechazados(int contPacientesRechazados) {
        this.contPacientesRechazados = contPacientesRechazados;
    }

    public boolean isSinLlegadas() {
        return sinLlegadas;
    }

    public void setSinLlegadas(boolean sinLlegadas) {
        this.sinLlegadas = sinLlegadas;
    }

    public float getAcumuladorReloj() {
        return acumuladorReloj;
    }

    public void setAcumuladorReloj(float acumuladorReloj) {
        this.acumuladorReloj = acumuladorReloj;
    }

    public float getTiempoEsperaPaciente() {
        return tiempoEsperaPaciente;
    }

    public void setTiempoEsperaPaciente(float tiempoEsperaPaciente) {
        this.tiempoEsperaPaciente = tiempoEsperaPaciente;
    }

    @Override
    public String toString() {
        return String.format("|%-8s", nroFila) + pacientes.toString() + "\n";
    }

    public String toString2() {
        String cadena = "";
        cadena = "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>" + nroFila + "</td>\n" +
                "\t\t\t\t\t<td>" + evento + "</td>\n" +
                "\t\t\t\t\t<td>" + reloj + "</td>\n" +
                "\t\t\t\t\t<td>" + randomLlegadaSinTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEntreLlegadasSinTurno + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + proximaLlegadaPacienteSinTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + randomLlegadaConTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEntreLlegadasConTurno + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + proximaLlegadaPacienteConTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + randomFinAtencion + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoAtencion + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + tiempoFinAtencion + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEstudio + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + tiempoFinEstudio + "</td>\n" +
                "\t\t\t\t\t<td>" + cierreConsultorio + "</td>\n" +
                "\t\t\t\t\t<td>" + secretaria + "</td>\n" +
                "\t\t\t\t\t<td>" + turnosDisponibles + "</td>\n" +
                "\t\t\t\t\t<td>" + colaTurnos+ "</td>\n" +
                "\t\t\t\t\t<td>" + medico + "</td>\n" +
                "\t\t\t\t\t<td>" + colaEstudio + "</td>\n" +
                "\t\t\t\t\t<td>" + contPacientesRechazados + "</td>\n" +
                "\t\t\t\t\t<td>" + contPacientesAtendidos + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoPermaneciaPaciente + "</td>\n" +
                "\t\t\t\t\t<td>" + acumuladorTiempoPermanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + promedioTiempoPermanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + contPacienteEsperandoEstudio + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEsperaPaciente + "</td>\n" +
                "\t\t\t\t\t<td>" + acumuladorTiempoEspera + "</td>\n" +
                "\t\t\t\t\t<td>" + promedioTiempoEspera + "</td>\n" +
                "\t\t\t\t\t<td>" + acumuladorReloj + "</td>\n" +
                "\t\t\t\t\t<td>" + acumOcupacionSecretaria + "</td>\n" +
                "\t\t\t\t\t<td>" + porcentajeOcupacionSecretaria + "</td>\n" +
                toStringPacientes() + "\n" +
                "\t\t\t\t</tr>\n";
        return cadena;
    }

    public String toString3(){

        String cadena = "\t\t\t<tfoot>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>" + nroFila + "</td>\n" +
                "\t\t\t\t\t<td>" + evento + "</td>\n" +
                "\t\t\t\t\t<td>" + reloj + "</td>\n" +
                "\t\t\t\t\t<td>" + randomLlegadaSinTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEntreLlegadasSinTurno + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + proximaLlegadaPacienteSinTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + randomLlegadaConTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEntreLlegadasConTurno + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + proximaLlegadaPacienteConTurno + "</td>\n" +
                "\t\t\t\t\t<td>" + randomFinAtencion + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoAtencion + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + tiempoFinAtencion + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEstudio + "</td>\n" +
                "\t\t\t\t\t<td class='color'>" + tiempoFinEstudio + "</td>\n" +
                "\t\t\t\t\t<td>" + cierreConsultorio + "</td>\n" +
                "\t\t\t\t\t<td>" + secretaria + "</td>\n" +
                "\t\t\t\t\t<td>" + turnosDisponibles + "</td>\n" +
                "\t\t\t\t\t<td>" + colaTurnos+ "</td>\n" +
                "\t\t\t\t\t<td>" + medico + "</td>\n" +
                "\t\t\t\t\t<td>" + colaEstudio + "</td>\n" +
                "\t\t\t\t\t<td>" + contPacientesRechazados + "</td>\n" +
                "\t\t\t\t\t<td>" + contPacientesAtendidos + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoPermaneciaPaciente + "</td>\n" +
                "\t\t\t\t\t<td>" + acumuladorTiempoPermanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + promedioTiempoPermanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + contPacienteEsperandoEstudio + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempoEsperaPaciente + "</td>\n" +
                "\t\t\t\t\t<td>" + acumuladorTiempoEspera + "</td>\n" +
                "\t\t\t\t\t<td>" + promedioTiempoEspera + "</td>\n" +
                "\t\t\t\t\t<td>" + acumuladorReloj + "</td>\n" +
                "\t\t\t\t\t<td>" + acumOcupacionSecretaria + "</td>\n" +
                "\t\t\t\t\t<td>" + porcentajeOcupacionSecretaria + "</td>\n" +
                toStringPacientes() + "\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</tfoot>\n";
        return cadena;
    }

    private String toStringPacientes(){
        String cadena = "";
        for (Paciente paciente : pacientes) {
            cadena = cadena + paciente.toString();
        }
        return cadena;
    }


    private void noMostrarDestruidos(){
        for (Paciente compu : this.pacientes) {
            if (compu.getEstado().equals("Destruido")){
                compu.setMostrado(compu.getMostrado() + 1);
            }
        }
    }

}
