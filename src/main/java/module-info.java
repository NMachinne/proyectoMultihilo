module com.Aulas.proyectoAulasPSP {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.Aulas.proyectoAulasPSP to javafx.fxml;
    exports com.Aulas.proyectoAulasPSP;
}
