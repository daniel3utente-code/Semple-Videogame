import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.*;

public class MyCar extends Car{

    private final LocalDateTime time = LocalDateTime.now();
    private Instant start;
    private Instant end;
    private long durationSec;
    private LocalDate giorno;
    private int passedCars;

    public MyCar(int lane, Image icon){
        super(Car.getPlayerY(),lane,icon);
        start = Instant.now();
        giorno = time.toLocalDate();
    }

    public void setDuration(){
        end = Instant.now();
        durationSec = Duration.between(start,end).toSeconds();
    }

    public void addPassedCars(){ passedCars++; }

    public void saveDatas(){
        setDuration();
        final String NAME_FILE = new String("/home/dans/Documents/Schiva_le_Macchine/out/dati.csv");
        File file = new File(NAME_FILE);
        try {
            file.createNewFile();
            System.out.println("File creato!");
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        try {
            FileWriter w = new FileWriter(NAME_FILE, StandardCharsets.UTF_8,true);
            w.write(this.toString()+"\n");
            w.close();
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }

    }

    public String toString(){
        return giorno+","+durationSec+","+passedCars;
    }
}
