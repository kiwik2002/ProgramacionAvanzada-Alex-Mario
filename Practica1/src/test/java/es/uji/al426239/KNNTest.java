package es.uji.al426239;

import es.uji.al426239.lector_de_tablas.CSV;
import es.uji.al426239.row_table.TableWithLabels;
import es.uji.al426239.algoritmos.KNN;
import es.uji.al426239.metodos.MetricaEuclidiana;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class KNNTest {
        private TableWithLabels TablaEntramiento;
        private KNN Estimador;
        private MetricaEuclidiana CalcularMetricaEuclidiana;
        private List<Double> Datos1, Datos2, Datos3, Datos4, Datos5;
        private List<Integer> Datos6;

        @BeforeEach
        void inicioClase() {
                try {
                        CalcularMetricaEuclidiana = new MetricaEuclidiana();
                        CSV lector = new CSV();
                        Estimador = new KNN();
                        String separator = System.getProperty("file.separator");
                        TablaEntramiento = lector.readTableWithLabels("." + separator + "iris.csv");
                        Estimador.train(TablaEntramiento);
                        Datos1 = new ArrayList<>(List.of(4.2, 2.6, 1.2, 0.7));
                        Datos2 = new ArrayList<>(List.of(7.2, 5.6, 2.5, 1.9));
                        Datos3 = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0));
                        Datos4 = new ArrayList<>(List.of(10.2, 7.6, 4.2, 2.7));
                        Datos5 = new ArrayList<>(List.of(6.2, 4.5, 3.5, 1.5));
                        Datos6 = new ArrayList<>(List.of(1, 2, 3, 4));
                } catch (FileNotFoundException e) {
                        // Manejar la excepción adecuadamente
                        e.printStackTrace();
                }
        }

        @DisplayName("Devuelve TablaEntrenamiento")
        @Test
        void getTablaEntramiento() {
                DecimalFormat decimalFormat = new DecimalFormat("#0.000");
                Assertions.assertEquals("0,592", decimalFormat.format(CalcularMetricaEuclidiana.CalcularMetricaEuclidiana(Datos1,TablaEntramiento.getRow(41))), "Debería dar 0.592");
                Assertions.assertEquals("4,019", decimalFormat.format(CalcularMetricaEuclidiana.CalcularMetricaEuclidiana(Datos2,
                        TablaEntramiento.getRow(1))), "Debería dar 4,019");
                Assertions.assertEquals("7,246", decimalFormat.format(CalcularMetricaEuclidiana.CalcularMetricaEuclidiana(Datos3,
                        TablaEntramiento.getRow(79))), "Debería dar 7,246");
                Assertions.assertEquals("6,722", decimalFormat.format(CalcularMetricaEuclidiana.CalcularMetricaEuclidiana(Datos4,
                        TablaEntramiento.getRow(121))), "Debería dar 6,722");
                Assertions.assertEquals("2,341", decimalFormat.format(CalcularMetricaEuclidiana.CalcularMetricaEuclidiana(Datos5,
                        TablaEntramiento.getRow(147))), "Debería dar 2,341");
        }

        @DisplayName("Estimación")
        @Test
        void estimate() {
                Assertions.assertEquals(Datos6.get(0), Estimador.estimate(Datos1));
                Assertions.assertEquals(Datos6.get(0), Estimador.estimate(Datos2));
                Assertions.assertEquals(Datos6.get(0), Estimador.estimate(Datos3));
                Assertions.assertEquals(Datos6.get(2), Estimador.estimate(Datos4));
                Assertions.assertEquals(Datos6.get(1), Estimador.estimate(Datos5));

        }
}