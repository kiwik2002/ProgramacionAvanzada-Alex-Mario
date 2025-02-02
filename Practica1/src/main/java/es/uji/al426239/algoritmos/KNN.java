package es.uji.al426239.algoritmos;

import es.uji.al426239.row_table.TableWithLabels;
import es.uji.al426239.metodos.MetricaEuclidiana;
import java.util.List;

public class KNN implements Algorithm<TableWithLabels, List<Double>, Integer> {
    private TableWithLabels TablaEntrenamiento;

    public KNN() {
        this.TablaEntrenamiento = new TableWithLabels();
    }

    // Método para aplicar a la tabla de entrenamiento los datos en cuestión
    @Override
    public void train(TableWithLabels data) {
        TablaEntrenamiento = data;
    }

    // Método para estimar el número de la clase al que pertenece esos datos
    @Override
    public Integer estimate(List<Double> data) {
        MetricaEuclidiana Calculador = new MetricaEuclidiana();
        int Estimacion = 0;
        double MenorAproximacion = Double.MAX_VALUE;
        for (int i = 0; i < TablaEntrenamiento.getRow().size(); i++) {
            double MetricaEuclidiana = Calculador.CalcularMetricaEuclidiana(data, TablaEntrenamiento.getRow(i));
            if (MetricaEuclidiana < MenorAproximacion) {
                MenorAproximacion = MetricaEuclidiana;
                Estimacion = TablaEntrenamiento.getRow(i).getNumberClass();
            }
        }
        return Estimacion;
    }
}
