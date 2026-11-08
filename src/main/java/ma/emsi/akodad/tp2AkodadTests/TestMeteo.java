package ma.emsi.akodad.tp2AkodadTests;

import ma.emsi.akodad.tp2AkodadTests.outil.meteo.MeteoTool;

import java.util.Arrays;

public class TestMeteo {

    public static void main(String[] args) {
        MeteoTool meteoTool = new MeteoTool();

        double[] coordonnees = meteoTool.getCoordonnees("Paris");

        System.out.println("Coordonnées de Paris : " + Arrays.toString(coordonnees));
    }
}