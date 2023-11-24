package com.edu.ufg.veterinaria.models;

public enum MotivoCita {

    CONTROL_VACUNAS("Control de vacunas"),
    REVISION_GENERAL("Revisión general"),
    PROBLEMA_GASTROINTESTINAL("Problema gastrointestinal"),
    CONSULTA_COMPORTAMIENTO("Consulta por comportamiento"),
    CIRUGIA_PROGRAMADA("Cirugía programada"),
    SEGUIMIENTO_POSTOPERATORIO("Seguimiento postoperatorio"),
    DERMATOLOGIA_PIEL("Dermatología y piel"),
    DOLOR_COJERA("Dolor o cojera"),
    CUIDADO_DENTAL("Cuidado dental"),
    NUTRICION_DIETA("Nutrición y dieta"),
    DESPARASITACION("Desparasitación"),
    MANEJO_ALERGIAS("Manejo de alergias"),
    CONSEJOS_CRIANZA("Consejos de crianza"),
    REPRODUCCION_ESTERILIZACION("Reproducción y esterilización"),
    HERIDAS_CORTES("Heridas o cortes");

    private final String motivo;

    MotivoCita(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

}
